<%@page contentType="text/html"  pageEncoding="latin2"%>
<html lang="pl-PL">
<head>
<meta charset="UTF-8">
<title>Broader Named Entity Identification and Linking</title>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="header.html"/>
<form name="myform" method="POST" action="process.jsp" accept-charset="UTF-8">
          <table>
            <tbody>     
            <tr><td colspan="2">
                <br>Please enter your text here:<br><br>
                <textarea valign="top" name="input" cols="7"></textarea>
            </td></tr>
            <tr><td align="left">
                <input id="process" type="submit" name="Process" value="Start">
                <input id="clear" type="button" value="Clear" onclick="this.form.elements['input'].value=''">
            </td></tr>
          </tbody></table>
        </form>
<jsp:include page="footer.html"/>
</body>
</html>
