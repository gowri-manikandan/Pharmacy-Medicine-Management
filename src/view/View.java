package view;


import util.Util;

import java.util.Scanner;

public class View
{
    static Scanner scanner;

    public static Scanner getSCANNER() {
        if(scanner==null)
        {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    private String readLine()
    {
        return getSCANNER().nextLine().trim();
    }

    protected String getString(String prompt) {
        while (true) {
            displayMessage(prompt);
            String value = readLine();
            if(Util.verifyString(value))
            {
                return value;
            }
            else
            {
                displayError("Invalid data. String should be 3 to 50 characters.");
            }
        }
    }
    public String getPhoneNumber(String prompt) {
        while (true)
        {
            displayMessage(prompt);
            String value = readLine();
            if(Util.verifyPhoneNumber(value)) {
                return value;
            }
            else
            {
                displayError("Invalid data. Phone should be 10 digits.");
            }
        }
    }
    public int getPositiveInt(String prompt)
    {
        while (true) {
            displayMessage(prompt);
            try {
                int value = Integer.parseInt(readLine());
                if (Util.verifyPositiveInt(value)) {
                    return value;
                }
                displayError("invalid number.Pleases enter a positive number.");
            }
            catch (NumberFormatException e)
            {
                displayError("Invalid input. Pleases enter a Number.");
            }
        }
    }

    public double getPositiveDouble(String prompt)
    {
        while (true)
        {
            displayMessage(prompt);
            try
            {
                double value = Double.parseDouble(readLine());
                if(Util.verifyPositiveDouble(value))
                {
                    return value;
                }
                else
                {
                    displayError("Invalid number.Price must greater than  0.0rs .");
                }
            }
            catch (Exception e)
            {
                displayError("Invalid number.Enter a valid input.(0.0)");
            }
        }
    }
    public static void displayError(String error)
    {
        System.out.println(error);
    }
    public static void displayMessage(String message)
    {
        System.out.println(message);
    }
}
