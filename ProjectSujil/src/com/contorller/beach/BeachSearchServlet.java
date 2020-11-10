package com.contorller.beach;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dto.BeachDTO;
import com.service.BeachService;

/**
 * Servlet implementation class BeachSearchServlet
 */
@WebServlet("/BeachSearchServlet")
public class BeachSearchServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String doNm = request.getParameter("dong");
		BeachService service = new BeachService();
		List<BeachDTO> list = service.BeachSearch(doNm);
		System.out.println(list);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String s = "";
		JSONArray jsonArr = new  JSONArray();
		for(BeachDTO dto : list) {
			JSONObject jsonObj = new JSONObject();
//			s += dto.getGugun_nm() + "," + dto.getSta_nm()+"/";
			jsonObj.put("군구", dto.getGugun_nm());
			jsonObj.put("해수욕장", dto.getSta_nm());
			jsonObj.put("경도", dto.getLat());
			jsonObj.put("위도", dto.getLon());
			System.out.println(jsonObj);
			jsonArr.add(jsonObj);
			System.out.println("for end");
		}
		
		out.print(jsonArr);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
