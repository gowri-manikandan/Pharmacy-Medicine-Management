import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Services
{
    static int branchId = 1;
    static int customerId = 1;
    static int stockId =1;
    static  int transactionId = 1;
    List<Branch> branchList ;
    List<Customer> customerList;
    Services(){
        branchList = new ArrayList<>();
        customerList = new ArrayList<>();
    }
    Scanner scanner = new  Scanner(System.in);
    public void start()
    {
        System.out.println("Welcome to MGM Pharmacy");
        while (true){
            mainMenu();
        }
    }
    public void mainMenu(){
        System.out.println();
        System.out.println("==========================");
        System.out.println("Main Menu");
        System.out.println("==========================");
        System.out.println();

        System.out.println("Enter 1 For Manage Branch");
        System.out.println("Enter 2 For Manage Stock");
        System.out.println("Enter 3 For Manage Customer");
        System.out.println("Enter 4 For  Purchase Products");
        System.out.println("Enter 5 For View Report");
        System.out.println("Enter 6 For Exit");
        System.out.print("Enter Your Choice :");
        int choice  = scanner.nextInt();
        switch (choice){
            case 1:{
                manageBranch();
                break;
            }
            case 2:{
                manageStock();
                break;
            }
            case 3:{
                manageCustomer();
                break;
            }
            case 4:{
                purchaseProducts();
                break;
            }
            case 5:{
                report();
                break;
            }
            case 6:{
                System.exit(1);
            }
            default:{
                System.out.println("InValid Number Enter a valid Number.\n");
            }
        }
    }
    public void manageBranch(){
        while (true){
            System.out.println();
            System.out.println("==========================");
            System.out.println("Branch Menu");
            System.out.println("==========================");
            System.out.println();
            System.out.println("Enter 1 For Add a Branch");
            System.out.println("Enter 2 For Edit a Branch");
            System.out.println("Enter 3 For Print all Branch Details");
            System.out.println("Enter 4 For Print a Branch Details");
            System.out.println("Enter 5 For Back to Main Menu");
            System.out.println("Enter 6 For Exit");
            System.out.print("Enter Your Choice :");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: {
                    addBranch();
                    break;
                }
                case 2:{
                    System.out.println("next 2\n");
                    break;
                }
                case 3:{
                    displayAllBranch();
                    break;
                }
                case 4:{
                    System.out.println("next 4\n");
                    break;
                }
                case 5:{
                    return;
                }
                case 6 : {
                    System.exit(1);
                }
                default:
                    System.out.println("Invalid Number.please Enter a Valid Number\n");
            }
        }
    }

    public void displayAllBranch() {
        if(branchList.isEmpty()){
            System.out.println("No Data\n");
            return;
        }
        System.out.println("Branch ID Branch Location Phone number\n");
        System.out.println("-----------------------------------------");
        for(Branch branch : branchList){
            System.out.println(branch.getBranchId()+" | "+branch.getLocationName()+" | "+branch.getBranchPhoneNumber());
            System.out.println("-----------------------------------------");
        }
    }

    public void addBranch(){
        System.out.print("Location Name :");
        scanner.nextLine();
        String branchName = scanner.nextLine();
        System.out.print("Phone Number :");
        long branchPhoneNumber = scanner.nextLong();
        if(searchBranchByPhoneNumber(branchPhoneNumber)){
            System.out.println("Phone number is already in the DataBase.\n");
        }
        else {
            Branch newBranch = new Branch(branchId++,branchName,branchPhoneNumber);
            branchList.add(newBranch);
            System.out.println("New Branch is Added with Id : "+newBranch.getBranchId()+"\n");
        }
    }
    public boolean searchBranchByPhoneNumber(long branchPhoneNumber){
        for(Branch branch : branchList){
            if(branch.getBranchPhoneNumber()==branchPhoneNumber){
                return true;
            }
        }
        return false;
    }
    public void manageStock(){
        while (true){
            System.out.println();
            System.out.println("==========================");
            System.out.println("Stock Menu");
            System.out.println("==========================");
            System.out.println();
            System.out.println("Enter 1 For Add a Stock");
            System.out.println("Enter 2 For Edit a Stock");
            System.out.println("Enter 3 For Delete a Stock from all Branch");
            System.out.println("Enter 4 For Delete a Stock from a Branch");
            System.out.println("Enter 5 For Print Stocks in all Branch");
            System.out.println("Enter 6 For Print Stocks From a Branch");
            System.out.println("Enter 7 For Map Alternate Product");
            System.out.println("Enter 8 For Back to Main Menu");
            System.out.println("Enter 9 For Exit");
            System.out.print("Enter Your Choice :");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: {
                    addStock();
                    break;
                }
                case 2:{
                    System.out.println("next 2\n");
                    break;
                }
                case 3:{
                    System.out.println("next 3\n");
                    break;
                }
                case 4:{
                    System.out.println("next 4\n");
                    break;
                }
                case 5:{
                    displayMedicineInAllBranch();
                    break;
                }
                case 6:{

                }
                case 7 : {
                    setAlternativeStock();
                    break;
                }
                case 8:{
                    return;
                }
                case 9:{
                    System.exit(1);
                }
                default:
                    System.out.println("Invalid Number.please Enter a Valid Number\n");
            }
        }
    }

    public void displayMedicineInAllBranch() {
        if(branchList.isEmpty()){
            System.out.println("No Branch in DataBase\n");
        }
        else {
            for(Branch branch:branchList){
                System.out.println("Branch ID Medicine Available Qty Price");
                System.out.println("-----------------------------------------");
                if(!branch.getStockList().isEmpty()){
                    for(Stock stock : branch.getStockList()){
                        System.out.println(stock.getBranchId()+" | "+stock.getStockId()+" | "+stock.getMedicineName()+" | "+stock.getAvailableStock()+" | "+stock.getPrice());
                        System.out.println("-----------------------------------------");
                    }
                }
            }
        }
    }

    public void addStock(){
        System.out.print("Enter the Branch Id :");
        int branchId = scanner.nextInt();
        Branch branch = searchBranchById(branchId);
        if(branch == null){
            System.out.println("Id not Found.\n");
        }
        else {
            System.out.print("Enter the Medicine Name :");
            scanner.nextLine();
            String medicineName = scanner.nextLine().trim().toLowerCase();
            if(searchMedicineByName(branch.getStockList(),medicineName))
                System.out.println("Medicine is Already in DataBase.\n");
            else {
                System.out.print("Enter the Available Qty :");
                int qty = scanner.nextInt();
                System.out.print("Enter the Price :");
                double price = scanner.nextDouble();
                Stock newstock = new Stock(stockId++,branch.getBranchId(),medicineName,qty,price);
                branch.addNewStock(newstock);
                System.out.println("New Medicine Added to Branch "+branch.getBranchId()+"\n");
            }
        }
    }
    public Branch searchBranchById(int branchid){
        for(Branch branch : branchList){
            if(branch.getBranchId() == branchid){
                return branch;
            }
        }
        return null;
    }
    public boolean searchMedicineByName(List<Stock> stockList,String medicineName){
        for(Stock stock : stockList){
            if(stock.getMedicineName().equals(medicineName))
                return true;
        }
        return false;
    }
    public void setAlternativeStock(){
        System.out.println("next \n");
    }
    public void manageCustomer(){
        while (true){
            System.out.println();
            System.out.println("----------------------------------");
            System.out.println("Customer Menu");
            System.out.println("----------------------------------");
            System.out.println();
            System.out.println("Enter 1 For Add a Customer");
            System.out.println("Enter 2 For Edit a Customer");
            System.out.println("Enter 3 For Print all Customer Details");
            System.out.println("Enter 4 For Print a Customer Details");
            System.out.println("Enter 5 For Delete a Customer");
            System.out.println("Enter 6 For Back to Main Menu");
            System.out.println("Enter 7 For Exit");
            System.out.print("Enter Your Choice :");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: {
                    addCustomer();
                    break;
                }
                case 2:{
                    System.out.println("next 2");
                    break;
                }
                case 3:{
                    displayAllCustomer();
                    break;
                }
                case 4:{
                    System.out.println("next 4");
                    break;
                }
                case 5:{
                    System.out.println("next 5");
                    break;
                }
                case 6:{
                    return;
                }
                case 7 : {
                    System.exit(1);
                }
                default:
                    System.out.println("Invalid Number.please Enter a Valid Number");
            }
        }
    }



    public void addCustomer() {
        System.out.print("Customer Name :");
        scanner.nextLine();
        String customerName = scanner.nextLine();
        System.out.print("Phone Number :");
        long customerPhoneNumber = scanner.nextLong();
        if(searchCustomerByPhoneNumber(customerPhoneNumber)){
            System.out.println("Phone number is already in the DataBase.");
        }
        else {
            Customer newCustomer = new Customer(customerId++,customerName,customerPhoneNumber);
            customerList.add(newCustomer);
            System.out.println("New Customer is Added with Id : "+ newCustomer.getCustomerId());
        }
    }
    public boolean searchCustomerByPhoneNumber(long customerPhoneNumber){
        for(Customer customer : customerList){
            if(customer.getCustomerPhoneNumber()==customerPhoneNumber){
                return true;
            }
        }
        return false;
    }
    public void displayAllCustomer() {
        if(customerList.isEmpty()){
            System.out.println("NO DATA\n");
        }
        else {
            System.out.println("Customer ID Customer Name Phone number");
            System.out.println("------------------------------------------");
            for(Customer customer : customerList){
                System.out.println(customer.getCustomerId()+" | "+customer.getCustomerName()+" | "+customer.getCustomerPhoneNumber());
            }
        }
    }
    public void report(){
        while (true){
            System.out.println();
            System.out.println("==========================");
            System.out.println("Report Menu");
            System.out.println("==========================");
            System.out.println();
            System.out.println("Enter 1 For Print Report for All Customer");
            System.out.println("Enter 2 For Print Report for a Customer");
            System.out.println("Enter 3 For Print Report for a Branch");
            System.out.println("Enter 4 For Print Report All Branch");
            System.out.println("Enter 5 For Back to Main menu");
            System.out.println("Enter 6 For Exit");
            System.out.print("Enter Your Choice :");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    System.out.println("next\n");
                    break;
                }
                case 2:{
                    displayReportByACustomer();
                    break;
                }
                case 3:{
                    System.out.println("next1\n");
                    break;
                }
                case 4:{
                    System.out.println("next2\n");
                    break;
                }
                case 5:{
                    return;
                }
                case 6:{
                    System.exit(1);
                }
            }
        }

    }

    public void displayReportByACustomer() {
        System.out.print("Enter Customer ID :");
        int customerId = scanner.nextInt();
        Customer customer = searchCustomerById(customerId);
        if(customer==null)
            System.out.print("InValid Customer ID");
        else {
            if(customer.getTransactionList().isEmpty()){
                System.out.println("No Data");
                return;
            }
            for(Transaction transaction : customer.getTransactionList()){
                System.out.println("Branch ID Transaction ID Medicine Quantity Price");
                for(TransactionItem transactionItem : transaction.getTransactionIdeas()){
                    System.out.println(transactionItem.getBranchId()+" | "+transaction.getTransactionId()+" | "+transactionItem.getMedicineName()+" | "+transactionItem.getCount()+" | "+transactionItem.getCost());
                }
                System.out.println(" Amount "+transaction.getTotal());
            }
        }
    }
    public Customer searchCustomerById(int customerId){
        for(Customer customer : customerList){
            if(customer.getCustomerId()==customerId)
                return customer;
        }
        return null;
    }
    public void purchaseProducts(){
        System.out.print("Customer Id:");
        int customerId = scanner.nextInt();
        Customer customer = searchCustomerById(customerId);
        if(customer==null){
            System.out.println("Customer Not Found\n");
            return;
        }
        System.out.print("Branch Id:");
        int branchId = scanner.nextInt();
        Branch branch  = searchBranchById(branchId);
        if(branch==null){
            System.out.println("Branch Not Found\n");
            return;
        }
        List<TransactionItem> transactionItemList = new ArrayList<>();
        double total = 0.0;
        boolean loop = true;
        while (loop){
            System.out.print("Enter Product:");
            scanner.nextLine();
            String product = scanner.nextLine().trim().toLowerCase();
            System.out.print("Enter  Quantity:");
            int  quantity = scanner.nextInt();
            Stock stock = searchMedicineByBranchIdAndMedicineName(branch,product);
            if(stock==null){
                int id = searchMedicineByMedicineName(product);
                if(id!=-1)
                    System.out.println("Medicine not available in this branch. Available in Branch "+id+" \n");
                else {
                    System.out.println("not Medicine not available Any Branch.\n");
                }
            }
            else{
                if(stock.getAvailableStock()<quantity) {
                    if (stock.getAlternateStock() != null) {
                        Stock alProduct = searchMedicineByBranchIdAndMedicineName(branch, stock.getAlternateStock());
                        if (alProduct != null && alProduct.getAvailableStock() >= quantity) {
                            System.out.println("Quantity not available.\n");
                            System.out.println("Do you want to purchase " + alProduct.getMedicineName() + " ?");
                            scanner.nextLine();
                            char choice = scanner.next().trim().charAt(0);
                            if (choice == 'Y') {
                                TransactionItem transactionItem = new TransactionItem(branch.getBranchId(), alProduct.getMedicineName(), quantity, alProduct.getPrice());
                                total += quantity * alProduct.getPrice();
                                alProduct.setAvailableStock(alProduct.getAvailableStock()-quantity);
                                transactionItemList.add(transactionItem);
                            }
                        }
                    } else {
                        int id = searchMedicineByMedicineName(product);
                        if (id != -1)
                            System.out.println("Medicine not available in this branch. Available in Branch " + id+" \n");
                        else {
                            System.out.println("not Medicine not available Any Branch.\n");
                        }
                    }
                }
                else {
                    TransactionItem transactionItem = new TransactionItem(branch.getBranchId(),stock.getMedicineName(),quantity,stock.getPrice());
                    total += quantity*stock.getPrice();
                    transactionItemList.add(transactionItem);
                    stock.setAvailableStock(stock.getAvailableStock()-quantity);
                }
            }
            System.out.println("Do you want to continue");
            scanner.nextLine();
            String choice = scanner.nextLine().trim().toLowerCase();
            if(choice.equals("no"))
                loop = false;
        }
        if(!transactionItemList.isEmpty()){
            Transaction transaction = new Transaction(transactionId,transactionItemList,total);
            customer.addNewTransaction(transaction);
            System.out.println("Branch ID Transaction ID Customer ID Medicine Quantity Price");
            System.out.println("---------------------------------------");
            for(TransactionItem transactionItem : transactionItemList){
                System.out.println(transactionItem.getBranchId()+" | "+transactionId+" | "+customer.getCustomerId()+" | "+transactionItem.getMedicineName()+" | "+transactionItem.getCount()+" | "+transactionItem.getCost());
            }
        }
    }
    public Stock searchMedicineByBranchIdAndMedicineName(Branch branch,String  medicineName){
        for(Stock stock : branch.getStockList()){
            if(stock.getMedicineName().equals(medicineName))
                return stock;
        }
        return null;
    }
    public int searchMedicineByMedicineName(String  medicineName) {
        for (Branch branch : branchList) {
            for (Stock stock : branch.getStockList()) {
                if (stock.getMedicineName().equals(medicineName))
                    return branch.getBranchId();
            }
        }
        return -1;
    }
}
