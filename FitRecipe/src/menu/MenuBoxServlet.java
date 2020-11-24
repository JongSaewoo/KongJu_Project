package menu;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import component.ComponentDAO;
import component.ComponentDTO;

@WebServlet("/MenuBoxServlet")
public class MenuBoxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	//form �±׿��� �����ҋ��� RLDecoder.decode�� �ʿ� ����.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String comItem = request.getParameter("comItem");
		
		if(comItem == null ||comItem.equals("")||comItem.trim().equals(""))
		{
			request.getSession().setAttribute("messageType", "�����޼���");
			request.getSession().setAttribute("messageContent", "��� ������ �Է��ϼ���");
			response.sendRedirect("searchMenu.jsp");
			return;
		}else {
			try {
				
				
				comItem = URLDecoder.decode(comItem, "UTF-8");
				
				response.getWriter().write(getBox(comItem));
				//Ư���� ����ڰ� ���� ��� �޼��� ����Ʈ�� ��ȯ
				
			} catch (Exception e) {
				response.getWriter().write("error");
			}
		}
	}
	
	public String getBox(String comItem) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		String gongback ="";
		
		ComponentDAO comDAO = new ComponentDAO();
		ArrayList<ComponentDTO> comList = new ComponentDAO().getList(comItem); 
		// comItem == �˻��� ,  getList() �Լ��� ������ �� �޴��� ���Ե� �丮 �̸��� ArrayList ���·� ��ȯ�Ѵ�.
		//MenuDTO menuList[] = new MenuDTO[comList.size()];  //�迭�� �غ���
		
		MenuDAO menuDAO = new MenuDAO();
		//MenuDTO menu = new MenuDAO().getMenuName(comList.get(0).getComName());
		MenuDTO menu[] = new MenuDTO[comList.size()];
		if(comList.size() == 0 ) {  
			return "-1";   //�ش� �����Ͱ� ������ ����
		}
		for(int i=0 ; i < comList.size() ; i++) {     //������Ʈ 
			
			
			//menuList[i] = new MenuDAO().getMenu(comList.get(i).getComName());
			menu[i] = new MenuDAO().getMenuName(comList.get(i).getComName()); 
			//�� �޴�, menuDTO��   ����, �޸� ȿ���� ���� ������ ������ �����Ѵ�.
			System.out.println(menuDAO.getProfile(menu[i].getMenuNum()+gongback));
		
			result.append("[{\"value\":\"" + menuDAO.getProfile(menu[i].getMenuNum()+gongback)+ "\"},");
			result.append("{\"value\":\"" + menu[i].getMenuNum()+ "\"},");
			result.append("{\"value\":\"" + menu[i].getMenuName()+ "\"},");
			result.append("{\"value\":\"" + menu[i].getMenuPrice()+ "\"}]");
				
			if(i != comList.size()-1 ) {//���������� ,���δ�
				result.append(",");
			}
		}
		result.append("], \"last\":\""+ comList.size()+"\"}");
		
		return result.toString();
	}

}