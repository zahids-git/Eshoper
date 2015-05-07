<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
	<title>${title }</title>
	<%@include file="cssinclude.jsp" %>
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
                                <label style="font-size: 1.2em" class="label label-success"><span>${userName}<span></label>
                            </div>
                        </div>
                        
                        <div style="margin-top: 100px"></div>
                        
                        <!--Update product-->
	                    <table class="table table-bordered">
                        <thead style="background: #407cc9;color: #fff">
                            <tr>
                                <th>#</th>
                                <th>Product Name</th>
                                <th>Category Name</th>
                                <th>Available</th>
                                <th>Is feature</th>
                                <th>Short description</th>
                                <th>Update/Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                           <c:forEach items="${product}" var="product" varStatus="status">
                              <tr>
                                <form action="<%=PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.SELLER_DELETE_PRODUCT%>" method="post">
                                	<input type="hidden" name="pid" value="${product.pid}">
                                	<th scope="row">${status.count}</th>
                                	<td>${product.pname}</td>
                                	<td>${product.cname}</td>
                                	<c:if test="${product.available == 1}">
                                		<td><span class="label label-success">Yes</span></td>
                                	</c:if>
                                	<c:if test="${product.available == 0}">
                                		<td><span class="label label-danger">No</span></td>
                                	</c:if>
                                	
                                	<c:if test="${product.is_featured == 1}">
                                		<td><span class="label label-success">Yes</span></td>
                                	</c:if>
                                	<c:if test="${product.is_featured == 0}">
                                		<td><span class="label label-warning">No</span></td>
                                	</c:if>
                                	
                                	<td>${product.brand_name}</td>
                                	
                                	<td><a href="?pid=${product.pid}"><span class="btn btn-info">Update</span></a><input style="margin-left: 10px" type="submit" class="btn btn-danger" value="Delete"/></td>
                               	</form>
                              </tr>
                           </c:forEach>
                           
                        </tbody>
                    </table>
                    
                   </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="jsinclude.jsp" %>
    <script>
        $("#menu-toggle").click(function(e) {
            e.preventDefault();
            $("#wrapper").toggleClass("active");
        });
    </script>

</body>