import ro.unibuc.fmi.pao.project.classes.*;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Handler handler = new Handler();

        Data data = Data.Data();

        int option = 1;

        while(option != 0) {
            handler.printOptions();
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            data.logOption(option);
            switch (option) {
                case 1:
                    handler.printFarmers(data.farmers);
                    break;
                case 2:
                    handler.printByCity(data.farmers);
                    break;
                case 3:
                    handler.printByProduct(data.farmers);
                    break;
                case 4:
                    handler.printByProductQuantity(data.farmers);
                case 5:
                    handler.printShops(data.shops);
                    break;
                case 6:
                    handler.addFarmerToShop(data.shops, data.farmers);
                    break;
                case 7:
                    handler.printShopFarmers(data.shops);
                    break;
                case 8:
                    handler.makeTransaction(data.clients, data.farmers, data.transactions);
                    break;
                case 9:
                    handler.printTransactions(data.transactions);
                    break;
                case 10:
                    handler.printClients(data.clients);
                    break;
                case 11:
                    data.addFarmer();
                    break;
                case 12:
                    data.addShop();
                    break;
                case 13:
                    data.addClient();
                    break;
                default:
                    System.out.println("Please enter one of the options");
                    break;

            }
        }
    }
}
