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
@WebServlet("/QnaUpdateWriteServlet")
public class QnaUpdateWriteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		QnaDTO dto = new QnaDTO();
		String Qnum = request.getParameter("Qnum");
		String Qtitle = request.getParameter("Qtitle");
		String Qusername =request.getParameter("Qusername");
		String Qpasswd4 = request.getParameter("Qpasswd4");
		String Qcontent = request.getParameter("Qcontent");
		String Qhide = request.getParameter("Qhide");
		if(Qhide != "on") {
			Qhide = "off";
		}
		String Qemail = request.getParameter("Qemail");
		dto.setQnum(Integer.parseInt(Qnum));
		dto.setQtitle(Qtitle);
		dto.setQcontent(Qcontent);
		dto.setQhide(Qhide);
		dto.setQpasswd4(Qpasswd4);
		dto.setQusername(Qusername);
		dto.setQemail(Qemail);

		QnaService service = new QnaService();
		String mesg ="";
		int n = service.qnaUpdate(dto);
		if(n == 1) {
		mesg = "수정 완료!";
		}
		System.out.println("mesg"+mesg);
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
