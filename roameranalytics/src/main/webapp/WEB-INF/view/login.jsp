
<!-- Loading style -->
<link href="css/login_bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/login.css" rel="stylesheet" type="text/css">
<link href="css/login_slider.css" rel="stylesheet" type="text/css">


<!--[if lt IE 10]>
    <script src=js/html5shiv.js"></script>
<![endif]-->

<div class="container-fluid">
	<div class="row">
		 <h2>
		<img src="images/login-images/R3602.png" width="473" height="103" >
		</h2>
		<div class="col-md-3 col-md-offset-1">
		<form name='f' action="perform_login" method='POST'>
		  <div class="form-group">
			<input type="text" class="form-control" name='username' placeholder="Username" />
		  </div>
		  <div class="form-group">
			<input type="password" class="form-control" name='password' placeholder="Password" />
		  </div>
		  <input type="submit" name="submit" class="btn form-control" value="LOGIN" />
		  <!--  <div class="checkbox">
			<label><input type="checkbox"> Remember Me</label><a href="#" class="pull-right">Forgot Password?</a>
		  </div>-->		  
		</form>
		</div>
	</div>
</div>

<!-- Common Js -->
<script src="js/libs/jquery-1.11.1.min.js"></script>
<script src="js/login/slider.min.js"></script>
<script>
$( function() {
	$.vegas( 'slideshow', {
		delay: 10000,
		backgrounds: [
			{src: 'images/login-images/login_bg.jpg', fade: 1000 },
			{src: 'images/login-images/group_login_bg.jpg', fade: 1000 }
		]
	} )( 'overlay' );
	
	$( "header" ).remove( ".clearfix");

} );
</script>


