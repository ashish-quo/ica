<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html class="full-width" ng-app="roamer-analytics">
<head>
<title>Mobileum</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewpoint" content="width=device-width, initial-scale=1.0">
<!-- Favicons -->
<link rel="shortcut icon" href="images/favicon/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="images/favicon/apple-touch-icon.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/favicon/apple-touch-icon-57x57.png" />
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/favicon/apple-touch-icon-72x72.png" />
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/favicon/apple-touch-icon-114x114.png" />


<!-- Loading style -->
<link href="styles/bootstrap.css" rel="stylesheet">
<link href="styles/style.css" rel="stylesheet">
<link href="styles/app.css" rel="stylesheet">
<link href="styles/responsive.css" rel="stylesheet">
<link href="styles/wireframe-style.css" rel="stylesheet">
<link href="assets/pop/modalPopLite.css" rel="stylesheet"/>

<link rel="stylesheet" href="assets/morris-charts/morris.css">
<link rel="stylesheet" href="assets/selectric/selectric.css">
<link rel="stylesheet" href="assets/morris-charts/dount/c3.css">


<body class="full-width" ng-controller="MainController" ng-init="roamType = '${roamType}'">
	<div class="home-backdrop" style="display:none">
        <div class="loader"></div>
</div>
	<header class="clearfix">
		<tiles:insertAttribute name="header" />
	</header>
	<div class="page-wrapper" id="leftPanel">
		<tiles:insertAttribute name="leftpanel" />
	</div>
	<div class="main-content clearfix" id="mainContent">
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>