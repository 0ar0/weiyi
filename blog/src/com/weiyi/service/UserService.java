package com.weiyi.service;

import com.weiyi.dao.UserDao;
import com.weiyi.db.TransactionContext;
import com.weiyi.util.StringUtil;

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
			tran.beginTran(true);
			UserDao userDao = new UserDao(tran);
			if(userName.contains("@")){
				//�����¼
				//userDao.
				if(!userDao.loginByEmail(userName,password)){
					throw new Exception("�˺Ż��������");
				}
				return true;
			}else if(StringUtil.isDigital(userName)){
				if(!userDao.loginByTel(userName, password)){
					throw new Exception("�˺Ż��������");
				}
				return true;	
			}else {
				if(!userDao.loginByUserName(userName, password)){
					throw new Exception("�˺Ż��������");
				}
				return true;
			}
		} catch (Exception e) {
			throw new Exception("DB����" + e.getMessage());
		}
	}
}
