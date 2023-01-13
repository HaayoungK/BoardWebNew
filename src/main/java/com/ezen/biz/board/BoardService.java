package com.ezen.biz.board;

import java.util.List;

import com.ezen.biz.dto.BoardVO;

public interface BoardService {

	// 게시글 등록
	void insertBoard(BoardVO board);
	// 게시글 수정
	void updateBoard(BoardVO board);
	// 게시글 삭제
	void deleteBoard(BoardVO board);
	// 게시글 상세 조회
	BoardVO getBoard(BoardVO board);
	// 게시글 목록 조회
	List<BoardVO> getBoardList();

}