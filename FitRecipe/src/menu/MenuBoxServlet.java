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
       
  
	//form 태그에서 전송할떄는 RLDecoder.decode가 필요 없다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String comItem = request.getParameter("comItem");
		
		if(comItem == null ||comItem.equals("")||comItem.trim().equals(""))
		{
			request.getSession().setAttribute("messageType", "오류메세지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요");
			response.sendRedirect("searchMenu.jsp");
			return;
		}else {
			try {
				
				
				comItem = URLDecoder.decode(comItem, "UTF-8");
				
				response.getWriter().write(getBox(comItem));
				//특정한 사용자가 가진 모든 메세지 리스트를 반환
				
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
		// comItem == 검색값 ,  getList() 함수에 넣으면 그 메뉴가 포함된 요리 이름을 ArrayList 형태로 반환한다.
		//MenuDTO menuList[] = new MenuDTO[comList.size()];  //배열로 해보자
		
		MenuDAO menuDAO = new MenuDAO();
		//MenuDTO menu = new MenuDAO().getMenuName(comList.get(0).getComName());
		MenuDTO menu[] = new MenuDTO[comList.size()];
		if(comList.size() == 0 ) {  
			return "-1";   //해당 데이터가 존재지 않음
		}
		for(int i=0 ; i < comList.size() ; i++) {     //컴포넌트 
			
			
			//menuList[i] = new MenuDAO().getMenu(comList.get(i).getComName());
			menu[i] = new MenuDAO().getMenuName(comList.get(i).getComName()); 
			//각 메뉴, menuDTO를   생성, 메모리 효율이 좋아 보이지 않으나 진행한다.
			System.out.println(menuDAO.getProfile(menu[i].getMenuNum()+gongback));
		
			result.append("[{\"value\":\"" + menuDAO.getProfile(menu[i].getMenuNum()+gongback)+ "\"},");
			result.append("{\"value\":\"" + menu[i].getMenuNum()+ "\"},");
			result.append("{\"value\":\"" + menu[i].getMenuName()+ "\"},");
			result.append("{\"value\":\"" + menu[i].getMenuPrice()+ "\"}]");
				
			if(i != comList.size()-1 ) {//최종점에는 ,붙인다
				result.append(",");
			}
		}
		result.append("], \"last\":\""+ comList.size()+"\"}");
		
		return result.toString();
	}

}