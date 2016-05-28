<%-- 
    Document   : newjsp
    Created on : 25 May, 2016, 9:10:40 PM
    Author     : kush
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
<%
    Integer hitcount = (Integer) application.getAttribute("hit");
    if(hitcount == null || hitcount == 0) {
        out.print("First time visit!");
        hitcount = 1;
    } else {
        out.print("Welcome again!");
        hitcount+=1;
    }
    application.setAttribute("hit", hitcount);
%>
        <p>Total visits: <%= hitcount %></p>
    </body>
</html>
