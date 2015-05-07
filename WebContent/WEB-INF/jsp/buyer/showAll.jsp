<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head lang="en">
	<title>${title }</title>
	<%@include file="../cssinclude.jsp" %>
	<link href="${pageContext.request.contextPath}/resources/css/morris.css" rel="stylesheet">
</head>
<body>


	<div id="wrapper">
		<%@include file="dashbord-sidemenu.jsp" %>
	
	<!-- Page content -->
        <div id="page-content-wrapper">
            <!-- Keep all page content within the page-content inset div! -->
            <div class="page-content inset">
                <div class="row">
                    <div class="col-md-12">

                        <div class="row">
                            <div class="col-xs-4 col-xs-offset-8">
                                <label style="font-size: 1.2em" class="label label-success"><span>${userName}<span></span></label>
                            </div>
                        </div>

                        <!-- Add product -->
                        <div class="row" style="margin-top: 120px">
                            <div class="col-xs-offset-1 col-xs-10">
					            <table class="table table-striped table-condensed">
					                  <thead>
					                  <tr>
					                      <th>Order Id</th>
					                      <th>Order Date</th>
					                      <th>Product Information</th>
					                      <th>Number of Product</th>
					                      <th>Total Price</th>
					                      <th>delivered</th>
					                  </tr>
					              </thead>   
					              <tbody>
					              	<c:forEach items="${order}" var="order">
					                <tr>
				                		<td>#${order.orderId}</td>
				                		<td>${order.orderDate}</td>
				                		<td>${order.productDesc}</td>
				                		<td>${order.numberOfProduct}</td>
				                		<td>$${order.totalPrice}</td>
				                		<c:if test="${order.delivered == 1}"><td><label class="label label-warning">Not Yet </label></td></c:if>
				                		<c:if test="${order.delivered == 0}"><td><label class="label label-success">Yes</label></td></c:if>
			                		</tr>
				                	</c:forEach>                             
					              </tbody>
					            </table>
				            </div>
                            </div>
                        </div>
                   </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../jsinclude.jsp" %>
    <script src="${pageContext.request.contextPath}/resources/js/morris.min.js"></script>
    <script>
        $("#menu-toggle").click(function(e) {
            e.preventDefault();
            $("#wrapper").toggleClass("active");
        });
    </script>

</body>