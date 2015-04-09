<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> message with ${it.email} </h1><br>
<h3> ${it.msg} </h3>
<form action="/social/sendMsg" method="POST">
<input type="hidden" name="myemail" value="${it.myemail}">
<input type="hidden" name="email" value="${it.email}">
<input type="text" name="msg">
<input type="submit" value="send">
</form>
<form action="/social/showAllMsg" method="POST">
<input type="hidden" name="myemail" value="${it.myemail}">
<input type="hidden" name="email" value="${it.email}">
<input type="submit" value="showAllMsg">
</form>
<form action="/social/showNewMsg" method="POST">
<input type="hidden" name="myemail" value="${it.myemail}">
<input type="hidden" name="email" value="${it.email}">
<input type="submit" value="showMesseges">
</form>
<form action="/social/home" method="POST">
<input type="submit" value="home">
</form>
</body>
</html>