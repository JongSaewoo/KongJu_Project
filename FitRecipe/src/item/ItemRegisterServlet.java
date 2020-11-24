package item;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ItemRegisterServlet")
public class ItemRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String itemName = request.getParameter("itemName");
		
		
		if(itemName == null || itemName.equals("") ||itemName.trim().equals("") )
		{
			request.getSession().setAttribute("messageType", "오류메세지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요");
			response.sendRedirect("itemWrite.jsp");
			return;
		}
				
		  ItemDAO itemDAO = new ItemDAO();
				  
          int result =itemDAO.write(itemName.trim());
          
          
          
          if(result==-1) {  ////write1 에러 
          	request.getSession().setAttribute("messageType", "오류메세지");
  			request.getSession().setAttribute("messageContent", "셀리늄 자체 에러 <br> ");
  			response.sendRedirect("itemWrite.jsp");
    			return;
    		}  
          
         
          else if(result==-3) {//write2 에러 
            	request.getSession().setAttribute("messageType", "오류메세지");
    			request.getSession().setAttribute("messageContent", "셀리늄 에러 <br> 혹시 브라우저 창을 닫으셨다면 조금만 기다려주시겠어요 ^^?창은 자동으로 꺼집니다!");
    			response.sendRedirect("itemWrite.jsp");
      			return;
      		}
          else if(result==-4) {
          	request.getSession().setAttribute("messageType", "오류메세지");
  			request.getSession().setAttribute("messageContent", "아이템 등록에 실패했습니다. <br> 검색에러  .");
  			response.sendRedirect("itemWrite.jsp");
    			return;
    		} 
          
          else if(result==-5) {
          	request.getSession().setAttribute("messageType", "오류메세지");
  			request.getSession().setAttribute("messageContent", "아이템 등록에 실패했습니다. <br> 해당 검색어는 해당 검색엔진사이트에 결과가 없는 검색어입니다.");
  			response.sendRedirect("itemWrite.jsp");
    			return;
    		}  
          
        else if(result==1) {
			ItemDTO itemDTO = new ItemDAO().getItem(itemName);
			request.getSession().setAttribute("messageType", "성공메세지");
			request.getSession().setAttribute("messageContent", "재료를 등록하였습니다<br>"+ itemDTO.getItemComment()+" 100그람당 "+itemDTO.getItemGram()+"원");
			response.sendRedirect("itemWrite.jsp");
			return;
		}
		else if(result==0){
			request.getSession().setAttribute("messageType", "성공메세지");
			request.getSession().setAttribute("messageContent", "이미 있는 재료입니다.(등록한지 30일 이내)");
			response.sendRedirect("itemWrite.jsp");
			return;
			
		}
		else {
			request.getSession().setAttribute("messageType", "오류메세지");
			request.getSession().setAttribute("messageContent", "아이템 등록에 실패했습니다.");
			response.sendRedirect("itemWrite.jsp");
			return;
			
		}
		
	}

}
