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
 * Servlet implementation class QnaPasswdCheck
 */
@WebServlet("/QnaPasswdCheck")
public class QnaPasswdCheck extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String passwd2 = request.getParameter("passwdCheck2");
	String qnum = request.getParameter("passwdUser");
	QnaDTO qDTO = new QnaDTO();
	QnaService service = new QnaService();
	qDTO = service.qnaUpdateRead(qnum);
	String passwd = qDTO.getQpasswd4();
	
	
	if(passwd.equals(passwd2)) {
		request.setAttribute("qnum", qnum);
		RequestDispatcher dis = request.getRequestDispatcher("QnaUpdateReadServlet");
		dis.forward(request, response);
		
	} else {
		System.out.println("notok");
		request.setAttribute("mesg", "비밀번호가 틀렸습니다");
		response.sendRedirect("QnaReadServlet");
	}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
