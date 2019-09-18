package com.familytoto.familytotoProject.login.service.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.changeCust.service.ChangeCustAuthService;
import com.familytoto.familytotoProject.config.SecretGlobalVariable;
import com.familytoto.familytotoProject.login.domain.SocialVO;
import com.familytoto.familytotoProject.login.service.social.SocalLoginService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoLoginVO {
    
	@Autowired
	ChangeCustAuthService changeCustAuthService;
	
	@Autowired
	SocalLoginService socalLoginService;
	private final String REST_API = SecretGlobalVariable.KAKAO_REST_API;
	private final String REDIRECT_URI = SecretGlobalVariable.DOMAIN_URL + "/login/social/kakao";
	private final String REDIRECT_URI_AUTH =  SecretGlobalVariable.DOMAIN_URL + "/login/social/kakao/auth";
	
	public String getKakaoLink() {
		return "https://kauth.kakao.com/oauth/authorize?client_id=" + REST_API + "&redirect_uri="+SecretGlobalVariable.DOMAIN_URL+"/login/social/kakao&response_type=code";
	}
	
	public String getKakaoAuthLink() {
		return "https://kauth.kakao.com/oauth/authorize?client_id=" + REST_API + "&redirect_uri="+SecretGlobalVariable.DOMAIN_URL+"/login/social/kakao/auth&response_type=code";
	}
	
    public String getAccessToken (String authorize_code, String sParam) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + REST_API);
            
            if(sParam.equals("login")) {
            	sb.append("&redirect_uri=" + REDIRECT_URI);            	
            } else {
            	sb.append("&redirect_uri=" + REDIRECT_URI_AUTH);
            }
            
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            
            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);
            
            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return access_Token;
    }
    
    public SocialVO getKakaoLogin (String access_Token, HttpSession session,
    		HttpServletRequest request) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        SocialVO vo = new SocialVO();
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            
            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
//            System.out.println("access === " + access_Token);
//            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode : " + responseCode);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            // System.out.println("response body : " + result);
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            String id = element.getAsJsonObject().get("id").getAsString();
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = "";
            String age_range = "";
            
            if(kakao_account.getAsJsonObject().get("email") != null) {
            	email = kakao_account.getAsJsonObject().get("email").getAsString();            	
            }
            
            if(kakao_account.getAsJsonObject().get("age_range") != null) {
            	age_range = kakao_account.getAsJsonObject().get("age_range").getAsString().substring(0,2);            	
            }
            
            if(nickname.equals("") || email.equals("") || age_range.equals("")) {
            	vo.setScCustId("-99");
            	kakaoDisRegister(access_Token);
            } else {
            	if(Integer.parseInt(age_range) < 20) {
            		vo.setScCustId("-98");
            		kakaoDisRegister(access_Token);
            	}
            	
            	vo.setScCustNick(nickname);
            	vo.setScCustEmail(email);
            	vo.setScCustGubun("KA");
            	vo.setScCustId(id);
            	vo.setRegIp(request.getRemoteAddr());
            	// vo.setRegIp(Equ);
            	
            	// 소셜 부분 
    			CustVO cVo = socalLoginService.getSocialFamilyNo(vo);
    			
    			int nScCustNo = socalLoginService.insertSocialId(vo);
    			vo.setScCustNo(nScCustNo);
    			
    			if(cVo == null) {
    				CustVO cVo2 = new CustVO();
    				cVo2.setCustNo(nScCustNo);
    				cVo2.setFamilyCustNick(nickname);
    				cVo2.setCustOperatorGubun("N");
    				session.setAttribute("cust", cVo2); // 세션 생성
    			} else {
    				cVo.setFamilyCustNick(nickname);
    				cVo.setCustNo(nScCustNo);
    				cVo.setCustOperatorGubun("N");
    				session.setAttribute("cust", cVo); // 세션 생성
    			}
    			
    			session.setAttribute("custSocial", vo); // 세션 생성
    			session.setAttribute("social", "KA"); // 세션 생성
    			// 소셜 부분
    			
    			
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }        
        
        return vo;
    }
    
    public void kakaoDisRegister (String access_Token) {
        
        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        String reqURL = "https://kapi.kakao.com/v1/user/unlink";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            
            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            
            System.out.println("response body : " + result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	System.out.println(e+"==");
            e.printStackTrace();
        }
    }

    public Map<String, Object> kakaoAuth (String access_Token, HttpSession session, HttpServletRequest request) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        Map<String, Object> map = new HashMap<String, Object>();
        int nResult = 0;
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            
            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            System.out.println("access === " + access_Token);
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            String id = element.getAsJsonObject().get("id").getAsString();
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = "";
            String age_range = "";
            
            if(kakao_account.getAsJsonObject().get("email") != null) {
            	email = kakao_account.getAsJsonObject().get("email").getAsString();            	
            }
            
            if(kakao_account.getAsJsonObject().get("age_range") != null) {
            	age_range = kakao_account.getAsJsonObject().get("age_range").getAsString().substring(0,2);            	
            }
            
            if(nickname.equals("") || email.equals("") || age_range.equals("")) {
            	map.put("error", -98);
            	kakaoDisRegister(access_Token);
            } else {
            	if(Integer.parseInt(age_range) < 20) {
            		map.put("error", -97);
            		kakaoDisRegister(access_Token);
            	}
            	
            	CustVO cVo = (CustVO) session.getAttribute("cust");
            	SocialVO vo = new SocialVO();
            	
            	vo.setScCustId(id);
    			vo.setScCustEmail(email);
    			vo.setRegIp(request.getRemoteAddr());
    			vo.setScCustNick(nickname);
    			vo.setFamilyCustNo(cVo.getFamilyCustNo());
    			vo.setChgCustNo(cVo.getCustNo());
    			vo.setScCustGubun("KA");
    			
    			nResult = changeCustAuthService.updateAuthSocial(vo);
    			
    			map.put("error", 0);
    			map.put("scCustEmail", email);
    			
    			if( nResult == 1) {
    				return map;
    			} else if( nResult == -99) { // 다른사람이 연동한계정
    				map.put("error", -99);
    				return map;
    			} else {
    				return null;
    			}
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            map.put("error", "-1");
            return map;
        }
        
        return map;
    }
}
