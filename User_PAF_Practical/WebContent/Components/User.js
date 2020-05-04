$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();

	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "UserAPI",
		type : type,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});
});

function onItemSaveComplete(response, status) {
	if (status == "success") 
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divItemsGrid").html(resultSet.data);
			
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
	
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}

// UPDATE==========================================
$(document)
		.on(
				"click",
				".btnUpdate",
				function(event) {

					$("#hidItemIDSave").val(
							$(this).closest("tr").find('#hidItemIDUpdate')
									.val());
					$("#UserNic").val(
							$(this).closest("tr").find('td:eq(0)').text());
					$("#UserName").val(
							$(this).closest("tr").find('td:eq(1)').text());
					$("#UserGender").val(
							$(this).closest("tr").find('td:eq(2)').text());
					$("#UserContact").val(
							$(this).closest("tr").find('td:eq(3)').text());
					$("#UserEmail").val(
							$(this).closest("tr").find('td:eq(4)').text());
					$("#UserPassword").val(
							$(this).closest("tr").find('td:eq(5)').text());

				});

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "UserAPI",
		type : "DELETE",
		data : "UserNic=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			
			$("#divItemsGrid").html(resultSet.data);
			
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
//regex for validations================================================================================================================================
var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
var phoneno = /^\d{10}$/;
var passw=  /^[A-Za-z]\w{6,14}$/;

//CLIENTMODEL=============================================================================================================================================
function validateItemForm() {

	// CODE
	if ($("#UserNic").val().trim() == "") {
		return "Insert NIC.";
	}
	if ($("#UserName").val().trim() == "") {
		return "Insert Name.";
	}
	// GENDER
	if ($("#UserGender").val() == "0") {
		return "Select Gender.";
	}
	if ($("#UserContact").val().trim() == "" || !phoneno.test($("#UserContact").val())) {
		return "Insert Valied Contact Number.";
	}
	if ($("#UserEmail").val().trim() == "" || !re.test($("#UserEmail").val())){
	
		return "Insert Valide Email";
	}
	if ($("#UserPassword").val().trim() == "" || !passw.test($("#UserPassword").val())) {
		return "Insert Password between 7 to 16 Characters start with Letter.";
	}

	return true;
}


	

