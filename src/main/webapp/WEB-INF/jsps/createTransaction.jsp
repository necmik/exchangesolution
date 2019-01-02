<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Currency Conversion</title>
</head>
<body>
<form action="completeReservation" method="post">
Source Currency:<input type="text" name="sourceCurrency" /> <br />
TargetCurrency:<input type="text" name="targetCurrency" /> <br />
Sell Amount:<input type="text" name="sourceAmount" /> <br />

<input type="submit" value="Get Quote" />
</form>
</body>
</html>