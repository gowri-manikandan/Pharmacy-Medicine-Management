package service;

import model.Branch;
import model.Customer;
import model.MedicineStock;
import repository.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetAndSearchServices
{

    Repository repository;
    public GetAndSearchServices()
    {
        repository = Repository.getInstance();
    }

    public int getCountOfBranches()
    {
        return repository.sizeOfBranchList();
    }

    public Map<Integer, List<MedicineStock>> getBranchStockMap()
    {
        return repository.getBranchStockMap();
    }
    public Branch searchBranchByPhoneNumber(String phoneNumber)
    {
        List<Branch> list = repository.getBranchList();
        for(Branch branch: list)
        {
            if(branch.getBranchPhoneNumber().equals(phoneNumber))
                return branch;
        }
        return null;
    }

    public List<Branch> getBranchList()
    {
        return repository.getBranchList();
    }

    public Branch searchBranchById(int branchId)
    {
        List<Branch> list = repository.getBranchList();
        for(Branch branch: list)
        {
            if(branch.getBranchId()==branchId)
                return branch;
        }
        return null;
    }

    public MedicineStock searchMedicineByBranchIdAndMedicineName(int branchId, String medicineName)
    {
        List<MedicineStock> list = repository.getMedicineListByBranchId(branchId);
        if (!list.isEmpty()) {
            for (MedicineStock medicineStock : list) {
                if (medicineStock.getMedicineName().equals(medicineName)) {
                    return medicineStock;
                }
            }
        }
        return null;
    }

    public Customer searchCustomerByPhoneNumber(String customerPhoneNumber)
    {
        List<Customer> list = repository.getCustomerList();
        if(!list.isEmpty())
        {
            for(Customer customer : list)
            {
                if(customer.getCustomerPhoneNumber().equals(customerPhoneNumber))
                {
                    return customer;
                }
            }
        }
        return null;
    }

    public List<Customer> getCustomerList()
    {
        return repository.getCustomerList();
    }

    public Customer getCustomerById(int customerId)
    {
        List<Customer> list = repository.getCustomerList();
        if(!list.isEmpty())
        {
            for(Customer customer : list)
            {
                if(customer.getCustomerId() == customerId)
                {
                    return customer;
                }
            }
        }
        return null;
    }

    public List<MedicineStock> getMedicineListByBranchId(int branchId)
    {
        return repository.getMedicineListByBranchId(branchId);
    }

    public Set<String> getUniqueMedicineName()
    {
        return repository.getMedicineSet();
    }

    public String searchMedicineNameInMap(String medicine)
    {
        return repository.getMedicineNameInMap(medicine);
    }

    public Map<String,String> getAlternateMedicineMap()
    {
        return repository.getAltrenateMedicineMap();
    }

    public int getBranchIdWhereMedicineAvailable(String medicineName, int quantity, int branchId)
    {
        Map<Integer,List<MedicineStock>> map = repository.getBranchStockMap();
        for(Map.Entry<Integer,List<MedicineStock>> branch : map.entrySet())
        {
            if(branch.getKey()!=branchId)
            {
                for (MedicineStock medicineStock : branch.getValue())
                {
                    if(medicineStock.getMedicineName().equals(medicineName) &&  medicineStock.getAvailableStock()>=quantity)
                    {
                        return branch.getKey();
                    }
                }
            }
        }
        return -1;
    }
}
