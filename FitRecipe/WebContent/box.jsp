<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>

<!DOCTYPE html>
<html>
 <% 
   	String userID = null;
   	if(session.getAttribute("userID")!= null){
   		userID = (String) session.getAttribute("userID");
   	}
   	
   	if (userID == null){
   		session.setAttribute("messageType", "오류 메세지");
   		session.setAttribute("messageContent", "현재 로그인이 되어있지 않습니다.");
   		response.sendRedirect("index.jsp");
   		return;
   	}
   %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width , initial-scale=1">
<title>유저 채팅</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">

	function getUnread(){
		
		$.ajax({
			type:"POST",
			url: "./chatUnread",
			data: {
				
				userID : encodeURIComponent('<%=userID%>'),
			},
			success: function(result){
				if(result >= 1){
					showUnread(result);
				}else{
					showUnread('');
				}
			}
			
		});
	}
	function getInfiniteUnread(){
		setInterval(function(){
			getUnread();
		}, 4000);
	}
	function showUnread(result){
		
		$('#unread').html(result);
	}
	
	function chatBoxFunction(){
		var userID = '<%= userID %>'
		
		$.ajax({
			type:"POST",
			url: "./chatBox",
			data: {
				
				userID : encodeURIComponent(userID),
			},
			success: function(data){
		        
				if(data =="") return;
				$('#boxTable').html(''); //일단 공백으로 초기화
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for(var i = 0; i < result.length; i++){
					
					if(result[i][0].value == userID){	  //내가 보냇을떄 상대방의 아이디가 보이게 해줌
						result[i][0].value =result[i][1].value;
					}else{
					result[i][1].value = result[i][0].value;	
					}
					addBox(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value, result[i][4].value);
				//각각의 메세지를 실제 출력해주는
				}
			}
			
		});
	}
	function addBox(lastID , toID, chatContent, chatTime, unread){
		$('#boxTable').append('<tr onclick="location.href=\'chat.jsp?toID='+encodeURIComponent(toID) +'\'">'+
		'<td style="width:150 px;"> <h5>' +lastID +'</h5></td>'+
		'<td>'+
		'<h5>'+ chatContent+
		'<span class="label label-info">'+ unread +'</span></h5>' +
		'<div class="pull-right">'+chatTime+'</div>' +
		'</td>'+
				
		'</tr>');
	}
	
	function getInfiniteBox(){
		
		setInterval(function(){
			chatBoxFunction();
			
		},3000); 
	}
	
</script>

</head>
<body>
	
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">레시피</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="index.jsp">메인</a></li>
					


					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">가격대 별 메뉴<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="menuView.jsp">전체 메뉴보기</a></li>
							<li><a href="menu2000Won.jsp">2000원 이하 메뉴</a></li>
							<li><a href="menu4000Won.jsp">4000원 이하 메뉴</a></li>
							<li><a href="menu6000Won.jsp">6000원 이하 메뉴</a></li>
							<li><a href="menu8000Won.jsp">8000원 이하 메뉴</a></li>
							<li><a href="menu8001Won.jsp">그외 비싼 메뉴</a></li>
						</ul></li>

					<li><a href="searchMenu.jsp">이 재료로 뭐먹을 수 있을까?</a></li>
					<li><a href="itemWrite.jsp">재료 등록</a></li>
					<li><a href="find.jsp">친구 찾기</a></li>
					<li><a href="box.jsp">메세지함<span id="unread"
							class="label label-info"></span></a></li>
					<li><a href="boardView.jsp">자유 게시판</a></li>

				</ul>

				<%
					if (userID == null) {
						
				%>
				<ul class="nav navbar-nav navbar-right">


					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="login.jsp">로그인</a></li>
							<li><a href="join.jsp">회원가입</a></li>
						</ul></li>

				</ul>


				<%
					} else {
						String Profile = new UserDAO().getProfile(userID);
				%>

				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리 <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="update.jsp">회원 정보 수정</a></li>
							<li><a href="profileUpdate.jsp">프로필 수정</a></li>
							<li><a href="logoutAction.jsp">로그아웃</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><%=userID%> 회원님 환영합니다</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><img class="media-object img-circle"
						style="width: 30px; height: 30px; margin-top: 20px;"
						src="<%=Profile %>" alt=""></li>
				</ul>
				<%
					}
				%>

			</div>
		</div>
	</nav>
	
	
	<div class="container">
		<table class="table" style="margin: 0 auto;">
			<thead>
			
				<tr>
					<th><h4>주고받은 메세지 목록</h4></th>
				</tr>
			</thead>
			
			<div style="overflow-y: auto; width: 100%; max-height: 450px;">
				<table class="table table-bordered table-hover" style="text-align: center;border: 1px solid #dddddd; margin: 0 auto;">
					<tbody id="boxTable"> 
					</tbody>
				</table>
			</div>
					
		</table>
	</div>
	
	
	
	
	

	<%
		String messageContent = null;
		if(session.getAttribute("messageContent")!= null){
			messageContent = (String)session.getAttribute("messageContent");
		}
		String messageType = null;
		if(session.getAttribute("messageContent")!= null){
			messageType = (String)session.getAttribute("messageType");
		}
		if(messageContent != null){
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
		    <div class="modal-dialog vertical-align-center">
			   <div class="modal-content <%if(messageType.equals("오류메세지")) out.println("panel-warning"); else out.println	("panel-success"); %>">
		     	    <div class="modal-header panel-heading">
				      <button type="button" class="close" data-dismiss="modal">
				      	 	<span aria-hidden="true">&times</span>
					  		   <span class="sr-only">Close</span>
				 				     </button>
				 		   			    <h4 class="modal-title">
				   					   	<%=messageType %>
			       					 	</h4>
								   </div>
			      			    <div class="modal-body">
									<%=messageContent %>
								</div>
								<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
		     					</div>
						</div>
					</div>	
				</div>
		</div>
		<% 
			session.removeAttribute("messageContent");
			session.removeAttribute("messageType");
		}
	%>
	
	<script >
		$('#messageModal').modal("show");
	</script>
	<%
		if(userID != null){  //사용자가 성공적으로 로그인을 한 상태라면
	%>
	
	<script type="text/javascript">
		$(document).ready(function(){
			getUnread();
			getInfiniteUnread();
			chatBoxFunction();  //메세지함
			getInfiniteBox();  //메세지함 반복
		
		});
	</script>
	<%
		}
	%>
	
	
	
	
</body>
</html>