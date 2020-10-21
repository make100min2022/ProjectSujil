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

@WebServlet("/SigunServlet")
public class SigunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SigunServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JusoService service = new JusoService();
		HttpSession session = request.getSession();
		
		String sido = request.getParameter("sido");
		System.out.println("시도: " + sido);
		
		if(sido != null) {
			List<String> sigunList = service.setSigun(sido);
			
			System.out.println(sigunList.toString());
			PrintWriter out = response.getWriter();
			out.print(sigunList);
			System.out.println(sigunList);
			
		} else {
			session.setAttribute("mesg", "검색할 지역을 선택해주세요");
			response.sendRedirect("search.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
