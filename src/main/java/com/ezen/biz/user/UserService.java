package com.ezen.biz.user;

import com.ezen.biz.dao.UserDAO;
import com.ezen.biz.dto.UserVO;

public interface UserService {

	//	CRUD 기능의 메소드 구현
	//	글 등록
	public UserVO getUser(UserVO vo);
	
	void setUserDAO(UserDAO userDao);

}