<%--
  Created by IntelliJ IDEA.
  User: valerii
  Date: 02.11.2021
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>Welcome to Hybris App</h1>
  <a href="products">Products menu</a>


  <form action="products" method="post">

    <input type="text" id="name" name="name" placeholder="name">
    <input type="text" id="price" name="price" placeholder="price">

    <select name="status" id="status">
        <c:forEach var="status" items="${productStatuses}">
          <option>${status}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Add product">
  </form>

  <c:forEach var="product" items="${products}">
      <p>${product}</p>
  </c:forEach>

  </body>
</html>
