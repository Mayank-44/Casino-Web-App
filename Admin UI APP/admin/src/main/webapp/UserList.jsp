<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="com.nagarro.model.Customer"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body style="background:#eafaf1">

<nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="position:sticky;top:0;z-index:100">
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="Home.jsp">Home</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="display.jsp">Register</a>
    </li>
    <li class="nav-item active">
      <a class="nav-link" href="UserList">User List</a>
    </li>
  </ul>
</nav>

<br>
<form action="SearchFilter" style="z-index:0">
  <div class="form-row" style="margin:10px">
    <div class="col">
      <input type="text" id="name" name="Name" class="form-control" placeholder="Name">
    </div>
    <div class="col">
      <input type="number" id="contact" name="Contact" class="form-control" placeholder="Contact">
    </div>
  </div>
  <div class="form-row" style="margin:10px">
    <div class="col">
      <input type="email" id="email" name="Email" class="form-control" placeholder="Email Id">
    </div>
    <div class="col">
      <input type="submit" class="form-control btn btn-dark" value="Search">
    </div>
  </div>
</form>

	<br>
	<div>
		<div
			style="margin-right: 20px; margin-left: 20px; background: #e8f8f5">
			<table class="table">
				<thead>
					<tr style="background: #58d68d">
						<th scope="col">S.No</th>
						<th scope="col" style="padding-left: 20px;">Name</th>
						<th scope="col" style="padding-left: 30px;">DOB</th>
						<th scope="col" style="padding-left: 30px;">Contact</th>
						<th scope="col" style="padding-left: 110px;">Email</th>
						<th scope="col" style="padding-left: 30px;">Balance (in Rs.)</th>
						<th scope="col" style="padding-left: 20px;">Recharge</th>
					</tr>
				</thead>
				<tbody>

					<c:set var="i" value="${0}" />
					<c:forEach items="${customers}" var="customer" varStatus="vs">
						<c:set var="i" value="${i+1}" />

						<tr>

							<td align="center"><c:out value=" ${i}" /></td>
							<td align="center"><c:out value=" ${customer.customerName}" /></td>
							<td align="center"><c:out value=" ${customer.dateOfBirth}" /></td>
							<td align="center"><c:out value=" ${customer.contactNumber}" /></td>
							<td align="center"><c:out value=" ${customer.emailId}" /></td>
							<td align="center"><c:out value=" ${customer.accountBalance}" /></td>
							<!--  <td align="center"><c:out value=" ${}" /></td>-->
							<td align="center">

								<div class="container">
							  <!-- Button to Open the Modal -->
							  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal${vs.index}">
							    +
							  </button>
							
							  <!-- The Modal -->
							  <div class="modal" id="myModal${vs.index}">
							    <div class="modal-dialog">
							      <div class="modal-content">
							      
							        <!-- Modal Header -->
							        <div class="modal-header">
							          <h4 class="modal-title">Recharge Balance</h4>
							          <button type="button" class="close" data-dismiss="modal">&times;</button>
							        </div>
							        
							        <!-- Modal body -->
							        <div class="modal-body">
							          <form name = "form" onsubmit="return validateform()" action="Recharge" method="post" style="margin-top: 10px;">
									<input type="hidden" name="uniqueUserId" value="${customer.uniqueUserId}">
									<Label>Amount</Label><Label style="margin-left:50px;"> Rs.</Label><input type="number" min="0" step=".01" id="recharge" name="recharge">
									<br>
									<button type="submit" data-toggle="tooltip" title="Recharge"
										style="background: #f5cba7; color: black;"
										class="btn btn-secondary" name="id" value="${customer.uniqueUserId}">
										Recharge</button>
								</form>
							        </div>
							        
							      </div>
							    </div>
							  </div>
							  
							</div>
							</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- <div style="padding-top: 20px;">
		<div>
			<form action="logout" method="post">
				<input type="submit" style="display: table; margin: 0 auto"
					class="btn btn-dark btn-lg " value="LOGOUT">
			</form>
		</div>
	</div> -->

	<div style="margin-top: 50px;"></div>
</body>
</html>