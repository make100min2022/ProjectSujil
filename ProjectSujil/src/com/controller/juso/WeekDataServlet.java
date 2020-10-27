package com.controller.juso;

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
import org.json.simple.parser.ParseException;

import com.dto.DayDTO;
import com.dto.JusoDTO;
import com.dto.WeekDTO;
import com.service.JusoService;

@WebServlet("/WeekDataServlet_T")
public class WeekDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WeekDataServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //HttpSession session = request.getSession();

        String sigun = request.getParameter("sigun");
        String dong = request.getParameter("dong");
        String stdt = request.getParameter("stdt");
        String eddt = request.getParameter("eddt");
        String nextPage = null;
        // 시도, 시군, 동이 모두 입력된 경우(dong != null) 아래 코드를 수행

        if (dong != null) {
            JusoService service = new JusoService();
            // API 호출을 위한 필수 변수값을 설정
            Map<String, String> map = new HashMap<>();
            map.put("sigun", sigun);
            map.put("dong", dong);

            List<JusoDTO> codeList = service.searchCode(map);
            String sgccd = codeList.get(0).getSgccd();
            String sitecd = codeList.get(0).getSitecd();
            System.out.println("지자체코드==" + sgccd);
            System.out.println("정수장코드==" + sitecd);

            String numOfRows = "100";
            String pageNo = "1";
            if (sgccd == null) {
            	request.setAttribute("mesg", "현재 서비스를 제공하고 있지 않습니다. 월별 조회를 이용하세요");
            	nextPage = "Weeksearch.jsp";
            	//RequestDispatcher dis = request.getRequestDispatcher("Weeksearch.jsp");
               // dis.forward(request, response);

            } else if (sgccd.equals("1")) {
            	stdt = stdt.substring(0, 7);
            	System.out.println(stdt);
                eddt = eddt.substring(0, 7);
                System.out.println(eddt);
            		System.out.println("광역정수장======");
                    String fcode = sitecd;
                    System.out.println("fcode==="+fcode);
                try {
                    request.setCharacterEncoding("utf-8");
                    // API url
                    StringBuilder urlBuilder = new StringBuilder(
                            "http://opendata.kwater.or.kr/openapi-data/service/pubd/waterways/wdr/wikwater/list"); /*
                                                                                                                              * URL
                                                                                                                              */
                    urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                            + "=kx4i5O3OEOraN03wOaAg3kH27jQHUProGpgvziVArSOG01MatJ3UkY8eZ2ZPlAbgjXRjf93N9P8JEgmWBNgTfQ%3D%3D"); /*
                                                                                                                                       * Service
                                                                                                                                       * Key
                                                                                                                                       */
                    urlBuilder.append("&" + URLEncoder.encode("fcode", "UTF-8") + "="
                            + URLEncoder.encode(sgccd, "UTF-8")); /* 지자체코드 */
                    urlBuilder.append("&" + URLEncoder.encode("stdt", "UTF-8") + "="
                            + URLEncoder.encode(stdt, "UTF-8")); /* 조회시작일 */
                    urlBuilder.append("&" + URLEncoder.encode("eddt", "UTF-8") + "="
                            + URLEncoder.encode(eddt, "UTF-8")); /* 조회종료일 */
                    urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
                            + URLEncoder.encode(numOfRows, "UTF-8")); /* 줄수 */
                    urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
                            + URLEncoder.encode(pageNo, "UTF-8")); /* 페이지번호 */
                    urlBuilder.append("&_type=json");
                    String serviceKey = "xUaCQjbwxEcfxfh7%2FHktyNRhuSsKDSauOvqlvcPI01iUuOpKUc%2FO0cvLrIHqa5DCB%2F1oYsEj1Y6nqvssEstEWg%3D%3D";

                    String urlStr = "http://opendata.kwater.or.kr/openapi-data/service/pubd/waterways/wdr/wikwater/list";
                    urlStr += "?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey;
                    urlStr += "&" + URLEncoder.encode("fcode", "UTF-8") + "=" + fcode;
                    urlStr += "&" + URLEncoder.encode("stdt", "UTF-8") + "=" + stdt;
                    urlStr += "&" + URLEncoder.encode("eddt", "UTF-8") + "=" + eddt;
                    urlStr += "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + numOfRows;
                    urlStr += "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo;
                    urlStr += "&" + URLEncoder.encode("_type", "UTF-8") + "=json";

                    URL url = new URL(urlStr);

                    System.out.println(url);
                    String line = "";
                    String result = "";

                    BufferedReader br;                
                    br = new BufferedReader(new InputStreamReader(url.openStream()));
                    
                    
                    
                    while ((line = br.readLine()) != null) {
                        result = result.concat(line);
                        // System.out.println(line);
                    }
                    
                    

                    // JSON parser 만들어 문자열 데이터를 객체화한다.
                    JSONParser parser = new JSONParser();
                    JSONObject obj;

                    obj = (JSONObject) parser.parse(result);
                    // System.out.println(obj);
                    List<WeekDTO> wList = new ArrayList<>();

                    JSONObject parse_response = (JSONObject) obj.get("response"); // response 로 부터 body 찾아옵니다.
                    JSONObject parse_body = (JSONObject) parse_response.get("body"); // body 로 부터 items 받아옵니다.
                    String totalC = (String) parse_body.get("totalCount").toString();
                    System.out.println("totalc=" + totalC);
                    
                    if(totalC.equals("0")){
                    	System.out.println("totalC가 0일때");
                    	request.setAttribute("mesg", "현재 서비스를 제공하고 있지 않습니다. 월별 조회를 이용하세요");
                    	nextPage = "Weeksearch.jsp";
                    	//RequestDispatcher dis = request.getRequestDispatcher("Weeksearch.jsp");
                        //dis.forward(request, response);
                    
                    }else if (totalC.equals("1")) {
                        System.out.println("들어왔");
                        JSONObject parse_items = (JSONObject) parse_body.get("items"); // items로 부터 itemlist 를 받아오기 itemlist
                        // : 뒤에 [ 로 시작하므로 jsonarray이다

                        System.out.println(parse_items.size());
                        JSONObject weekWaterQlt = (JSONObject) parse_items.get("item");

                        WeekDTO wDTO = new WeekDTO();
                        String cltdt = (String) weekWaterQlt.get("mesurede").toString();
                        System.out.println(cltdt);
                        String bacteria = (String) weekWaterQlt.get("item1").toString(); // 일반세균
                        // System.out.println(data1);
                        String TotalColita = (String) weekWaterQlt.get("itmem2").toString(); // 총대장균군
                        // System.out.println(data2);
                        String Coli = (String) weekWaterQlt.get("item3").toString(); // 대장균/분원성대장균
                        // System.out.println(data3);
                        String Ammonia = (String) weekWaterQlt.get("item4").toString(); // 암모니아성질소
                        // System.out.println(data4);

                        String Nnitrogen = (String) weekWaterQlt.get("item5").toString(); // 질산성질소
                        // System.out.println(data5);
                        //item6 없앰!!!!!
                        String Evaresi = (String) weekWaterQlt.get("item7").toString(); // 증발잔류물
                        System.out.println(Evaresi);

                        // String sgcnm = (String) weekWaterQlt.get("sgcnm"); // 지자체명
                        // //System.out.println(sgcnm);
                        // String sitenm = (String) weekWaterQlt.get("sitenm");
                        // //System.out.println(sitenm);

                        wDTO.setCltdt(cltdt);
                        wDTO.setBacteria(bacteria);
                        wDTO.setTotalColi(TotalColita);
                        wDTO.setColi(Coli);
                        wDTO.setAmmonia(Ammonia);
                        wDTO.setNnitrogen(Nnitrogen);
                        wDTO.setEvaresi(Evaresi);

                        wList.add(wDTO);

                    } else {
                    	JSONObject parse_items = (JSONObject) parse_body.get("items"); // items로 부터 itemlist 를 받아오기 itemlist
                        // : 뒤에 [ 로 시작하므로 jsonarray이다

                    	System.out.println(parse_items.size());
                        JSONArray parse_item = (JSONArray) parse_items.get("item");

                        JSONObject weekWaterQlt;

                        for (int i = 0; i < parse_item.size(); i++) {
                            weekWaterQlt = (JSONObject) parse_item.get(i);
                            WeekDTO wDTO = new WeekDTO();
                            String cltdt = (String) weekWaterQlt.get("mesurede").toString();
                            System.out.println(cltdt);
                            String bacteria = (String) weekWaterQlt.get("item1").toString(); // 일반세균
                            System.out.println("bacteria==="+bacteria);
                            String TotalColita = (String) weekWaterQlt.get("item2").toString(); // 총대장균군
                            System.out.println("TotalColita===="+TotalColita);
                            String Coli = (String) weekWaterQlt.get("item3").toString(); // 대장균/분원성대장균
                            System.out.println("Coli==="+Coli);
                            String Ammonia = (String) weekWaterQlt.get("item4").toString(); // 암모니아성질소
                             System.out.println("Ammonia===="+Ammonia);
                            
                            String Nnitrogen = (String) weekWaterQlt.get("item5").toString(); // 질산성질소
                            // System.out.println(data5);
                            //item6 없앰!!!!!
                            System.out.println("Nnitrogen===="+Nnitrogen);
                            String Evaresi = (String) weekWaterQlt.get("item7").toString(); // 증발잔류물
                            System.out.println("Evaresi===="+Evaresi);

                            // String sgcnm = (String) weekWaterQlt.get("sgcnm"); // 지자체명
                            // //System.out.println(sgcnm);
                            // String sitenm = (String) weekWaterQlt.get("sitenm");
                            // //System.out.println(sitenm);

                            wDTO.setCltdt(cltdt);
                            wDTO.setBacteria(bacteria);
                            wDTO.setTotalColi(TotalColita);
                            wDTO.setColi(Coli);
                            wDTO.setAmmonia(Ammonia);
                            wDTO.setNnitrogen(Nnitrogen);
                            wDTO.setEvaresi(Evaresi);

                            wList.add(wDTO);

                        }
                    }
                    request.setAttribute("wList", wList);
                    nextPage = "Weeksearch.jsp";
                   // RequestDispatcher dis = request.getRequestDispatcher("Weeksearch.jsp");
                    //dis.forward(request, response);
                    }catch (ParseException e) {
                        e.printStackTrace();
                        
                    }
                }
                 else {
                	 System.out.println("지방정수장======");
                    try {
                        request.setCharacterEncoding("utf-8");
                        // API url
                        StringBuilder urlBuilder = new StringBuilder(
                                "http://opendata.kwater.or.kr/openapi-data/service/pubd/waterinfos/waterquality/daywater/list"); /*
                                                                                                                                    * URL
                                                                                                                                    */
                        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
                                + "=xUaCQjbwxEcfxfh7%2FHktyNRhuSsKDSauOvqlvcPI01iUuOpKUc%2FO0cvLrIHqa5DCB%2F1oYsEj1Y6nqvssEstEWg%3D%3D"); /*
                                                                                                                                            * Service
                                                                                                                                            * Key
                                                                                                                                            */
                        urlBuilder.append("&" + URLEncoder.encode("sgccd", "UTF-8") + "="
                                + URLEncoder.encode(sgccd, "UTF-8")); /* 지자체코드 */
                        urlBuilder.append("&" + URLEncoder.encode("sitecd", "UTF-8") + "="
                                + URLEncoder.encode(sitecd, "UTF-8")); /* 정수장코드 */
                        urlBuilder.append("&" + URLEncoder.encode("stdt", "UTF-8") + "="
                                + URLEncoder.encode(stdt, "UTF-8")); /* 조회시작일 */
                        urlBuilder.append("&" + URLEncoder.encode("eddt", "UTF-8") + "="
                                + URLEncoder.encode(eddt, "UTF-8")); /* 조회종료일 */
                        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
                                + URLEncoder.encode(numOfRows, "UTF-8")); /* 줄수 */
                        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
                                + URLEncoder.encode(pageNo, "UTF-8")); /* 페이지번호 */
                        urlBuilder.append("&_type=json");
                        String serviceKey = "xUaCQjbwxEcfxfh7%2FHktyNRhuSsKDSauOvqlvcPI01iUuOpKUc%2FO0cvLrIHqa5DCB%2F1oYsEj1Y6nqvssEstEWg%3D%3D";

                        String urlStr = "http://opendata.kwater.or.kr/openapi-data/service/pubd/waterinfos/waterquality/weekwater/list";
                        urlStr += "?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey;
                        urlStr += "&" + URLEncoder.encode("sgccd", "UTF-8") + "=" + sgccd;
                        urlStr += "&" + URLEncoder.encode("sitecd", "UTF-8") + "=" + sitecd;
                        urlStr += "&" + URLEncoder.encode("stdt", "UTF-8") + "=" + stdt;
                        urlStr += "&" + URLEncoder.encode("eddt", "UTF-8") + "=" + eddt;
                        urlStr += "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + numOfRows;
                        urlStr += "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo;
                        urlStr += "&" + URLEncoder.encode("_type", "UTF-8") + "=json";

                        URL url = new URL(urlStr);

                        System.out.println(url);
                        String line = "";
                        String result = "";

                        BufferedReader br;
                        br = new BufferedReader(new InputStreamReader(url.openStream()));
                        while ((line = br.readLine()) != null) {
                            result = result.concat(line);
                            // System.out.println(line);
                        }

                        // JSON parser 만들어 문자열 데이터를 객체화한다.
                        JSONParser parser = new JSONParser();
                        JSONObject obj;

                        obj = (JSONObject) parser.parse(result);
                        // System.out.println(obj);
                        List<WeekDTO> wList = new ArrayList<>();

                        JSONObject parse_response = (JSONObject) obj.get("response"); // response 로 부터 body 찾아옵니다.
                        JSONObject parse_body = (JSONObject) parse_response.get("body"); // body 로 부터 items 받아옵니다.
                        String totalC = (String) parse_body.get("totalCount").toString();
                        System.out.println("totalc=" + totalC);
                        
                        
                        if(totalC.equals("0")) {
                        	System.out.println("지방=======");
                        	request.setAttribute("mesg", "현재 서비스를 제공하고 있지 않습니다. 월별 조회를 이용하세요");
                        	nextPage = "Weeksearch.jsp";
                        	//RequestDispatcher dis = request.getRequestDispatcher("Weeksearch.jsp");
                            //dis.forward(request, response);	
                        	
                        }else if (totalC.equals("1")) {
                            System.out.println("들어왔");
                            JSONObject parse_items = (JSONObject) parse_body.get("items"); // items로 부터 itemlist 를 받아오기 itemlist
                            System.out.println(parse_items);                                                              // : 뒤에 [ 로 시작하므로 jsonarray이다
                            System.out.println(parse_items.size());
                            JSONObject weekWaterQlt = (JSONObject) parse_items.get("item");

                            WeekDTO wDTO = new WeekDTO();
                            String cltdt = (String) weekWaterQlt.get("cltdt").toString();
                            System.out.println(cltdt);
                            String bacteria = (String) weekWaterQlt.get("data1").toString(); // 일반세균
                            // System.out.println(data1);
                            String TotalColita = (String) weekWaterQlt.get("data2").toString(); // 총대장균군
                            // System.out.println(data2);
                            String Coli = (String) weekWaterQlt.get("data3").toString(); // 대장균/분원성대장균
                            // System.out.println(data3);
                            String Ammonia = (String) weekWaterQlt.get("data4").toString(); // 암모니아성질소
                            // System.out.println(data4);

                            String Nnitrogen = (String) weekWaterQlt.get("data5").toString(); // 질산성질소
                            // System.out.println(data5);
                            String PotaPerm = (String) weekWaterQlt.get("data6").toString(); // 과망간산칼륨소비량
                            System.out.println(PotaPerm);
                            String Evaresi = (String) weekWaterQlt.get("data7").toString(); // 증발잔류물
                            System.out.println(Evaresi);

                            // String sgcnm = (String) weekWaterQlt.get("sgcnm"); // 지자체명
                            // //System.out.println(sgcnm);
                            // String sitenm = (String) weekWaterQlt.get("sitenm");
                            // //System.out.println(sitenm);

                            wDTO.setCltdt(cltdt);
                            wDTO.setBacteria(bacteria);
                            wDTO.setTotalColi(TotalColita);
                            wDTO.setColi(Coli);
                            wDTO.setAmmonia(Ammonia);
                            wDTO.setNnitrogen(Nnitrogen);
                            wDTO.setEvaresi(Evaresi);

                            wList.add(wDTO);

                        } else {
                        	JSONObject parse_items = (JSONObject) parse_body.get("items"); // items로 부터 itemlist 를 받아오기 itemlist
                            System.out.println(parse_items);                                                              // : 뒤에 [ 로 시작하므로 jsonarray이다
                            System.out.println(parse_items.size());
                            JSONArray parse_item = (JSONArray) parse_items.get("item");

                            JSONObject weekWaterQlt;

                            for (int i = 0; i < parse_item.size(); i++) {
                                weekWaterQlt = (JSONObject) parse_item.get(i);
                                WeekDTO wDTO = new WeekDTO();
                                String cltdt = (String) weekWaterQlt.get("cltdt").toString();
                                System.out.println(cltdt);
                                String bacteria = (String) weekWaterQlt.get("data1").toString(); // 일반세균
                                // System.out.println(data1);
                                String TotalColita = (String) weekWaterQlt.get("data2").toString(); // 총대장균군
                                // System.out.println(data2);
                                String Coli = (String) weekWaterQlt.get("data3").toString(); // 대장균/분원성대장균
                                // System.out.println(data3);
                                String Ammonia = (String) weekWaterQlt.get("data4").toString(); // 암모니아성질소
                                // System.out.println(data4);

                                String Nnitrogen = (String) weekWaterQlt.get("data5").toString(); // 질산성질소
                                // System.out.println(data5);
                                String Evaresi = (String) weekWaterQlt.get("data7").toString(); // 증발잔류물
                                System.out.println(Evaresi);

                                // String sgcnm = (String) weekWaterQlt.get("sgcnm"); // 지자체명
                                // //System.out.println(sgcnm);
                                // String sitenm = (String) weekWaterQlt.get("sitenm");
                                // //System.out.println(sitenm);

                                wDTO.setCltdt(cltdt);
                                wDTO.setBacteria(bacteria);
                                wDTO.setTotalColi(TotalColita);
                                wDTO.setColi(Coli);
                                wDTO.setAmmonia(Ammonia);
                                wDTO.setNnitrogen(Nnitrogen);
                                wDTO.setEvaresi(Evaresi);

                                wList.add(wDTO);
                                
                            }
                        }

                        request.setAttribute("wList", wList);
                        nextPage = "Weeksearch.jsp";
                       
                        //RequestDispatcher dis = request.getRequestDispatcher("Weeksearch.jsp");
                        //dis.forward(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("catch실행===");
                        request.setAttribute("mesg", "조회할 지역을 모두 선택하세요."); //mseg 수정!!!!!!!
                    	nextPage = "Weeksearch.jsp";
                    }
                }
        } else {
        	request.setAttribute("mesg", "조회할 지역을 모두 선택하세요.");
        	nextPage = "Weeksearch.jsp";
        	//RequestDispatcher dis = request.getRequestDispatcher("Weeksearch.jsp");
            //dis.forward(request, response);
        }
        System.out.println(nextPage);
        RequestDispatcher dis = request.getRequestDispatcher(nextPage);
        dis.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
