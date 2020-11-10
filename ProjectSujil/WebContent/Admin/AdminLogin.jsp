<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="AdminLoginServlet" method = "get">
<h1>관리자 전용</h1>
<span>ID: </span><input type="text" name ="userid" id="userid"><br>
<span>PW: </span><input type="text" name ="passwd" id="">
<button type="submit">로그인</button>
</form>
</body>
</html>