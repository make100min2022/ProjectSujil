package com.controller.waterQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.dto.DayDTO;
import com.dto.JusoDTO;
import com.service.JusoService;


@WebServlet("/DayDataServlet")
public class DayDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DayDataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String sigun = request.getParameter("sigun");
		String dong = request.getParameter("dong");
		String nextPage = null;
		
		//시도, 시군, 동이 모두 입력된 경우(dong != null) 아래 코드를 수행
		if(dong != null) {
			JusoService service = new JusoService();
			//API 호출을 위한 필수 변수값을 설정
			Map<String, String> map = new HashMap<>();
			map.put("sigun", sigun);
			map.put("dong", dong);
			
			
			List<JusoDTO> codeList = service.searchCode(map);
			
			String sgccd = codeList.get(0).getSgccd();
			String sitecd = codeList.get(0).getSitecd();
			System.out.println("지자체코드==" + sgccd);
			System.out.println("정수장코드==" + sitecd);
			String stdt = request.getParameter("stdt");
			String eddt = request.getParameter("eddt");
			System.out.println("시작일=="+stdt+"\t"+"종료일=="+eddt);
			String numOfRows = "100";
			String pageNo = "1";
			
			StringBuilder sb = new StringBuilder();
			List<DayDTO> dList = new ArrayList<>();
			JSONParser parser = new JSONParser();
			
			
			if(sgccd == null) {
				request.setAttribute("mesg", "현재 서비스를 제공하고 있지 않습니다. 월별 조회를 이용하세요.");
				nextPage = "Month.jsp";
			} else if(sgccd.equals("1")) { //광역 정수장 API 호출
				StringBuilder urlBuilder = new StringBuilder("http://opendata.kwater.or.kr/openapi-data/service/pubd/waterways/wdr/dailwater/list"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=xUaCQjbwxEcfxfh7%2FHktyNRhuSsKDSauOvqlvcPI01iUuOpKUc%2FO0cvLrIHqa5DCB%2F1oYsEj1Y6nqvssEstEWg%3D%3D"); /*Service Key*/
		        urlBuilder.append("&" + URLEncoder.encode("fcode","UTF-8") + "=" + URLEncoder.encode(sitecd, "UTF-8")); /*정수장 코드 조회 서비스 참조*/
		        urlBuilder.append("&" + URLEncoder.encode("stdt","UTF-8") + "=" + URLEncoder.encode(stdt, "UTF-8")); /*조회시작일*/
		        urlBuilder.append("&" + URLEncoder.encode("eddt","UTF-8") + "=" + URLEncoder.encode(eddt, "UTF-8")); /*조회종료일*/
		        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*줄수*/
		        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
		        urlBuilder.append("&_type=json");
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
		        System.out.println("Response code: " + conn.getResponseCode());
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		            request.setAttribute("mesg", "공공데이터 호출 오류입니다. 다시 시도해 주세요.");
		            nextPage = "DaySearch.jsp";
		        }
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        System.out.println(sb.toString());
		        
		        try {
			        JSONObject obj = (JSONObject) parser.parse(sb.toString());
					JSONObject parseResponse = (JSONObject)obj.get("response");
					JSONObject parseBody = (JSONObject)parseResponse.get("body");
					
					String totalCount = (String) parseBody.get("totalCount").toString();
					System.out.println("totalCount===="+totalCount);
					JSONObject dayWaterQlt = null;
					if(totalCount.equals("0")) {
						request.setAttribute("mesg", "현재 서비스를 제공하고 있지 않습니다. 월별 조회를 이용하세요.");
						nextPage = "DaySearch.jsp";
					} else {
			    		if(!stdt.equals(eddt)) {
			    			JSONObject parseItems = (JSONObject)parseBody.get("items");
			    			JSONArray parseItem = (JSONArray) parseItems.get("item");
								for(int i = 0; i < parseItem.size(); i++) {
									dayWaterQlt = (JSONObject)parseItem.get(i);
									DayDTO dDTO = new DayDTO();
									String date = (String)dayWaterQlt.get("mesurede").toString();
									String taste = (String)dayWaterQlt.get("item1").toString();
									String smell = (String)dayWaterQlt.get("item2").toString();
									String chromaticity = (String)dayWaterQlt.get("item3").toString();
									String pH = (String)dayWaterQlt.get("item4").toString();
									String turbidity = (String)dayWaterQlt.get("item5").toString();
									String residualCI = (String)dayWaterQlt.get("item6").toString();
									
									dDTO.setDate(date);
									dDTO.setTaste(taste);
									dDTO.setSmell(smell);
									dDTO.setChromaticity(chromaticity);
									dDTO.setpH(pH);
									dDTO.setTurbidity(turbidity);
									dDTO.setResidualCI(residualCI);
									
									dList.add(dDTO);
								}
							nextPage = "DaySearch.jsp";
			    		} else {
								DayDTO dDTO = new DayDTO();
								String date = (String)dayWaterQlt.get("mesurede").toString();
								String taste = (String)dayWaterQlt.get("item1").toString();
								String smell = (String)dayWaterQlt.get("item2").toString();
								String chromaticity = (String)dayWaterQlt.get("item3").toString();
								String pH = (String)dayWaterQlt.get("item4").toString();
								String turbidity = (String)dayWaterQlt.get("item5").toString();
								String residualCI = (String)dayWaterQlt.get("item6").toString();
								
								dDTO.setDate(date);
								dDTO.setTaste(taste);
								dDTO.setSmell(smell);
								dDTO.setChromaticity(chromaticity);
								dDTO.setpH(pH);
								dDTO.setTurbidity(turbidity);
								dDTO.setResidualCI(residualCI);
								
								dList.add(dDTO);
			    		}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else { //지방 정수장 API 호출
				//API url 생성
				StringBuilder urlBuilder = new StringBuilder("http://opendata.kwater.or.kr/openapi-data/service/pubd/waterinfos/waterquality/daywater/list"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=xUaCQjbwxEcfxfh7%2FHktyNRhuSsKDSauOvqlvcPI01iUuOpKUc%2FO0cvLrIHqa5DCB%2F1oYsEj1Y6nqvssEstEWg%3D%3D"); /*Service Key*/
		        urlBuilder.append("&" + URLEncoder.encode("sgccd","UTF-8") + "=" + URLEncoder.encode(sgccd, "UTF-8")); /*지자체코드*/
		        urlBuilder.append("&" + URLEncoder.encode("sitecd","UTF-8") + "=" + URLEncoder.encode(sitecd, "UTF-8")); /*정수장코드*/
		        urlBuilder.append("&" + URLEncoder.encode("stdt","UTF-8") + "=" + URLEncoder.encode(stdt.replaceAll("-", ""), "UTF-8")); /*조회시작일*/
		        urlBuilder.append("&" + URLEncoder.encode("eddt","UTF-8") + "=" + URLEncoder.encode(eddt.replaceAll("-", ""), "UTF-8")); /*조회종료일*/
		        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*줄수*/
		        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
		        urlBuilder.append("&_type=json");
		        System.out.println(urlBuilder);
		        
		        //URL 객체 생성하기
		        URL url = new URL(urlBuilder.toString());
		        
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
		        System.out.println("Response code: " + conn.getResponseCode());
		        
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		            request.setAttribute("mesg", "공공데이터 호출 오류입니다. 다시 시도해 주세요.");
		            nextPage = "DaySearch.jsp";
		        }
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        System.out.println(sb.toString());
		        try {
			        JSONObject obj = (JSONObject) parser.parse(sb.toString());
					JSONObject parseResponse = (JSONObject)obj.get("response");
					JSONObject parseBody = (JSONObject)parseResponse.get("body");
					String totalCount = (String) parseBody.get("totalCount").toString();
					System.out.println("totalCount===" + totalCount);
					JSONObject dayWaterQlt = null;
					
					if(totalCount.equals("0")) {
						request.setAttribute("mesg", "현재 서비스를 제공하고 있지 않습니다. 월별 조회를 이용하세요.");
						nextPage = "DaySearch.jsp";
					} else {
			    		if(!stdt.equals(eddt)) {
			    			JSONObject parseItems = (JSONObject)parseBody.get("items");
			    			JSONArray parseItem = (JSONArray) parseItems.get("item");
							for(int i = 0; i < parseItem.size(); i++) {
								dayWaterQlt = (JSONObject)parseItem.get(i);
								DayDTO dDTO = new DayDTO();
								String date = (String)dayWaterQlt.get("cltdt").toString();
								String taste = (String)dayWaterQlt.get("data1").toString();
								String smell = (String)dayWaterQlt.get("data2").toString();
								String chromaticity = (String)dayWaterQlt.get("data3").toString();
								String pH = (String)dayWaterQlt.get("data4").toString();
								String turbidity = (String)dayWaterQlt.get("data5").toString();
								String residualCI = (String)dayWaterQlt.get("data6").toString();
								
								dDTO.setDate(date);
								dDTO.setTaste(taste);
								dDTO.setSmell(smell);
								dDTO.setChromaticity(chromaticity);
								dDTO.setpH(pH);
								dDTO.setTurbidity(turbidity);
								dDTO.setResidualCI(residualCI);
								
								dList.add(dDTO);
							}
						} else {
								JSONObject parseItems = (JSONObject)parseBody.get("items");
								dayWaterQlt = (JSONObject) parseItems.get("item");
								
								DayDTO dDTO = new DayDTO();
								String date = (String)dayWaterQlt.get("cltdt").toString();
								String taste = (String)dayWaterQlt.get("data1").toString();
								String smell = (String)dayWaterQlt.get("data2").toString();
								String chromaticity = (String)dayWaterQlt.get("data3").toString();
								String pH = (String)dayWaterQlt.get("data4").toString();
								String turbidity = (String)dayWaterQlt.get("data5").toString();
								String residualCI = (String)dayWaterQlt.get("data6").toString();
								
								dDTO.setDate(date);
								dDTO.setTaste(taste);
								dDTO.setSmell(smell);
								dDTO.setChromaticity(chromaticity);
								dDTO.setpH(pH);
								dDTO.setTurbidity(turbidity);
								dDTO.setResidualCI(residualCI);
								
								dList.add(dDTO);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
			}
			request.setAttribute("dList", dList);
			nextPage = "DaySearch.jsp";
		//dong == null인 경우
		} else {
			request.setAttribute("mesg", "조회할 지역을 모두 선택하세요.");
			nextPage = "DaySearch.jsp";
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
