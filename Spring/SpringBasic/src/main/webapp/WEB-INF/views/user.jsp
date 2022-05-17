<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${userVO.userNum}번 회원 정보 입니다.</h1>
<h2>아이디 : ${userVO.userId }</h2>
<h2>비밀번호 : ${userVO.userPw }</h2>
<h2>이름 : ${userVO.userName }</h2>
<h2>나이 : ${userVO.userAge }</h2>
</body>
</html>