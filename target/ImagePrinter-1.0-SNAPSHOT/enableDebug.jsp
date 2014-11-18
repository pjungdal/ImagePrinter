<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.log4j.Level" %>
<%@ page import="org.apache.log4j.LogManager" %>
<html>
<head>
    <meta name="Pragma" content="NO-CACHE" />
    <meta http-equiv="Cache-Control" content="No-cache" />
    <meta http-equiv="Expires" content="1" />
    <title>Log4J in debug mode</title>
</head>

<%
    LogManager.getRootLogger().setLevel(Level.DEBUG);
%>
<body>
</body>
</html>
