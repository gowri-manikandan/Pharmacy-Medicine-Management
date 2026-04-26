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
        System.out.print("Enter the Branch Location :");
        String branchLocation = scanner.next();
        if(branchLocation.length()<3 || branchLocation.length()>50)
        {
            System.out.println("Invalid data.");
            getBranchLocation();
        }
        return branchLocation;
    }
    public String getBranchPhoneNumber()
    {
        System.out.print("Enter the Branch Phone Number :");
        String branchPhoneNumber = scanner.next();
        if(branchPhoneNumber.length()!=10)
        {
            System.out.println("Invalid data.");
            getBranchPhoneNumber();
        }
        return branchPhoneNumber;
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
