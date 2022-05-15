$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
 	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateStationForm();
	if (status != true)
 	{
 		$("#alertError").text(status);
 		$("#alertError").show();
 		return;
 	}
	// If valid------------------------
	var type = ($("#hideStationIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
	{
		url : "StationAPI",
		type : type,
		data : $("#formStation").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onStationSaveComplete(response.responseText, status);
		}
	});
	
});

function onStationSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divStationGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
 $("#hideStationIDSave").val("");
 $("#formStation")[0].reset();
}
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hideStationIDSave").val($(this).closest("tr").find('td:eq(0)').text());
 $("#stationID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#StationCode").val($(this).closest("tr").find('td:eq(0)').text());
 $("#StationName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Location").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Zone").val($(this).closest("tr").find('td:eq(3)').text());
 $("#Province").val($(this).closest("tr").find('td:eq(4)').text());
 $("#Capacity").val($(this).closest("tr").find('td:eq(5)').text());
 $("#Status").val($(this).closest("tr").find('td:eq(6)').text());
});

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "StationAPI",
		 type : "DELETE",
		 data : "stationID=" + $(this).attr("stationID"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onStationDeleteComplete(response.responseText, status);
		 }
		 });
		});
		
function onStationDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divStationGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

// CLIENT-MODEL================================================================
function validateStationForm()
{
// Code-----------------------------------
	if ($("#StationCode").val().trim() == "")
	 {
	 	return "Insert Station Code!!";
	 }
	 
	// Name----------------------------------
	if ($("#StationName").val().trim() == "")
	 {
	 	return "Insert Station Name!!!";
	 } 
	 
	// Location----------------------------------
	if ($("#Location").val().trim() == "")
	 {
	 	return "Insert Station Location!!!";
	 } 
	
	// Zone-------------------------------
	if ($("#Zone").val().trim() == "")
	 {
	 	return "Insert Station Zone!!";
	 }
	 
	 // Province------------------------
	if ($("#Province").val().trim() == "")
	 {
	 	return "Insert Station Province!!!";
	 }
	
	 // Capacity------------------------
	if ($("#Capacity").val().trim() == "")
	 {
	 	return "Insert Station's Capacity'!!!";
	 }
	
	 // Status------------------------
	if ($("#Status").val().trim() == "")
	 {
	 	return "Insert Station's Status'!!!";
	 }
return true;
}




