package view;

import model.Branch;
import model.Customer;
import model.MedicineStock;
import model.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DisplayView extends View
{
    public void disPlayAllBranch(List<Branch> list)
    {
        if(list.isEmpty())
        {
            displayError("No Data");
        }
        else
        {
            displayMessage("Branch ID Branch Location Phone number");
            for(Branch branch : list)
            {
                displayLabel();
                displayMessage(branch.getBranchId()+" | "+branch.getLOCATION_NAME()+" | "+branch.getBranchPhoneNumber()+"\n");
            }
        }
    }


    public void displayStockSummary(Map<Integer,List<MedicineStock>> map)
    {
        if(map.isEmpty())
        {
            displayMessage("data base is empty.");
            return;
        }
        displayMessage("Branch ID Medicine Available Qty Price");
        displayLabel();
        for (Map.Entry<Integer, List<MedicineStock>> entry : map.entrySet()) {
            for (MedicineStock medicineStock : entry.getValue()) {
                System.out.printf("%d | %s | %d | %.2f%n",
                        entry.getKey(), medicineStock.getMedicineName(), medicineStock.getAvailableStock(), medicineStock.getPrice());
                displayLabel();
            }
        }
    }
    public void displayCustomerSummary(List<Customer> list)
    {
        displayMessage("Customer ID Customer Name Phone number");
        displayLabel();
        for (Customer customer : list) {
            System.out.println(customer.getCustomerId() + " | " + customer.getCustomerName() + " | " + customer.getCustomerPhoneNumber());
            System.out.println("-----------------------------------------------------");
        }
    }

    void displayCustomerPurchaseSummary(List<Transaction> list)
    {
        displayMessage("Branch ID Transaction ID Medicine Quantity Price");
        displayLabel();
        for (Transaction tx : list) {
            System.out.printf("%d %d %s %d %.2f%n", tx.getBranchId(), tx.getTransactionId(), tx.getMedicineName(), tx.getQuantity(), tx.getPrince());
            displayLabel();
        }
    }


    public static void displayError(String error)
    {
        System.out.println(error);
    }
    public static void displayMessage(String message)
    {
        System.out.println(message);
    }
    private void displayLabel()
    {
        displayMessage("-------------------------------------------------------------\n");
    }

    public void displayMedicineName(List<MedicineStock> medicineStocks)
    {
        for(MedicineStock medicineStock : medicineStocks)
        {
            displayMessage(medicineStock.getMedicineName());
        }
    }

    public void displayAllMedicine(Set<String> uniqueMedicineName)
    {
        int tem = 1;
        for(String medicineName : uniqueMedicineName)
        {
            displayMessage(tem+++". "+medicineName);
        }
    }

    public void displayAlternateMedicine(Map<String ,String > map)
    {
        if(map.isEmpty())
        {
            displayMessage("no data found.");
            return;
        }
        displayMessage("Medicine Alternate");
        displayLabel();
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            displayMessage(entry.getKey() + " | " + entry.getValue());
            displayLabel();
        }
    }
    public void displayTransactionOfCustomer(List<Transaction> list,int customerId)
    {
        displayMessage("Branch ID Transaction ID Customer ID Medicine Quantity Price");
        displayLabel();
        for (Transaction tx : list) {
            System.out.printf("%d %d %d %s %d %.2f%n",
                    tx.getBranchId(), tx.getTransactionId(), customerId, tx.getMedicineName(), tx.getQuantity(), tx.getPrince());
            displayLabel();
        }
    }
}
