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
			request.getSession().setAttribute("messageType", "�����޼���");
			request.getSession().setAttribute("messageContent", "��� ������ �Է��ϼ���");
			response.sendRedirect("itemWrite.jsp");
			return;
		}
				
		  ItemDAO itemDAO = new ItemDAO();
				  
          int result =itemDAO.write(itemName.trim());
          
          
          
          if(result==-1) {  ////write1 ���� 
          	request.getSession().setAttribute("messageType", "�����޼���");
  			request.getSession().setAttribute("messageContent", "������ ��ü ���� <br> ");
  			response.sendRedirect("itemWrite.jsp");
    			return;
    		}  
          
         
          else if(result==-3) {//write2 ���� 
            	request.getSession().setAttribute("messageType", "�����޼���");
    			request.getSession().setAttribute("messageContent", "������ ���� <br> Ȥ�� ������ â�� �����̴ٸ� ���ݸ� ��ٷ��ֽðھ�� ^^?â�� �ڵ����� �����ϴ�!");
    			response.sendRedirect("itemWrite.jsp");
      			return;
      		}
          else if(result==-4) {
          	request.getSession().setAttribute("messageType", "�����޼���");
  			request.getSession().setAttribute("messageContent", "������ ��Ͽ� �����߽��ϴ�. <br> �˻�����  .");
  			response.sendRedirect("itemWrite.jsp");
    			return;
    		} 
          
          else if(result==-5) {
          	request.getSession().setAttribute("messageType", "�����޼���");
  			request.getSession().setAttribute("messageContent", "������ ��Ͽ� �����߽��ϴ�. <br> �ش� �˻���� �ش� �˻���������Ʈ�� ����� ���� �˻����Դϴ�.");
  			response.sendRedirect("itemWrite.jsp");
    			return;
    		}  
          
        else if(result==1) {
			ItemDTO itemDTO = new ItemDAO().getItem(itemName);
			request.getSession().setAttribute("messageType", "�����޼���");
			request.getSession().setAttribute("messageContent", "��Ḧ ����Ͽ����ϴ�<br>"+ itemDTO.getItemComment()+" 100�׶��� "+itemDTO.getItemGram()+"��");
			response.sendRedirect("itemWrite.jsp");
			return;
		}
		else if(result==0){
			request.getSession().setAttribute("messageType", "�����޼���");
			request.getSession().setAttribute("messageContent", "�̹� �ִ� ����Դϴ�.(������� 30�� �̳�)");
			response.sendRedirect("itemWrite.jsp");
			return;
			
		}
		else {
			request.getSession().setAttribute("messageType", "�����޼���");
			request.getSession().setAttribute("messageContent", "������ ��Ͽ� �����߽��ϴ�.");
			response.sendRedirect("itemWrite.jsp");
			return;
			
		}
		
	}

}
