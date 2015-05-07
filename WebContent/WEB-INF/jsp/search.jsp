<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title}</title>
	<%@include file="cssinclude.jsp" %>
</head>
<body>

	<%@include file="header.jsp" %>
	
	
	
	<section style="margin-top: 20px">
		<div class="container">
			<div class="row">
				<div class="col-sm-3 ">
                    <!-- Filter option -->
                    <div class="panel panel-success panel-body">
                    	<div class="text-center">
		                    <h4>Advance search</h4>
		                </div><br/>
		                <form action="" method="get">
                        <input value="" type="text" name="productName" placeholder="Product Name" class="form form-control"/>
                        <hr/>
                        <select name="catId" id="" multiple style="height: 130px;">
                        	<option value="0" selected="selected">All Category</option>
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
                        <hr/>
                        <input name="priceRange" type="text" class="span2 form form-control" value="0,500" data-slider-min="0" data-slider-max="500" data-slider-step="5" data-slider-value="[0,500]" id="sl2" placeholder="Price Range" ><br />
                        <b class="pull-left">$0</b> <b class="pull-right">$500</b>
                        <br/><hr/>
                        <input name="numberOfProduct" type="number" step="any" placeholder="Weigth" class="form form-control"/>
                        <hr/>
                        <input type="submit" class="btn btn-block btn-success"/>
                        </form>
                    </div>
				</div>

				
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">Search Result For {ABC}</h2>

						<c:forEach items="${result}" var="product">
                        <div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="${pageContext.request.contextPath}/resources/product_images/${product.productId}.jpg" alt="${product.productName}" />
										<h2>${product.productPrice}</h2>
                                        <strong>${product.productName}</strong><br/><br/>
										<a href="${pageContext.request.contextPath}<%= PageMapping.PRODUCT_DETAILS %>?pid=${product.productId}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
									</div>

									<div class="product-overlay">
										<div class="overlay-content">
                                            <h2>${product.productPrice}</h2>
                                        	<strong>${product.productName}</strong><br/><br/>
											<a href="${pageContext.request.contextPath}<%= PageMapping.PRODUCT_DETAILS %>?pid=${product.productId}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
										</div>
									</div>
								</div>
								<div class="choose">
									<ul class="nav nav-pills nav-justified">
										<li><a href="#"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
										<li><a href="${pageContext.request.contextPath}<%= PageMapping.PRODUCT_DETAILS %>?pid=${product.productId}"><i class="fa fa-plus-square"></i>Description</a></li>
									</ul>
								</div>
							</div>
						</div>
						</c:forEach>
                    </div>
				</div>
			</div>
		</div>
	</section>
	
	
	
	
	
	
	<%-- <c:forEach items="${result}" var="product">
		<c:out value="${product.pid }"></c:out>
		<c:out value="${product.pname }"></c:out>
		<c:out value="${product.available }"></c:out>
		<c:out value="${product.brand_name }"></c:out><br/>		
	</c:forEach> --%>
	
	<%@include file="jsinclude.jsp" %>

</body>
</html>