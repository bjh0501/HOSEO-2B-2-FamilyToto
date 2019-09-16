package com.familytoto.familytotoProject.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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

import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.SafeHtml.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.FileVO;
import com.familytoto.familytotoProject.board.domain.SearchVO;
import com.familytoto.familytotoProject.board.service.AWSService;
import com.familytoto.familytotoProject.board.service.BoardService;
import com.familytoto.familytotoProject.comment.domain.CommentVO;
import com.familytoto.familytotoProject.comment.service.CommentService;
import com.familytoto.familytotoProject.config.GlobalVariable;
import com.familytoto.familytotoProject.config.SecretGlobalVariable;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.google.gson.Gson;

@Controller
@ControllerAdvice
public class BoardController {
	@Autowired
	BoardService boardService;

	@Autowired
	CommentService commentService;

	@RequestMapping("/boardList")
	public String boardList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String keyword) throws Exception {
		// 전체 게시글 개수

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

	@RequestMapping(value = { "/registerBoard" })
	public ModelAndView registerBoard(HttpSession session, ModelAndView mv, HttpServletRequest request) {

		mv.setViewName("/board/registerBoard");
		mv.addObject("loginGubun", session.getAttribute("cust"));

		return mv;
	}

	@RequestMapping(value = { "/registerBoard/{boardNo}" })
	public ModelAndView registerBoard(HttpSession session, ModelAndView mv, @PathVariable("boardNo") int nBoardNo,
			@Param("grpNo") int grpNo, @Param("grpOrd") int grpOrd, @Param("grpDepth") int grpDepth) {

		mv.setViewName("/board/registerBoard");
		mv.addObject("loginGubun", session.getAttribute("cust"));
		mv.addObject("grpNo", grpNo);
		mv.addObject("grpOrd", grpOrd);
		mv.addObject("grpDepth", grpDepth);
		mv.addObject("replyNo", nBoardNo);

		return mv;
	}

	@RequestMapping("/registerBoard/insert")
	@Transactional
	public String insertBoard(@Valid @ModelAttribute BoardVO vo, @Valid @ModelAttribute FileVO fileVo,
			HttpServletRequest request, HttpSession session) {
		vo.setRegIp(request.getRemoteAddr());

		if (session.getAttribute("cust") != null) {
			CustVO cVo = (CustVO) session.getAttribute("cust");
			vo.setRegCustNo(cVo.getCustNo());

			int nResult = 0;
			
			if(vo.getBoardNotice() == null) {
				vo.setBoardNotice("N");
			}
			
			if(cVo.getCustOperatorGubun().equals("N") &&
					vo.getBoardNotice().equals("Y")) {
				return  "-1";
			}
			
			if (vo.getBoardGrpNo() != 0) { // 답장
				vo.setBoardGrpNo(vo.getBoardGrpNo());
				vo.setBoardGrpDepth(vo.getBoardGrpDepth() + 1);
				vo.setBoardGrpOrd(vo.getBoardGrpOrd() + 1);

				nResult = boardService.insertCustBoard(vo, 1);
			} else {
				vo.setBoardGrpNo(0);
				vo.setBoardGrpDepth(0);
				vo.setBoardGrpOrd(0);
				nResult = boardService.insertCustBoard(vo, 0);
			}

			if (fileVo != null) {
				if(fileVo.getBoardFileName() != null && fileVo.getBoardFilePath() != null) {  
					fileVo.setRegIp(request.getRemoteAddr());
					fileVo.setBoardNo(vo.getBoardNo());
					fileVo.setRegCustNo(cVo.getCustNo());
					boardService.insertFile(fileVo);
				}
			}

			if (nResult == 1) {
				return "redirect:/boardList";
			}
		}

		return "-99";
	}

	@RequestMapping("/registerBoard/anno/insert")
	@Transactional
	public String insertAnnoBoard(@Valid @ModelAttribute BoardVO vo, @Valid @ModelAttribute FileVO fileVo,
			HttpServletRequest request, HttpSession session) {
		vo.setRegIp(request.getRemoteAddr());

		if (session.getAttribute("cust") == null) {
			int nResult = 0;

			if (vo.getBoardGrpNo() != 0) { // 답장
				vo.setBoardGrpNo(vo.getBoardGrpNo());
				vo.setBoardGrpDepth(vo.getBoardGrpDepth() + 1);
				vo.setBoardGrpOrd(vo.getBoardGrpOrd() + 1);

				nResult = boardService.insertAnnoBoard(vo, 1);
			} else {
				vo.setBoardGrpNo(0);
				vo.setBoardGrpDepth(0);
				vo.setBoardGrpOrd(0);
				nResult = boardService.insertAnnoBoard(vo, 0);
			}

			if (fileVo != null) {
				if(fileVo.getBoardFileName() != null && fileVo.getBoardFilePath() != null) {
					fileVo.setRegIp(request.getRemoteAddr());
					fileVo.setBoardNo(vo.getBoardNo());
					fileVo.setRegCustNo(0);
					boardService.insertFile(fileVo);
				}
			}

			if (nResult >= 1) {
				return "redirect:/boardList";
			} else if (nResult == -99) {
				return "-99";
			} else if (nResult == -98) {
				return "-98";
			} else {
				return Integer.toString(nResult);
			}
		} else { // 회원이 익명 글쓰기
			return "-1";
		}
	}

	@RequestMapping("/showBoard/{boardNo}")
	public ModelAndView showBoard(HttpSession session,
			@PathVariable("boardNo") String sBoardNo,
			ModelAndView mv,
			HttpServletRequest request) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(sBoardNo));
		CustVO custVo = (CustVO) session.getAttribute("cust");
		int nCommentCnt = boardService.getCommentCnt(vo);
		int nCustNo = -1;

		vo.setBoardNo(Integer.parseInt(sBoardNo));
		vo = boardService.getShowBoard(vo);

		if (custVo != null) {
			nCustNo = custVo.getCustNo();
		}

		List<CommentVO> listCommentVo = commentService.getListComment(vo);

		for(int i = 0; i < listCommentVo.size(); i++) {
			
			String sDateStr = GlobalVariable.formatTimeString(listCommentVo.get(i).getRegDt());
			
			if(sDateStr.equals("출력")) {
				Timestamp ts = listCommentVo.get(i).getRegDt();
				Date date = new Date();
				date.setTime(ts.getTime());
				String formattedDate = new SimpleDateFormat("yyyy. MM. dd. hh:mm").format(date);
				
				listCommentVo.get(i).setRegDtStr(formattedDate);
			} else {
				listCommentVo.get(i).setRegDtStr(sDateStr);
			}
		}
		
		String sGubun = (String) session.getAttribute("social");

		if (sGubun != null) {
			if (sGubun.equals("KA")) {
				sGubun = "/img/social/icon/kakaoMiniIcon.jpg";
			} else if (sGubun.equals("FA")) {
				sGubun = "/img/social/icon/facebookMiniIcon.jpg";
			} else if (sGubun.equals("NA")) {
				sGubun = "/img/social/icon/naverMiniIcon.jpg";
			} else if (sGubun.equals("ON")) {
				sGubun = "/img/social/icon/onesportsMiniIcon.jpg";
			}
		} else {
			sGubun = "/img/social/icon/onesportsMiniIcon.jpg";
		}

		boardService.updateVisitLog(vo.getBoardNo());

		mv.addObject("cust", nCustNo);
		mv.addObject("board", vo);
		mv.addObject("custComment", custVo);
		mv.addObject("comment", listCommentVo);
		mv.addObject("socialImg", sGubun);
		mv.addObject("commentCnt", nCommentCnt);
		mv.addObject("replyBoardList", boardService.listReplyBoard(vo.getBoardGrpNo()));
		
		String sLink = "";
		if(request.getHeader("referer") == null || request.getHeader("referer").indexOf("boardList") == -1) {
			sLink = "/boardList";
		}
		
		else {
			sLink = request.getHeader("referer");
		}
		
		mv.addObject("listLink", sLink);
		
		mv.setViewName("/board/showBoard");

		return mv;
	}

	@RequestMapping("/deleteBoard/{boardNo}")
	public String deleteBoard(HttpSession session,
			@PathVariable("boardNo") String sNo,
			HttpServletRequest request,
			@ModelAttribute BoardVO vo) {
		if (boardService.updateDeleteBoard(sNo, vo, session, request) == 1) {
			return "redirect:/boardList";
		} else {
			return null;
		}
	}

	@RequestMapping("/deleteAnnoBoard/{boardNo}")
	@ResponseBody
	public int deleteAnnoBoard(@PathVariable("boardNo") String sNo, HttpServletRequest request,
			@ModelAttribute BoardVO vo) {
		int nResult = boardService.updateDeleteAnnoBoard(sNo, request, vo);

		if (nResult == 1) {
			return 0;
		} else if (nResult == -99) {
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

		if (cVo.isDecodePassword(cVo, bVo.getBoardAnnoPw())) {
			model.addAttribute("board", bVo);

			return "0";
		} else { // 비번틀린경우

			return "-98";
		}
	}

	@RequestMapping("/updateBoard/{boardNo}")
	public String showUpdateBoard(BoardVO bVo, Model model, HttpSession session, HttpServletResponse response) {
		// 비밀번호 체크 && 리퍼러 체크하기> 실패하면 원래있던 보드로 이동
		// 성공하면 수정창이동

		CustVO cVo = (CustVO) session.getAttribute("cust");

		BoardVO resultBoardVo = boardService.getUpdateBoard(bVo);

		if (cVo == null && resultBoardVo.getBoardAnnoId() == null) { // 익명로그인 + 회원글쓰기
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 경로입니다.');location.replace('/');</script>");
				out.flush();

			} catch (Exception e) {
			}
		}

		model.addAttribute("board", resultBoardVo);
		return "/board/updateBoard";
	}

	@RequestMapping("/updateBoard/{boardNo}/update")
	@ResponseBody
	public int updateBoard(@PathVariable("boardNo") String sBoardNo, @Valid @ModelAttribute BoardVO bVo, Model model,
			@Valid @ModelAttribute FileVO fileVo, HttpServletRequest request, HttpSession session) {
		bVo.setChgIp(request.getRemoteAddr());

		CustVO cVo = (CustVO) session.getAttribute("cust");

		if (cVo == null) {
			fileVo.setChgCustNo(0);
		} else {
			fileVo.setChgCustNo(cVo.getCustNo());
			fileVo.setRegCustNo(cVo.getCustNo());
		}

		// 파일 삭제한 경우
		if (request.getParameter("notUseFile").equals("Y")) {
			fileVo.setChgIp(request.getRemoteAddr());
			fileVo.setBoardNo(bVo.getBoardNo());
			boardService.updateFile(fileVo); // 기존에있는 파일 삭제
		}

		// 업로드한 파일 있을경우
		if (fileVo != null) {
			if(fileVo.getBoardFileName() != null && fileVo.getBoardFilePath() != null) {
				fileVo.setRegIp(request.getRemoteAddr());
				fileVo.setBoardNo(bVo.getBoardNo());
				fileVo.setChgIp(request.getRemoteAddr());
				boardService.updateFile(fileVo); // 기존에있는 파일 삭제
				boardService.insertFile(fileVo);
			}
		}

		return boardService.updateBoard(bVo, session);
	}

	@RequestMapping("/board/uploadImages")
	@ResponseBody
	private String boardInsertProc(MultipartHttpServletRequest mtfRequest, HttpServletRequest request)
			throws Exception {
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		ArrayList<Map<String, Object>> list = new ArrayList<>();

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String time1 = format1.format(time);
		String[] sFolderName = time1.split("-");

		String path = System.getProperty("user.dir") + "/src/main/webapp/img/board/"
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

			if (fileSize <= 1024 * 1024 * 3) { // 3메가 제한
				long lTime = System.currentTimeMillis();
				UUID uuid =UUID.randomUUID();
				String localFullPathFile = path + lTime + "_" + uuid + "_" + originFileName;

				String sAwsFilePath = "img/board/" + "" + sFolderName[0] + "/" + sFolderName[1] + "/" + sFolderName[2];

				Map<String, Object> map = new HashMap<String, Object>();

				map.put("imgUrl", SecretGlobalVariable.AWS_S3_LINK + "/img/board/" + "" + sFolderName[0] + "/"
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

	@RequestMapping("/board/uploadFile")
	@ResponseBody
	private String boardInsertFile(MultipartHttpServletRequest mtfRequest) throws Exception {
		List<MultipartFile> fileList = mtfRequest.getFiles("file");

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String time1 = format1.format(time);
		String[] sFolderName = time1.split("-");

		String path = System.getProperty("user.dir") + "/src/main/webapp/file/board/" + "" + sFolderName[0] + "/"
				+ sFolderName[1] + "/" + sFolderName[2] + "/";

		File folder = new File(path);

		if (!folder.exists()) {
			try {
				folder.mkdirs(); // 폴더 생성합니다.
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		AWSService awsService = new AWSService();

		FileVO fileVo = new FileVO();

		for (MultipartFile mf : fileList) {
			long fileSize = mf.getSize(); // 파일 사이즈
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명

			if (fileSize <= 1024 * 1024 * 3) { // 3메가 제한
				long lTime = System.currentTimeMillis();
				String localFullPathFile = path + lTime + "_" + originFileName;
				String sFileName = lTime + "_" + originFileName;
				String sDBFilePath = SecretGlobalVariable.AWS_S3_LINK + "/file/board/" + "" + sFolderName[0] + "/"
						+ sFolderName[1] + "/" + sFolderName[2] + "/" + sFileName;

				String sAwsFilePath = "file/board/" + "" + sFolderName[0] + "/" + sFolderName[1] + "/" + sFolderName[2];

				fileVo.setBoardFilePath(sDBFilePath); // DB에들어갈 패스
				fileVo.setBoardFileName(originFileName); // DB에 들어갈 이름

				try {
					mf.transferTo(new File(localFullPathFile));
					File awsUploadFile = new File(localFullPathFile);
					if (awsService.uploadFile(sAwsFilePath, awsUploadFile) == 0) {
						awsUploadFile.delete();
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
					return "-99";
				} catch (IOException e) {
					e.printStackTrace();
					return "-99";
				}
			}
		}

		Gson gson = new Gson();
		String sJson = gson.toJson(fileVo);

		return sJson;
	}

	@RequestMapping("/board/editor/image")
	public String editorImage() {
		return "/board/editor/image";
	}

	@ResponseBody
	@RequestMapping("/board/downloadFile/{boardNo}")	
	public String downloadFile(String filePath,
			HttpServletResponse response,
			HttpServletRequest request,
			@PathVariable("boardNo") int nBoardNo) throws IOException {
		
		AWSService awsService = new AWSService();
		FileVO fVo = boardService.getUploadedFile(nBoardNo);
		
		String key = fVo.getBoardFilePath().replace(SecretGlobalVariable.AWS_S3_LINK+"/", "");
        
		S3ObjectInputStream inputStream = awsService.downloadFIle(key, fVo.getBoardFileName());
		
		// 여기부터 파일 다운 부분
		String client = request.getHeader("User-Agent");
	   
        // 파일 다운로드 헤더 지정
        response.reset() ;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Description", "JSP Generated Data");
        
        String orgfilename = fVo.getBoardFileName();
		
        // IE
        if(client.indexOf("MSIE") != -1){
            response.setHeader ("Content-Disposition", "attachment; filename="+new String(orgfilename.getBytes("KSC5601"),"ISO8859_1"));
 
            }else{
                // 한글 파일명 처리
            orgfilename = new String(orgfilename.getBytes("utf-8"),"iso-8859-1");
 
                response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
            response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
        }
        
        try (BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream())) {
            int leng = 0;
            byte b[] = new byte[1024*3];
            while( (leng = inputStream.read(b)) > 0 ){
                os.write(b, 0, leng);
            }
        } catch(Exception e) {System.out.println(e);}
	
		return "0";
	}	
}
