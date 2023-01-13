package com.ezen.biz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.ezen.biz.common.JDBCUtil;
import com.ezen.biz.dto.BoardVO;

@Repository("boardDAO") // 미리 빈 객체 만들기
public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// SQL 명령어 상수
	private static final String BOARD_INSERT = 
			"INSERT INTO board(seq, title, writer, content) VALUES(board_seq.NEXTVAL, ?, ?, ?)";
	private static final String BOARD_UPDATE =
			"UPDATE board SET title=?, writer=?, content=? WHERE seq=?";
	private static final String BOARD_DELETE = 
			"DELETE board WHERE seq=?";
	private static final String BOARD_GET = 
			"SELECT * FROM board WHERE seq=?";
	private static final String BOARD_LIST =
			"SELECT * FROM board ORDER BY seq DESC";
	// 게시글 등록
	public void insertBoard(BoardVO board) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 게시글 수정
	public void updateBoard(BoardVO board) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getSeq());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 게시글 삭제
	public void deleteBoard(BoardVO board) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, board.getSeq());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 게시글 상세 조회
	public BoardVO getBoard(BoardVO board) {
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		BoardVO vo = new BoardVO();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, board.getSeq());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setRegDate(rs.getDate("regdate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	// 게시글 목록 조회
	public List<BoardVO> getBoardList() {
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		List<BoardVO> boardList = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_LIST);
			
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<BoardVO>();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setRegDate(rs.getDate("regdate"));
				
				boardList.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return boardList;
	}
}












