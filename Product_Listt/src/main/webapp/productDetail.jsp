<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="com.kh.product.Product" %>
    <%@ page import ="com.kh.product.ProductDAO" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>제품 상세 페이지</title>
	</head>
	<body>
		<h1>제품 상세 정보</h1>
		<%
			ProductDAO productDAO = new ProductDAO();
			// String = id 값을 가지고 오겠다.
			String productIdValue = request.getParameter("productId");
			int productId = Integer.parseInt(productIdValue);
			
			
			// DAO 작업
			Product product = new Product();
			
			product = productDAO.getProductId(productId);
		%>
		
		<p>제품 ID : <%= product.getProductId() %></p>
		<p>제품 ID : <%= product.getProductName() %></p>
		<p>제품 ID : <%= product.getCategory() %></p>
		<p>제품 ID : <%= product.getPrice() %></p>
		<p>제품 ID : <%= product.getStockQuantity() %></p>
	</body>
</html>