package view;

import java.util.Scanner;

public class MainView extends View
{
    Scanner scanner;
    FromView fromView;
    DisplayView displayView;

    String name;
    String city;
    public MainView()
    {
        scanner = View.getSCANNER();
        fromView = new FromView();
        displayView = new DisplayView();
    }
    public void init()
    {
        name = getString("Enter the Pharmacy Name :");
        city = getString("Enter the City Name :");
        displayMainMenu();
    }

    private void displayMainMenu() {
        while (true) {
            System.out.println("====== Welcome To " + name + " Pharmacy " + " ======");
            System.out.println("1. Add Branch");
            System.out.println("2. Add MedicineStock to a Pharmacy Branch");
            System.out.println("3. Associate Alternate Products");
            System.out.println("4. Add Customer");
            System.out.println("5. Purchase Products");
            System.out.println("6. Print Customer Purchase Summary");
            System.out.println("7. exit");
            int choice = getPositiveInt("Enter your Choice :");
            switch (choice) {
                case 1:
                {
                    fromView.getNewBranchFrom();
                    break;
                }
                case 2:
                {
                    fromView.getNewStockFrom();
                    break;
                }
                case 3:
                {
                    fromView.associateAlternateProducts();
                    break;
                }
                case 4:
                {
                    fromView.getNewCustomerFrom();
                    break;
                }
                case 5:
                {
                    fromView.purchaseProductFrom();
                    break;
                }
                case 6:
                {
                    fromView.getCustomerIdForDisplayTransaction();
                    break;
                }
                case 7:
                {
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Invalid Choice.");
                }

            }
        }
    }
}
