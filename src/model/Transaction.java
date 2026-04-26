package model;

import java.util.List;

public class Transaction
{
    private final int transactionId;
    private final int branchId;
    private final String medicineName;
    private final int quantity;
    private final double prince;
    public Transaction(int transactionId,int branchId,String medicineName,int quantity,double prince )
    {
        this.transactionId = transactionId;
        this.branchId = branchId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.prince = prince;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrince() {
        return prince;
    }
}
