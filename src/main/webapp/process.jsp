<%@page contentType="text/html"  pageEncoding="latin2"%>
<html lang="pl-PL">
<head>
<meta charset="UTF-8">
<title>Broader Named Entity Identification and Linking</title>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="header.html"/>
<p>Result:</p>

<% 
String input = request.getParameter("input");

out.println(input);
%>

<jsp:include page="footer.html"/>
</body>
</html>
