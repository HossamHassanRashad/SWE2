<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<form action="/social/signout" method="POST">
<p> Welcome b2a ya ${it.name} </p>
<input type="hidden" name="myemail" value="${it.email}">
<p> This is should be user home page </p>
<p> Current implemented services "http://fci-swe-apps.appspot.com/rest/RegistrationService --- {requires: uname, email, password}" </p>
<p> and "http://fci-swe-apps.appspot.com/rest/LoginService --- {requires: uname,  password}" </p>
<p> you should implement sendFriendRequest service and addFriend service <br>
<input type="submit" value="Logout">
</form>
<form action="/social/accept" method="POST">
<P> Search: <input type="TEXT" name="searchName"> <input type="submit" value="Go">
<input type="hidden" name="myemail" value="${it.email}">
</form>
<form action="/social/checkRequest" method="POST">
<input type="submit" value="check friend requests">
<input type="hidden" name="myemail" value="${it.email}">
</form>
</body>
</html>