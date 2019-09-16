package com.familytoto.familytotoProject.board.dao;

import java.util.List;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.FileVO;
import com.familytoto.familytotoProject.board.domain.SearchVO;

public interface BoardDao {
	// 게시글 등록
	int insertCustBoard(BoardVO vo);
	
	// 익명 게시글 등록
	int insertAnnoBoard(BoardVO vo);
	
	// 게시글 등록후 GrpNo부여
	int updateBoardGrpNo(BoardVO vo);
	
	// 다답글 재정렬
	int updateBeforeReply(BoardVO vo);
	
	// 게시글수정
	int updateBoard(BoardVO vo);
	
	int updateSocialBoard(BoardVO vo);
	
	// 게시글 삭제
	int updateDeleteBoard(BoardVO vo);
	
	// 게시글 수정 보기
	BoardVO getUpdateBoard(BoardVO vo);
	
	// 게시글 보기
	BoardVO getShowBoard(BoardVO vo);
	
	// 익명게시판 삭제
	int updateDeleteAnnoBoard(BoardVO vo);
	
	// 익명 게시판 비번확인
	String checkAnnoBoardPass(BoardVO vo);
	
	// 페이징
	public int getBoardListCnt(SearchVO search) throws Exception;
	
	public List<BoardVO> getBoardList(SearchVO search) throws Exception;
	
	// 댓글 카운트
	public int getCommentCnt(BoardVO vo);

	// 파일첨부
	public int insertFile(FileVO vo);
	
	// 파일수정
	public int updateFile(FileVO vo);
	
	public int updateBeforeBoardReply(BoardVO vo);
	
	// 로그 추가
	public int updateVisitLog(int boardNo);
	
	public FileVO getUploadedFile(int boardNo);
	
	// 공지사항보기
	public List<BoardVO> listNotice();
	
	// 게시글 답장 리스트보기
	List<BoardVO> listReplyBoard(int boardGrpNo);

	// 답글있는경우 W로
	boolean isDeleteGrpBoard(BoardVO vo);
	
	// 전부다 삭제된 게시물인지 확인
	boolean isAssertWAndGrpNo(int boardGrpNo);
	
	// 전부다 W일경우 N으로
	int updateIfAllWThenN(BoardVO vo);
}
