package com.familytoto.familytotoProject.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.FileVO;
import com.familytoto.familytotoProject.board.domain.SearchVO;
import com.familytoto.familytotoProject.board.service.BoardService;
import com.familytoto.familytotoProject.comment.domain.CommentVO;
import com.familytoto.familytotoProject.comment.service.CommentService;
import com.familytoto.familytotoProject.config.GlobalVariable;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.google.gson.Gson;

@Controller
@ControllerAdvice
public class BoardController {
	private int nReplyNo = 0;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	CommentService commentService;
	
	private FileVO fileVo = new FileVO();
	
	@RequestMapping("/boardList")
    public String boardList(Model model
    		, @RequestParam(required = false, defaultValue = "1") int page
			, @RequestParam(required = false, defaultValue = "1") int range
			, @RequestParam(required = false, defaultValue = "title") String searchType
			, @RequestParam(required = false) String keyword
    		) throws Exception {
		//전체 게시글 개수
		
		SearchVO search = new SearchVO();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		int listCnt = boardService.getBoardListCnt(search);

		search.pageInfo(page, range, listCnt);

	    // Pagination 객체생성		
		search.pageInfo(page, range, listCnt);

		model.addAttribute("pagination", search);
		model.addAttribute("boardList", boardService.getBoardList(search));
		
        return "/board/boardList";
    }
	
	@RequestMapping(value = {"/registerBoard"} )
    public ModelAndView registerBoard(HttpSession session,
    		ModelAndView mv, HttpServletRequest request) {
		
		mv.setViewName("/board/registerBoard");
		mv.addObject("loginGubun", session.getAttribute("cust"));

        return mv;
    }
	
	@RequestMapping(value = {"/registerBoard/{boardNo}"} )
    public ModelAndView registerBoard(HttpSession session,
    		ModelAndView mv,
    		@PathVariable("boardNo") int nBoardNo) {
		
		if(nBoardNo != 0 ) {
			mv.addObject("replyNo", nBoardNo);
			nReplyNo = nBoardNo;
		}
		
		mv.setViewName("/board/registerBoard");
		mv.addObject("loginGubun", session.getAttribute("cust"));
		
        return mv;
    }
	
	@RequestMapping("/registerBoard/insert")
    public String insertBoard(@Valid @ModelAttribute BoardVO vo, HttpServletRequest request, HttpSession session) {
		vo.setRegIp(request.getRemoteAddr());
		vo.setBoardReplyNo(nReplyNo);
		
		if(session.getAttribute("cust") != null) {
			CustVO cVo = (CustVO) session.getAttribute("cust");
			vo.setRegCustNo(cVo.getCustNo());
			
			int nResult = boardService.insertCustBoard(vo);
			
			fileVo.setRegIp(request.getRemoteAddr());
			fileVo.setBoardNo(vo.getBoardNo());
			boardService.insertFile(fileVo);
			
			if(nResult==1) {
				return "redirect:/boardList";
			}
		}  
		
		return "-99";
    }
	
	@RequestMapping("/registerBoard/anno/insert")
    public String insertAnnoBoard(@Valid @ModelAttribute BoardVO vo, HttpServletRequest request, HttpSession session) {
		vo.setRegIp(request.getRemoteAddr());
		
		if(session.getAttribute("cust") == null) {
			int nResult = boardService.insertAnnoBoard(vo);
			
			fileVo.setBoardNo(vo.getBoardNo());
			fileVo.setRegIp(request.getRemoteAddr());
			boardService.insertFile(fileVo);
			
			if(nResult == 1) {
				return "redirect:/boardList";
			} else if(nResult == -99) {
				return "-99";
			} else if(nResult == -98) {
				return "-98";
			} else {
				return Integer.toString(nResult);
			}
		} else { // 회원이 익명 글쓰기
			return "-1";
		}
    }
		
	@RequestMapping("/showBoard/{boardNo}")
    public ModelAndView showBoard(HttpSession session, @PathVariable ("boardNo") String sBoardNo, ModelAndView mv) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(sBoardNo));
		CustVO custVo = (CustVO) session.getAttribute("cust");
		int nCommentCnt = boardService.getCommentCnt(vo);
		int nCustNo = -1;
		
		vo.setBoardNo(Integer.parseInt(sBoardNo));
		vo = boardService.getShowBoard(vo);
		
		if(custVo != null) {
			nCustNo = custVo.getCustNo();
		} 
				
		List<CommentVO> listCommentVo = commentService.getListComment(vo);

		String sGubun = (String) session.getAttribute("social"); 
		
		if(sGubun != null) {
			if(sGubun.equals("KA")) {
				sGubun = "/img/social/icon/kakaoMiniIcon.jpg";
			} else if(sGubun.equals("FA")) {
				sGubun = "/img/social/icon/facebookMiniIcon.jpg";
			} else if(sGubun.equals("NA")) {
				sGubun = "/img/social/icon/naverMiniIcon.jpg";
			} else if(sGubun.equals("ON")) {
				sGubun = "/img/social/icon/onesportsMiniIcon.jpg";
			}
		} else {
			sGubun = "/img/social/icon/onesportsMiniIcon.jpg";
		}

		mv.addObject("cust", nCustNo);
		mv.addObject("board", vo);
		mv.addObject("custComment", custVo);
		mv.addObject("comment", listCommentVo);
		mv.addObject("socialImg", sGubun);
		mv.addObject("commentCnt", nCommentCnt);

		mv.setViewName("board/showBoard");
		
		return mv;
    }	
	
	@RequestMapping("/deleteBoard/{boardNo}")
	public String deleteBoard(HttpSession session, @PathVariable ("boardNo") String sNo, HttpServletRequest request) {
		if(boardService.updateDeleteBoard(sNo, session, request) == 1) {
			return "redirect:/boardList";			
		} else {
			return null;
		}
    }
	
	@RequestMapping("/deleteAnnoBoard/{boardNo}")
	@ResponseBody
    public int deleteAnnoBoard(@PathVariable ("boardNo") String sNo, HttpServletRequest request, @ModelAttribute BoardVO vo) {
		int nResult = boardService.updateDeleteAnnoBoard(sNo, request,vo);
		
		if(nResult == 1) {
			return 0;			
		} else if(nResult == -99) {
			return -99;
		} else {
			return -98;
		}
    }
	
	@RequestMapping("/updateBoard/check")
	@ResponseBody
    public String updateCheckBoard(BoardVO bVo, Model model) {
		CustVO cVo = new CustVO();
		String sOriginalPass = bVo.getBoardAnnoPw();
		
		bVo = boardService.getUpdateBoard(bVo);
		cVo.setCustPassword(sOriginalPass);
		
		if(cVo.isDecodePassword(cVo, bVo.getBoardAnnoPw())) {
			model.addAttribute("board", bVo);
			
	        return "0";
		} else { // 비번틀린경우
			
			return "-98";
		}		
    }
	
	@RequestMapping("/updateBoard/{boardNo}")
	public String showUpdateBoard(BoardVO bVo, Model model) {
		// 비밀번호 체크 && 리퍼러 체크하기> 실패하면 원래있던 보드로 이동 
		// 성공하면 수정창이동
		BoardVO resultBoardVo = boardService.getUpdateBoard(bVo);
		
		
		model.addAttribute("board", resultBoardVo);
		return "/board/updateBoard";
    }
	
	@RequestMapping("/updateBoard/{boardNo}/update")
	@ResponseBody
	public int updateBoard(@PathVariable ("boardNo") String sBoardNo,
			@Valid @ModelAttribute BoardVO bVo, Model model,
			HttpServletRequest request, HttpSession session) {
		bVo.setChgIp(request.getRemoteAddr());

		return boardService.updateBoard(bVo, session);
    }
	
	@RequestMapping("/board/uploadImages")
	@ResponseBody
    private String boardInsertProc(MultipartHttpServletRequest mtfRequest) throws Exception{
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		ArrayList<Map<String, Object>> list = new ArrayList<>(); 
		
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");		
		Date time = new Date();
		String time1 = format1.format(time);
		String[] sFolderName = time1.split("-");
		
		String path = GlobalVariable.BOARD_IMG_PATH
				+ "" + sFolderName[0]
				+ "/" + sFolderName[1]
				+ "/" + sFolderName[2]+"/";
								
		
		File folder = new File(path);
		
		if (!folder.exists()) {
			try {
				folder.mkdirs(); // 폴더 생성합니다.
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		
		for (MultipartFile mf : fileList) {
			long fileSize = mf.getSize(); // 파일 사이즈
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명			
			
			if(fileSize <= 1024*1024) {
//				System.out.println("originFileName : " + originFileName);
//				System.out.println("fileSize : " + fileSize);
			
				long lTime = System.currentTimeMillis(); 
				
				String safeFile = path + lTime +"_" + originFileName;
						
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("imgUrl","/img/board/"
						+ "" + sFolderName[0]
						+ "/" + sFolderName[1]
						+ "/" + sFolderName[2]
						+ "/" + lTime+"_" + originFileName + "?t=" + lTime);
				map.put("originalFileName", originFileName);
				map.put("fileSize", fileSize);
				list.add(map);
				
				try {
					mf.transferTo(new File(safeFile));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		Gson gson = new Gson();
		String sPath = gson.toJson(list);
		
		return sPath;
    }
	
	@RequestMapping("/board/uploadFile")
	@ResponseBody
    private String boardInsertFile(MultipartHttpServletRequest mtfRequest) throws Exception{
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");		
		Date time = new Date();
		String time1 = format1.format(time);
		String[] sFolderName = time1.split("-");
		
		String path = GlobalVariable.BOARD_FILE_PATH
				+ "" + sFolderName[0]
				+ "/" + sFolderName[1]
				+ "/" + sFolderName[2]+"/";
								
		
		File folder = new File(path);
		
		if (!folder.exists()) {
			try {
				folder.mkdirs(); // 폴더 생성합니다.
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		
		for (MultipartFile mf : fileList) {
			long fileSize = mf.getSize(); // 파일 사이즈
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명			
			
			if(fileSize <= 1024*1024*3) { //3메가 제한
				long lTime = System.currentTimeMillis();
				String safeFile = path + lTime +"_" + originFileName;
				String sFileName = lTime+"_" + originFileName;
				String sFilePath = "/file/board/"
						+ "" + sFolderName[0]
						+ "/" + sFolderName[1]
						+ "/" + sFolderName[2]
						+ "/" + sFileName;
				
				fileVo.setBoardFilePath(sFilePath);
				fileVo.setBoardFileName(originFileName);
				
				try {
					mf.transferTo(new File(safeFile));
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
		
		return "0";
    }
	
	
	/*@Autowired
	private AmazonS3 amazonS3;
	 
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	@RequestMapping("/board/uploadFile")
	@ResponseBody
	public void s3Test(MultipartFile file, String fname) {
	    TransferManager tm = TransferManagerBuilder.standard().withS3Client(amazonS3).build();
	 
	 
	    PutObjectRequest request;
	    try {
	 
	        ObjectMetadata metadata = new ObjectMetadata();
	        metadata.setCacheControl("604800"); // 60*60*24*7 일주일
	        metadata.setContentType("image/png");
	        request = new PutObjectRequest(bucket, fname, file.getInputStream(), metadata)
	                .withCannedAcl(CannedAccessControlList.PublicRead);
	        // amazonS3.putObject(request);
	        Upload upload = tm.upload(request);
	 
	        upload.waitForCompletion();
	 
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (AmazonServiceException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (AmazonClientException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	   }*/
}
