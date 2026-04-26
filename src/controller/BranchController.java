package controller;

import model.Branch;
import repository.BranchRepository;
import view.BranchView;

public class BranchController
{
    static int branchId = 1;

    BranchView branchView;
    public BranchController()
    {
        branchView = new BranchView();
    }
    public void addBranch()
    {
        String branchLocation = branchView.getBranchLocation();
        String branchPhoneNumber = branchView.getBranchPhoneNumber();
        Branch newBranch = new Branch(branchId++,branchLocation,branchPhoneNumber);
        BranchRepository branchRepository = BranchRepository.getInstance();
        branchRepository.addNewBranch(newBranch);
        branchView.disPlayAllBranch(branchRepository.getBranchList());
    }
}
