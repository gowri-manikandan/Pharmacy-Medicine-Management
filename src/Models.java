import java.util.ArrayList;
import java.util.List;

public class Models {
}
class Branch{
    private final int branchId;
    private final String locationName;
    private long branchPhoneNumber;
    private final List<Stock> stockList ;
    public Branch(int branchId,String branchName,long branchPhoneNumber){
        this.branchId = branchId;
        locationName = branchName;
        this.branchPhoneNumber = branchPhoneNumber;
        stockList = new ArrayList<>();
    }

    public int getBranchId() {
        return branchId;
    }

    public String getLocationName() {
        return locationName;
    }

    public long getBranchPhoneNumber() {
        return branchPhoneNumber;
    }

    public void setBranchPhoneNumber(long branchPhoneNumber) {
        this.branchPhoneNumber = branchPhoneNumber;
    }

    public List<Stock> getStockList() {
        return stockList;
    }
    public void addNewStock(Stock stock){
        stockList.add(stock);
    }

}
class Stock{
    private final int stockId;
    private int branchId;
    private String medicineName ;
    private int availableStock;
    private double price;
    private String alternateStock;

    public Stock(int stockId, int branchId, String medicineName, int availableStock, double price) {
        this.stockId = stockId;
        this.branchId = branchId;
        this.medicineName = medicineName;
        this.availableStock = availableStock;
        this.price = price;
        this.alternateStock = null;
    }

    public int getStockId() {
        return stockId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getAlternateStock(){
        return alternateStock;
    }
}
class Customer{
    private final int customerId;
    private String customerName;
    private long customerPhoneNumber;
    private final List<Transaction> transactionList;
    public Customer(int customerId, String customerName, long customerPhoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        transactionList = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(long customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
    public void addNewTransaction(Transaction transaction){
        transactionList.add(transaction);
    }
    public List<Transaction> getTransactionList(){
        return transactionList;
    }
}
class Transaction{
    private final int transactionId;
    private final List<TransactionItem> transactionItems;
    private final double total;

    public Transaction(int transactionId, List<TransactionItem> transactionIdeas, double total) {
        this.transactionId = transactionId;
        this.transactionItems = transactionIdeas;
        this.total = total;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public List<TransactionItem> getTransactionIdeas() {
        return transactionItems;
    }

    public double getTotal() {
        return total;
    }
}
class TransactionItem {
    private final int branchId;
    private final String medicineName;
    private final int count;
    private final double cost;

    public TransactionItem(int branchId, String medicineName, int count, double cost) {
        this.branchId = branchId;
        this.medicineName = medicineName;
        this.count = count;
        this.cost = cost;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getCount() {
        return count;
    }

    public double getCost() {
        return cost;
    }
}