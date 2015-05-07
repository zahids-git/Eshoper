<%@page import="interfaces.PageMapping"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <div id="sidebar-wrapper">
        <ul id="sidebar_menu" class="sidebar-nav">
            <li class="sidebar-brand"><a id="menu-toggle" href="#">Menu<span id="main_icon" class="glyphicon glyphicon-align-justify"></span></a></li>
        </ul>
        <ul class="sidebar-nav" id="sidebar">
        	<li class="sideMenuItem " ><a href="<%= PageMapping.BASE_URL %>" class="">Home<span class="sub_icon glyphicon glyphicon-home"></span></a></li>
            <li class="sideMenuItem " ><a href="<%= PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.SELLER_ADD_PRODUCT%>" class="${addProduct}">Add Product<span class="sub_icon glyphicon glyphicon-plus"></span></a></li>
            <li class="sideMenuItem " ><a href="<%= PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.SELLER_ADD_PACKAGE%>" class="${makePackage}">Make Package<span class="sub_icon glyphicon glyphicon-list-alt"></span></a></li>
            <li class="sideMenuItem " ><a href="<%= PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.SELLER_UPDATE_PRODUCT%>" class="${updateProduct}">Update/Delete<span class="sub_icon glyphicon glyphicon-refresh"></span></a></li>
            <li class="sideMenuItem " ><a href="<%= PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.SELLER_REPORT%>" class="${report}">Report<span class="sub_icon glyphicon glyphicon-stats"></span></a></li>
            <li class="sideMenuItem " ><a href="<%= PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.DASHBOARD_LOGOUT %>" class="">Log out<span class="sub_icon glyphicon glyphicon-log-out"></span></a></li>
        </ul>
    </div>                  
                    
                    