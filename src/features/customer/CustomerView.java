package features.customer;

import java.util.Scanner;

public class CustomerView {
    private final CustomerModel customerModel;
    private final Scanner scanner = new Scanner(System.in);
    CustomerView(){
        customerModel = new CustomerModel(this);
    }
}
