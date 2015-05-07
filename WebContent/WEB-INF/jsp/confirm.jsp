<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title}</title>
	<%@include file="cssinclude.jsp" %>
</head>
<body>
	<%@include file="header.jsp" %>
	
	
	<div class="row" style="margin-top: 50px;margin-bottom: 150px;">
     <div class="col-lg-8 col-lg-offset-2">
     	<h2 style="text-align: center;color: #008aff;">${msg}</h2><br>
         <div class="row">
             <div class="col-lg-2">
                 <h5>Product Image</h5>
             </div>
             <div class="col-lg-4">
                 <h5>Details</h5>
             </div>
             <div class="col-lg-2">
                 <h5>Amount</h5>
             </div>
             <div class="col-lg-2">
                 <h5>Total Price</h5>
             </div>
             <div class="col-lg-2">
                 <h5>Action</h5>
             </div>
         </div>
         <hr/>

	<form action="${pageContext.request.contextPath}/confirm" method="post">
      	<c:forEach items="${chart}" var="chart">
        <div class="row">
            <div class="col-lg-2">
                <img class="cartListImage" src="${pageContext.request.contextPath}/resources/product_images/${chart.pid}.jpg" alt="No image">
            </div>
            <div class="col-lg-4">
                <h4><a href="">${chart.pname}</a></h4>
                <p>Package ID: ${chart.pack_id}</p>
                <input type="hidden" name="pack_id" value="${chart.pack_id}">
            </div>
            <div class="col-lg-2">
                <p>${chart.total_ammount}</p>
                <input type="hidden" name="ammount" value="${chart.total_ammount}">
            </div>
            <div class="col-lg-2">
                <h4>$ ${chart.total_price}</h4>
            </div>
            <div class="col-lg-2">
                <a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>
            </div>
        </div>
        <hr/>
        </c:forEach>
        <div class="row">
        	<div class="col-lg-4 col-lg-offset-2">
        		<h4 style="margin:0px;">Total Ammount : </h4>	
        	</div>
        	<div class="col-lg-2 col-lg-offset-2">
        		<h3 style="margin:0px;">$ ${totalPrice}</h3>	
        	</div>
        	<input type="hidden" name="total" value="${totalPrice}">
        	<div class="col-lg-1 col-lg-offset-1">
        		<input type="submit" value="Confirm" class="btn btn-success" style="float: right;" >	
        	</div>
        </div>
        </form>
	    </div>
	</div>
	
	
	<%@include file="footer.jsp" %>
	<%@include file="jsinclude.jsp" %>
</body>
</html>