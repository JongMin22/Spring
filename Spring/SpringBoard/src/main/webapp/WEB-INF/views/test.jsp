<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Ajax 테스트</h2>
<ul id="replies">.
</ul>
<ul id="test">
<!--  #test 사이에 daum.net으로 이동하는 a태그를 jquery를 이용해 넣어주세요.-->
</ul>
<button id="testBtn">테스트</button>

<!-- jquery는 이곳에 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
let bno=28;
		// 주소                         // 콜백함수 주소 요청으로 들어온 JSON을 어떻게 처리할지
		function getAllList(){
$.getJSON("/replies/all/" + bno , function(data){
	let str="";
	
	console.log(data.length);
	// $(JSON데이터).each => 내부 JSON을 향상된 for문 형식으로 처리
	// 역시 내부에 콜백함수(로직이 실행되었을때 추가로 실행할 구문을 정의하기위해 파라미터로 넣는 함수 )
	// 를 이용해 data를	 하나하나 향상된 for문형식으로 처리할때 실행구문을 적을 수 있습니다.
	$(data).each(
		function(){
			// 하나하나 반복되는 데이터는 this라는 키워드로 표현됩니다..
			str += "<li data-rno='" + this.rno +  "' class='replyLi'>" + this.rno + ":" + this.reply +"</li>";
		});
	$("#replies").html(str);
	
	});
		}
		getAllList();
		// 버튼(testBtn)클릭시 발동되는 이벤트 
		// testBtn클릭시   아래코드실행(36~39라인)
	$("#testBtn").on("click",function(){
		let str1="<a href='https://www.daum.net/'>다음</a>";
		$("#test").html(str1);
	})
		
</script>
</body>
</html>