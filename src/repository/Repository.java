package repository;

import model.Branch;
import model.Customer;
import model.MedicineStock;


import java.util.*;

public class Repository
{
    List<Branch> branchList;
    Map<Integer,List<MedicineStock>> branchStockMap;
    List<Customer> customerList;
    Set<String> medicineSet;
    Map<String,String> alternateMap;
    static Repository repository;
    private Repository()
    {
        branchList = new ArrayList<>();
        branchStockMap = new HashMap<>();
        customerList = new ArrayList<>();
        medicineSet = new LinkedHashSet<>();
        alternateMap = new HashMap<>();
    }
    public static Repository getInstance()
    {
        if(repository == null)
        {
           repository = new Repository();
        }
        return repository;
    }
    public void addNewBranch(Branch newBranch)
    {
        branchList.add(newBranch);
        branchStockMap.put(newBranch.getBranchId(),new ArrayList<>());
    }

    public int sizeOfBranchList()
    {
        return branchList.size();
    }

    public List<Branch> getBranchList()
    {
        return branchList;
    }

    public List<MedicineStock> getMedicineListByBranchId(int branchId)
    {
        return branchStockMap.get(branchId);
    }

    public void addNewStockToBranchByBranchId(int branchId, MedicineStock medicine)
    {
        branchStockMap.get(branchId).add(medicine);
        medicineSet.add(medicine.getMedicineName());
    }

    public Map<Integer, List<MedicineStock>> getBranchStockMap()
    {
        return branchStockMap;
    }

    public List<Customer> getCustomerList()
    {
        return customerList;
    }

    public void addNewCustomer(Customer customer)
    {
        customerList.add(customer);
    }

    public Set<String> getMedicineSet()
    {
        return medicineSet;
    }

    public String getMedicineNameInMap(String medicine)
    {
        if(alternateMap.containsKey(medicine))
        {
            return alternateMap.get(medicine);
        }
        return null;
    }

    public  Map<String,String> getAltrenateMedicineMap()
    {
        return alternateMap;
    }
}
