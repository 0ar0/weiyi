package com.weiyi.service;

import com.weiyi.dao.UserDao;
import com.weiyi.db.TransactionContext;

public class UserService {
	/**
	 * 
	 * @param userName 用户名值，肯能是邮箱，电话，用户名
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean login(String userName,String password) throws Exception{
		try {
			TransactionContext tran = new TransactionContext();
			UserDao userDao = new UserDao(tran);
			if(userName.contains("@")){
				//邮箱登录
				//userDao.
				
			}
			
		} catch (Exception e) {
			throw new Exception("DB出错" + e.getMessage());
		}
		
		return false;
	}
}
