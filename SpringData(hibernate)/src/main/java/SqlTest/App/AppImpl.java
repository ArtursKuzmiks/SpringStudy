package SqlTest.App;

import SqlTest.Service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Artur Kuzmik on 18.8.6
 */

@Service("App")
public class AppImpl implements App {

    private CustomerServiceImpl customerService;

    @Autowired
    public AppImpl(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run() throws IOException {
        int menu;

        System.out.println("Menu");
        System.out.println("1: Check the table");
        System.out.println("2: Add customer");
        System.out.println("3: Remote customer");
        System.out.println("4: Correct data");
        System.out.println("5: Sort by order date and Surname");
        System.out.println("6: Debtors list");
        System.out.println("7: Cost of all orders");
        System.out.println("0: END");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            loop:
            for (; ; ) {

                System.out.print("\nInput: ");
                menu = Integer.parseInt(reader.readLine());

                switch (menu) {
                    case 1:
                        customerService.printCustomers();
                        break;
                    case 2:
                        customerService.addCustomer();
                        customerService.printCustomers();
                        break;
                    case 3:
                        customerService.deleteCustomer();
                        customerService.printCustomers();
                        break;
                    case 4:
                        customerService.editCustomer();
                        break;
                    case 5:
                        customerService.sortDateSurname();
                        break;
                    case 6:
                        customerService.debtors();
                        break;
                    case 7:
                        customerService.allPrice();
                        break;
                    case 0:
                        System.out.println("The work is finished.");
                        break loop;
                    default:
                        System.out.print("There is no such option, try again.\n");
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Input error");
        }

    }
}
