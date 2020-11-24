<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="menu.MenuDTO"%>
<%@ page import="menu.MenuDAO"%>
<%@ page import="component.ComponentDTO"%>
<%@ page import="component.ComponentDAO"%>
<%@ page import="user.UserDAO"%>

<!DOCTYPE html>
<html>
<%
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}

	if (userID == null) { //비로그인시 접근 불가 에어리어
		session.setAttribute("messageType", "오류 메세지");
		session.setAttribute("messageContent", "현재 로그인이 되어있지 않습니다.");
		response.sendRedirect("index.jsp");
		return;
	}

	UserDTO user = new UserDAO().getUser(userID);
	String menuNum = request.getParameter("menuNum");
	if (menuNum == null || menuNum.equals("")) {
		session.setAttribute("messageType", "오류 메세지");
		session.setAttribute("messageContent", "접근할 수 없습니다. (없는 게시글)");
		response.sendRedirect("index.jsp");
		return;
	}
	MenuDAO menuDAO = new MenuDAO();
	MenuDTO menu = menuDAO.getMenu(menuNum);

	if (!userID.equals(menu.getUserID())) { //유저와 글쓴이가 다른 경우
		session.setAttribute("messageType", "오류 메세지");
		session.setAttribute("messageContent", "접근할 수 없습니다. (게시글 작성자가 아님)");
		response.sendRedirect("menuView.jsp");
		return;
	}

	ComponentDAO comDAO = new ComponentDAO();
	ComponentDTO comDTO = comDAO.getComponent(menu.getMenuName());

	String com1 = "";
	String com2 = "";
	String com3 = "";
	String com4 = "";
	String com5 = "";
	String com6 = "";
	String com7 = "";
	int item1 = 0;
	int item2 = 0;
	int item3 = 0;
	int item4 = 0;
	int item5 = 0;
	int item6 = 0;
	int item7 = 0;

	if (comDTO.getComItem1() == null || comDTO.getComItem1().equals("")) {
		com1 = "";
	} else {
		com1 = comDTO.getComItem1();
	}

	if (comDTO.getComItem2() == null || comDTO.getComItem2().equals("")) {
		com2 = "";
	} else {
		com2 = comDTO.getComItem2();
	}

	if (comDTO.getComItem3() == null || comDTO.getComItem3().equals("")) {
		com3 = "";
	} else {
		com3 = comDTO.getComItem3();
	}

	if (comDTO.getComItem4() == null || comDTO.getComItem4().equals("")) {
		com4 = "";
	} else {
		com4 = comDTO.getComItem4();
	}

	if (comDTO.getComItem5() == null || comDTO.getComItem5().equals("")) {
		com5 = "";
	} else {
		com5 = comDTO.getComItem5();
	}

	if (comDTO.getComItem6() == null || comDTO.getComItem6().equals("")) {
		com6 = "";
	} else {
		com6 = comDTO.getComItem6();
	}

	if (comDTO.getComItem7() == null || comDTO.getComItem7().equals("")) {
		com7 = "";
	} else {
		com7 = comDTO.getComItem7();
	}

	if (comDTO.getComGram1() != 0) {
		item1 = comDTO.getComGram1();
	}
	if (comDTO.getComGram2() != 0) {
		item2 = comDTO.getComGram2();
	}
	if (comDTO.getComGram3() != 0) {
		item3 = comDTO.getComGram3();
	}
	if (comDTO.getComGram4() != 0) {
		item4 = comDTO.getComGram4();
	}
	if (comDTO.getComGram5() != 0) {
		item5 = comDTO.getComGram5();
	}
	if (comDTO.getComGram6() != 0) {
		item6 = comDTO.getComGram6();
	}
	if (comDTO.getComGram7() != 0) {
		item7 = comDTO.getComGram7();
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

	
	


		function ItemCheckFunction1(){
			var userID = $('#comItem1').val();
			$.ajax({
				type: 'POST',
				url: './ItemFindServlet',
				data: {userID: userID},
				success: function(result){
					
					if(result==-1){
						alert("재료 1 빈칸을 채워주세요");	
					}
					else{
					 alert(result);
					}

				}
				
			});
		}
			
		function ItemCheckFunction2(){
			var userID = $('#comItem2').val();
			$.ajax({
				type: 'POST',
				url: './ItemFindServlet',
				data: {userID: userID},
				success: function(result){
					
					if(result==-1){
						alert("재료 2 빈칸을 채워주세요");	
					}
					else{
					 alert(result);
					}

				}
				
			});
		}	
			
		function ItemCheckFunction3(){
			var userID = $('#comItem3').val();
			$.ajax({
				type: 'POST',
				url: './ItemFindServlet',
				data: {userID: userID},
				success: function(result){
					
					if(result==-1){
						alert("재료 3 빈칸을 채워주세요");	
					}
					else{
					 alert(result);
					}

				}
				
			});
		}	
		
		function ItemCheckFunction4(){
			var userID = $('#comItem4').val();
			$.ajax({
				type: 'POST',
				url: './ItemFindServlet',
				data: {userID: userID},
				success: function(result){
					
					if(result==-1){
						alert("재료 4 빈칸을 채워주세요");	
					}
					else{
					 alert(result);
					}

				}
				
			});
		}
		
		function ItemCheckFunction5(){
			var userID = $('#comItem5').val();
			$.ajax({
				type: 'POST',
				url: './ItemFindServlet',
				data: {userID: userID},
				success: function(result){
					
					if(result==-1){
						alert("재료 5 빈칸을 채워주세요");	
					}
					else{
					 alert(result);
					}

				}
				
			});
		}
		
		function ItemCheckFunction6(){
			var userID = $('#comItem6').val();
			$.ajax({
				type: 'POST',
				url: './ItemFindServlet',
				data: {userID: userID},
				success: function(result){
					
					if(result==-1){
						alert("재료 6 빈칸을 채워주세요");	
					}
					else{
					 alert(result);
					}

				}
				
			});
		}
		
		function ItemCheckFunction7(){
			var userID = $('#comItem7').val();
			$.ajax({
				type: 'POST',
				url: './ItemFindServlet',
				data: {userID: userID},
				success: function(result){
					
					if(result==-1){
						alert("재료 7빈칸을 채워주세요");	
					}
					else{
					 alert(result);
					}

				}
				
			});
		}
		
		function getUnread(){
			
			$.ajax({
				type:"POST",
				url: "./chatUnread",
				data: {
					
					userID : encodeURIComponent('<%=userID%>'),
			},
			success : function(result) {
				if (result >= 1) {
					showUnread(result);
				} else {
					showUnread('');
				}
			}

		});
	}
	function getInfiniteUnread() {
		setInterval(function() {
			getUnread();
		}, 4000);
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
						src="<%=Profile%>" alt=""></li>
				</ul>
				<%
					}
				%>

			</div>
		</div>
	</nav>




	<div class="container">
		<form method="post" action="./menuUpdate"
			enctype="multipart/form-data">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd">

				<thead>
					<tr>
						<th colspan="6"><h3>조리법 수정 양식</h3></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2"><h5>아이디</h5></td>
						<td colspan="4"><h5><%=user.getUserID()%></h5> <input
							type="hidden" name="userID" value="<%=user.getUserID()%>">
							<input type="hidden" name="menuNum" value="<%=menuNum%>"></td>
					</tr>

					<tr>
						<td colspan="2"><h5>글 제목</h5></td>
						<td colspan="4"><input class="form-control" type="text"
							maxlength="50" name="menuName" placeholder="메뉴이름"
							value="<%=menu.getMenuName()%>"></td>
					</tr>

					<tr>
						<td colspan="2"><h5>레시피 내용</h5></td>
						<td colspan="4"><textarea class="form-control" rows="10"
								name="menuRecipe" maxlength="2048" placeholder="메뉴 내용"> <%=menu.getMenuRecipe()%></textarea></td>
					</tr>
					<tr>
						<td colspan="2"><h5>재료 기재 사항</h5></td>
						<td colspan="4">한 큰술 : 약 15gram</td>
					</tr>

					<tr>
						<td colspan="2"><h5>재료 1 정보</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="comItem1" maxlength="20" name="comItem1" placeholder="재료1 이름"
							value="<%=com1%>"></td>
						<td colspan="1"><input class="form-control" type="text"
							maxlength="20" name="comGram1" placeholder="재료1 양(gram)"
							value="<%=item1%>"></td>
						<td colspan="1">
							<button class="btn btn-primary" onclick="ItemCheckFunction1();"
								type="button">재료 체크</button>
						</td>
					<tr>
					<tr>
						<td colspan="2"><h5>재료 2 정보</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="comItem2" maxlength="20" name="comItem2" placeholder="재료2 이름"
							value="<%=com2%>"></td>
						<td colspan="1"><input class="form-control" type="text"
							maxlength="20" name="comGram2" placeholder="재료2 양(gram)"
							value="<%=item2%>"></td>
						<td colspan="1">
							<button class="btn btn-primary" onclick="ItemCheckFunction2();"
								type="button">재료 체크</button>
						</td>
					<tr>
					<tr>
						<td colspan="2"><h5>재료 3 정보</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="comItem3" maxlength="20" name="comItem3" placeholder="재료3 이름"
							value="<%=com3%>"></td>
						<td colspan="1"><input class="form-control" type="text"
							maxlength="20" name="comGram3" placeholder="재료3 양(gram)"
							value="<%=item3%>"></td>
						<td colspan="1">
							<button class="btn btn-primary" onclick="ItemCheckFunction3();"
								type="button">재료 체크</button>
						</td>
					<tr>
					<tr>
						<td colspan="2"><h5>재료 4 정보</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="comItem4" maxlength="20" name="comItem4" placeholder="재료4 이름"
							value="<%=com4%>"></td>
						<td colspan="1"><input class="form-control" type="text"
							maxlength="20" name="comGram4" placeholder="재료4 양(gram)"
							value="<%=item4%>"></td>
						<td colspan="1">
							<button class="btn btn-primary" onclick="ItemCheckFunction4();"
								type="button">재료 체크</button>
						</td>
					<tr>
					<tr>
						<td colspan="2"><h5>재료 5 정보</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="comItem5" maxlength="20" name="comItem5" placeholder="재료5 이름"
							value="<%=com5%>"></td>
						<td colspan="1"><input class="form-control" type="text"
							maxlength="20" name="comGram5" placeholder="재료5 양(gram)"
							value="<%=item5%>"></td>
						<td colspan="1">
							<button class="btn btn-primary" onclick="ItemCheckFunction5();"
								type="button">재료 체크</button>
						</td>
					<tr>
					<tr>
						<td colspan="2"><h5>재료 6 정보</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="comItem6" maxlength="20" name="comItem6" placeholder="재료6 이름"
							value="<%=com6%>"></td>
						<td colspan="1"><input class="form-control" type="text"
							maxlength="20" name="comGram6" placeholder="재료6 양(gram)"
							value="<%=item6%>"></td>
						<td colspan="1">
							<button class="btn btn-primary" onclick="ItemCheckFunction6();"
								type="button">재료 체크</button>
						</td>
					<tr>
					<tr>
						<td colspan="2"><h5>재료 7 정보</h5></td>
						<td colspan="2"><input class="form-control" type="text"
							id="comItem7" maxlength="20" name="comItem7" placeholder="재료7 이름"
							value="<%=com7%>"></td>
						<td colspan="1"><input class="form-control" type="text"
							maxlength="20" name="comGram7" placeholder="재료7 양(gram)"
							value="<%=item7%>"></td>
						<td colspan="1">
							<button class="btn btn-primary" onclick="ItemCheckFunction7();"
								type="button">재료 체크</button>
						</td>
					<tr>
						<td colspan="6"><h5>사진 업로드</h5></td>
					</tr>
					<tr>
						<td colspan="6"><input type="file" name="menuProfile"
							class="file">
							<div class="input-group col-xs-12">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-picture"></i></span> <input type="text"
									class="form-control input-lg" disabled
									placeholder="메뉴 사진을 등록하세요"> <span
									class="input-group-btn">
									<button class="browse btn btn-primary input-lg" type="button">
										<i class="glyphicon glyphicon-search"></i>파일찾기
									</button>
								</span>
							</div></td>
					</tr>


					<tr>
						<td style="text-align: left;" colspan="8">
							<h5 style="color: red;"></h5> <input
							class="btn btn-primary pull-right" type="submit" value="등록">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>


	<%
		String messageContent = null;
		if (session.getAttribute("messageContent") != null) {
			messageContent = (String) session.getAttribute("messageContent");
		}
		String messageType = null;
		if (session.getAttribute("messageContent") != null) {
			messageType = (String) session.getAttribute("messageType");
		}
		if (messageContent != null) {
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div
					class="modal-content <%if (messageType.equals("오류메세지"))
					out.println("panel-warning");
				else
					out.println("panel-success");%>">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%=messageType%>
						</h4>
					</div>
					<div class="modal-body">
						<%=messageContent%>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>



	<script>
		$('#messageModal').modal("show");
	</script>

	<%
		session.removeAttribute("messageContent");
			session.removeAttribute("messageType");
		}
	%>

	<%
		if (userID != null) { //사용자가 성공적으로 로그인을 한 상태라면
	%>
	<script type="text/javascript">
		$(document).ready(function() {
			getUnread();
			getInfiniteUnread();

		});
	</script>
	<%
		}
	%>

	<script type="text/javascript">
		$(document).on('click', '.browse', function() {
			var file = $(this).parent().parent().parent().find('.file');
			file.trigger('click');
		});

		$(document).on(
				'change',
				'.file',
				function() {
					$(this).parent().find('.form-control').val(
							$(this).val().replace(/C:\\fakepath\\/i, ''));
				});
	</script>

</body>
</html>