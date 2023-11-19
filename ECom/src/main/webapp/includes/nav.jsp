<%@page import="java.util.*"%>
<%@ page import="com.EcommerceStore.model.*" %>
<%@ page import="com.EcommerceStore.dao.*" %>
<%@ page import="com.EcommerceStore.connection.*" %>

<%
User auth = (User) request.getSession().getAttribute("auth");
if(auth != null){
	request.setAttribute("auth", auth);
}

ArrayList<Cart> cartList = (ArrayList) session.getAttribute("cart-list");
List<Cart> cartProduct = null;

if(cartList != null){
	ProductDao pDao = new ProductDao(DBconnection.getConnection());
	cartProduct = pDao.getCartProducts(cartList);
	
	request.setAttribute("cart_list", cartList);
}
%>


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
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart <span class="badge badge-danger">${cart_list.size() }</span></a></li>
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
		