<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title}</title>
	<%@include file="cssinclude.jsp"%>
	<link href="${pageContext.request.contextPath}/resources/css/details.css" rel="stylesheet">
</head>
<body>
	<%@include file="header.jsp"%>

	
	<div class="row">
        <div class="col-lg-3"> <!-- This is for menu --> </div>
        <div class="col-sm-8 padding-right">
            <div class="product-details"><!--product-details-->
            	<c:forEach items="${product}" var="product">
                <div class="col-sm-5">
                    <div class="view-product">
                        <img src="${pageContext.request.contextPath}/resources/product_images/${product.pid}.jpg" alt="${product.pname}">
                    </div>
                </div>
                <div class="col-sm-7">
                    <div class="product-information"><!--/product-information-->
                        <h2>${product.pname}</h2>
                        <p>${product.brand_name}</p><br/>
                        <p><b>Category : </b>${cname}</p>
                        <p><b>Available : </b>${available}</p>
                        <p><b>Featured : </b> &nbsp${featured}</p>
                    </div><!--/product-information-->
                </div>
                </c:forEach>
            </div><!--/product-details-->
            <div class="category-tab shop-details-tab"><!--category-tab-->

                <div class="col-sm-12">
                    <ul class="nav nav-tabs">
                        <li class="active" ><a href="#Packages" data-toggle="tab">Packages</a></li>
                        <li><a href="#companyprofile" data-toggle="tab">Company Profile</a></li>
                    </ul>
                </div>

                <div class="tab-content">
                    <div class="tab-pane fade  active in" id="Packages">
                        <form action="" method="post">
                        <c:forEach items="${packageInfo}" var="packageInfo">
                        <div class="row packageList">
                            <div class="col-xs-9">
                                <div class="checkbox">
						          <label>
						            <input type="checkbox" name="packId" value="${packageInfo.packId}">
						            <span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
						            Number of product : ${packageInfo.total_number} , Price : $ ${packageInfo.total_price}
						          </label>
						        </div>
                            </div>
                            <div class="col-xs-3">
                                <input class="form form-control quantity" type="number" name="numberOfItem" placeholder="Quantity" value="0"/>
                            </div>
                        </div>
                        <hr/>
                        </c:forEach>
                        <input type="submit" class="btn btn-info" value="Add To Chart">
                        </form>
                    </div>

                    <div class="tab-pane fade" id="companyprofile">
                        <!--Repeat this by using database value-->
                        <c:forEach items="${suggestProduct}" var="suggestProduct">
                        <div class="col-sm-3">
                            <div class="product-image-wrapper">
                                <div class="single-products">
                                    <div class="productinfo text-center">
                                        <img src="${pageContext.request.contextPath}/resources/product_images/${suggestProduct.pid}.jpg" alt="${suggestProduct.pname}">
                                        <h2>${suggestProduct.pname}</h2>
                                        <a href="?pid=${suggestProduct.pid}"><button type="button" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button></a>
                                    </div>
                                </div>
                            </div>
                        </div>
						</c:forEach>
                    </div>
                    
                    
                </div>
            </div>
        </div>
    </div>

	<%@include file="footer.jsp"%>
	<%@include file="jsinclude.jsp"%>
</body>
</html>