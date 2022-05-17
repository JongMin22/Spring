<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>해당 과목의 성적을 입력해주세요</h1>
<form action="/score" method="post">
수학 : <input type="number" name="math" min="0" max="100"/><br/>
영어 : <input type="number" name="eng" min="0" max="100"/><br/>
언어 : <input type="number" name="kor" min="0" max="100"/><br/>
사회 : <input type="number" name="social" min="0" max="100"/><br/>
컴퓨터 : <input type="number" name="computer" min="0" max="100"/><br/>
<input type="submit" value="제출"/>
</form>
</body>
</html>