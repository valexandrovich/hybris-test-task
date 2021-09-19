package utils;

import model.domain.Product;

import java.util.Map;

public class OrderedProductPrinter {

    private final int NAME_WIDTH = 40;
    private final int PRICE_WIDTH = 10;
    private final int STATUS_WIDTH = 15;
    private final int QUANTITY_WIDTH = 8;

    private final int total_width = NAME_WIDTH + PRICE_WIDTH + STATUS_WIDTH + QUANTITY_WIDTH + 16;


    public String printProducts(Map<Integer, Product> products) {
        StringBuilder sb = new StringBuilder();
        sb.append(buildHeader());
        for (Integer key : products.keySet()) {
            sb.append(buildProductRow(products.get(key), key));
        }
        return sb.toString();
    }

    private String buildHeader() {
        StringBuilder row = new StringBuilder();
        row.append(new String(new char[total_width]).replace("\0", "-") + "\n");
        String header = "PRODUCTS LIST";
        row.append(new String(new char[total_width / 2 - header.length()]).replace("\0", " "));
        row.append(header);
        row.append(new String(new char[total_width / 2 - header.length()]).replace("\0", " ") + "\n");
        row.append(new String(new char[total_width]).replace("\0", "-") + "\n");
        row.append("| ");
        row.append(compactString("NAME", NAME_WIDTH));
        row.append(" | ");
        row.append(compactString("PRICE", PRICE_WIDTH));
        row.append(" | ");
        row.append(compactString("STATUS", STATUS_WIDTH));
        row.append(" | ");
        row.append(compactString("QUANTITY", QUANTITY_WIDTH));
        row.append(" | ");
        row.append("\n" + new String(new char[total_width]).replace("\0", "-") + "\n");
        return row.toString();
    }

    private String buildProductRow(Product product, Integer quantity) {
        StringBuilder row = new StringBuilder();
        row.append("| ");
        row.append(compactString(product.getName(), NAME_WIDTH));
        row.append(" | ");
        row.append(compactString(product.getPrice().toString(), PRICE_WIDTH));
        row.append(" | ");
        row.append(compactString(product.getStatus().toString(), STATUS_WIDTH));
        row.append(" | ");
        row.append(compactString(String.valueOf(quantity), QUANTITY_WIDTH));
        row.append(" |\n");
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
