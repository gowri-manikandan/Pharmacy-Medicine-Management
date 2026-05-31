package util;

import model.MedicineStock;

import java.util.List;

public class Util
{
    public static boolean verifyString(String value)
    {
        return value.length() >= 3 && value.length() <= 50;
    }
    public static boolean verifyPhoneNumber(String value)
    {
        return value.matches("^[6-9][0-9]{9}$");
    }
    public static boolean verifyPositiveInt(int num)
    {
        return num>0;
    }

    public static boolean verifyPositiveDouble(double num)
    {
        return num>0;
    }

    public static MedicineStock verifyMedicineNameInList(List<MedicineStock> medicineListByBranchId, String medicineName)
    {
        for(MedicineStock medicineStock :medicineListByBranchId )
        {
            if(medicineStock.getMedicineName().equals(medicineName))
            {
                return medicineStock;
            }
        }
        return null;
    }
}
