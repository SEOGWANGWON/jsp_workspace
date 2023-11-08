<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	</head>
	<body>
		<h1>로그인</h1>
		<form action ="LoginServlet" method="post">
			<label for="memail"> 이메일 :</label>
			<input type="email" id="memail" name="memail" required><br>
			
			<label for = "mno"> 로그인할 비밀번호 넘버 : </label>
			<input type ="password" id="mno" name="mno"><br>
			
			<input type="submit" value="로그인">
		</form>
	</body>
</html>