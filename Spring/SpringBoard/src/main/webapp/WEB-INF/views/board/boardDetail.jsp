<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#modDiv{
	width:300px;
	height:100px;
	background-color:pink;
	position:absolute;
	top:50%;
	left:50%;
	margin-top:-50px;
	margin-left:-150px;
	padding:10px;
	z-index:1000;
	}
	
	.uploadResult{
		width:100$;
		background-color : gray;
	}
	.uploadResult ul{
		display: flex;
		flex-flow:row;
		justify-content: center;
		align-items: center;
	}
	.uploadResult ul li {
		list-style: none;
		padding: 10px;
		align-content: center;
		text-align: center;
		
	}
	.uploadResult ul li img{
		width: 100px;
		
	}
</style>
</head>
<body>
<div class="container">
<h1 class="text text-primary">${board.bno }번 글 상세페이지</h1>
<div class="row">
	<h3 class="text-primary">첨부파일</h3>
	<div id="uploadResult">
		<ul>
		<!--  첨부파일이 들어갈 위치 -->
		</ul>
	</div>
</div>
<div class="row">
<div class="col-md-9">
	<input type="text" class="form-control" value="제목 : ${board.title }" readonly/>
</div>
<div class="col-md-3">
	<input type="text" class="form-control" value="글쓴이 : ${board.writer }" readonly/>
</div>
<textarea rows="10" class="form-control" readonly>${board.content }</textarea>
<div class="row">
<div class="col-md-3">쓴날짜 : </div>
<div class="col-md-3">${board.regdate } </div>
<div class="col-md-3">수정날짜 :  </div>
<div class="col-md-3">${board.updatedate } </div>
<div class="col-md-6">
	<form action="/board/boardUpdateForm" method="post">
	<input type="hidden" name="bno" value="${board.bno }"/>
	<input type="hidden" name="pageNum" value="${param.pageNum}"/>
	<input type="hidden" name="searchType" value="${param.searchType}"/>
	<input type="hidden" name="keyword" value="${param.keyword}"/>
	<input type="submit" class="form-control" value="수정 "/>
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
	</form>
</div>
<div class="col-md-6">
	<form action="/board/boardDelete" method="post">
	<input type="hidden" name="bno" value="${board.bno }"/>
	<input type="hidden" name="pageNum" value="${param.pageNum}"/>
	<input type="hidden" name="searchType" value="${param.searchType}"/>
	<input type="hidden" name="keyword" value="${param.keyword}"/>
	<input type="submit" class="form-control" value="삭제 "/>
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
	</form>
	</div>

</div>
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
<a href="/board/boardList?pageNum=${param.pageNum == null ? 1 : param.pageNum}&searchType=${param.searchType}&keyword=${param.keyword}" class="btn btn-success">글목록</a>
<ul id="replies">
</ul>
<div class="row box-box-success">
<div class="box-header">
<h2 class="text-primary">댓글 작성</h2>
</div>
<div class="box-body">
<strong>Writer</strong>
<input type="text" placeholder="글쓴이" id="newReplyWriter"/><br/>
<strong>ReplyText</strong>
<input type="text" placeholder="댓글" id="newReply"/>
</div>
<div class="box-footer"> 
	<button type="button" class="btn btn-success" id="replyAddBtn">Add Reply</button>
</div>
</div>
	
	<!-- 여기부터 댓글 비동기처리 자바스크립트 영역-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
	var csrfHeaderName ="${_csrf.headerName}";
	var csrfTokenValue ="${_csrf.token}";
	var bno =${board.bno};
	
	(function(){
		$.getJSON("/board/getAttachList",{bno : bno}, function(arr){
			console.log(arr);
			
			var str ="";
			
			$(arr).each(function(i, obj){
				
				if(!obj.fileType){
					var fileCallPath = encodeURIComponent(obj.uploadPath+"/" + obj.uuid + "_" + obj.fileName);
					str += "<li "
						+ "data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid
						+ "' data-filename='" + obj.fileName + "' data-type='" + obj.fileType
						+ "'><a href='/download?fileName=" + fileCallPath
						+ "'>" + "<img src='/resources/attach.png'>"
						+ obj.fileName + "</a>"
						+ "<span data-file=\'" + fileCallPath + "\' data-type='file'> X </span>"
						+ "</li>";
					
				} else {
					// str += "<li>" + obj.fileName + "</li>";
					// 수정코드 
					var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_" + obj.uuid + "_" + obj.fileName);
					var fileCallPathOriginal = encodeURIComponent(obj.uploadPath+"/" + obj.uuid + "_" + obj.fileName);
					console.log("fileCallPath : " + fileCallPath);
					str += "<li "
						+ "data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid
						+ "' data-filename='" + obj.fileName + "' data-type='" + obj.fileType
						+ "'><a href='/download?fileName=" + fileCallPathOriginal
						+ "'>" + "<img src='/display?fileName="+ fileCallPath + "'>"
						+ obj.fileName + "</a>"
						+ "<span data-file=\'" + fileCallPath + "\' data-type='image'> X </span>"
						+ "</li>";
				}
			});
			$("#uploadResult ul").html(str);
		}); // end getJSON 
	})();	// end annonymoust
	function getAllList(){
		$.getJSON("/replies/all/" + bno , function(data){
			let str="";
			
			console.log(data.length);
			// $(JSON데이터).each => 내부 JSON을 향상된 for문 형식으로 처리
			// 역시 내부에 콜백함수(로직이 실행되었을때 추가로 실행할 구문을 정의하기위해 파라미터로 넣는 함수 )
			// 를 이용해 data를	 하나하나 향상된 for문형식으로 처리할때 실행구문을 적을 수 있습니다.
			$(data).each(
				function(){
					var timestamp = this.updateDate;
					var date = new Date(timestamp);
					var formattedTime = "게시일 : " + date.getFullYear()
					+"/" + (date.getMonth()+1)
					+"/" + date.getDate()
					// 시분초도 추가해보세요.
					+"/" + date.getHours()+"시"
					+"/" + date.getMinutes() +"분"
					+"/" + date.getSeconds() +"초" ; 
					// 하나하나 반복되는 데이터는 this라는 키워드로 표현됩니다..
					str += "<div class='replyLi' data-rno='" + this.rno +"'><strong>@"
					+ this.replyer + "</strong> - " + formattedTime + "<br>"
					+ "<div class='reply'>" + this.reply + "</div>"
					+ "<button type='button' class='btn btn-info'>수정/삭제</button>"
					+"</div>";
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
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
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
		
		// 4월 27일 수정 : this(button)의 부모(.replyLi)가 아닌 
		// 형제 태그 .reply의 내용을 대신 가져올수 있도록
		// 변수 replyContent를 선언해 거기에 저장해주세오.
		// (hint : .sibling("요소명"); 으로 형제태그를 가져올수있습니다.)
		var replyContent = $(this).siblings(".reply").text();
		// var replyContent = $(this).prev().text() button 직전 태그인 .reply의 내용물 가져오기;
		// var replyContent = $(this).parent().children(".reply").text();
		var replytag = $(this).parent();
		console.log(replytag);
		
		// 클릭한 버튼과 연계된 li태그의 data-rno에 든 값을 가져와 변수 rno에 저장.
		var rno = replytag.attr("data-rno");
		console.log(rno);
		
		//rno뿐만 아니라 본문도 가져와야함.
		// var reply = replytag.text(); // 내부 텍스트를 가져옴.
		$(".modal-title").html(rno);
		$("#reply").val(replyContent);
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
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
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
			beforeSend : function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
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

</div>
</div>
</div>
</body>
</html>