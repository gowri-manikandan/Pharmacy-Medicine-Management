package features;

import java.util.Scanner;

public class BaseView
{
    private BaseModel baseModel;
    public BaseView()
    {
        baseModel = new BaseModel(this);
    }
    Scanner scanner = new Scanner(System.in);

    public void init()
    {
        System.out.println("========= Welcome To MGM Pharmacy =========");
    }
    public int getMenuChoice()
    {
        printMainMenu();
        int choice = scanner.nextInt();
    }
    private void printMainMenu()
    {
        System.out.println("___________ Main Menu ___________");
        System.out.println("1. Add Branch");
        System.out.println("2. Add Stock to a Pharmacy Branch");
        System.out.println("3. Associate Alternate Products");
        System.out.println("4. Add Customer");
        System.out.println("5. Purchase Products");
        System.out.println("6. Print Customer Purchase Summary");
        System.out.println("7. Exit");
        System.out.println("Enter Your Choice");
    }
}
