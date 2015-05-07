<%@page import="interfaces.PageMapping"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
								<li><a href="#"><i class="fa fa-dribbble"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/resources/images/home/logo.png" alt="Logo" /></a>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="${pageContext.request.contextPath}<%=PageMapping.DASHBOARD_INDEX%>"><i class="fa fa-user"></i> Account</a></li>
								<li><a href="${pageContext.request.contextPath}<%=PageMapping.CONFIRM_CART%>" ><i class="fa fa-shopping-cart"></i> Cart</a></li>
								<li><a href='<%= PageMapping.BASE_URL+PageMapping.USER_LOGIN %>'><i class="fa fa-lock"></i> Login</a></li>
                                <li><div class="search_box pull-right"><form action="${pageContext.request.contextPath}/search" method="get"><input name="productName" type="text" placeholder="Search"/><input type="submit" style="display: none;"> </form> </div></li>
							</ul>
						</div>
					</div>
				</div>
                </div>
			</div>
			
			
			 
		</div><!--/header-middle-->
	</header><!--/header-->
	
	