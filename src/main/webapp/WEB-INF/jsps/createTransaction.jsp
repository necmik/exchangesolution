<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Currency Conversion</title>
</head>
<body>
<h2>Create Transaction</h2>

<form action="create" method="post">
<pre>
Sell Currency:<input type="text" name="sellCurrency" /> <br />
Buy Currency:<input type="text" name="buyCurrency" /> <br />
Sell Amount:<input type="text" name="sellAmount" /> <br />
<input type="submit" value="Get Quote" />
<br />
${msg}
</pre>
</form>

<h2>Transactions:</h2>
<table >
<tr><th>Transaction Id</th>
<th>Date</th>
<th>Sell Currency</th>
<th>Sell Amount</th>
<th>Buy Currency</th>
<th>Buy Amount</th>
</tr>

<c:forEach items="${transactions}" var="transaction">
<tr>
<td>${transaction.transactionId}</td>
<td>${transaction.transactionDate}</td>
<td>${transaction.fromCurrency}</td>
<td>${transaction.sellAmount}</td>
<td>${transaction.toCurrency}</td>
<td>${transaction.buyAmount}</td>
</tr>
</c:forEach>
</table>
</body>
</html>