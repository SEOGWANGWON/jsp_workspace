package com.kh.imageUpload;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig

/*
@MultipartConfig(	// 파일을 올리기 전에 파일 값을 설정하는 공간 
	fileSizeThreshold = 1024 * 1024, // 파일 데이터를 디스크에 기록하기 시작하기 전에 메모리에 보유되는 최대크기(1MB)
	maxFileSize = 1024 * 1024 * 5, // 업로드할 파일의 최대 크기 (이것은 5MB)
	maxRequestSize = 1024 * 1024 * 10, // 요청 데이터의 최대 크기
	location = "/tmp"	// 파일이 디스크에 저장될 임시 디렉터리  
)
*/
public class UploadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUser = "subject";
		String jdbcPW = "subject";
		
		Connection conn = null;
		
		/*
		 * 	title VARCHAR2(100),
		    content VARCHAR2(200), 
		    image BLOB,
		    사용자가 요청한 폼 데이터를 처리하는데 사용하는 문장
		    jsp title 파라미터를 가지고와서 title 문자열 변수에 저장
		    폼에서 입력된 제목을 나타냄
		    String title =  request.getParameter("title");
		 */
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part imagePart = request.getPart("image");
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPW);
			
			String sql = "INSERT INTO Board (board_id, title, content, image, create_at, author)"
					+ "VALUES(board_sequence.nextval, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setBinaryStream(3, imagePart.getInputStream(),(int) imagePart.getSize()); 
																//getSize 는 기본적으로 long 타입인데 int로 줄여서 쓴다 (long으로 써야하면 그대로)
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			ps.setString(5, "author name");
			
			ps.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("imageList.jsp");
		
		
		
		
		
	}

}
