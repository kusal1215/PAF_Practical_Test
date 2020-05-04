<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="UserPackage.User"%>
<!DOCTYPE html>
<html>
<head>
	<title>Register Form</title>
	<link rel="stylesheet" href="User.css">
	<link href='https://fonts.googleapis.com/css?family=Aclonica' rel='stylesheet'>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/User.js"></script>
    
</head>
<body>	
     <section id="nav-bar">
              <nav class="navbar navbar-expand-lg navbar-light">
              <a class="navbar-brand" href="#"><img src="img/313797.png"></a>
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                  <li class="nav-item">
                    <a class="nav-link" href="">HOME</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="">CONTACT US</a>
                  </li>
                 
                </ul>
              </div>
            </nav>
    </section>
    
    <section id="form-register">
    
            <h1> User Form </h1>
            
            <div class="container">
                    <div class="row">
                        <div class="col-lg-6">                        
                             <div >
                                 <img src="img/undraw_collaborating_g8k8.svg" style="width:100%;"> 
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <form id="formItem" name="formItem" >
                                <div class="form-group">
                                    <label class="form-group" style="font-weight: 700; font-size: 20px;"> Enter NIC No :</label>
                                    <input id="UserNic" name="UserNic" type="text" class="form-control" placeholder="NIC No.">
                                </div>
                                <div class="form-group">
                                    <label class="form-group" style="font-weight: 700; font-size: 20px;"> Enter Username :</label>
                                    <input id="UserName" name="UserName"  type="text" class="form-control" placeholder="Your Name">
                                </div>
                                 <div class="form-group" style="font-weight: 700; font-size: 20px;">
                                     <label for="form-group">Enter Gender :</label><br>
                                     <select id = "UserGender" name = "UserGender" class="btn btn-primary btn-lg dropdown-toggle dropdown-toggle-split" style="width: 100%;" required>
                                           <option value="0">--Select Gender--</option>
                                           <option >Male</option>
                                           <option>Female</option>
                                     </select>
                                </div>
                                  <div class="form-group">
                                    <label class="form-group" style="font-weight: 700; font-size: 20px;"> Enter Phone No :</label>
                                    <input id="UserContact" name="UserContact" type="number" class="form-control" placeholder="Phonr no.">
                                </div>
                                  <div class="form-group">
                                    <label class="form-group" style="font-weight: 700; font-size: 20px;"> Enter Email :</label>
                                    <input id="UserEmail" name="UserEmail" type="Email" class="form-control" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <label class="form-group" style="font-weight: 700; font-size: 20px;"> Enter Password :</label>
                                    <input id = "UserPassword" name = "UserPassword" type="password" class="form-control" placeholder="Password">
                                </div>
                                <div class="form-group">
                                      <input id="btnSave" name="btnSave" type="button" value="Save Details" class="btn btn-outline-primary" style="float: right;">
                                      <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
                                </div> 
                            </form> <br><br>
                                <div id="alertSuccess" class="alert alert-success"></div>
								<div id="alertError" class="alert alert-danger"></div>                         
                        </div>
                    </div>
                     <br>    
		            <div id="divItemsGrid">
							<%
								User itemObj = new User();
								out.print(itemObj.readUsers());
							%>
		            </div> 
            </div>

    </section>	
    
</body>
</html>
