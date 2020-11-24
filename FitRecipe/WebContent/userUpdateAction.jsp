<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
 <%@ page import="user.UserDAO" %>
    
 <%@ page import="java.io.PrintWriter" %>
 <% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id ="user" class= "user.UserDTO" scope="page" />
<jsp:setProperty name="user" property="userID"/>
<jsp:setProperty name="user" property="userPassword"/>
<jsp:setProperty name="user" property="userName"/>
<jsp:setProperty name="user" property="userGender"/>
<jsp:setProperty name="user" property="userAge"/>
<jsp:setProperty name="user" property="userEmail"/>

 
 
<!DOCTYPE html>
<html>
<head>
<% 

//크로스사이트 스크립트 테스트용 사이트
 String userID2 = null;
   	if(session.getAttribute("userID")!= null){
   		userID2 = (String) session.getAttribute("userID");
   	}
   	if (userID2 == null){
   		session.setAttribute("messageType", "오류 메세지");
   		session.setAttribute("messageContent", "현재 로그인이 되어있지 않습니다.");
   		response.sendRedirect("index.jsp");
   		return;
   	}
   	
   	if (userID2.equals(user.getUserID()+"")){
   		
   	}else{
   		session.setAttribute("messageType", "오류 메세지");
   		session.setAttribute("messageContent", "본인만 접근 가능합니다.");
   		response.sendRedirect("index.jsp");
   		return;
   	}
   	
   	
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <title>CSRF 테스트</title>  
</head>
<body>
<%--    <% request.setCharacterEncoding("UTF-8"); %> == 건너오는 데이터를 UTF로 받는다 --%>
<!-- scope="page 현재 페이지에서만 자바 빈즈가 사용되도록 -->
<%-- <jsp:setProperty name="user" property="userID"/>  login.jsp에서 넘겨준 userID를 받아서 한명의 사용자인 user에 넣는다 --%>

<!--    mysql jdbc driver 설치후  mysql-connectory-java-5.1.47-bin jar 파일을   WEB-INF /lib안에 넣어주도록 하자 -->
<!-- 그뒤에 프로펄티에서  자바 빌드패스 해준다. -->
   <%
   
   
   
   
   
   
   
   
   // 크로스 사이트 스크립트 연습용 파일//////////////////////////////////
   	
       String userID = user.getUserID();
       String userPassword = user.getUserPassword();
       String userName = user.getUserName();
       String userAge = user.getUserAge()+"";
       String userGender = user.getUserGender();
       String userEmail = user.getUserEmail();

   

   		UserDAO userDAO = new UserDAO();
   		int result = userDAO.update(userID, userPassword, userName, userAge, userGender, userEmail);

   		if (result == -1) { //-1  데이터베이스 오류 == 프라이머리 키 중복 , 기본키 중복을 뜻함
   			PrintWriter script = response.getWriter();
   			script.println("<script>");
   			script.println("alert('이미 존재하는 아이디 입니다.')");
   			script.println("history.back();");
   			script.println("</script>");

   		}

   		else { //정상 회원가입
   			session.setAttribute("userID", user.getUserID()); //세션값 넣기 

   			PrintWriter script = response.getWriter();
   			request.getSession().setAttribute("userID", userID); //회원가입 성공시 자동 으로 세션유지 , 로그인
			request.getSession().setAttribute("messageType", "성공메세지");
			request.getSession().setAttribute("messageContent", "회원정보 수정에 성공했습니다");
			response.sendRedirect("index.jsp");	

   		}

   	
   %>


</body>
</html>