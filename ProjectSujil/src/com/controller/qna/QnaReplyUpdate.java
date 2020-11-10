package com.controller.qna;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.QnaDTO;
import com.service.QnaService;

/**
 * Servlet implementation class qnaReplyUpdate
 */
@WebServlet("/QnaReplyUpdate")
public class QnaReplyUpdate extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		QnaDTO qDTO = new QnaDTO();
		qDTO.setQnum(Integer.parseInt(request.getParameter("qnaReplyNum")));
		System.out.println("replynum: "+Integer.parseInt(request.getParameter("qnaReplyNum")));
		qDTO.setQreply(request.getParameter("replyUpdateArea"));
		System.out.println("replyUpdateArea: "+request.getParameter("replyUpdateArea"));
		QnaService service = new QnaService();
		int n = service.replayUpdate(qDTO);
		System.out.println("n:"+n);
		
		
		response.sendRedirect("QnaReadServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
