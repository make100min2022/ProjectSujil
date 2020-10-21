package com.controller.juso;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.JusoService;

@WebServlet("/DongServlet")
public class DongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DongServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sigun = request.getParameter("sigun");
		sigun = sigun.trim();
		
		HttpSession session = request.getSession();
		JusoService service = new JusoService();
		
		System.out.println("시군: " + sigun);
		
		if(sigun != null) {
			List<String> dongList = service.setDong(sigun);
			System.out.println(dongList.toString());
			PrintWriter out = response.getWriter();
			out.print(dongList);
			System.out.println(dongList);
			
		} else {
			session.setAttribute("mesg", "검색할 지역을 선택해주세요");
			response.sendRedirect("search.jsp");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
