package model;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
    private int customerId;
    private String customerName;
    private String customerPhoneNumber;
    private List<Transaction> transactionList;
    public Customer(int customerId, String customerName, String customerPhoneNumber)
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
    public String getCustomerPhoneNumber()
    {
        return customerPhoneNumber;
    }
    public List<Transaction> getTransactionList()
    {
        return transactionList;
    }
}
