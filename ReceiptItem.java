package receiptparser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileManager.java
 * Handles saving receipts to .txt files and loading them back.
 * Files are saved in the "receipts/" folder.
 */
public class FileManager {

    // Folder where receipts are saved
    private static final String RECEIPTS_DIR = "receipts";

    // Save a receipt to a .txt file
    public static boolean saveReceipt(Receipt receipt) {
        // Create receipts directory if it doesn't exist
        File dir = new File(RECEIPTS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Generate a filename from store name and date
        String filename = RECEIPTS_DIR + "/" +
                receipt.getStoreName().replaceAll("\\s+", "_") +
                "_" + receipt.getDate().replaceAll("/", "-") + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            // Write receipt header
            writer.println("STORE=" + receipt.getStoreName());
            writer.println("DATE=" + receipt.getDate());
            writer.println("TAX=" + receipt.getTaxRate());
            writer.println("DISCOUNT=" + receipt.getDiscount());
            writer.println("ITEMS_START");

            // Write each item as CSV
            for (ReceiptItem item : receipt.getItems()) {
                writer.println(item.toCsv());
            }

            writer.println("ITEMS_END");
            System.out.println("\n  Receipt saved to: " + filename);
            return true;

        } catch (IOException e) {
            System.out.println("\n  Error saving receipt: " + e.getMessage());
            return false;
        }
    }

    // Load all saved receipts from the receipts folder
    public static List<Receipt> loadAllReceipts() {
        List<Receipt> receipts = new ArrayList<>();
        File dir = new File(RECEIPTS_DIR);

        // Check if folder exists and has files
        if (!dir.exists() || !dir.isDirectory()) {
            return receipts;
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            return receipts;
        }

        // Load each file
        for (File file : files) {
            Receipt receipt = loadReceiptFromFile(file);
            if (receipt != null) {
                receipts.add(receipt);
            }
        }

        return receipts;
    }

    // Load a single receipt from a file
    private static Receipt loadReceiptFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String storeName = "";
            String date      = "";
            double taxRate   = 0;
            double discount  = 0;
            boolean readingItems = false;
            List<ReceiptItem> items = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("STORE="))    storeName = line.substring(6);
                else if (line.startsWith("DATE=")) date      = line.substring(5);
                else if (line.startsWith("TAX="))  taxRate   = Double.parseDouble(line.substring(4));
                else if (line.startsWith("DISCOUNT=")) discount = Double.parseDouble(line.substring(9));
                else if (line.equals("ITEMS_START")) readingItems = true;
                else if (line.equals("ITEMS_END"))   readingItems = false;
                else if (readingItems && !line.isEmpty()) {
                    items.add(ReceiptItem.fromCsv(line));
                }
            }

            // Build the receipt object
            Receipt receipt = new Receipt(storeName, date, taxRate, discount);
            for (ReceiptItem item : items) {
                receipt.addItem(item);
            }
            return receipt;

        } catch (IOException | NumberFormatException e) {
            System.out.println("  Warning: Could not load " + file.getName());
            return null;
        }
    }

    // List all saved receipt filenames
    public static String[] listSavedFiles() {
        File dir = new File(RECEIPTS_DIR);
        if (!dir.exists()) return new String[0];
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        if (files == null) return new String[0];
        String[] names = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        return names;
    }
}
