<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>member �ּ�</h1>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
		<a href="/secu/admin">������������</a>
	</sec:authorize>
	<br/>
		<sec:authorize access="isAuthenticated()">
		<a href="/customLogout">�α׾ƿ�</a>
	</sec:authorize>


</body>
</html>