<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.EcommerceStore.model.*" %>
    
    
    <%
		User auth = (User) request.getSession().getAttribute("auth");
		if(auth != null){
			response.sendRedirect("index.jsp");
		}
	%>
<!DOCTYPE html>
<html>
<head>

<title>Login</title>
<%@include file = "includes/head.jsp" %>

</head>
<body>
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="index.jsp">Store</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="index.jsp">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart</a></li>
					<%if(auth != null){%>
						<li class="nav-item"><a class="nav-link disabled" href="orders.jsp">Orders</a>
						</li>
						
						<li class="nav-item"><a class="nav-link disabled" href="log-out">Logout</a>
						</li>
					<%}else{%>
						<li class="nav-item"><a class="nav-link disabled" href="login.jsp">Login</a>
						</li>
					<%}%>
				
			</ul>
		</div>
	</div>
</nav>
	
	
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Email</label>
						
						<input type="email" class="form-control" name="login-email" placeholder="Enter your email" required>						
					</div>
					<br>
					<div class="form-group">
						<label>Password</label>
						<input type="password" class="form-control" name="login-pass" placeholder="Enter your password" required>						
					</div>
					<br>
					<br>
					<div class="text-center">
						<button type="submit" class="btn btn-secondary">Login</button>
					</div>
					<div style="text-decoration: none;font-weight: bold;text-align: center;margin-top: 30px;">
			            <p>Don't have an account? <a href="registerhere.jsp" id="registerLink" style="color: #007bff;">Register</a></p>
			        </div>
				</form>
			</div>
		</div>
	</div>
	<%@include file = "includes/footer.jsp" %>
</body>
</html>