package com.valexa.utils;

import com.valexa.model.Product;

import java.util.List;

public class ProductPrinter {

    private final int ID_WIDTH = 5;
    private final int NAME_WIDTH = 40;
    private final int PRICE_WIDTH = 10;
    private final int STATUS_WIDTH = 15;

    private final int total_width =
            ID_WIDTH + NAME_WIDTH + PRICE_WIDTH + STATUS_WIDTH + 16;


    public String printProducts(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        sb.append(buildHeader());
        for (Product product : products) {
            sb.append(buildProductRow(product));
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
        row.append(compactString("ID", ID_WIDTH));
        row.append("| ");
        row.append(compactString("NAME", NAME_WIDTH));
        row.append(" | ");
        row.append(compactString("PRICE", PRICE_WIDTH));
        row.append(" | ");
        row.append(compactString("STATUS", STATUS_WIDTH));
        row.append(" | ");
        row.append("\n" + new String(new char[total_width]).replace("\0", "-") + "\n");
        return row.toString();
    }

    private String buildProductRow(Product product) {
        StringBuilder row = new StringBuilder();
        row.append("| ");
        row.append(compactString(String.valueOf(product.getId()), ID_WIDTH));
        row.append("| ");
        row.append(compactString(product.getName(), NAME_WIDTH));
        row.append(" | ");
        row.append(compactString(product.getPrice().toString(), PRICE_WIDTH));
        row.append(" | ");
        row.append(compactString(product.getStatus().toString(), STATUS_WIDTH));
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
