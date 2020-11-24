<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="board.BoardDAO" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page trimDirectiveWhitespaces="true" %> 



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>유저 채팅</title>

</head>
<body>
	
	<%
		request.setCharacterEncoding("UTF-8");
		String boardID = request.getParameter("boardID");
		if(boardID ==null || boardID.equals("")){
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "접근할 수 없습니다.");
	   		response.sendRedirect("index.jsp");
	   		return;
		}
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root+ "upload";
		String fileName ="";
		String realFile ="";
		BoardDAO boardDAO = new BoardDAO();
		fileName = boardDAO.getFile(boardID);
		realFile = boardDAO.getRealFile(boardID);
		
		if(fileName.equals("")|| realFile.equals("")) { //파일이 존재하지 않는경우
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "접근할 수 없습니다2.");
	   		response.sendRedirect("index.jsp");
	   		return;
		}
		//소켓 프로그래밍 에어리어
		InputStream in = null;
		OutputStream os = null;
		
		File file	 = null;
		boolean skip = false;
		String client = "";
		try{
			try{
				file = new File(savePath, realFile);  //실제 파일경로를 가져온다
				in = new FileInputStream(file);
			}catch(FileNotFoundException e)
			{
				skip = true;
			}
			client = request.getHeader("User-Agent"); //클라이언트의 접속 브라우저 크롬인지 익스플로어 인지
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");
			if(!skip){
				 if(client.indexOf("MISE") != -1){ //익스플로어 외 크롬 ,파폭
					 response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("KSC5601"), "ISO8859_1"));
	 			 }else{
	 				 fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
	 				 
	 				response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
	 				response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");  //현제 에러..  
	 			 }
				 //서버가 클라이언트로 전송할 길이 제약
				 response.setHeader("Content-Length", ""+ file.length());
				 os = response.getOutputStream();
				 byte b[] = new byte[(int)file.length()];
				 int leng = 0;
				 while ((leng = in.read(b)) > 0 ){
					 os.write(b,0, leng);
				 }
				 
			}else{//파일이 존재하지 않는경우
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script>alrert('파일을 찾을 수 없습니다');history.back(); </script>");
			}
			in.close();
			os.close();
			
		}catch(Exception ee){
			ee.printStackTrace();
		}
		
	%>
	
	
	
	
</body>
</html>