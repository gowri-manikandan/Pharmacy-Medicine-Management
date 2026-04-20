package db.dto;

public class Stock
{
    private final int stockId;
    private int branchId;
    private String medicineName ;
    private int availableStock;
    private double price;

    public Stock(int stockId, int branchId, String medicineName, int availableStock, double price)
    {
        this.stockId = stockId;
        this.branchId = branchId;
        this.medicineName = medicineName;
        this.availableStock = availableStock;
        this.price = price;
    }

    public int getStockId()
    {
        return stockId;
    }

    public int getBranchId()
    {
        return branchId;
    }

    public void setBranchId(int branchId)
    {
        this.branchId = branchId;
    }

    public String getMedicineName()
    {
        return medicineName;
    }

    public void setMedicineName(String medicineName)
    {
        this.medicineName = medicineName;
    }

    public int getAvailableStock()
    {
        return availableStock;
    }

    public void setAvailableStock(int availableStock)
    {
        this.availableStock = availableStock;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}
