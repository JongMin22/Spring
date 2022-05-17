<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<ul id="replies">

	</ul>
	<button id="requestBtn">댓글 로딩해오기</button>
	
	<!-- jquery cdn -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!--  script태그에 requestBtn에대한 onClick이벤트를 걸어주세요. 
	onClick시 $getJson을 이용해 댓글 데이터를 요청한다음 글번호는 test.jsp에 설정해둔 글번호를
	그대로 쓰세요. 얻오은 데이터를 이용해 #replies 내부에 댓글을 집어넣도록 해주세요.-->
	<script type="text/javascript">
	$("#requestBtn").on("click",function(){
	let bno=28;
	function getAllList(){
	$.getJSON("/replies/all/" + bno , function(data){
	let str="";
	console.log(data.length);
	$(data).each(
	function(){
	str += "<li data-rno='" + this.rno +  "' class='replyLi'>" + this.rno + ":" + this.reply +"</li>";
	});
	$("#replies").html(str);
	});
	}
	getAllList();
	})
	
	</script>
</body>
</html>