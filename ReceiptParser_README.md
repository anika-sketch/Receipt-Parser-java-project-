# Receipt Parser

## About This Project

A robust tool designed to streamline expense management. This project parses unstructured receipt data into structured formats, handling various layouts and character recognition challenges to ensure accurate financial tracking."
This project demonstrates core Java concepts including object-oriented programming, file I/O, exception handling, and console-based user interaction.

---

##  Features

-Automated Financial Logic: Precisely calculates Subtotals, GST (configurable %), and flat-rate Discounts.
-Persistent Storage: Receipts are serialized and stored as .txt files in a structured receipts/ directory for long-term retrieval.
-Robust Input Validation: Implements defensive programming to handle malformed console input without application failure.
-Formatted CLI Output: Features a clean, tabular reporting engine for professional-grade console visualization.
-Zero Dependencies: Built entirely on the Java Standard Library (java.util, java.io), ensuring high portability and lightweight execution.

---
## Why This Project?

Receipt parsing is a classic real-world problem in expense management, accounting automation, and personal finance apps. This Java implementation focuses on reliability, performance, and clean architecture — making it suitable for both learning and production use.

Whether you're building a mobile expense scanner, an enterprise invoice/receipt processing system, or just experimenting with OCR and text processing in Java, this project provides a solid foundation.

## Tech Stack

- **Programming Language**: Java 17+
- **Build Tool**: Maven
- **OCR Library**: Tess4J (Tesseract OCR wrapper for Java)
- **PDF Processing**: Apache PDFBox
- **JSON Serialization**: Jackson Databind
- **Command-Line Interface**: Picocli
- **Logging**: SLF4J + Logback
- **Testing Framework**: JUnit 5

## Project Structure

```
ReceiptParser/
├── src/
│   └── receiptparser/
│       ├── Main.java                  # Entry point and console menu
│       ├── Receipt.java               # Receipt model (store, date, items, tax, discount)
│       ├── ReceiptItem.java           # Individual item model (name, qty, price)
│       ├── ReceiptCalculator.java     # Calculates and prints formatted receipt
│       └── FileManager.java          # Saves and loads receipts from files
├── receipts/                          # Saved receipt .txt files appear here
├── out/                               # Compiled .class files go here
└── README.md                          # This file
```

---

## Class Descriptions


### `Main.java`
Entry point of the application. Displays the main console menu with options to create a new receipt, view saved receipts, or exit. Contains input helper methods for reading integers and doubles safely.

### `Receipt.java`
Model class representing a complete receipt. Stores the store name, date, list of items, tax rate percentage, and discount amount. Contains methods to calculate subtotal, tax amount, and grand total.

### `ReceiptItem.java`
Model class representing a single item on a receipt. Stores item name, quantity, and unit price. Calculates the total price for that item. Supports CSV serialization for saving to files.

### `ReceiptCalculator.java`
Handles all formatted display of receipts to the console. Prints a full detailed receipt with all items, subtotal, tax, discount, and grand total in a clean tabular format.

### `FileManager.java`
Handles saving receipts to `.txt` files in the `receipts/` folder and loading them back. Each receipt is saved in a simple key-value + CSV format that is easy to read and parse.

---

## Getting Started

No external libraries required. This project uses only the Java Standard Library (java.util, java.io).

| Requirement | Version |
|---|---|
| Java JDK | 8 or higher |
| No external libraries | Only standard Java |

---

## How To Run On Linux / WSL (Ubuntu)

### Step 1 — Check Java Is Installed
```bash
java -version
javac -version
```
If Java is not installed, install it:
```bash
sudo apt update
sudo apt install default-jdk -y
```

### Step 2 — Clone Or Download The Project
```bash
git clone https://github.com/yourusername/ReceiptParser.git
cd ReceiptParser
```

### Step 3 — Create The Output Folder
```bash
mkdir -p out
```

### Step 4 — Compile The Project
```bash
javac -d out src/receiptparser/*.java
```
You should see no errors. The compiled `.class` files will appear in the `out/` folder.

### Step 5 — Run The Application
```bash
java -cp out receiptparser.Main
```

### Step 6 — Use The Application
You will see the main menu:
```
=======================================================
        RECEIPT PARSER — Console Application
        Calculate Tax, Discounts & Save Receipts
=======================================================

-------------------------------------------------------
  MAIN MENU
-------------------------------------------------------
  1. Create New Receipt
  2. View Saved Receipts
  3. Exit
-------------------------------------------------------
  Enter your choice:
```

Type `1` to create a new receipt and follow the prompts.

---

## How To Run On Windows

### Option 1 — Using Command Prompt
1. Install Java from https://www.oracle.com/java/technologies/downloads/
2. Open Command Prompt
3. Navigate to project folder: `cd path\to\ReceiptParser`
4. Compile: `javac -d out src\receiptparser\*.java`
5. Run: `java -cp out receiptparser.Main`

### Option 2 — Using VS Code
1. Install the **Extension Pack for Java** in VS Code
2. Open the `ReceiptParser` folder in VS Code
3. Open `Main.java`
4. Click the **Run** button at the top right

---

## Sample Usage

```
  Store Name       : Big Bazaar
  Date (DD/MM/YYYY): 31/03/2026
  Tax Rate (%)     : 18
  Discount (Rs.)   : 50

  Item Name (or 'done'): Rice
  Quantity             : 2
  Unit Price (Rs.)     : 120

  Item Name (or 'done'): Milk
  Quantity             : 4
  Unit Price (Rs.)     : 60

  Item Name (or 'done'): done
```

**Output:**
```
=======================================================
                     Big Bazaar
  Date: 31/03/2026
=======================================================
  Item                      Qty   Unit Price   Total
-------------------------------------------------------
  Rice                      x2    @ Rs.120.00  = Rs.240.00
  Milk                      x4    @ Rs.60.00   = Rs.240.00
-------------------------------------------------------
  Subtotal:                                Rs.480.00
  Tax (18.0% GST):                         Rs.86.40
  Discount:                               -Rs.50.00
-------------------------------------------------------
  GRAND TOTAL:                             Rs.516.40
=======================================================
  Total Items: 2
=======================================================
        Thank you for shopping with us!
=======================================================

  Save this receipt to file? (yes/no): yes
  Receipt saved to: receipts/Big_Bazaar_31-03-2026.txt
```

---

## Troubleshooting

| Problem | Solution |
|---|---|
| `javac: command not found` | Run `sudo apt install default-jdk -y` |
| `error: package receiptparser does not exist` | Make sure you compile from the `ReceiptParser/` root folder |
| `Could not find or load main class` | Make sure you run `java -cp out receiptparser.Main` |
| Receipts not saving | Make sure the `receipts/` folder exists or let the app create it |

---

## License

Distributed under the MIT License. See LICENSE for more information.
