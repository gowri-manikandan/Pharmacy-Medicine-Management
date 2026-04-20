package features.stock;

import java.util.Scanner;

public class StockView
{
    private final StockModel stockModel;
    private final Scanner scanner = new Scanner(System.in);
    StockView()
    {
        stockModel = new StockModel(this);
    }
}
