<%@ page import = "com.Station" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>


<title>Electro Grid Sub-Stations</title>
</head>
<body>



<h1>Sub-Station Management</h1>
<form id="formStation" name="formStation">
 Station code:
 		<input id="stationCode" name="stationCode" type="text" class="form-control form-control-sm">
 		
 		<br> Station name:
 		<input id="stationName" name="stationName" type="text" class="form-control form-control-sm">
 		
 		<br> Location:
 		<input id="location" name="location" type="text" class="form-control form-control-sm">
 		
 		<br> Zone:
 		<input id="zone" name="zone" type="text" class="form-control form-control-sm">
 		
 		<br> Province:
 		<input id="province" name="province" type="text" class="form-control form-control-sm">
 		
 		<br> Capacity:
 		<input id="capacity" name="capacity" type="text" class="form-control form-control-sm">
 		
 		<br> Status:
 		<input id="status" name="status" type="text" class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
				 class="btn btn-primary">
				<input type="hidden" id="hideStationIDSave" name="hideStationIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
    <div id="alertError" class="alert alert-danger"></div>

<br>

<br><br>
	<div id="divStationGrid">
	<%
	Station stationObj = new Station();
			out.print(stationObj.readStations());
	%>
	
	 
	</div>

</body>
</html>