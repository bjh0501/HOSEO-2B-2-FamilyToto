package com.familytoto.familytotoProject.creditShop.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.familytoto.familytotoProject.board.service.AWSService;
import com.familytoto.familytotoProject.config.SecretGlobalVariable;
import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.creditShop.service.CreditShopService;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;
import com.familytoto.familytotoProject.productbuy.service.ProductBuyService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.google.gson.Gson;

@Controller
public class CreditShopController {
	@Autowired
	CreditShopService creditShopService;
	
	@Autowired
	ProductBuyService productBuyService;
	
	@Autowired
	AWSService awsService;
	
	@RequestMapping("/creditShop")
    public String creditShop(Model model, HttpSession session) {
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(custVo != null && custVo.getFamilyCustCompanyNumber() != null) {
			model.addAttribute("productButton", "Y");
		} else {
			model.addAttribute("productButton", "N");
		}
		
		model.addAttribute("categoryList", creditShopService.listProductCategory());
		return "/shop/creditShop/creditShop";
    }
	
	@RequestMapping("/creditShop/list")
	@ResponseBody
    public String creditShopList(Model model,
    		HttpServletRequest request,
    		ProductVO vo,
    		HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
				
		if(cVo != null) {
			vo.setFamilyCustNo(cVo.getFamilyCustNo());
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(creditShopService.listCreditShop(vo));
		return json;
    }
	
	
	@RequestMapping("/showProduct/{productNo}")
    public String showProduct(ProductVO vo, Model model,
    		@PathVariable("productNo") int nProductNo,
    		HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		vo.setProductNo(nProductNo);
		
		ProductVO pVo= new ProductVO();
		pVo = creditShopService.getShowProduct(vo);
		
		if(cVo != null) {
			if(cVo.getFamilyCustNo() != 0) {
				ProductBuyVO pbVo = new ProductBuyVO();
				pbVo.setFamilyCustNo(cVo.getFamilyCustNo());
				pbVo.setProductNo(nProductNo);
				
				String useYn = creditShopService.getPreferProduct(pbVo);
				
				if(useYn != null && useYn.equals("Y")) {
					model.addAttribute("preferProdut", creditShopService.getPreferProduct(pbVo));
				}
			}
		}
		
		
		model.addAttribute("product", pVo);
		model.addAttribute("listComment", creditShopService.listProductComment(vo));
		model.addAttribute("commentCnt", creditShopService.productCommentCnt(vo));
		model.addAttribute("listImgs", creditShopService.listProductImgs(vo));
		
		return "/shop/creditShop/showProduct";
    }
	
	@RequestMapping("/showProduct/comment")
	@ResponseBody
    public int insertComment(@Valid @ModelAttribute ProductCommentVO vo,
    		HttpSession session,
    		HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo == null) {
			return -97;
		}
		
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		vo.setRegCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		
		return creditShopService.insertProductComment(vo, cVo, request);
    }
	
	@RequestMapping("/showProduct/prefer")
	@ResponseBody
    public int preferProduct(int productNo,
    		HttpSession session,
    		HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo == null) {
			return -97;
		}
		
		if(cVo.getFamilyCustNo() == 0) {
			return -96;
		}
		
		ProductBuyVO vo = new ProductBuyVO();
		vo.setProductNo(productNo);
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		vo.setRegCustNo(cVo.getCustNo());
		vo.setChgCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		
		String useYn = creditShopService.getPreferProduct(vo);
		
		if(useYn == null) {					// 최초찜
			creditShopService.insertPreferProduct(vo);
			return 0;
		} else if(useYn.equals("N")) {		// 찜 취소했다가 다시 찜
			vo.setUseYn("Y");
			creditShopService.updatePreferProduct(vo);
			return 0;
		} else {							// 찜 취소하기
			vo.setUseYn("N");
			creditShopService.updatePreferProduct(vo);
			return 1;
		}
    }
	
	@RequestMapping("/product/insertProduct")
    public String insertProductPage(Model model,
    		HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo == null) {
			return "/error/notLogin";
		}
		
		model.addAttribute("categoryList", creditShopService.listProductCategory());
		
		return "/shop/creditShop/insertProduct";
    }
	
	@RequestMapping("/product/insertProduct/insert")
	@ResponseBody
    public int insertProduct(Model model,
    		HttpSession session,
    		ProductVO productVo,
    		String[] productImgUrls,
    		HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(cVo.getFamilyCustCompanyNumber() == null) {
			return -1;
		}
		
		productVo.setRegCustNo(cVo.getCustNo());
		productVo.setRegIp(request.getRemoteAddr());
		productVo.setFamilyCustNo(cVo.getFamilyCustNo());
		productBuyService.insertProduct(productVo, productImgUrls);
		
		return 0;
	}
	
	@RequestMapping("/product/uploadImages")
	@ResponseBody
	private String boardInsertProc(MultipartHttpServletRequest mtfRequest, HttpServletRequest request)
			throws Exception {
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		ArrayList<Map<String, Object>> list = new ArrayList<>();

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String time1 = format1.format(time);
		String[] sFolderName = time1.split("-");

		String path = System.getProperty("user.dir") + "/src/main/webapp/img/product/"
		// GlobalVariable.BOARD_IMG_PATH
				+ "" + sFolderName[0] + "/" + sFolderName[1] + "/" + sFolderName[2] + "/";

		File folder = new File(path);

		if (!folder.exists()) {
			try {
				folder.mkdirs(); // 폴더 생성합니다.
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		AWSService awsService = new AWSService();

		for (MultipartFile mf : fileList) {
			long fileSize = mf.getSize(); // 파일 사이즈
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명

			if (fileSize <= 1024 * 1024 * 1) { // 1메가 제한
				long lTime = System.currentTimeMillis();
				UUID uuid = UUID.randomUUID();
				String localFullPathFile = path + lTime + "_" + uuid + "_" + originFileName;

				String sAwsFilePath = "img/product/" + "" + sFolderName[0] + "/" + sFolderName[1] + "/" + sFolderName[2];

				Map<String, Object> map = new HashMap<String, Object>();

				map.put("imgUrl", SecretGlobalVariable.AWS_S3_LINK + "/img/product/" + "" + sFolderName[0] + "/"
						+ sFolderName[1] + "/" + sFolderName[2] + "/" + lTime + "_" + uuid + "_" + originFileName);
				map.put("originalFileName", originFileName);
				map.put("fileSize", fileSize);
				list.add(map);

				try {
					mf.transferTo(new File(localFullPathFile));
					File awsUploadFile = new File(localFullPathFile);

					if (awsService.uploadFile(sAwsFilePath, awsUploadFile) == 0) {
						awsUploadFile.delete();
					}
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "-99";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "-99";
				}
			}
		}

		Gson gson = new Gson();
		String sPath = gson.toJson(list);

		return sPath;
	}
}
