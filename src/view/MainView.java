package view;

import controller.BranchController;
import controller.StockController;
import model.Stock;

import java.util.Scanner;

public class MainView
{
    private final  Scanner scanner;
    private final BranchController branchController;
    private final StockController stockController;
    String name;
    String city;
    public MainView()
    {
        scanner = new Scanner(System.in);
        branchController = new BranchController();
    }
    public void init()
    {
        System.out.print("Enter the Pharmacy Name :");
        name = scanner.next();
        System.out.print("Enter the City Name :");
        city = scanner.next();
        displayMainMenu();
    }

    private void displayMainMenu() {
        while (true) {
            System.out.println("====== Welcome To " + name + " Pharmacy " + " ======");
            System.out.println("1. Add Branch");
            System.out.println("2. Add Stock to a Pharmacy Branch");
            System.out.println("3. Associate Alternate Products");
            System.out.println("4. Add Customer");
            System.out.println("5. Purchase Products");
            System.out.println("6. Print Customer Purchase Summary");
            System.out.println("7. exit");
            System.out.print("Enter your Choice :");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                {
                    branchController.addBranch();
                    break;
                }
                case 2:
                {

                }
                case 7:
                {
                    System.exit(1);
                }
                default: {
                    System.out.println("Invalid Choice.");
                }

            }
        }
    }
}
