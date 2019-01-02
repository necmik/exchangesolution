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
Phone:<input type="text" name="passengerPhone" /> <br />

<h2>Card Details:</h2>
Name on the card: <input type="text" name="nameOnTheCard"/> <br />
Card No: <input type="text" name="cardNumber"/> <br />
Expiry Date: <input type="text" name="expirationDate"/> <br />
Three Digit Sec. Code: <input type="text" name="securityCode"/> <br />

<input type="hidden" name="flightId" value="${flight.id}" />
<input type="submit" value="confirm" />
</form>
</body>
</html>