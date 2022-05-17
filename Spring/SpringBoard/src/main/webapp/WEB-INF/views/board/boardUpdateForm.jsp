<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1 class="text text-primary">${board.bno }번 글 수정페이지</h1>
<form action="/board/boardUpdate" method="get">
<div class="row">

<div class="col-md-9">
	
	<input type="text" class="form-control" name="title" value="${board.title }"/>
</div>
<div class="col-md-3">
	<input type="text" class="form-control" name="writer" value="${board.writer }"/>
</div>
<textarea rows="10" class="form-control" name="content">${board.content }</textarea>


<input type="hidden" name="bno" value="${board.bno }"/>
<input type="hidden" name="pageNum" value="${param.pageNum}"/>
<input type="hidden" name="searchType" value="${param.searchType}"/>
<input type="hidden" name="keyword" value="${param.keyword}"/>
<div class="col-md-6">
<input type="submit" class="form-control" value="수정"/>
</div>
<div class="col-md-6">
<input type="reset" class="form-control" value="초기화"/>
</div>
</div>
</form>

</div>

</body>
</html>