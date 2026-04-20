package features.transaction;

import java.util.Scanner;

public class TransactionView
{
    private final TransactionModel transactionModel;
    public final Scanner scanner = new Scanner(System.in);
    TransactionView()
    {
        transactionModel = new TransactionModel(this);
    }
}
