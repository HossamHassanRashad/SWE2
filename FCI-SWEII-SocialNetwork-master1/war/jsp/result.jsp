<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/social/sendRequest" method="POST">
<p>${it.name}</p>
<input type="text" name="email" value="${it.email}"><br>
<input type="hidden" name="myemail" value="${it.myemail}">
<input type="submit" value="Add">
</form>
</body>
</html>