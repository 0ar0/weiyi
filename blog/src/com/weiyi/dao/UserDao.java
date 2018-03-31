package com.weiyi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.weiyi.db.TransactionContext;
import com.weiyi.model.User;

public class UserDao implements BaseDao{
	private TransactionContext tran = null;
	
	
	public UserDao(TransactionContext tran) {
		super();
		this.tran = tran;
	}

	@Override
	public int add(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(Object t, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteById(int[] id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public boolean loginByUserName(User user) throws Exception{
		Connection conn = tran.getConnection();
		String sql = "select id from db_userd where username = ? and password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	public boolean loginByEmail(User user) throws Exception{
		Connection conn = tran.getConnection();
		String sql = "select id from db_userd where username = ? and password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getEmail());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	
	public boolean loginByTel(User user) throws Exception{
		Connection conn = tran.getConnection();
		String sql = "select id from db_userd where username = ? and password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getTel());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
}
