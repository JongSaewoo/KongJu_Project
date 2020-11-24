<%@page import="item.ItemDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="menu.MenuDAO" %>
<%@ page import="menu.MenuDTO" %>
<%@ page import="item.ItemDTO" %>
<%@ page import="item.ItemDAO" %>
<%@ page import="component.ComponentDTO" %>
<%@ page import="component.ComponentDAO" %>
<%@ page import="user.UserDAO" %>


<!DOCTYPE html>
<html>

<style>  
/* 파폭, 엣지 전부다 Css 적용되는데 크롬만 css/custom.css못읽어 온다. 어쩔수 없이 긴급방안 크롬 내부적 버그인듯 */
.viewItem {
    position: relative;
    display: inline-block;
    
}
.viewItem:hover .viewText {
    visibility: visible;
}
.viewItem:hover  {
    
}
.viewItem >.viewText {
    visibility: hidden;
    width: 200px;
    background-color: black;
    color: #fff;
    text-align: center;
    padding: 5px 0;
    border-radius: 6px;
    top: -5px;
 	left: 105%;  
 	 
    position: absolute;
    z-index: 1;
}  
</style>
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
  	String menuNum =null;
  	if(request.getParameter("menuNum") != null){
  		menuNum = (String) request.getParameter("menuNum");
  	}
  	if(menuNum == null || menuNum.equals("")) {
		session.setAttribute("messageType", "오류 메세지");
   		session.setAttribute("messageContent", "게시물을 선택해 주세요");
   		response.sendRedirect("index.jsp");
   		return;
  	}
  	
  	
  	
  	
  	MenuDAO menuDAO = new MenuDAO();
  	MenuDTO menu = menuDAO.getMenu(menuNum); //해당 게시글 DTO만 가져오도록
  	ComponentDAO comDAO = new ComponentDAO();
	ComponentDTO comDTO = comDAO.getComponent(menu.getMenuName());
	ItemDAO itemDAO = new ItemDAO();
	
	
	
  	String item1="";
  	String item2="";
  	String item3="";
  	String item4="";
  	String item5="";
  	String item6="";
  	String item7="";
  	
  	String comment1="";
  	String comment2="";
  	String comment3="";
  	String comment4="";
  	String comment5="";
  	String comment6="";
  	String comment7="";
  	
  	
  	
  	if(comDTO.getComGram1()!=0){
  		item1 += comDTO.getComItem1() + "&nbsp;"+comDTO.getComGram1() +"그램";
        comment1 += itemDAO.getItemComment(comDTO.getComItem1()) +"<br>"+menuDAO.getMenuPrice(comDTO.getComItem1(), comDTO.getComGram1())  +"원<br>";
  	}
  	if(comDTO.getComGram2()!=0){
  		item2 += comDTO.getComItem2() + " "+comDTO.getComGram2() +"그램";
  		comment2 += itemDAO.getItemComment(comDTO.getComItem2()) +"<br>"+menuDAO.getMenuPrice(comDTO.getComItem2(), comDTO.getComGram2())  +"원<br>";
  	}
  	if(comDTO.getComGram3()!=0){
  		
  		item3 += comDTO.getComItem3() + " "+comDTO.getComGram3() +"그램";
  		comment3 += itemDAO.getItemComment(comDTO.getComItem3()) +"<br>"+menuDAO.getMenuPrice(comDTO.getComItem3(), comDTO.getComGram3())  +"원<br>";
  	}
  	if(comDTO.getComGram4()!=0){
  		item4 += comDTO.getComItem4() + " "+comDTO.getComGram4() +"그램";
  		comment4 += itemDAO.getItemComment(comDTO.getComItem4()) +"<br>"+menuDAO.getMenuPrice(comDTO.getComItem4(), comDTO.getComGram4())  +"원<br>";
  	}
  	if(comDTO.getComGram5()!=0){
  		item5 += comDTO.getComItem5() + " "+comDTO.getComGram5() +"그램";
  		comment5 += itemDAO.getItemComment(comDTO.getComItem5()) +"<br>"+menuDAO.getMenuPrice(comDTO.getComItem5(), comDTO.getComGram5())  +"원<br>";
  	}
  	if(comDTO.getComGram6()!=0){
  		item6 += comDTO.getComItem6() + " "+comDTO.getComGram6() +"그램";
  		comment6 += itemDAO.getItemComment(comDTO.getComItem6()) +"<br>"+menuDAO.getMenuPrice(comDTO.getComItem6(), comDTO.getComGram6())  +"원<br>";
  	}
  	if(comDTO.getComGram7()!=0){
  		item7 += comDTO.getComItem7() + " "+comDTO.getComGram7() +"그램";
  		comment7 += itemDAO.getItemComment(comDTO.getComItem7()) +"<br>"+menuDAO.getMenuPrice(comDTO.getComItem7(), comDTO.getComGram7())  +"원<br>";
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
		<table class="table table-bordered table-hover" style="text-align:center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="4"><h4>게시물 보기</h4></th>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5> 제목</h5></td>
					<td colspan="3"><h5><%=menu.getMenuName() %></h5></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5> 작성자</h5></td>
					<td colspan="3"><h5><%=menu.getUserID() %></h5></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>재료 목록</h5></td>
					
					<td>
					
						<div class="viewItem"><%=item1 %> 
						<span class="viewText"><%=comment1 %></span>						
						</div>
						<br>
						<div class="viewItem"><%=item2 %> 
						<span class="viewText"><%=comment2 %></span>
						</div>
						<br>
						<div class="viewItem"><%=item3 %> 
						<span class="viewText"><%=comment3 %></span>
						</div>
						<br>
						<div class="viewItem"><%=item4 %> 
						<span class="viewText"><%=comment4 %></span>
						</div>
						<br>
						<div class="viewItem"><%=item5 %> 
						<span class="viewText"><%=comment5 %></span>
						</div>
						<br>
						<div class="viewItem"><%=item6 %> 
						<span class="viewText"><%=comment6 %></span>
						</div>
						<br>
						<div class="viewItem"><%=item7 %> 
						<span class="viewText"><%=comment7 %></span>
						</div>
						<br>
						<div style="text-align:right;" >
						총 가격 <%=menu.getMenuPrice() %> 원 
						
						</div>
						
						
					
						

			
					</td>
					
				</tr>
				
				
				<tr>
					<td style="vertical-align:middle; min-height:150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>조리법</h5></td>
					<td colspan="3" style="text-align: left"><h5><%=menu.getMenuRecipe() %></h5></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>완성사진</h5></td>
					
					
					<td>
					
					<% if( menu.getMenuProfile().equals("") ){
					%>
					<h5>해당 이미지가 존재하지 않습니다.</h5>
					<% 
						
					}else{
					%>
					
					<img class="media-object img-circle" style="max-width:300px; max-height:180px; margin : 0 auto;"
					 src="<%= menuDAO.getProfile(menu.getMenuNum()+"") %>">
					 
					 </td>
					 <%
					 }
					 %>
				</tr>
			</thead>
			<tbody>
			
				<tr>
					<td colspan="5" style="text-algin:right">
					<a href="menuView.jsp" class="btn btn-primary" >목록</a>
						<%
							if(userID.equals(menu.getUserID())){
						%>
							<a href="menuUpdate.jsp?menuNum=<%=menu.getMenuNum() %>" class="btn btn-primary " >수정</a>
							<a href="menuDelete?menuNum=<%=menu.getMenuNum() %>" class="btn btn-primary " onclick = "return confirm('정말로 삭제하시겠습니까?')" >삭제</a>
						<% 		
								
							}
						%>
					</td>
				<tr>
			</tbody>
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
	




	<script >
		$('#messageModal').modal("show");
	</script>
	
	<% 
			session.removeAttribute("messageContent");
			session.removeAttribute("messageType");
		}
	%>
	
	<%
		if(userID != null){  //사용자가 성공적으로 로그인을 한 상태라면
	%>
		<script type="text/javascript">
			$(document).ready(function(){
			getUnread();
			getInfiniteUnread();	
		
			});
			</script>
	<%
			}
	%>
	
	
	
	
</body>
</html>