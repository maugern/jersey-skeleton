<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/template.jsp"%>
<div class="container">
	<div class="container">
		<div id="signinbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">
					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>

					<form action="signin" method="post" id="loginform" class="form-horizontal" role="form">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="username"
								type="text" class="form-control" name="username" value=""
								placeholder="Username">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="password"
								type="password" class="form-control" name="password"
								placeholder="Password">
						</div>

						<div style="margin-top: 10px" class="form-group">
							<!-- Sign in button -->
							<div class="col-md-offset-3 col-md-7">
								<button id="btn-signup" type="submit"
									class="btn btn-info center-block">
									<i class="icon-hand-right"></i> Sign in
								</button>

							</div>
						</div>

						<!-- Redirection to register -->
						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									New to Jersey-Skeleton ? <a href="/resources/register.jsp"
										onClick="$('#loginbox').hide(); $('#signupbox').show()">
										Create an account ! </a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>