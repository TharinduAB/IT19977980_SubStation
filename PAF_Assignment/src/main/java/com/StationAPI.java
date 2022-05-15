package com;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StationAPI
 */
@WebServlet("/StationAPI")
public class StationAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Station stationObj; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StationAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = stationObj.insertStation(
				request.getParameter("stationCode"),
				request.getParameter("stationName"),
				request.getParameter("location"),
				request.getParameter("zone"),
				request.getParameter("province"),
				request.getParameter("capacity"),
				request.getParameter("status"));
				response.getWriter().write(output);
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = stationObj.updateStation(
				paras.get("stationID").toString(),
				paras.get("stationCode").toString(),
				paras.get("stationName").toString(),
				paras.get("location").toString(),
				paras.get("zone").toString(),
				paras.get("province").toString(),
				paras.get("capacity").toString(),
				paras.get("status").toString());
				response.getWriter().write(output);
	}
	
	
	

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		String output = stationObj.deleteStation(paras.get("id").toString());
		response.getWriter().write(output);
	}
	
	
	
	private static Map getParasMap(HttpServletRequest request)
	{
		 Map<String, String> map = new HashMap<String, String>();
		 try
		 {
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			 String queryString = scanner.hasNext() ?
			 scanner.useDelimiter("\\A").next() : "";
			 scanner.close();
			 String[] params = queryString.split("&");
			 for (String param : params)
			 { 
		
			String[] p = param.split("=");
			 map.put(p[0], p[1]);
			 }
		 }
		 catch (Exception e)
		 {
			 System.out.print("Error!!");
		 }
		return map;
	}

}
