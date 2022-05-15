package com;

import java.sql.*; 

public class Station {
	
	//A common method to connect to the DB
	private Connection connect()
	 {
		Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "root");
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();
		 }
	 return con;
	 } 
	
	public String insertStation(String code, String name, String location, String zone, String province, String capacity, String status)
	 {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
	 
	 // create a prepared statement
	 String query = " insert into stations (`stationID`,`stationCode`,`stationName`,`location`,`zone`,`province`,`capacity`,`status`)" + " values (?, ?, ?, ?, ?,?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, code);
	 preparedStmt.setString(3, name);
	 preparedStmt.setString(4, location);
	 preparedStmt.setString(5, zone);
	 preparedStmt.setString(6, province);
	 preparedStmt.setString(7, capacity);
	 preparedStmt.setString(8, status);
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 	String newStations = readStations();
	 	output = "{\"status\":\"success\", \"data\": \"" + newStations + "\"}";
	 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Station.\"}";
		 System.err.println(e.getMessage());
	 }
	 
	 return output;
	 }
	
	public String readStations()
	 {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
	 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Station Code</th><th>Station Name</th>" + "<th>Location</th>" + "<th>Zone</th>" + "<th>Province</th>" + "<th>Capacity (kW)</th>" + "<th>Status</th> </tr>";

			String query = "select * from stations";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
	 
			// iterate through the rows in the result set
			while (rs.next())
			{
				String stationID = Integer.toString(rs.getInt("stationID"));
				String stationCode = rs.getString("stationCode");
				String stationName = rs.getString("stationName");
				String location = rs.getString("location");
				String zone = rs.getString("zone");
				String province = rs.getString("province");
				String capacity = rs.getString("capacity");
				String status = rs.getString("status");
				// Add into the html table
				output += "<tr><td>" + stationCode + "</td>";
				output += "<td>" + stationName + "</td>";
				output += "<td>" + location + "</td>";
				output += "<td>" + zone + "</td>";
				output += "<td>" + province + "</td>";
				output += "<td>" + capacity + "</td>";
				output += "<td>" + status + "</td>";
				//buttons
				output += "<td><input name='btnUpdate' "
    					+ "type='button' value='Update' "
    					+ " class='btnUpdate btn btn-secondary'></td>"
    					+ "<td><input name='btnRemove' "
    					+ "type='button' value='Remove' "
    					+ "class='btnRemove btn btn-danger' "
    					+ "data-id='"
    			 + stationID + "'>" + "</td></tr>";
			}
			con.close();
	 
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the stations.";
			System.err.println(e.getMessage());
		}
		return output;
	 }
	
	public String updateStation(String ID, String code, String name, String location, String zone, String province, String capacity, String status)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
		 
		if (con == null)
		 {
			return "Error while connecting to the database for updating."; 
			}
		 // create a prepared statement
		 String query = "UPDATE stations SET stationCode=?,stationName=?,location=?,zone=?,province=?,capacity=?,status=? WHERE stationID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, code);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, location);
		 preparedStmt.setString(4, zone);
		 preparedStmt.setString(5, province);
		 preparedStmt.setString(6, capacity);
		 preparedStmt.setString(7, status);
		 preparedStmt.setInt(8, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newStations = readStations();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newStations + "\"}";
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
		public String deleteStation(String stationID)
		 {
			String output = "";
		 try
		 {
		 Connection con = connect();
		 
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting."; 
			 }
		 
		 // create a prepared statement
		 String query = "delete from stations where stationID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(stationID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newStations = readStations();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newStations + "\"}";
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while deleting the station.\"}";
			 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
}