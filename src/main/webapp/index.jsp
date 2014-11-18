<!DOCTYPE html>

<%@page import="dk.test.Test"%>
<%@page import="dk.test.FileFunctions"%>
<%@ page language="java"%>



<html xmlns:wicket="http://wicket.apache.org">
<head>
    <title wicket:id="pageTitle">Image printer</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

</head>

<body>
<script src="jquery-1.11.1/jquery-1.11.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap.file-input.js"></script>

    <hr>
    <div class="footer">
        <p>Image Printer Console <span wicket:id="buildVersion" id="buildVersion">1.0</span> </p>
    </div>
</body>
</html>
