package model;

public class Branch
{
    private  int branchId;
    private String locationName;
    private String branchPhoneNumber;
    public Branch(int branchId,String branchName,String branchPhoneNumber)
    {
        this.branchId = branchId;
        this.locationName = branchName;
        this.branchPhoneNumber = branchPhoneNumber;
    }

    public int getBranchId()
    {
        return branchId;
    }

    public String getLOCATION_NAME()
    {
        return locationName;
    }

    public String getBranchPhoneNumber()
    {
        return branchPhoneNumber;
    }

}