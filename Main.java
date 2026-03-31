package receiptparser;

/**
 * ReceiptCalculator.java
 * Handles all calculation and formatted display of receipt totals.
 * Prints a nicely formatted receipt summary to the console.
 */
public class ReceiptCalculator {

    // Print the full formatted receipt to console
    public static void printReceipt(Receipt receipt) {
        String line  = "=".repeat(55);
        String dline = "-".repeat(55);

        System.out.println("\n" + line);
        System.out.printf("  %-53s%n", centerText(receipt.getStoreName(), 53));
        System.out.printf("  Date: %-47s%n", receipt.getDate());
        System.out.println(line);
        System.out.printf("  %-25s %-5s %-10s %-10s%n",
                "Item", "Qty", "Unit Price", "Total");
        System.out.println(dline);

        // Print each item
        for (ReceiptItem item : receipt.getItems()) {
            System.out.println(item.toString());
        }

        System.out.println(dline);

        // Print subtotal, tax, discount, grand total
        System.out.printf("  %-40s Rs.%.2f%n", "Subtotal:", receipt.getSubtotal());
        System.out.printf("  %-40s Rs.%.2f%n",
                "Tax (" + receipt.getTaxRate() + "% GST):", receipt.getTaxAmount());

        if (receipt.getDiscount() > 0) {
            System.out.printf("  %-40s -Rs.%.2f%n", "Discount:", receipt.getDiscount());
        }

        System.out.println(dline);
        System.out.printf("  %-40s Rs.%.2f%n", "GRAND TOTAL:", receipt.getGrandTotal());
        System.out.println(line);
        System.out.printf("  Total Items: %-40d%n", receipt.getItemCount());
        System.out.println(line);
        System.out.println("        Thank you for shopping with us!");
        System.out.println(line + "\n");
    }

    // Helper to center text within a given width
    private static String centerText(String text, int width) {
        if (text.length() >= width) return text;
        int padding = (width - text.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) sb.append(" ");
        sb.append(text);
        return sb.toString();
    }

    // Print a quick summary (used in view saved receipts)
    public static void printSummary(Receipt receipt) {
        System.out.printf("  Store: %-20s Date: %-12s Items: %-3d Total: Rs.%.2f%n",
                receipt.getStoreName(), receipt.getDate(),
                receipt.getItemCount(), receipt.getGrandTotal());
    }
}
