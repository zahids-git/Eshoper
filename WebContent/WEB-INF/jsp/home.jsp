<%@page import="interfaces.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${pageName}</title>
	<%@include file="cssinclude.jsp" %>
</head>
<body>
	<%@include file="header.jsp" %>
	
	<section id="slider"><!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>
						
						<div class="carousel-inner">
						
							<c:forEach items="${slideProduct}" var="slideProduct" varStatus="status">
								<div ${status.first ? 'class="item active"' : 'class="item"' }">
								<div class="col-sm-6">
									<h1><span>${slideProduct.pname}</span></h1>
									<p>${slideProduct.brand_name}</p>
									<a href="details?pid=${slideProduct.pid}" ><button type="button" class="btn btn-default get">Get it now</button></a>
								</div>
								<div class="col-sm-6">
									<img src="${pageContext.request.contextPath}/resources/product_images/${slideProduct.pid}.jpg" alt="${slideProduct.pname}" />
								</div>
							</div>							
							</c:forEach>
							
						</div>
						
						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
					
				</div>
			</div>
		</div>
	</section><!--/slider-->
	
	
	
	<section>
		<div class="container">
			<div class="row">
			
				<!-- Category -->
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Category</h2>
					<div class="panel-group category-products" id="accordian"><!--category-productsr-->
						<c:forEach var="category" items="${catMain}">
						<c:if test="${category.main_catid == 0}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordian" href="#${category.cname}">
									<c:forEach var="ctg" items="${catSub}">
									<c:set var="cid" value="${category.cid}"/>
									<c:set var="mainCid" value="${ctg.main_catid}"/>
										<%	int cid = Integer.parseInt(pageContext.getAttribute("cid").toString());
											int parentCid = Integer.parseInt(pageContext.getAttribute("mainCid").toString());
											if(cid == parentCid){
												out.println("<span class=\'badge pull-right\'><i class=\'fa fa-plus\'></i></span>");
												break;
											}
										%>
									</c:forEach>
										${category.cname}
									</a>
								</h4>
							</div>
							
							<div id="${category.cname}" class="panel-collapse collapse">
								<div class="panel-body">
									<ul>
										<c:forEach var="ctg" items="${catSub}">
							        	<c:if test="${category.cid == ctg.main_catid}">
						        		<li><a href="search?catId=${ctg.cid}">${ctg.cname}</a></li>
							        	</c:if>
								        </c:forEach>
									</ul>
								</div>
							</div>
						</div>
						</c:if>
						</c:forEach>
						
					</div>
				
				</div>
				</div>


				<div class="col-sm-9 padding-right">
					<!--features_items-->
					<div class="features_items">
						<h2 class="title text-center">Features Product</h2>
						<c:forEach items="${featureProduct}" var="featureProduct">
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
										<div class="productinfo text-center">
											<img src="${pageContext.request.contextPath}/resources/product_images/${featureProduct.pid}.jpg" alt="${featureProduct.pname}" />
											<h2>${featureProduct.pname}</h2>
											<p>${featureProduct.brand_name}</p>
											<a href="details?pid=${featureProduct.pid}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
										</div>
										<div class="product-overlay">
											<div class="overlay-content">
												<h2>${featureProduct.pname}</h2>
												<p>${featureProduct.brand_name}</p>
												<a href="details?pid=${featureProduct.pid}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
											</div>
										</div>
								</div>
								<div class="choose">
									<ul class="nav nav-pills nav-justified">
										<li><a href="#"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
										<li><a href="details?pid=${featureProduct.pid}"><i class="fa fa-plus-square"></i>Details</a></li>
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
    
    <%@include file="footer.jsp" %>
    <%@include file="jsinclude.jsp" %>
	
</body>
</html>