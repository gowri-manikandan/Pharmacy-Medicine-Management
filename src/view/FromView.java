package view;

import controller.Controller;
import model.Branch;
import model.Customer;
import model.MedicineStock;
import model.Transaction;
import util.Util;

import java.util.*;

public class FromView extends View {
    Scanner scanner;
    Controller controller;
    DisplayView displayView;

    FromView() {
        controller = new Controller();
        scanner = View.getSCANNER();
        displayView = new DisplayView();
    }

    public void getNewBranchFrom()
    {
        String branchLocation = getString("Enter the Branch Name :");
        String branchPhoneNumber = getPhoneNumber("Enter Branch Phone Number :");
        if (controller.requestBranchByPhoneNumber(branchPhoneNumber)) {
            View.displayError("Phone number already exites.");
            return;
        }
        controller.requestNewBranch(branchLocation, branchPhoneNumber);
        List<Branch> branchList = controller.requestBranchList();
        displayView.disPlayAllBranch(branchList);
    }

    public void getNewStockFrom() {
        if (controller.requestBranchSize() == 0) {
            System.out.println("No branch data. Add branch first.");
            return;
        }
        int branchId = getPositiveInt("Enter Branch ID :");
        Branch branch = controller.requestBranchById(branchId);
        if (branch == null) {
            View.displayError("Invalid Branch ID.");
            return;
        }
        String medicineName = getString("Enter Medicine Name :").trim().toLowerCase(Locale.ROOT);
        if (controller.requestMedicineByBranchIdAndMedicineName(branchId, medicineName) != null) {
            View.displayError("Medicine is already exites.");
            return;
        }
        int qty = getPositiveInt("Enter Available Quantity :");
        double price = getPositiveDouble("Enter Price :");
        controller.requestNewStock(branchId, medicineName, qty, price);
        displayMessage("Medicine is add Successfully.");
        Map<Integer, List<MedicineStock>> map = controller.requestBranchStockMap();
        displayView.displayStockSummary(map);
    }

    public void getNewCustomerFrom() {
        String customerName = getString("Enter Customer Name :");
        String customerPhoneNumber = getPhoneNumber("Enter Customer Phone Number :");
        if (controller.requestCustomerByPhoneNumber(customerPhoneNumber) != null) {
            displayError("Phone number already exists.");
            return;
        }
        controller.requestNewCustomer(customerName, customerPhoneNumber);
        List<Customer> customerList = controller.requestCustomerList();
        displayView.displayCustomerSummary(customerList);
    }

    public void getCustomerIdForDisplayTransaction() {
        int customerId = getPositiveInt("Enter Customer ID :");
        Customer customer = controller.requestCustomerById(customerId);
        if (customer == null) {
            displayError("Invalid Customer ID.");
            return;
        }
        if (customer.getTransactionList().isEmpty()) {
            displayMessage("No purchase history.");
            return;
        }
        displayView.displayCustomerPurchaseSummary(customer.getTransactionList());
    }

    void purchaseProductFrom()
    {
        int customerId = getPositiveInt("Enter Customer ID :");
        Customer customer = controller.requestCustomerById(customerId);
        if (customer == null) {
            displayError("Invalid Customer ID.");
            return;
        }
        int branchId = getPositiveInt("Enter Branch ID :");
        Branch branch = controller.requestBranchById(branchId);
        if (branch == null) {
            displayError("Invalid Branch ID.");
            return;
        }

        List<Transaction> purchasedItems = new ArrayList<>();
        List<MedicineStock> medicineListByBranchId = controller.requestMedicineListByBranchId(branchId);
        boolean continuePurchase = true;
        while (continuePurchase) {
            displayView.displayMedicineName(medicineListByBranchId);
            String medicineName = getString("Enter Product :").trim().toLowerCase(Locale.ROOT);
            MedicineStock selectedMedicine = Util.verifyMedicineNameInList(medicineListByBranchId, medicineName);
            if (selectedMedicine == null) {
                displayError("invalid name.Medicine Number is not fount in the list.");
                return;
            }

            int quantity = getPositiveInt("Enter Quantity :");

            if (selectedMedicine.getAvailableStock() >= quantity) {
                selectedMedicine.setAvailableStock(selectedMedicine.getAvailableStock() - quantity);
                Transaction tx = controller.requestNewTransaction(branchId, selectedMedicine.getMedicineName(), quantity, selectedMedicine.getPrice() * quantity);
                purchasedItems.add(tx);
            }
            else
            {
                String alternateMedicineName = controller.requestMedicineNameInAlternateMap(medicineName);
                boolean purchasedAlternate = false;
                if (alternateMedicineName != null)
                {
                    MedicineStock alternateMedicine = controller.requestMedicineByBranchIdAndMedicineName(branchId, alternateMedicineName);
                    if (alternateMedicine.getAvailableStock() >= quantity)
                    {
                        System.out.print("Do you want to purchase " + alternateMedicineName + "? if Yes Enter (Y) or else enter any key:");
                        String choice = scanner.nextLine().trim();
                        if (choice.equalsIgnoreCase("Y"))
                        {
                            alternateMedicine.setAvailableStock(alternateMedicine.getAvailableStock() - quantity);
                            Transaction tx = controller.requestNewTransaction(branchId, alternateMedicine.getMedicineName(), quantity, alternateMedicine.getPrice() * quantity);
                            purchasedItems.add(tx);
                            purchasedAlternate = true;
                        }
                    }
                }
                if (!purchasedAlternate)
                {
                    int suggestionBranchId = controller.requestBranchIdWhereMedicineAvailable(medicineName, quantity, branchId);
                    if (suggestionBranchId != -1) {
                        displayMessage("Quantity not available in this branch. Available in Branch-" + suggestionBranchId);
                    } else {
                        displayMessage("Medicine / alternateMedicineName not available.");
                    }
                }
            }
            displayMessage("To continue enter Y :");
            String continueText = scanner.nextLine().trim();
            continuePurchase = continueText.equalsIgnoreCase("Y");
        }
        if (!purchasedItems.isEmpty())
        {
            controller.requestAddPurchaseToCustomer(customer,purchasedItems);
            displayView.displayTransactionOfCustomer(purchasedItems,customerId);
        }
        else
        {
            System.out.println("No purchase made.");
        }
    }

    void associateAlternateProducts()
    {
        Set<String> uniqueMedicineNameList = controller.requestUniqueMedicineName();
        if(uniqueMedicineNameList.isEmpty())
        {
            displayMessage("no medicine found.");
            return;
        }
        String medicine = getMedicineName("Enter Medicine Index:",uniqueMedicineNameList);
        String alternate = getMedicineName("Enter Alternate Medicine Name :",uniqueMedicineNameList);
        if (medicine.equals(alternate))
        {
            displayError("medicine and alternate medicine can not be same.");
            return;
        }
        String tem = controller.requestMedicineNameInAlternateMap(medicine);
        if(tem!=null)
        {
            if(tem.equals(alternate))
            {
                displayMessage("the medicine is already mapped with the same alternate medicine");
            }
            else
            {
                displayMessage("this medicine is already mapped with "+tem+" do you want to change it if yes enter (Y) or enter any key.");
                String check = scanner.nextLine();
                if(!check.trim().equalsIgnoreCase("Y"))
                {
                    return;
                }
            }
        }
        controller.requestNewAlternateMedicine(medicine,alternate);
        displayView.displayAlternateMedicine(controller.requestAlternateMedicineMap());
    }

    private String getMedicineName(String prompt,Set<String> medicineList)
    {
        while (true)
        {
            displayView.displayAllMedicine(medicineList);
            int medicineIndex = getPositiveInt(prompt);

            List<String> medicines = new ArrayList<>(medicineList);

            if (medicineIndex >= 1 && medicineIndex <= medicines.size())
            {
                return medicines.get(medicineIndex - 1);
            }

            displayError("Invalid input. Enter a correct index.");
        }
    }
}
