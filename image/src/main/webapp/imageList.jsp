<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>Image List</title>
	</head>
	<body>
		<h1>이미지 목록</h1>
		<h2>이미지 목록</h2>
		<p>이미지 내용 불러오기</p>
		<img alt="" src="ImageServlet?image_id=${image.boardId}">
	</body>
</html>