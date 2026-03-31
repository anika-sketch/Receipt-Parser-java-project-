package receiptparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Receipt.java
 * Represents a full receipt containing a store name, date,
 * list of items, tax rate, and discount amount.
 */
public class Receipt {

    private String storeName;
    private String date;
    private List<ReceiptItem> items;
    private double taxRate;       // e.g. 18.0 means 18% GST
    private double discount;      // flat discount in rupees

    // Constructor
    public Receipt(String storeName, String date, double taxRate, double discount) {
        this.storeName = storeName;
        this.date      = date;
        this.taxRate   = taxRate;
        this.discount  = discount;
        this.items     = new ArrayList<>();
    }

    // Add an item to the receipt
    public void addItem(ReceiptItem item) {
        items.add(item);
    }

    // Calculate subtotal (sum of all item totals before tax/discount)
    public double getSubtotal() {
        double subtotal = 0;
        for (ReceiptItem item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    // Calculate tax amount based on subtotal
    public double getTaxAmount() {
        return getSubtotal() * (taxRate / 100.0);
    }

    // Calculate grand total = subtotal + tax - discount
    public double getGrandTotal() {
        return getSubtotal() + getTaxAmount() - discount;
    }

    // Getters
    public String getStoreName()        { return storeName; }
    public String getDate()             { return date; }
    public List<ReceiptItem> getItems() { return items; }
    public double getTaxRate()          { return taxRate; }
    public double getDiscount()         { return discount; }

    // Returns how many items are on this receipt
    public int getItemCount() {
        return items.size();
    }
}
