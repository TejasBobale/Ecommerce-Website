<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.EcommerceStore.model.*" %>
    
    
 <%

%>
<!DOCTYPE html>
<html>
<head>

<title>Register</title>
<%@include file = "includes/head.jsp" %>

</head>
<body>
	
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Sign up</div>
			<div class="card-body">
				<form action="register-page" method="post">
					<div class="form-group">
						<label>Name</label>
						
						<input type="text" class="form-control" name="login-name" placeholder="Enter your name" required>						
					</div>
					<br>
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
						<button type="submit" class="btn btn-secondary">Register</button>
					</div>
					<div style="text-decoration: none;font-weight: bold;text-align: center;margin-top: 30px;">
			            <p>Already have an account. <a href="login.jsp" id="registerLink" style="color: #007bff;">Login</a></p>
			        </div>
				</form>
			</div>
		</div>
	</div>
	
	<%@include file = "includes/footer.jsp" %>
	
	
</body>
</html>
