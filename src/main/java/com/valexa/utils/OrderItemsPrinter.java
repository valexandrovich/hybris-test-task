package com.valexa.utils;

import com.valexa.model.OrderItem;

import java.util.List;

public class OrderItemsPrinter {

    private final int PRODUCT_ID_WIDTH = 10;
    private final int PRODUCT_NAME_WIDTH = 40;
    private final int PRODUCT_PRICE_WIDTH = 15;
    private final int QUANTITY_WIDTH = 15;

    private final int total_width = PRODUCT_ID_WIDTH + PRODUCT_NAME_WIDTH + PRODUCT_PRICE_WIDTH + QUANTITY_WIDTH + 16;

    public String printOrderItems(List<OrderItem> orderItems) {
        StringBuilder sb = new StringBuilder();
        sb.append(buildHeader());
        for (OrderItem orderItem : orderItems) {
            sb.append(buildOrderItemRow(orderItem));
        }
        return sb.toString();
    }

    public String buildHeader() {
        StringBuilder row = new StringBuilder();
        row.append(new String(new char[total_width]).replace("\0", "-") + "\n");
        String header = "PRODUCTS IN ORDER";
        row.append(new String(new char[total_width / 2 - header.length()]).replace("\0", " "));
        row.append(header);
        row.append(new String(new char[total_width / 2 - header.length()]).replace("\0", " ") + "\n");
        row.append(new String(new char[total_width]).replace("\0", "-") + "\n");
        row.append("| ");
        row.append(compactString("PRODUCT ID", PRODUCT_ID_WIDTH));
        row.append(" | ");
        row.append(compactString("PRODUCT NAME", PRODUCT_NAME_WIDTH));
        row.append(" | ");
        row.append(compactString("PRODUCT PRICE", PRODUCT_PRICE_WIDTH));
        row.append(" | ");
        row.append(compactString("QUANTITY", QUANTITY_WIDTH));
        row.append(" | ");
        row.append("\n" + new String(new char[total_width]).replace("\0", "-") + "\n");
        return row.toString();
    }

    public String buildOrderItemRow(OrderItem orderItem) {
        StringBuilder row = new StringBuilder();
        row.append("| ");
        row.append(compactString(orderItem.getProduct().getId().toString(), PRODUCT_ID_WIDTH));
        row.append(" | ");
        row.append(compactString(orderItem.getProduct().getName(), PRODUCT_NAME_WIDTH));
        row.append(" | ");
        row.append(compactString(orderItem.getProduct().getPrice().toString(), PRODUCT_PRICE_WIDTH));
        row.append(" | ");
        row.append(compactString(orderItem.getQuantity().toString(), QUANTITY_WIDTH));
        row.append(" | \n");
        return row.toString();
    }

    private String compactString(String string, int maxWidth) {
        int width = string.length();
        if (width > maxWidth) {
            return string.substring(0, maxWidth);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(string);
            stringBuilder.append(new String(new char[maxWidth - width]).replace("\0", " "));
            return stringBuilder.toString();
        }
    }
}
