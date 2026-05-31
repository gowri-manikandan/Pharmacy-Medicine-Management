package service;

import model.Branch;
import model.Customer;
import model.MedicineStock;
import model.Transaction;
import repository.Repository;

import java.util.List;

public class PutServices
{
    int branchIdCounter = 1;
    int medicineStockIdCounter = 1;
    int customerIdCounter = 1;
    int transactionIdCounter = 1;
    Repository repository;
    public PutServices()
    {
        repository = Repository.getInstance();
    }
    public void createNewBranch(String location,String phoneNumber)
    {
        repository.addNewBranch(new Branch(branchIdCounter++,location,phoneNumber));
    }


    public MedicineStock createNewStock(String medicineName, int qty, double price)
    {
        return new MedicineStock(medicineStockIdCounter++,medicineName,qty,price);
    }

    public void addNewStock(int branchId, MedicineStock medicineStock)
    {
        repository.addNewStockToBranchByBranchId(branchId, medicineStock);
    }

    public void createNewCustomer(String customerName, String customerPhoneNumber)
    {
        repository.addNewCustomer(new Customer(customerIdCounter++,customerName,customerPhoneNumber));
    }

    public Transaction createNewTransaction(int branchId, String medicineName, int quantity, double cost)
    {
        return new Transaction(transactionIdCounter,branchId,medicineName,quantity,cost);
    }

    public void addPurchaseToCustomer(Customer customer, List<Transaction> purchasedItems)
    {
        for(Transaction transaction : purchasedItems)
        {
            customer.getTransactionList().add(transaction);
        }
        transactionIdCounter++;
    }

    public void addNewAlternateMedicine(String medicine, String alternate)
    {
        repository.getAltrenateMedicineMap().put(medicine,alternate);
    }
}
