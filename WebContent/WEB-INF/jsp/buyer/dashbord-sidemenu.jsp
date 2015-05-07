<%@page import="interfaces.PageMapping"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <div id="sidebar-wrapper">
        <ul id="sidebar_menu" class="sidebar-nav">
            <li class="sidebar-brand"><a id="menu-toggle" href="#">Menu<span id="main_icon" class="glyphicon glyphicon-align-justify"></span></a></li>
        </ul>
        <ul class="sidebar-nav" id="sidebar">
        	<li class="sideMenuItem " ><a href="<%= PageMapping.BASE_URL %>" class="">Home<span class="sub_icon glyphicon glyphicon-home"></span></a></li>
            <li class="sideMenuItem " ><a href="<%= PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.BUYER_SHOW_BUYINGPRODUCT%>" class="${showProduct}">Show Product<span class="sub_icon glyphicon glyphicon-shopping-cart"></span></a></li>
        	<li class="sideMenuItem " ><a href="<%= PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.DASHBOARD_LOGOUT %>" class="">Log out<span class="sub_icon glyphicon glyphicon-log-out"></span></a></li>
        </ul>
    </div>                  
                    
                    