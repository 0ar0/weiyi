package com.weiyi.dao;

import java.util.List;

public interface BaseDao <T>{
	public int add(T t) throws Exception;
	public int updateById(T t,int id)throws Exception;
	public List<Object> selectById(int id)throws Exception;
	public int deleteById(int id[])throws Exception;
	
}
