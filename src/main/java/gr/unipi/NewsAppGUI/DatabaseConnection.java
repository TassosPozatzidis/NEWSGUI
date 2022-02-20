package gr.unipi.NewsAppGUI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.NewsAPIException;

import java.sql.*;

import model.IpAPI;
import model.NewsAPI;
import model.NewsInfo;
import services.NewsAPIService;
import services.IpAPIService;

public class DatabaseConnection {
	  static final String SQL_CONN_STRING = "jdbc:mariadb://localhost:3306/news_db";
	  static final String USER = "user1";
	  static final String PASS = "pass";
	 static final NewsAPIService newsAPIService = NewsAPI.getNewsAPIService();
	 static final IpAPIService IpAPIService = IpAPI.getIpAPIService();
	
	public static ResultSet readNews() throws SQLException {
	    try (Connection con = DriverManager.getConnection(SQL_CONN_STRING, USER, PASS)) {//connect to db credentials
	        try(Statement pstmt = con.createStatement()){
	        try (ResultSet resultSet = pstmt.executeQuery("select * from NEWS_CRITERIA ORDER BY CREATED_AT DESC LIMIT 5")) {//��� queries ��� �������� ����������
	        	return   resultSet;
	        
	        }
	        }
	      }
	    }
	public static ArrayList<String> readLastTopSearch() throws SQLException {
	    try (Connection con = DriverManager.getConnection(SQL_CONN_STRING, USER, PASS)) {//connect to db credentials
	        try(Statement pstmt = con.createStatement()){
	        try (ResultSet resultSet = pstmt.executeQuery("select  * from NEWS_CRITERIA where length(country)>1 ORDER BY CREATED_AT DESC LIMIT 1,1;")) {//return second to last row from db
	        	
	        	//Stores properties of a ResultSet object, including column count
	        	ResultSetMetaData rsmd = resultSet.getMetaData(); 
	        	int columnCount = rsmd.getColumnCount();
	        	
	        	ArrayList<String> ResultList = new ArrayList<>(columnCount); 
	        	while (resultSet.next()) {              
	        	   int i = 1;
	        	   while(i <= columnCount) {
	        	        ResultList.add(resultSet.getString(i++));
	        	   }
	        	}
	        	return   ResultList;
	        
	        }
	        }
	      }
	    }
	public static ArrayList<String> readLastSearch() throws SQLException {
	    try (Connection con = DriverManager.getConnection(SQL_CONN_STRING, USER, PASS)) {//connect to db credentials
	        try(Statement pstmt = con.createStatement()){
	        try (ResultSet resultSet = pstmt.executeQuery("select  * from NEWS_CRITERIA where length(country)<2 ORDER BY CREATED_AT DESC LIMIT 1,1;")) {//return second to last row from db
	        	
	        	//Stores properties of a ResultSet object, including column count
	        	ResultSetMetaData rsmd = resultSet.getMetaData(); 
	        	int columnCount = rsmd.getColumnCount();
	        	
	        	ArrayList<String> ResultList = new ArrayList<>(columnCount); 
	        	while (resultSet.next()) {              
	        	   int i = 1;
	        	   while(i <= columnCount) {
	        	        ResultList.add(resultSet.getString(i++));
	        	   }
	        	}
	        	return   ResultList;
	        
	        }
	        }
	      }
	    }
	
	public static void addNews(String country,String category,String key,String lan,String src,String from,String to) throws SQLException{//add search criteria to a db
	    try (Connection con = DriverManager.getConnection(SQL_CONN_STRING, USER, PASS)) {
	        try(PreparedStatement pstmt = con.prepareStatement("INSERT INTO NEWS_CRITERIA(COUNTRY, CATEGORY , KEYWORD, LANG, DOMAINS, DATE_FROM,DATE_TO)"
	                + " VALUES(?, ?, ?, ?, ?, ?, ?)")){
	          pstmt.setString(1, country);
	          pstmt.setString(2, category);
	          pstmt.setString(3, key);
	          pstmt.setString(4, lan);
	          pstmt.setString(5, src);
	          pstmt.setString(6, from);
	          pstmt.setString(7, to);
	          pstmt.executeUpdate();
	        }
	      }
	    }



}
