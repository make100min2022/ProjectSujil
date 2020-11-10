//package com.contorller.beach;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import com.dto.BeachDTO;
//
//
///**
// * Servlet implementation class BeachInfoServlet1
// */
//@WebServlet("/BeachInfoServlet")
//public class BeachInfoServlet extends HttpServlet {
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			String dong = (String) request.getParameter("dong");
//			System.out.println(dong);
//			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1192000/service/OceansBeachSeawaterService1/getOceansBeachSeawaterInfo1"); /*URL*/
//	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=dx5c0fJoFx03FKGtzkBHMRdYHs94IeTyL3Cim8NYz%2BqBfvO3%2FTthkY4cj0No9NHPnJcWCDmeuPbptuT3EXstCQ%3D%3D"); /*공공데이터포털에서 발급받은 인증키*/
//	        urlBuilder.append("&" + URLEncoder.encode("pag"
//	        		+ "eNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
//	        urlBuilder.append("&" + URLEncoder.encode("SIDO_NM","UTF-8") + "=" + URLEncoder.encode(dong, "UTF-8")); /*시도명*/
//	        urlBuilder.append("&" + URLEncoder.encode("RES_YEAR","UTF-8") + "=" + URLEncoder.encode("2019", "UTF-8")); /*조사년도*/
//	        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*결과형식 ( XML/JSON ) 값이 없으면 xml로 자동설정됨.*/
//	        URL url = new URL(urlBuilder.toString());
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	        conn.setRequestMethod("GET");
//	        conn.setRequestProperty("Content-type", "application/json");
//	        System.out.println("Response code: " + conn.getResponseCode());
//	        BufferedReader rd;
//	        System.out.println(url);
//	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	        } else {
//	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//	        }
//	        StringBuilder sb = new StringBuilder();
//	        String line;
//	        while ((line = rd.readLine()) != null) {
//	            sb.append(line);
//	        }
//	        rd.close();
//	        conn.disconnect();
//	        List<BeachDTO> list = new ArrayList<>();
//	        try {
//	        	JSONParser jsonParser = new JSONParser();
//				JSONObject jsonObj = (JSONObject)jsonParser.parse(sb.toString());
//				JSONObject jsonResponse = (JSONObject)jsonObj.get("getOceansBeachSeawaterInfo");
//				JSONArray jsonArr = (JSONArray)jsonResponse.get("item");
//				for(int i =0; i<jsonArr.size(); i++) {
//					JSONObject obj = (JSONObject) jsonArr.get(i);
//					DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
//					String today = sdFormat.format(obj.get("res_date"));
//					BeachDTO bDTO = new BeachDTO();
//					bDTO.setGugunNm(obj.get("gugun_nm").toString());
//					bDTO.setLat(obj.get("lat").toString());
//					bDTO.setLon(obj.get("lon").toString());
//					bDTO.setNum(obj.get("num").toString());
//					bDTO.setRes1(obj.get("res1").toString());
//					bDTO.setRes2(obj.get("res2").toString());
//					bDTO.setResDate(today);
//					bDTO.setResKnd(obj.get("res_knd").toString());
//					bDTO.setResLoc(obj.get("res_loc").toString());
//					bDTO.setResNum(obj.get("res_num").toString());
//					bDTO.setResYear(obj.get("res_year").toString());
//					bDTO.setResYn(obj.get("res_yn").toString());
//					bDTO.setSidoNm(obj.get("sido_nm").toString());
//					bDTO.setStaNm(obj.get("sta_nm").toString());
//					list.add(bDTO);
//				}
//				
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	        request.setAttribute("beachList", list);
//	        RequestDispatcher dis = request.getRequestDispatcher("beach.jsp");
//	        dis.forward(request, response);
//	        
//	    }
//	
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
