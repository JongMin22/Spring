<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#modDiv{
	width:300px;
	height:100px;
	background-color:aqua;
	position:absolute;
	top:50%;
	left:50%;
	margin-top:-50px;
	margin-left:-150px;
	padding:10px;
	z-index:1000;
	}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
<h2>Ajax 댓글 등록 테스트</h2>
<!-- 댓글이 추가될 공간 -->
<ul id="replies">
</ul>
<!-- modal은 일종의 팝업입니다. 
	단, 새 창을 띄우지는 않고 CSS를 이용해 특정 태그가 조건부로 보이거나 안 보이도록 처리해서 
	마치 창이 새로 띄워지는것처럼 만듭니다. 
	따라서 눈에 보이지는 않아도 modal을 구성하는 태그 자체는 화면에 미리 적혀있어야 합니다.-->
	<div id="modDiv" style="display:none;">
	<div class="modal-title"></div>
	<div>
		<input type="text" id="reply"/>
	</div>
	<div>
		<button type="button" id="replyModBtn">수정</button>
		<button type="button" id="replyDelBtn">삭제</button>
		<button type="button" id="closeBtn">닫기</button>
	</div>
	</div>
<div>
<div>
	댓글 작성자<input type="text" name="replyer" id="newReplyWriter"/>
</div>
<div>
	댓글 내용<input type="text" name="reply" id="newReply"/>
</div>
	<button id="replyAddBtn">댓글 추가</button>
</div>
<!-- 위임 이해를 위한 코드(삭제예정) -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	var bno = 28;
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
					str += "<li data-rno='" + this.rno +  "' class='replyLi'>" + this.rno + ":" + this.reply 
					+"<button>수정/삭제</button></li>";
				});
			$("#replies").html(str);
			
			});
				}
	getAllList();
	$("#replyAddBtn").on("click", function(){
		var replyer = $("#newReplyWriter").val();
		var reply = $("#newReply").val();
		
		$.ajax({
			type : 'post',
			url: '/replies',
			headers:{
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				reply : reply
			}),
			success : function(result){
				if(result == 'SUCCESS'){
					alert("등록 되었습니다.");
					getAllList();
					$("#newReplyWriter").val(null);
					$("#newReply").val(null);
				}
			}
			});
		
	});
	

	$("#replies").on("click",".replyLi button", function(){
		// 클릭한 요소가 this이고, 현재 button에 걸렸기 때문에
		// this는 button입니다. button의 부모가 바로 .replyLi 입니다.
		// 즉, 클릭한 버튼과 연계된 li태그를 replytag 변수에 저장합니다.
		var replytag = $(this).parent();
		console.log(replytag);
		
		// 클릭한 버튼과 연계된 li태그의 data-rno에 든 값을 가져와 변수 rno에 저장.
		var rno = replytag.attr("data-rno");
		console.log(rno);
		
		//rno뿐만 아니라 본문도 가져와야함.
		var reply = replytag.text(); // 내부 텍스트를 가져옴.
		$(".modal-title").html(rno);
		$("#reply").val(reply);
		$("#modDiv").show("slow");
		
		// alert(rno + " : " + reply);
	});
	
	$("#closeBtn").on("click",function(){ // #closeBtn 클릭시
		$("#modDiv").hide("slow"); // #modDiv를 닫습니다.
	});
	
	$("#replyDelBtn").on("click",function(){
		var rno = $(".modal-title").html();
		
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			header : {
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',
			success : function(result){
				console.log("result : " + result );
				if(result=='SUCCESS'){
					alert("삭제 되었습니다.");
					$("#modDiv").hide("slow");
					getAllList();
				}
			}
		})
	});
	
	$("#replyModBtn").on("click", function(){
		var rno =$(".modal-title").html();
		var reply = $("#reply").val();
		
		$.ajax({
			type:'patch',
			url : '/replies/' + rno,
			header :{
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PATCH"
			},
			contentType :"application/json",
			data: JSON.stringify({reply:reply}),
			dataType : 'text',
			success : function(result){
				console.log("result: " + result);
				if(result =='SUCCESS'){
					alert("수정 되었습니다.");
					$("#modDiv").hide("slow");
					getAllList();
				}
			}
		})
	});
	
	


	
	
	
</script>
</body>
</html>