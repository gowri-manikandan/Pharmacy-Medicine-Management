package features.branch;

import java.util.Scanner;

public class BranchView
{
    private Scanner scanner = new Scanner(System.in);
    private final BranchModel branchModel;
    public BranchView()
    {
        branchModel = new BranchModel(this);
    }
}
