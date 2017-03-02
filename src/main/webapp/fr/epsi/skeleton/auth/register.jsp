<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/template.jsp"%>

<div class="container">
	<div class="container">
		<div id="registerBox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Register</div>
				</div>
				
				<div style="padding-top: 30px" class="panel-body">
					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>
					<form id="signupform"  action="register" method="post" class="form-horizontal" role="form">
						
						<div id="signupalert" style="display: none;"
							class="alert alert-danger">
							<p>Error:</p>
							<span></span>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-3 col-md-7">
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input type="text"
									class="form-control" name="username" placeholder="Username">
							</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-3 col-md-7">
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i
                                    class="glyphicon glyphicon-envelope"></i></span> <input type="email"
                                    class="form-control" name="email" placeholder="Email">
                            </div>
                            </div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-3 col-md-7">
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input type="password"
									class="form-control" name="password" placeholder="Password">
							</div>
							</div>
						</div>

						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-7">
								<button id="btn-signup" type="submit"
									class="btn btn-info center-block">
									<i class="icon-hand-right"></i> Register
								</button>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Already registered ? <a href="signin"
										onClick="$('#signupBox').hide(); $('#registerBox').show()">
										Sign in ! </a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>