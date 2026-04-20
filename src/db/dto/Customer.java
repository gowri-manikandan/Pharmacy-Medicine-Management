package db.dto;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
    private final int customerId;
    private String customerName;
    private long customerPhoneNumber;
    private final List<Transaction> transactionList;
    public Customer(int customerId, String customerName, long customerPhoneNumber)
    {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        transactionList = new ArrayList<>();
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public long getCustomerPhoneNumber()
    {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(long customerPhoneNumber)
    {
        this.customerPhoneNumber = customerPhoneNumber;
    }
    public void addNewTransaction(Transaction transaction)
    {
        transactionList.add(transaction);
    }
    public List<Transaction> getTransactionList()
    {
        return transactionList;
    }
}
