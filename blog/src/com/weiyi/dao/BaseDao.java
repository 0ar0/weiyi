package com.weiyi.dao;

import java.util.List;

public interface BaseDao <T>{
	public int add(T t);
	public int updateById(T t,int id);
	public List<Object> selectById(int id);
	public int deleteById(int id[]);
	
}
