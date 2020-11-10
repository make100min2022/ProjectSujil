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
 * Servlet implementation class QnaUserDel
 */
@WebServlet("/QnaUserDel")
public class QnaUserDel extends HttpServlet {
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qnum = request.getParameter("passwdUser");
		QnaService service = new QnaService();
		QnaDTO qDTO = new QnaDTO();
		qDTO=service.qnaUpdateRead(qnum);
		String passwd1 = qDTO.getQpasswd4();		
		String passwd2 = request.getParameter("passwdCheck2");
		int i = 0;
		String mesg = null;
		if(passwd1.equals(passwd2)) {
			i =service.qnaDel(qnum);
			if(i == 1) {
				mesg = "삭제 성공!";
			}
		}else {
			mesg = "비밀번호가 틀렸습니다.";
		}
		request.setAttribute("mesg", mesg);
		RequestDispatcher dis = request.getRequestDispatcher("QnaReadServlet");
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
