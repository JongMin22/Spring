<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/userInfo" method="post">
유저 넘버:<input type="number" name="userNum" /><br/>
아이디 :<input type="text" name="userId" /><br/>
비밀번호 : <input type="password" name="userPw" /><br/>
유저 이름 :<input type="text" name="userName" /><br/>
나이 :<input type="number" name="userAge" /><br/>
<input type="submit" value="제출" />

</form>
</body>
</html>