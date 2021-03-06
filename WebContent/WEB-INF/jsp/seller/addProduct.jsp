<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                                <label style="font-size: 1.2em" class="label label-success"><span>${userName}<span></span></label>
                            </div>
                        </div>

                        <!-- Add product -->
                        <div class="row" style="margin-top: 120px">
                            <div class="col-xs-offset-2 col-xs-8 panel">

                                <div class="panel panel-info">
                                    <div class="panel-heading">
                                        <h3>Add Product</h3>
                                    </div>
                                    
                                    
                                    <form action="" method="post" class="panel-body" enctype="multipart/form-data">
                                    
                                    	<%-- <input type="hidden" value="${product.pid}" name="pid"> --%>
                                    	
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="pname" placeholder="Name of your product" required="required"/>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <select class="col-xs-3" name="available" required="required">
                                                    <optgroup label="Is it Available now ?">
                                                        <option value="1" selected="selected">Yes</option>
                                                        <option value="0">No</option>
                                                    </optgroup>
                                                </select>
                                                
                                                <select class="col-xs-2 col-xs-offset-1" name="is_featured" required="required">
                                                    <optgroup label="Featured product ?">
                                                        <option value="1">Yes</option>
                                                        <option value="0" selected="selected">No</option>
                                                    </optgroup>
                                                </select>

                                                <select class="col-xs-5 col-xs-offset-1" name="cid" required="required">
                                                    <c:forEach items="${parentCat}" var="parent">
                                                    	<c:if test="${parent.main_catid == 0}">
                                                    	<optgroup label="${parent.cname}">
                                                    		<c:forEach items="${childCat}" var="child">
                                                   			<c:if test="${child.main_catid == parent.cid}">
                                                   			<option value="${child.cid}">${child.cname}</option>
                                                   			</c:if>
                                                    		</c:forEach>
                                                    	</optgroup>
                                                    	</c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group" style="clear: both;">
                                            <input type="file" class="form-control" name="pic" required="required"/>
                                        </div>
                                        <div class="form-group">
                                            <textarea class="form-control" name="brand_name" placeholder="Short description"></textarea>
                                        </div>
                                        <div class="form-group col-xs-4 col-xs-offset-4">
                                            <label class="label ${status}">${statusMsg}</label>
                                        </div>
                                        <div class="form-group" style="clear: both;">
                                            <input type="submit" class="col-xs-4 col-xs-offset-4 btn btn-info" value="Add Product"/>
                                        </div>
                                    </form>
                                </div>

                            </div>
                        </div>
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