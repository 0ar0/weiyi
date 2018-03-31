package com.weiyi.db;
import java.io.*;
import java.sql.*;
import java.util.*;

import org.junit.Test;
/**
 * 该类实现数据库连接对象的保存与关闭，同时包括基于连接对象的事务处理
 * 
 * @author zwei
 */
public class TransactionContext {
	private Connection dbConnection = null;//前当连接对象
	private boolean unitOfWork = false;// 真为手动事务，假为自动事务。由 begintran()决定
	private static boolean isDebug = true;//是否打印调试
	private static boolean isReadFromCfg=true; //从配置文件取数据库连接字符串
	
	private String strDriver = "";
	private String strURL = "";
	private String strUser = "";
	private String strPwd = "";

	/**
	 * 构造函数，内部装载驱动 
	 * 技巧：可以在此加入连接池的创建，或引入配置文件等
	 */
	public TransactionContext() throws Exception {
		try {
			log("TransactionContext: setup JDBC Driver");
			//从配置文件（jdbc.properties）获得strDriver，strURL，strUser,strPwd
			if(isReadFromCfg){			
				Properties prop=new Properties();
				//注意路径的写法，这样可以比较灵活
				String cfgFile=this.getClass().getClassLoader().getResource("").getPath()+"jdbc.properties";
				prop.load(new BufferedReader(new FileReader(cfgFile)));
				this.strDriver=prop.getProperty("strDriver");
				this.strURL=prop.getProperty("strURL");
				this.strUser=prop.getProperty("strUser");
				this.strPwd=prop.getProperty("strPwd");
				log("jdbc.properties="+cfgFile);
				log("strDriver="+strDriver);
				log("strURL="+strURL);
				log("strUser="+strUser+" strPwd="+strPwd);
			}
			// 载入驱动程序
			Class.forName(strDriver);
			log("TransactionContext: setup JDBC loaded ok");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Load JDBC Driver Error!");
		}
	}

	/**
	 * 取连接对象
	 * 
	 * @return Connection
	 */
	public Connection getConnection() throws Exception {
		log("TransactionContext: in get connection");
		if (dbConnection == null) {
			throw new Exception("dbConnection is null!");
		}
		return dbConnection;
	}

	/**
	 * 开始事务
	 * 
	 * @param supportTran
	 *            boolean,true表示启用显示事务，否则为自动事务
	 * @throws TranSysException+
	 */
	public void beginTran(boolean supportTran) throws Exception {
		unitOfWork = supportTran;
		// 真正获取一个连接对象，并保存为属性
		getDBConnection();
		try {
			if (supportTran) {
				log("TransactionContext: auto commit off");
				dbConnection.setAutoCommit(false);
			} else {
				log("TransactionContext: auto commit on");
				dbConnection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new Exception("setAutoCommit Error!");
		}

	}

	private void getDBConnection() throws Exception {
		try {
			log("TransactionContext: before get connection");

			// 1. 获取数据库连接，并保存
			dbConnection = DriverManager.getConnection(strURL, strUser, strPwd);

			// 2. 使用连接池
			// Context initContext = new InitialContext();
			// Context envContext = (Context)
			// initContext.lookup("java:/comp/env");
			// DataSource ds = (DataSource) envContext.lookup("jdbc/box");
			// dbConnection = ds.getConnection();			

			log("TransactionContext: after get connection");
		} catch (Exception se) {
			log("TransactionContext: SQL Exception");
			se.printStackTrace();
			throw new Exception("get Connection Error!");
		}
	}

	/**
	 * 提交事务。不管是否开启事务，都可以正常关闭连接。
	 * @throws Exception
	 */
	public void commitTran() throws Exception {
		log("TransactionContext: in commit");
		if (unitOfWork) {
			try {
				dbConnection.commit();
				log("TransactionContext: afterCommit");
				// closeConnection();
			} catch (SQLException e) {
				rollBackTran();
				throw new Exception("CommitTran Error!");
			} finally {
				closeConnection();
			}
		}
		// 提交后关闭连接对象
		closeConnection();
	}

	/**
	 * 用当前连接对象回滚数据
	 * 
	 * @throws TranRollBackException
	 * @throws TranSysException
	 * @return boolean
	 */
	public boolean rollBackTran() throws Exception {
		boolean returnValue = false;
		try {
			dbConnection.rollback();
			log("TransactionContext: afterRollback");
			closeConnection();
			returnValue = true;
		} catch (SQLException e) {
			throw new Exception("Rollback Error!");
		}
		return returnValue;
	}

	/**
	 * 关闭结果集对象
	 * 
	 * @param result
	 *            ResultSet
	 * @throws TranSysException
	 */
	public void closeResultSet(ResultSet result) throws Exception {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException se) {
			throw new Exception("ResultSet Close Error!");
		}
	}

	/**
	 * 关闭语句对象
	 * 
	 * @param stmt
	 *            Statement
	 * @throws TranSysException
	 */
	public void closeStatement(Statement stmt) throws Exception {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException se) {
			throw new Exception("Statement close Error!");
		}
	}

	/**
	 * 关闭连接对象
	 */
	public void closeConnection() throws Exception {
		try {
			if (dbConnection != null && !dbConnection.isClosed()) {
				dbConnection.close();
				log("TransactionContext: closing connection");
			}
		} catch (SQLException se) {
			throw new Exception("Connection close Error!");
		}
	}

	/**
	 * 调试信息
	 * 
	 * @param s
	 *            String
	 */
	private void log(String s) {
		if (isDebug) {
			System.out.println(s);
		}
	}

	public static void main(String[] arg) {
		try {
			TransactionContext tran = new TransactionContext();
			tran.beginTran(false);
			Connection conn = tran.getConnection();
			String sql1 = "select * from emp";
			PreparedStatement pst = conn.prepareStatement(sql1);
			ResultSet rs=pst.executeQuery();
			while(rs!=null&&rs.next()){
			  System.out.println(rs.getString(1)+" "+rs.getString(2) );
			}
			tran.commitTran();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	@Test
	public void test(){
		try {
			TransactionContext trans = new TransactionContext();
			trans.beginTran(false);
			
			trans.commitTran();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
