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


<hr>
<h2>Orders</h2>
<form action="orders" method="post">
    <select name="status" id="status">
        <c:forEach var="status" items="${orderStatuses}">
            <option>${status}</option>
        </c:forEach>
    </select>
    <button type="button" onclick="addProduct()">Add item</button>
    <input type="submit" value="Save order">
    <table id="orderProducts">
        <tr>
            <th>Products in new order</th>
        </tr>
        <tr>
            <th>
                <label for="productId">Product ID</label>
                <input type="text" id="productId" name="productId">
                <label for="quantity">Quantity</label>
                <input type="text" id="quantity" name="quantity">
                <a onclick="removeProduct()" href="#">X</a>
            </th>
        </tr>
        <tr>
            <th>

                <label for="productId">Product ID</label>
                <input type="text" id="productId" name="productId">
                <label for="quantity">Quantity</label>
                <input type="text" id="quantity" name="quantity">
                <a onclick="removeProduct()" href="#">X</a>

            </th>
        </tr>
    </table>
</form>
<c:forEach var="orderItem" items="${orderItems}">
    <p>${orderItem}</p>
</c:forEach>
</body>
<script>
    function addProduct() {
        newProductRow = document.createElement("th")
        newProductsList = document.getElementById("newProducts")


        newProductsList.append(newProductRow)
    }

    function removeProduct() {
        alert("remove")
    }
</script>
</html>
