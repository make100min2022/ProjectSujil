package com.controller.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.QnaDTO;
import com.service.QnaService;

/**
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/QnaUpdateReadServlet")
public class QnaUpdateReadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qnum = (String)request.getAttribute("qnum");
		if(qnum == null) {			
			qnum = request.getParameter("qnum");
		}
		System.out.println("readservlet"+qnum);
		QnaService service = new QnaService();
		QnaDTO qDTO = service.qnaUpdateRead(qnum);
		
		request.setAttribute("qDTO", qDTO);
		System.out.println("qDTO num:"+qDTO.getQnum());
		RequestDispatcher dis = request.getRequestDispatcher("qnaUpdate.jsp");
		dis.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
