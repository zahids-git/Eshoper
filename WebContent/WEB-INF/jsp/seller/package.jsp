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
                                <label style="font-size: 1.2em" class="label label-success"><span>${userName}<span></label>
                            </div>
                        </div>

						 <div style="margin-top: 80px"></div>

                        <div class="row">
                            <div class="col-xs-8 col-xs-offset-2">
                            
                            
                            <div class="row">
                                <select onchange="pidChange()" id="pidSelection" name="pid" class="form-control" >
                                    <optgroup label="Select one product">
                                        <c:forEach items="${Allproduct}" var="product" varStatus="start">
	                                        <c:if test="${pid == product.pid}"><option selected="selected" value="${product.pid}">${product.pname}</option></c:if>
	                                       	<c:if test="${!(pid == product.pid)}"><option value="${product.pid}">${product.pname}</option></c:if>
                                        </c:forEach>
                                    </optgroup>
                                </select>
                            </div>
                            
                            <c:if test="${totalPackage > 0}">
                            
                            <form action="<%= PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.SELLER_UPDATE_PACKAGE%>" method="post">
                               <div class="row panel panel-warning" style="margin-top: 25px">
                                   <div class="panel-heading">
                                       Update Package
                                   </div>
                                   <div class="panel-body">

										<input type="hidden" value="${pid}" id="updatePid" name="pid">
										
									   <c:forEach items="${packageForProduct}" var="packageList" varStatus="packageFirst" >									   		
									   		
									   		<c:choose>
									   			<c:when test="${packageFirst.first}"></c:when>
									   			<c:otherwise><hr/></c:otherwise>
									   		</c:choose>
									   		
								   		<input type="hidden" name="packId[]" value="${packageList.packId}">
									   											   											   		
                                       <div class="col-xs-3">
                                           <input value="${packageList.total_number}" type="number" name="total_number[]" class="form-control" step="any" placeholder="Total Number"/>
                                       </div>

                                       <div class="col-xs-3">
                                           <input value="${packageList.total_price}" type="number" name="total_price[]" class="form-control" step="any" placeholder="Total Amount"/>
                                       </div>

                                       <div class="col-xs-3">
                                           <input value="${packageList.pdate}" type="number" name="day[]" class="form-control" step="any" placeholder="Total Time"/>
                                       </div>
                                       
                                       <div class="col-xs-3">
                                           <select name="dayType[]" class="form-control">
                                               <optgroup label="Select one product">
                                               <c:if test='${packageList.dayType == "days"}'>
                                               		<option selected="selected" value="days">Days</option>
                                               		<option value="weeks">Weeks</option>
                                               		<option value="months">Month</option>
                                               </c:if>
                                               <c:if test='${packageList.dayType == "weeks"}'>
                                              		<option value="days">Days</option>
                                              		<option selected="selected" value="weeks">Weeks</option>
                                               		<option value="months">Month</option>
                                           		</c:if>
                                               <c:if test='${packageList.dayType == "months"}'>
                                               		<option value="days">Days</option>
                                               		<option value="weeks">Weeks</option>
                                               		<option selected="selected" value="months">Month</option>
                                               </c:if>
                                               </optgroup>
                                           </select>
                                       </div>
                                       <div style="clear: both"></div>
										</c:forEach>
                                   </div>
                               </div>

                               <div class="row">
                                   <div class="col-xs-3 col-xs-offset-9">
                                       <input type="submit" name="submit" class="form-control btn btn-info" value="Update"/>
                                   </div>
                               </div>
                            </form>
                            
                            </c:if>
                            
                            
                            <form action="" method="post">       
                                 <div class="row panel panel-default" style="margin-top: 25px">
                                     <div class="panel-heading">
                                         New Package
                                     </div>
                                     <div class="panel-body">
                                     
										<input type="hidden" value="${pid}" id="newPid" name="pid">

                                         <!--Single package-->
                                         <div class="col-xs-3">
                                             <input type="number" name="total_number[]" class="form-control" step="any" placeholder="Total Number"/>
                                         </div>

                                         <div class="col-xs-3">
                                             <input type="number" name="total_price[]" class="form-control" step="any" placeholder="Total Amount"/>
                                         </div>

                                         <div class="col-xs-3">
                                             <input type="number" name="day[]" class="form-control" step="any" placeholder="Total Time"/>
                                         </div>

                                         <div class="col-xs-3">
                                             <select name="dayType[]" class="form-control">
                                                 <optgroup label="Select one product">
                                                     <option value="days">Days</option>
                                                     <option value="weeks">Weeks</option>
                                                     <option value="months">Month</option>
                                                 </optgroup>
                                             </select>
                                         </div>
                                         <div style="clear: both"></div>

                                         <!--Add new package-->
                                         <div id="addPackageArea"></div>

                                     </div>
                                 </div>

                                 <div class="row">
                                     <div class="col-xs-3">
                                         <a href="#" onclick="addPackage()" class="btn btn-info"> <span class="glyphicon glyphicon-plus"> Add New</span></a>
                                     </div>
                                     <div class="col-xs-3 col-xs-offset-6">
                                         <input type="submit" name="submit" class="form-control btn btn-success" value="Submit"/>
                                     </div>
                                 </div>
                             </form>
                             
                             
                            </div>
                        </div>
                   </div>
                </div>
            </div>
        </div>
    </div>
    <div style="margin-bottom: 50px;"></div>
    
    <%@include file="jsinclude.jsp" %>
    <script>
        $("#menu-toggle").click(function(e) {
            e.preventDefault();
            $("#wrapper").toggleClass("active");
        });
        function pidChange(){
        	//alert("asd");
        	var pid = document.getElementById("pidSelection").value;
        	window.location = "/shop/dashboard/package?pid="+pid;
        	document.getElementById("updatePid").value = pid;
        	document.getElementById("newPid").value = pid;
        	
        	
        }
    </script>

</body>