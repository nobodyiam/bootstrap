<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html ng-app="Greeting">
<meta http-equiv=Content-Type content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Bootstrap</title>
<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"/>
<!-- This is only for demo use. Please put static contents to CDN in prodution environment. -->
<link rel="stylesheet" type="text/css" href="/styles/angular-toastr-1.4.1.min.css"/>
<link rel="stylesheet" type="text/css" href="/styles/app.css"/>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="http://apps.bdimg.com/libs/angular.js/1.3.9/angular.min.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<body>
<div ng-include src="'/templates/list.html'"></div>
<script type="text/javascript" src="http://apps.bdimg.com/libs/underscore.js/1.7.0/underscore-min.js"></script>
<!-- This is only for demo use. Please compress and put static contents to CDN in prodution environment. -->
<script type="text/javascript" src="/scripts/ui-bootstrap-0.13.0.min.js"></script>
<script type="text/javascript" src="/scripts/ui-bootstrap-tpls-0.13.0.min.js"></script>
<script type="text/javascript" src="/scripts/angular-toastr-1.4.1.tpls.min.js"></script>
<script type="text/javascript" src="/scripts/http.js"></script>
<script type="text/javascript" src="/scripts/app.js"></script>
</body>
</html>
