package com.jihoon.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.jihoon.mysite.vo.UserVo;

@Repository
public class UserDao {
	public void update(UserVo userVo) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?sslMode=DISABLED&characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. 쿼리 준비
			String sql = "UPDATE user SET name=?, email=?, password=?, gender=?  WHERE no=? ";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getEmail());
			pstmt.setString(3, userVo.getPassword());
			pstmt.setString(4, userVo.getGender());
			pstmt.setLong(5, userVo.getNo());

			// 5. sql 실행
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 에러" + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		}

	}
	public UserVo getByNo(long no)	{
		UserVo userVo = new UserVo();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?sslMode=DISABLED&characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. 쿼리 준비
			String sql = "select no, name, email, password, gender from user where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 결과 받아오기
			while (rs.next()) {
				long no1 = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				String gender = rs.getString(5);

				userVo.setNo(no1);
				userVo.setName(name);
				userVo.setEmail(email);;
				userVo.setPassword(password);;
				userVo.setGender(gender);;

			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);

		}

		return userVo;


	}
	
	
	
	
	public void join(UserVo userVo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
		
			//2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?sslMode=DISABLED&characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. 쿼리 준비
			String sql = "insert into user values(null, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
		
			//4. 바인딩
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getEmail());
			pstmt.setString(3, userVo.getPassword());
			pstmt.setString(4, userVo.getGender());
			
			//5. sql 실행
			pstmt.executeUpdate();

		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 에러" + e);
		} catch(SQLException e) {
			System.out.println("error" + e);
		}
	}
	public UserVo login(UserVo userVo) {
		
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
		
			//2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?sslMode=DISABLED&characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. 쿼리 준비
			String sql = "select no, name from user where email= ? and password =?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userVo.getEmail());
			pstmt.setString(2, userVo.getPassword());
			
		
			//4. sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
				
			}
			
			

		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 에러" + e);
		} catch(SQLException e) {
			System.out.println("error" + e);
		}
		
		return result;

	}
	
}
