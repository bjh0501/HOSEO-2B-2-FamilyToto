package com.familytoto.familytotoProject.login.service.naver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.changeCust.service.ChangeCustAuthService;
import com.familytoto.familytotoProject.config.SecretGlobalVariable;
import com.familytoto.familytotoProject.login.domain.SocialVO;
import com.familytoto.familytotoProject.login.service.social.SocalLoginService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

@Component
public class NaverLoginVO {
	/* 인증 요청문을 구성하는 파라미터 */
//client_id: 애플리케이션 등록 후 발급받은 클라이언트 아이디
//response_type: 인증 과정에 대한 구분값. code로 값이 고정돼 있습니다.
//redirect_uri: 네이버 로그인 인증의 결과를 전달받을 콜백 URL(URL 인코딩). 애플리케이션을 등록할 때 Callback URL에 설정한 정보입니다.
//state: 애플리케이션이 생성한 상태 토큰
	
	
	
	@Autowired
	SocalLoginService socalLoginService; 
	
	@Autowired
	ChangeCustAuthService changeCustAuthService;
	
	private final static String CLIENT_ID = SecretGlobalVariable.NAVER_CLIENT_ID;
	private final static String CLIENT_SECRET = SecretGlobalVariable.NAVER_CLIENT_SECRET;
	private final static String REDIRECT_URI = SecretGlobalVariable.DOMAIN_URL + "/login/social/naver";
	private final static String REDIRECT_URI2 = SecretGlobalVariable.DOMAIN_URL + "/login/social/naver/auth";
	private final static String SESSION_STATE = "social_cust";
	/* 프로필 조회 API URL */
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";

	/* 네이버 아이디로 인증 URL 생성 Method */
	public String getAuthorizationUrl(HttpSession session) {
		/* 세션 유효성 검증을 위하여 난수를 생성 */
		String state = generateRandomString();
		/* 생성한 난수 값을 session에 저장 */
		setSession(session, state);
		/* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 */
		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI).state(state) // 앞서 생성한 난수값을 인증 URL생성시 사용함
				.build(NaverLoginApi.instance());
		return oauthService.getAuthorizationUrl();
	}
	
	public String getAuthorizationChangeUrl(HttpSession session) {
		/* 세션 유효성 검증을 위하여 난수를 생성 */
		String state = generateRandomString();
		/* 생성한 난수 값을 session에 저장 */
		setSession(session, state);
		/* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 */
		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI2).state(state) // 앞서 생성한 난수값을 인증 URL생성시 사용함
				.build(NaverLoginApi.instance());
		return oauthService.getAuthorizationUrl();
	}

	/* 네이버아이디로 Callback 처리 및 AccessToken 획득 Method */
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {
		/* Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 */
		String sessionState = getSession(session);
		if (StringUtils.pathEquals(sessionState, state)) {
			OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URI).state(state).build(NaverLoginApi.instance());
			/* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}

	/* 세션 유효성 검증을 위한 난수 생성기 */
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}

	/* http session에 데이터 저장 */
	private void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}

	/* http session에서 데이터 가져오기 */
	private String getSession(HttpSession session) {
		return (String) session.getAttribute(SESSION_STATE);
	}	

	/* Access Token을 이용하여 네이버 사용자 프로필 API를 호출 */
	public String getUserProfile(OAuth2AccessToken oauthToken, String sRedirectURI) throws IOException {
		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(sRedirectURI).build(NaverLoginApi.instance());
		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}
	
	public void deleteNaver(OAuth2AccessToken oauthToken) throws UnsupportedEncodingException {
		String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete";
	    apiURL += "&client_id=" + CLIENT_ID;
	    apiURL += "&client_secret=" + CLIENT_SECRET;
	    apiURL += "&access_token=" + URLEncoder.encode(oauthToken.getAccessToken(), "UTF-8");
	    apiURL += "&service_provider=NAVER";
	    
	    
	    System.out.println("apiURL="+apiURL);
	    
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      
	      br.close();
	      
	      if(responseCode==200) {
	        System.out.println("삭제완료 ");
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	}
	
	private NaverLoginVO naverLoginVO;
	private String apiResult1 = null;

	@Autowired
	private void setNaverLoginVO(NaverLoginVO naverLoginVO) {
		this.naverLoginVO = naverLoginVO;
	}
	
	public int naverLogin(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request) 
			throws IOException, ParseException{
		
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginVO.getAccessToken(session, code, state);
		// 1. 로그인 사용자 정보를 읽어온다.
		apiResult1 = naverLoginVO.getUserProfile(oauthToken, REDIRECT_URI); // String형식의 json데이터
		/**
		 * apiResult json 구조 {"resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"shinn0608@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		// 2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult1);
		JSONObject jsonObj = (JSONObject) obj;
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// response의 nickname값 파싱
		String nickname = (String) response_obj.get("nickname");
		String age = (String) response_obj.get("age");
		String email = (String) response_obj.get("email");
		String id = (String) response_obj.get("id");
		
		// 사용자가 체크를 해제했다면
		// 이부분 다시봐야함.토큰이 삭제가안대는ㄴ 버그이슷ㅁ.
		if(nickname == null || age == null || email== null) {
			naverLoginVO.deleteNaver(oauthToken);
			return -99;
		} else { // 로그인완료
			int nAge = Integer.parseInt(age.substring(0, 2));
			
			if(nAge <= 19) {
				naverLoginVO.deleteNaver(oauthToken);
				return -98;
			}
			
			SocialVO vo = new SocialVO();
			
			vo.setScCustId(id);
			vo.setScCustEmail(email);
			vo.setRegIp(request.getRemoteAddr());
			vo.setScCustNick(nickname);
			vo.setScCustGubun("NA");
			
			// 소셜 부분 
			vo.setRegIp(request.getRemoteAddr());
			CustVO cVo = socalLoginService.getSocialFamilyNo(vo);
			
			int nScCustNo = socalLoginService.insertSocialId(vo);
			
			if(cVo == null) {
				CustVO cVo2 = new CustVO();
				cVo2.setCustNo(vo.getScCustNo());
				cVo2.setFamilyCustNick(nickname);
				cVo2.setCustOperatorGubun("N");
				session.setAttribute("cust", cVo2); // 세션 생성
			} else {
				cVo.setCustNo(nScCustNo);
				cVo.setFamilyCustNick(nickname);
				cVo.setCustOperatorGubun("N");
				session.setAttribute("cust", cVo); // 세션 생성
			}
			
			session.setAttribute("custSocial", vo); // 세션 생성
			session.setAttribute("social", "NA"); // 세션 생성
			// 소셜 부분
			
			return 0;
		}
	}
	
	// 새 연동, 업데이트 담당
	public Map<String, Object> naverAuth(@RequestParam String code,
			@RequestParam String state, HttpSession session, HttpServletRequest request) 
			throws IOException, ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		int nResult = 0;
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginVO.getAccessToken(session, code, state);
		// 1. 로그인 사용자 정보를 읽어온다.
		apiResult1 = naverLoginVO.getUserProfile(oauthToken, REDIRECT_URI2); // String형식의 json데이터
		/**
		 * apiResult json 구조 {"resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"shinn0608@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		// 2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult1);
		JSONObject jsonObj = (JSONObject) obj;
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// response의 nickname값 파싱
		String nickname = (String) response_obj.get("nickname");
		String age = (String) response_obj.get("age");
		String email = (String) response_obj.get("email");
		String id = (String) response_obj.get("id");
		
		// 사용자가 접근권한 체크를 해제했다면
		// 이부분 다시봐야함.토큰이 삭제가안대는ㄴ 버그이슷ㅁ.
		if(nickname == null || age == null || email== null) {
			map.put("error", -98);
			naverLoginVO.deleteNaver(oauthToken);
			
			return map;
		} else { // 
			CustVO cVo = (CustVO) session.getAttribute("cust");
			SocialVO vo = new SocialVO();
			
			vo.setScCustId(id);
			vo.setScCustEmail(email);
			vo.setRegIp(request.getRemoteAddr());
			vo.setScCustNick(nickname);
			vo.setFamilyCustNo(cVo.getFamilyCustNo());
			vo.setChgCustNo(cVo.getCustNo());
			vo.setScCustGubun("NA");
			
			nResult = changeCustAuthService.updateAuthSocial(vo);
			
			map.put("error", 0);
			map.put("scCustEmail", email);
		}
		
		if( nResult == 1) {
			return map;
		} else if( nResult == -99) { // 다른사람이 연동한계정
			map.put("error", -99);
			return map;
		} else {
			return null;
		}
	}
}