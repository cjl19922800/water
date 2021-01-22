package com.shanghaiwater.base.webapi.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;
public class JdbcUtil{
    private static String driver="oracle.jdbc.driver.OracleDriver";
    //private static String url="jdbc:oracle:thin:@49.235.81.184:1521:ctorcl";
    private static String url="jdbc:oracle:thin:@10.3.1.102:1521:ctorcl";
    private Connection conn = null;
    private PreparedStatement pre=null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ArrayList<Map<String,String>> rsList = null;

	 /**
     * 文件读取，只会执行一次，使用静态代码块
     */
    static {
        //4.注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取连接
     * @return 连接对象
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public Connection getConnection(String user,String password) throws SQLException{
    	
    	Connection conn = DriverManager.getConnection(url, user, password);
    	return conn;	
	}
    
    //查询数据
    public ArrayList<Map<String,String>> dataQuery(String sql,int page,int limit,String name,String pass){
    	rsList = new ArrayList<Map<String,String>>();
		try {
			pre=getConnection(name,pass).prepareStatement(sql);
			rs = pre.executeQuery();
			System.out.println("结果集对象"+rs);
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			 
			while(rs.next()){
				Map<String,String> rsMap = new HashMap<String,String>();
				for(int i = 1;i <= count;i++){
					String columnName = rsmd.getColumnName(i);
					String value = rs.getString(columnName);
					rsMap.put(columnName, value);
				}
				
				rsList.add(rsMap);
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		return rsList;
    	
    }
    
    

    /**
     * 释放资源
     * @param rs
     * @param st
     * @param conn
     */
    public void close(){
		try {
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
