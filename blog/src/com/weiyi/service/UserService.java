package com.weiyi.service;

import com.weiyi.dao.UserDao;
import com.weiyi.db.TransactionContext;

public class UserService {
	/**
	 * 
	 * @param userName �û���ֵ�����������䣬�绰���û���
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean login(String userName,String password) throws Exception{
		try {
			TransactionContext tran = new TransactionContext();
			UserDao userDao = new UserDao(tran);
			if(userName.contains("@")){
				//�����¼
				//userDao.
				
			}
			
		} catch (Exception e) {
			throw new Exception("DB����" + e.getMessage());
		}
		
		return false;
	}
}
