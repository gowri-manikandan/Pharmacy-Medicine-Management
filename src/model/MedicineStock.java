package model;

public class MedicineStock
{
    private int medicineStockId;
    private String medicineName ;
    private int availableStock;
    private double price;

    public MedicineStock(int medicineStockId, String medicineName, int availableStock, double price)
    {
        this.medicineStockId = medicineStockId;
        this.medicineName = medicineName;
        this.availableStock = availableStock;
        this.price = price;
    }


    public String getMedicineName()
    {
        return medicineName;
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
}
