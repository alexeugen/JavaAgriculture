import ro.unibuc.fmi.pao.project.classes.Farmer;
import ro.unibuc.fmi.pao.project.classes.Handler;
import ro.unibuc.fmi.pao.project.classes.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Farmer> farmers = new ArrayList<>();
        List<Shop> shops = new ArrayList<>();
        Handler handler = new Handler();
        handler.initialize(farmers);


        int option = 1;

        while(option != 0) {
            handler.printOptions();
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            switch (option) {
                case 1:
                    handler.print(farmers);
                    break;

                case 2:
                    handler.printByCity(farmers);
                    break;
                case 3:
                    handler.printByProduct(farmers);
                    break;
                case 4:
                    handler.printByProductQuantity(farmers);
                default:
                    System.out.println("Please enter one of the options");
                    break;
            }
        }
    }
}
