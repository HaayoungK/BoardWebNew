package com.ezen.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.UserDAO;
import com.ezen.biz.dto.UserVO;

@Service("userService")	// (2) 어노테이션 방식으로 객체 생성시 
public class UserServiceImpl implements UserService {
		
		@Autowired // (2) 어노테이션 방식으로 객체 생성시(의존성 주입)
		private UserDAO userDAO;
		
		public UserVO getUser(UserVO vo) {
			return userDAO.getUser(vo);
		}
		
		public void setUserDAO(UserDAO userDAO) {
			this.userDAO = userDAO;
		}

	}

