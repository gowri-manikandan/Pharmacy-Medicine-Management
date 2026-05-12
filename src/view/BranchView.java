package view;

import model.Branch;

import java.util.List;
import java.util.Scanner;

public class BranchView
{
    static int  branchId = 1;
    Scanner scanner;
    public BranchView()
    {
        scanner = new Scanner(System.in);
    }

    public String getBranchLocation()
    {
        while (true) {
            System.out.print("Enter the Branch Location :");
            String branchLocation = scanner.nextLine().trim();
            if (branchLocation.length() < 3 || branchLocation.length() > 50) {
                System.out.println("Invalid data. Name should be 3 to 50 characters.");
                continue;
            }
            return branchLocation;
        }
    }
    public String getBranchPhoneNumber()
    {
        while (true) {
            System.out.print("Enter the Branch Phone Number :");
            String branchPhoneNumber = scanner.nextLine().trim();
            if (branchPhoneNumber.length() != 10 || !branchPhoneNumber.matches("\\d{10}")) {
                System.out.println("Invalid data. Phone should be 10 digits.");
                continue;
            }
            return branchPhoneNumber;
        }
    }

    public void disPlayAllBranch(List<Branch> branchList)
    {
        if(branchList.isEmpty())
        {
            System.out.println("No Data");
        }
        else
        {
            System.out.println("Branch ID Branch Location Phone number");
            for(Branch branch : branchList)
            {
                System.out.println("-------------------------------------------------------------\n");
                System.out.println(branch.getBranchId()+" | "+branch.getLocationName()+" | "+branch.getBranchPhoneNumber()+"\n");
            }
        }
    }
}
