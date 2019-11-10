<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<title>Register</title>
<script src="resources/register.js"></script>
</head>

<body style="background: url(resources/70387.jpg);background-size:1300px 650px;">

<nav id="navbar	" class="navbar navbar-expand-sm bg-light navbar-light">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="Home.jsp">Home</a>
			</li>
			<li class="nav-item active"><a class="nav-link"
				href="display.jsp">Register</a></li>
			<li class="nav-item"><a class="nav-link" href="UserList">User
					List</a></li>
		</ul>
	</nav>
	<div style="margin-right: 50px; margin-left: 50px; margin-top: 20px; opacity:0.9">
		<div class="alert alert-dark" role="alert" style="background: #3498db">
			<div style="display: table; margin: 0 auto">
				<strong>Customer Registration Page</strong>
			</div>
		</div>
	</div>
	
	<div id="div" style="margin-right: 50px; margin-left: 50px; margin-top: 20px; display:none; opacity:0.9">
		<div class="alert alert-dark" role="alert" style="background: #e75480">
			<div style="display: table; margin: 0 auto">
				<strong id="error"></strong>
			</div>
		</div>
	</div>
	
	<form action="register" onsubmit="return validateForm()" name = "registerform" method="post" enctype="multipart/form-data">
		<div style="opacity:0.9">

			<div class="alert alert-primary" role="alert"
				style="margin-right: 50px; margin-left: 50px; padding-left: 10px; padding-right: 10px; padding-top: 10px; padding-bottom: 10px">
				<table class="table">
					<tr>

						<td><strong>Name</strong></td>
						<td><input class="form-control" type="text" name="name"
							placeholder="name" required> </td>

					</tr>
					<tr>
						<td><strong>Contact</strong></td>
						<td><input class="form-control" type="number" name="contact"
							placeholder="contact" required></td>
					</tr>
					<tr>
						<td><strong>Date of Birth</strong></td>
						<td><input class="form-control" type="date" name="dob"
							required></td>
					</tr>
					<tr>
						<td><strong>Email</strong></td>
						<td><input class="form-control" type="email" name="email"
							placeholder="abc@gmail.com" required></td>
					</tr>
					
					<tr>
						<td><strong>Identity Proof</strong></td>
						<td><input class="form-control" type="file" accept="image/*" name="file"
							required></td>
					</tr>
				</table>
			</div>
		</div>
		<div style="padding-top: 20px;">
			<div>
				<input type="submit" style="display: table; margin: 0 auto"
					class="btn btn-dark btn-lg " value="Register">
			</div>
		</div>

	</form>
</body>
</html>

