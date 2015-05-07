<%@page import="interfaces.PageMapping"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title }</title>
	<%@include file="cssinclude.jsp" %>
</head>
<body>
	<%@include file="header.jsp" %>
		
	<div class="container" style="margin-top: 75px">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" class="active" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action='<%= PageMapping.BASE_URL+PageMapping.USER_LOGIN %>' method="post" role="form" style="display: none;">
									<div class="form-group">
										<input type="email" name="uEmail" id="username" tabindex="1" class="form-control" placeholder="Email Address" value="">
									</div>
									<div class="form-group">
										<input type="password" name="uPass" id="password" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group text-center">
										<label class="label label-danger">${errorLogin}</label>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
											</div>
										</div>
									</div>
									<!-- <div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="http://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a>
												</div>
											</div>
										</div>
									</div> -->
								</form>
								

								<form id="register-form" action='<%= PageMapping.BASE_URL+PageMapping.USER_REGISTRATION %>' method="post" role="form" style="display: block;">
									<div class="form-group">
										<input type="text" required name="uName" id="username" tabindex="1" class="form-control" placeholder="Full Name" >
									</div>
									<div class="form-group">
										<input type="email" required name="uEmail" id="email" tabindex="1" class="form-control" placeholder="Email Address">
									</div>
									<div class="form-group">
										<input type="password" required name="uPass" id="password" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<input type="password" required name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirm Password">
									</div>
									<div class="form-group">
										<select name="accountType" class="selectpicker form-control" required>
										    <optgroup label="Who you are ?">
										      <option value="buyer">Buyer</option>
										      <option value="seller">Seller</option>
										    </optgroup>
										  </select>									
									</div>
									<div class="form-group">
										<input type="text" class="form-control" name="address" placeholder="Your address">					
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-4 col-sm-offset-4">
												<label class="label label-danger">${errorReg}</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">
											
											</div>
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
	
	<div style="margin-top: 135px"></div>
	
	<%@include file="jsinclude.jsp" %>
	<script src="resources/js/login-reg.js"></script>
	<%@include file="footer.jsp" %>
</body>
</html>