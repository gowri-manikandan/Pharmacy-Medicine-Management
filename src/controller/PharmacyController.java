package controller;

import model.Branch;
import model.Customer;
import model.MedicineStock;
import model.Transaction;
import service.GetAndSearchServices;
import service.PutServices;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PharmacyController
{
    GetAndSearchServices getServices;
    PutServices putServices;
    public PharmacyController()
    {
        getServices = new GetAndSearchServices();
        putServices = new PutServices();
    }
    public void requestNewBranch(String branchLocation,String branchPhoneNumber)
    {
        putServices.createNewBranch(branchLocation,branchPhoneNumber);
    }
    public int requestBranchSize()
    {
        return getServices.getCountOfBranches();
    }

    public boolean requestBranchByPhoneNumber(String branchPhoneNumber)
    {
        return getServices.searchBranchByPhoneNumber(branchPhoneNumber)!=null;
    }

    public List<Branch> requestBranchList()
    {
        return getServices.getBranchList();
    }

    public Branch requestBranchById(int branchId)
    {
        return getServices.searchBranchById(branchId);
    }

    public MedicineStock requestMedicineByBranchIdAndMedicineName(int branchId, String medicineName)
    {
        return getServices.searchMedicineByBranchIdAndMedicineName(branchId,medicineName);
    }

    public void requestNewStock(int branchId, String medicineName, int qty, double price)
    {
        MedicineStock medicineStock = putServices.createNewStock(medicineName,qty,price);
        putServices.addNewStock(branchId, medicineStock);
    }

    public Map<Integer, List<MedicineStock>> requestBranchStockMap()
    {
        return getServices.getBranchStockMap();

    }

    public Customer requestCustomerByPhoneNumber(String customerPhoneNumber)
    {
        return getServices.searchCustomerByPhoneNumber(customerPhoneNumber);
    }

    public void requestNewCustomer(String customerName, String customerPhoneNumber)
    {
        putServices.createNewCustomer(customerName,customerPhoneNumber);
    }

    public List<Customer> requestCustomerList()
    {
        return getServices.getCustomerList();
    }

    public Customer requestCustomerById(int customerId)
    {
        return getServices.getCustomerById(customerId);
    }


    public List<MedicineStock> requestMedicineListByBranchId(int branchId)
    {
        return getServices.getMedicineListByBranchId(branchId);
    }

    public Transaction requestNewTransaction(int branchId, String medicineName, int quantity, double cost)
    {
        return putServices.createNewTransaction(branchId,medicineName,quantity,cost);
    }

    public void requestAddPurchaseToCustomer(Customer customer, List<Transaction> purchasedItems)
    {
        putServices.addPurchaseToCustomer(customer,purchasedItems);
    }

    public Set<String> requestUniqueMedicineName()
    {
        return getServices.getUniqueMedicineName();
    }

    public String  requestMedicineNameInAlternateMap(String medicine)
    {
        return  getServices.searchMedicineNameInMap(medicine);
    }

    public void requestNewAlternateMedicine(String medicine, String alternate)
    {
        putServices.addNewAlternateMedicine(medicine,alternate);
    }

    public Map<String, String> requestAlternateMedicineMap()
    {
        return getServices.getAlternateMedicineMap();
    }

    public int requestBranchIdWhereMedicineAvailable(String medicineName, int quantity, int branchId)
    {
       return getServices.getBranchIdWhereMedicineAvailable(medicineName,quantity,branchId);
    }
}
