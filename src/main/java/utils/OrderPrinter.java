package utils;

import model.domain.OrderItem;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderPrinter {
    private final int ORDER_ID_WIDTH = 5;
    private final int ORDER_ITEM_ID_WIDTH = 13;
    private final int PRODUCTS_PRICE_WIDTH = 12;
    private final int PRODUCT_NAME_WIDTH = 40;
    private final int QUANTITY_WIDTH = 15;
    private final int ORDER_CREATED_DATE_WIDTH = 16;

    SimpleDateFormat shortDate = new SimpleDateFormat("yyyy-mm-dd HH:mm");

    private final int total_width = ORDER_ID_WIDTH + ORDER_ITEM_ID_WIDTH + PRODUCTS_PRICE_WIDTH
            + PRODUCT_NAME_WIDTH + QUANTITY_WIDTH + ORDER_CREATED_DATE_WIDTH + 16;

    public String printOrders(List<OrderItem> orderItems) {
        StringBuilder sb = new StringBuilder();
        sb.append(buildHeader());
        for (OrderItem orderItem : orderItems) {
            sb.append(buildOrderRow(orderItem));
        }
        return sb.toString();
    }

    public String buildHeader() {
        StringBuilder row = new StringBuilder();
        row.append(new String(new char[total_width]).replace("\0", "-") + "\n");
        String header = "ORDERS";
        row.append(new String(new char[total_width / 2 - header.length()]).replace("\0", " "));
        row.append(header);
        row.append(new String(new char[total_width / 2 - header.length()]).replace("\0", " ") + "\n");
        row.append(new String(new char[total_width]).replace("\0", "-") + "\n");
        row.append("| ");
        row.append(compactString("ORDER ID", ORDER_ID_WIDTH));
        row.append(" | ");
        row.append(compactString("ORDER ITEM ID", ORDER_ITEM_ID_WIDTH));
        row.append(" | ");
        row.append(compactString("TOTAL PRICE", PRODUCTS_PRICE_WIDTH));
        row.append(" | ");
        row.append(compactString("PRODUCT NAME", PRODUCT_NAME_WIDTH));
        row.append(" | ");
        row.append(compactString("QUANTITY", QUANTITY_WIDTH));
        row.append(" | ");
        row.append(compactString("CREATED", ORDER_CREATED_DATE_WIDTH));
        row.append(" | ");
        row.append("\n" + new String(new char[total_width]).replace("\0", "-") + "\n");
        return row.toString();
    }

    public String buildOrderRow(OrderItem orderItem) {
        StringBuilder row = new StringBuilder();
        row.append("| ");
        row.append(compactString(orderItem.getOrder().getId().toString(), ORDER_ID_WIDTH));
        row.append("| ");
        row.append(compactString(orderItem.getId().toString(), ORDER_ITEM_ID_WIDTH));
        row.append(" | ");
        row.append(compactString(String.valueOf(orderItem.getProduct().getPrice() * orderItem.getQuantity()), PRODUCTS_PRICE_WIDTH));
        row.append(" | ");
        row.append(compactString(orderItem.getProduct().getName(), PRODUCT_NAME_WIDTH));
        row.append(" | ");
        row.append(compactString(String.valueOf(orderItem.getQuantity()), QUANTITY_WIDTH));
        row.append(" | ");
        row.append(compactString(shortDate.format(orderItem.getOrder().getCreateAt()), ORDER_CREATED_DATE_WIDTH));
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
