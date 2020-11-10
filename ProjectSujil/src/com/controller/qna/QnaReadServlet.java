package com.controller.qna;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.QnaDTO;
import com.service.QnaService;

/**
 * Servlet implementation class QnaReadServlet
 */
@WebServlet("/QnaReadServlet")
public class QnaReadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		QnaService service = new QnaService();
		List<QnaDTO> list = service.qnaRead();
		String mesg= (String) request.getAttribute("mesg");
		
		
		request.setCharacterEncoding("utf-8");
		request.setAttribute("list", list);
		request.setAttribute("mesg", mesg);
		RequestDispatcher dis = request.getRequestDispatcher("qna.jsp");
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
