<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
you have a friend request from <input type = "text" name= "sender" value="${it.email}">
<form action="/social/acceptRequest" method="POST">
<input type="Hidden" name="myemail" value="${it.myemail}">
<input type="submit" value="accept">
</form>
<form action="" method="POST">
<input type="submit" value="reject">
</form>
</body>
</html>