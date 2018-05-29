package SqlTest;

import SqlTest.AppConfig.AppConfig;
import SqlTest.Service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author Artur Kuzmik on 18.29.5
 */

public class App {

    public static void main(String[] args) throws IOException {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerService customerService = (CustomerService) context.getBean("CustomerService");

        int menu;

        System.out.println("Menu");
        System.out.println("Check the table                                   : 1");
        System.out.println("Add customer                                      : 2");
        System.out.println("Remote customer                                   : 3");
        System.out.println("Correct data                                      : 4");
        System.out.println("Sort by order date and Surname                    : 5");
        System.out.println("Debtors list                                      : 6");
        System.out.println("Cost of all orders                                : 7");
        System.out.println("END                                               : 0");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            loop:
            for (; ; ) {

                System.out.print("Input:");
                menu = Integer.parseInt(br.readLine());

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
