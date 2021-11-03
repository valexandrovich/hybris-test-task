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

    <style>
        DIV.table {
            display: table;
        }

        FORM.tr, DIV.tr {
            display: table-row;
        }

        SPAN.td {
            display: table-cell;
        }
    </style>
</head>
<body>
<h1>Welcome to Hybris App</h1>

<a href="products">Prod GET</a>

<form action="products" method="post" style="outline: blue solid 2px">
    <input type="text" id="name" name="name" placeholder="name">
    <input type="text" id="price" name="price" placeholder="price">
    <select name="status" id="status">
        <c:forEach var="status" items="${productStatuses}">
            <option>${status}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Add product">
</form>

<div class="table">
    <div class="tr">
        <span class="td">ID</span>
        <span class="td">Name</span>
        <span class="td">Price</span>
        <span class="td">Status</span>
        <span class="td">Delete</span>
    </div>
    <c:forEach var="product" items="${products}">
        <div class="tr">
                <%--    <form class="tr" action="products" method="get">--%>
            <span class="td">${product.getId()}</span>
            <span class="td">${product.getName()}</span>
            <span class="td">${product.getPrice()}</span>
            <span class="td">${product.getStatus()}</span>
            <span class="td"><a href="products?del=${product.getId()}">X</a></span>
        </div>
    </c:forEach>
</div>

<%--<table>--%>
<%--    <tr>--%>
<%--        <th>ID</th>--%>
<%--        <th>Name</th>--%>
<%--        <th>Price</th>--%>
<%--        <th>Status</th>--%>
<%--        <th>Delete product</th>--%>
<%--    </tr>--%>
<%--    <c:forEach var="product" items="${products}">--%>
<%--        <tr>--%>
<%--            <th>${product.getId()}</th>--%>
<%--            <th>${product.getName()}</th>--%>
<%--            <th>${product.getPrice()}</th>--%>
<%--            <th>${product.getStatus()}</th>--%>
<%--            <th><a href="products?del=${product.getId()}">X</a></th>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>

<%--</table>--%>


<hr>
<h2>Orders</h2>
<form action="orders" method="post" style="outline: red solid 2px">
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
        <button type="button" onclick="removeProduct('asd')">X</button>
    </table>
</form>

<div class="table">
    <div class="tr">
        <span class="td">Order ID</span>
        <span class="td">Order item ID</span>
        <span class="td">Total price</span>
        <span class="td">Product name</span>
        <span class="td">Quantity</span>
        <span class="td">Created</span>
        <span class="td">Save</span>
    </div>
    <c:forEach var="orderItem" items="${orderItems}">
        <form class="tr" action="orderItems" method="post">
                <span class="td">
                    ${orderItem.getOrder().getId()}
                    <input type="hidden" id="orderItemId" name="orderItemId" value="${orderItem.getOrder().getId()}">
                </span>
            <span class="td">${orderItem.getId()}</span>
            <span class="td">${orderItem.getProduct().getPrice() * orderItem.getQuantity()}</span>
            <span class="td">${orderItem.getProduct().getName()}</span>
                <%--                <span>${orderItem.getQuantity()}</span>--%>
            <span class="td"><input type="text" name="quantity" id="quantity"
                                    value="${orderItem.getQuantity()}"></span>
            <span class="td">${orderItem.getOrder().getCreateAt()}</span>
            <span class="td"><input type="submit" value="save"></span>
        </form>
    </c:forEach>

</div>


</body>
<script>
    productInOrderCount = 1;

    function addProduct() {
        productInOrderCount++
        const newProductRow = document.createElement("tr");
        newProductRow.id = "newProduct" + productInOrderCount
        newProductRow.innerHTML = "<th>" +
            "<label for='productId'>Product ID</label>" +
            "<input type='text' id='productId' name='productId'>" +
            "<label for='quantity'>Quantity</label>" +
            "<input type='text' id='quantity' name='quantity'>" +
            "<button type='button' onclick=removeProduct('" + "newProduct" + productInOrderCount + "')>X</button>" +
            "</th>"
        newProductsList = document.getElementById("orderProducts")
        newProductsList.appendChild(newProductRow)
    }

    function removeProduct(id) {
        document.getElementById(id).remove()
    }
</script>
</html>
