package com.ezen.biz.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.ezen.biz.common.JDBCUtil;
import com.ezen.biz.dto.UserVO;

@Repository("userDAO")
public class UserDAO {
	//	JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//	SQL 명령어들
	private static final String USER_GET 
				= "SELECT * FROM users WHERE id=? and password=?";
	
	//	CRUD 기능의 메소드 구현
	//	글 등록
	public UserVO getUser(UserVO vo) {
		
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 하나의 행만 조회하기 때문에 while이 아닌 if를 씀.
				
				user = new UserVO();
				
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getNString("name"));
				user.setRole(rs.getString("role"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		
		return user;
	}

}
