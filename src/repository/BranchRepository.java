package repository;

import model.Branch;


import java.util.ArrayList;
import java.util.List;

public class BranchRepository
{
    List<Branch> branchList ;
    static BranchRepository repository;
    private BranchRepository()
    {
        branchList = new ArrayList<>();
    }
    public static BranchRepository getInstance()
    {
        if(repository == null)
        {
           repository = new BranchRepository();
        }
        return repository;
    }
    public void addNewBranch(Branch newBranch)
    {
        branchList.add(newBranch);
    }
    public List<Branch> getBranchList()
    {
        return branchList;
    }
}
