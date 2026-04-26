package model;

public class Branch
{
    private final int branchId;
    private final String locationName;
    private String branchPhoneNumber;
    public Branch(int branchId,String branchName,String branchPhoneNumber)
    {
        this.branchId = branchId;
        locationName = branchName;
        this.branchPhoneNumber = branchPhoneNumber;
    }

    public int getBranchId()
    {
        return branchId;
    }

    public String getLocationName()
    {
        return locationName;
    }

    public String getBranchPhoneNumber()
    {
        return branchPhoneNumber;
    }

    public void setBranchPhoneNumber(String branchPhoneNumber)
    {
        this.branchPhoneNumber = branchPhoneNumber;
    }


}