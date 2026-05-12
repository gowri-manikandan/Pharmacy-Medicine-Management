package view;

import controller.BranchController;
import model.Branch;
import model.Customer;
import model.Stock;
import model.Transaction;
import repository.BranchRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class MainView
{
    private final  Scanner scanner;
    private final BranchController branchController;
    private final BranchRepository branchRepository;
    private final List<Customer> customerList;
    private final Map<Integer, List<Stock>> stockByBranch;
    private final Map<String, String> alternateMedicineMap;
    private int customerIdCounter;
    private int stockIdCounter;
    private int transactionIdCounter;
    String name;
    String city;
    public MainView()
    {
        scanner = new Scanner(System.in);
        branchController = new BranchController();
        branchRepository = BranchRepository.getInstance();
        customerList = new ArrayList<>();
        stockByBranch = new HashMap<>();
        alternateMedicineMap = new HashMap<>();
        customerIdCounter = 1;
        stockIdCounter = 1;
        transactionIdCounter = 1;
    }
    public void init()
    {
        name = getValidName("Enter the Pharmacy Name :");
        city = getValidName("Enter the City Name :");
        displayMainMenu();
    }

    private void displayMainMenu() {
        while (true) {
            System.out.println("====== Welcome To " + name + " Pharmacy " + " ======");
            System.out.println("1. Add Branch");
            System.out.println("2. Add Stock to a Pharmacy Branch");
            System.out.println("3. Associate Alternate Products");
            System.out.println("4. Add Customer");
            System.out.println("5. Purchase Products");
            System.out.println("6. Print Customer Purchase Summary");
            System.out.println("7. exit");
            System.out.print("Enter your Choice :");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                {
                    branchController.addBranch();
                    break;
                }
                case 2:
                {
                    addStockToBranch();
                    break;
                }
                case 3:
                {
                    associateAlternateProducts();
                    break;
                }
                case 4:
                {
                    addCustomer();
                    break;
                }
                case 5:
                {
                    purchaseProducts();
                    break;
                }
                case 6:
                {
                    printCustomerPurchaseSummary();
                    break;
                }
                case 7:
                {
                    System.exit(1);
                    break;
                }
                default: {
                    System.out.println("Invalid Choice.");
                }

            }
        }
    }

    private String getValidName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.next().trim();
            if (value.length() < 3 || value.length() > 50) {
                System.out.println("Invalid data. Name should be 3 to 50 characters.");
                continue;
            }
            return value;
        }
    }

    private String getValidPhoneNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.matches("\\d{10}")) {
                System.out.println("Invalid data. Phone should be 10 digits.");
                continue;
            }
            return value;
        }
    }

    private void addStockToBranch() {
        if (branchRepository.getBranchList().isEmpty()) {
            System.out.println("No branch data. Add branch first.");
            return;
        }
        System.out.print("Enter Branch ID :");
        int branchId = scanner.nextInt();
        scanner.nextLine();
        Branch branch = findBranchById(branchId);
        if (branch == null) {
            System.out.println("Invalid Branch ID.");
            return;
        }

        System.out.print("Enter Medicine Name :");
        String medicineName = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        if (medicineName.isEmpty()) {
            System.out.println("Medicine name cannot be empty.");
            return;
        }

        System.out.print("Enter Available Quantity :");
        int qty = scanner.nextInt();
        System.out.print("Enter Price :");
        double price = scanner.nextDouble();
        scanner.nextLine();

        if (qty <= 0 || price <= 0) {
            System.out.println("Invalid quantity or price.");
            return;
        }

        List<Stock> stocks = stockByBranch.computeIfAbsent(branchId, key -> new ArrayList<>());
        Stock existing = findStock(stocks, medicineName);
        if (existing != null) {
            existing.setAvailableStock(existing.getAvailableStock() + qty);
            existing.setPrice(price);
        } else {
            stocks.add(new Stock(stockIdCounter++, branchId, medicineName, qty, price));
        }

        displayStockSummary();
    }

    private void displayStockSummary() {
        System.out.println("Branch ID Medicine Available Qty Price");
        System.out.println("-------------------------------------------------------------");
        for (Map.Entry<Integer, List<Stock>> entry : stockByBranch.entrySet()) {
            for (Stock stock : entry.getValue()) {
                System.out.printf("%d | %s | %d | %.2f%n",
                        entry.getKey(), stock.getMedicineName(), stock.getAvailableStock(), stock.getPrice());
                System.out.println("-------------------------------------------------------------");
            }
        }
    }

    private void associateAlternateProducts() {
        System.out.print("Enter Medicine Name :");
        String medicine = scanner.next().trim().toLowerCase(Locale.ROOT);
        System.out.print("Enter Alternate Medicine Name :");
        String alternate = scanner.next().trim().toLowerCase(Locale.ROOT);
        if (medicine.isEmpty() || alternate.isEmpty()) {
            System.out.println("Medicine names cannot be empty.");
            return;
        }
        alternateMedicineMap.put(medicine, alternate);

        System.out.println("Medicine Alternate");
        System.out.println("------------------------");
        for (Map.Entry<String, String> entry : alternateMedicineMap.entrySet()) {
            System.out.println(entry.getKey() + " | " + entry.getValue());
            System.out.println("------------------------");
        }
    }

    private void addCustomer() {
        String customerName = getValidName("Enter Customer Name :");
        String customerPhone = getValidPhoneNumber("Enter Customer Phone Number :");

        for (Customer customer : customerList) {
            if (customer.getCustomerPhoneNumber().equals(customerPhone)) {
                System.out.println("Phone number already exists.");
                return;
            }
        }

        customerList.add(new Customer(customerIdCounter++, customerName, customerPhone));
        System.out.println("Customer ID Customer Name Phone number");
        System.out.println("-----------------------------------------------------");
        for (Customer customer : customerList) {
            System.out.println(customer.getCustomerId() + " | " + customer.getCustomerName() + " | " + customer.getCustomerPhoneNumber());
            System.out.println("-----------------------------------------------------");
        }
    }

    private void purchaseProducts() {
        System.out.print("Enter Customer ID :");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Invalid Customer ID.");
            return;
        }

        System.out.print("Enter Branch ID :");
        int branchId = scanner.nextInt();
        scanner.nextLine();
        Branch branch = findBranchById(branchId);
        if (branch == null) {
            System.out.println("Invalid Branch ID.");
            return;
        }

        List<Transaction> purchasedItems = new ArrayList<>();
        boolean continuePurchase = true;
        int currentTransactionId = transactionIdCounter;

        while (continuePurchase) {
            System.out.print("Enter Product :");
            String medicine = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            System.out.print("Enter Quantity :");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (quantity <= 0) {
                System.out.println("Invalid quantity.");
            } else {
                List<Stock> branchStocks = stockByBranch.getOrDefault(branchId, new ArrayList<>());
                Stock selected = findStock(branchStocks, medicine);
                if (selected != null && selected.getAvailableStock() >= quantity) {
                    selected.setAvailableStock(selected.getAvailableStock() - quantity);
                    Transaction tx = new Transaction(currentTransactionId, branchId, selected.getMedicineName(), quantity, selected.getPrice() * quantity);
                    purchasedItems.add(tx);
                    customer.addNewTransaction(tx);
                } else {
                    String alternate = alternateMedicineMap.get(medicine);
                    boolean purchasedAlternate = false;
                    if (alternate != null) {
                        Stock altStock = findStock(branchStocks, alternate);
                        if (altStock != null && altStock.getAvailableStock() >= quantity) {
                            System.out.print("Do you want to purchase " + alternate + "? (Y/N) :");
                            String choice = scanner.nextLine().trim();
                            if (choice.equalsIgnoreCase("Y")) {
                                altStock.setAvailableStock(altStock.getAvailableStock() - quantity);
                                Transaction tx = new Transaction(currentTransactionId, branchId, altStock.getMedicineName(), quantity, altStock.getPrice() * quantity);
                                purchasedItems.add(tx);
                                customer.addNewTransaction(tx);
                                purchasedAlternate = true;
                            }
                        }
                    }

                    if (!purchasedAlternate) {
                        int suggestionBranchId = findBranchWithMedicineStock(medicine, quantity, branchId);
                        if (suggestionBranchId != -1) {
                            System.out.println("Quantity not available in this branch. Available in Branch-" + suggestionBranchId);
                        } else {
                            System.out.println("Medicine / alternate not available.");
                        }
                    }
                }
            }

            System.out.print("Do you want to continue: (Yes/No) :");
            String continueText = scanner.nextLine().trim();
            continuePurchase = continueText.equalsIgnoreCase("yes");
        }

        if (!purchasedItems.isEmpty()) {
            System.out.println("Branch ID Transaction ID Customer ID Medicine Quantity Price");
            System.out.println("-------------------------------------------------------------------------------");
            for (Transaction tx : purchasedItems) {
                System.out.printf("%d %d %d %s %d %.2f%n",
                        tx.getBranchId(), tx.getTransactionId(), customerId, tx.getMedicineName(), tx.getQuantity(), tx.getPrince());
                System.out.println("-------------------------------------------------------------------------------");
            }
            transactionIdCounter++;
        } else {
            System.out.println("No purchase made.");
        }
    }

    private void printCustomerPurchaseSummary() {
        System.out.print("Enter Customer ID :");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Invalid Customer ID.");
            return;
        }
        if (customer.getTransactionList().isEmpty()) {
            System.out.println("No purchase history.");
            return;
        }

        System.out.println("Branch ID Transaction ID Medicine Quantity Price");
        System.out.println("-------------------------------------------------------------------------------");
        for (Transaction tx : customer.getTransactionList()) {
            System.out.printf("%d %d %s %d %.2f%n",
                    tx.getBranchId(), tx.getTransactionId(), tx.getMedicineName(), tx.getQuantity(), tx.getPrince());
            System.out.println("-------------------------------------------------------------------------------");
        }
    }

    private Branch findBranchById(int branchId) {
        for (Branch branch : branchRepository.getBranchList()) {
            if (branch.getBranchId() == branchId) {
                return branch;
            }
        }
        return null;
    }

    private Customer findCustomerById(int customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    private Stock findStock(List<Stock> stocks, String medicineName) {
        for (Stock stock : stocks) {
            if (stock.getMedicineName().equalsIgnoreCase(medicineName)) {
                return stock;
            }
        }
        return null;
    }

    private int findBranchWithMedicineStock(String medicineName, int quantity, int currentBranchId) {
        for (Map.Entry<Integer, List<Stock>> entry : stockByBranch.entrySet()) {
            if (entry.getKey() == currentBranchId) {
                continue;
            }
            for (Stock stock : entry.getValue()) {
                if (stock.getMedicineName().equalsIgnoreCase(medicineName) && stock.getAvailableStock() >= quantity) {
                    return entry.getKey();
                }
            }
        }
        return -1;
    }
}
