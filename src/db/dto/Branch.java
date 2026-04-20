package db.dto;

public class Branch
{
    private final int branchId;
    private final String locationName;
    private long branchPhoneNumber;
    public Branch(int branchId,String branchName,long branchPhoneNumber)
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

    public long getBranchPhoneNumber()
    {
        return branchPhoneNumber;
    }

    public void setBranchPhoneNumber(long branchPhoneNumber)
    {
        this.branchPhoneNumber = branchPhoneNumber;
    }


}