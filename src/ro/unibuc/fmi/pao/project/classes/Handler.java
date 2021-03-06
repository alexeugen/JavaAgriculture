package ro.unibuc.fmi.pao.project.classes;

import ro.unibuc.fmi.pao.project.abs.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Handler {
    private Data data = Data.Data();

    public void printFarmers(List<Farmer> farmers) {
        for (int i=0; i<farmers.size(); i++) {
            farmers.get(i).print();
            System.out.println("-----------------------------------------------------------");
        }
    }

    public void printShops(List<Shop> shops) {
        for (int i=0; i<shops.size(); i++) {
            shops.get(i).print();
            System.out.println("-----------------------------------------------------------");
        }
    }

    public void printByCity(List<Farmer> farmers) {
        System.out.println("What city?");
        Scanner sc = new Scanner(System.in);
        String city = sc.next();

        for(int i = 0; i < farmers.size(); i++) {
            if(farmers.get(i).getCity().equals(city)) {
                farmers.get(i).print();
                System.out.println("-----------------------------------------------------------");
            }
        }

    }

    public void printOptions() {
        System.out.println("Ce doresti draga?");
        for(int i = 0; i <= 13; i++) {
            System.out.println(data.options.get(i));
        }

    }


    public void printByProduct(List<Farmer> farmers) {
        System.out.println("What product?");
        Scanner sc = new Scanner(System.in);
        String product = sc.next();

        for(int i = 0; i < farmers.size(); i++) {
            if(farmers.get(i).hasProduct(product)) {
                farmers.get(i).print();
                System.out.println("-----------------------------------------------------------");
            }
        }
    }

    public void printByProductQuantity(List<Farmer> farmers) {
        System.out.println("What product?");
        Scanner sc = new Scanner(System.in);
        String product = sc.next();
        System.out.println("What quantity?");
        int quantity = sc.nextInt();
        for(int i = 0; i < farmers.size(); i++) {
            if(farmers.get(i).hasProductQuantity(product, quantity)) {
                farmers.get(i).print();
                System.out.println("-----------------------------------------------------------");
            }
        }
    }

    public void addFarmerToShop(List<Shop> shops, List<Farmer> farmers) {
        System.out.println("What shop?");
        Scanner sc = new Scanner(System.in);
        String shop = sc.next();
        System.out.println("What farmer?");
        String farmer= sc.next();

        Shop theShop = null;
        Farmer theFarmer = null;

        for(int i = 0; i < shops.size(); i++)
            if(shops.get(i).getName().equals(shop)) {
                theShop = shops.get(i);
                break;
            }

        for(int i = 0; i < farmers.size(); i++)
            if(farmers.get(i).getName().equals(farmer)) {
                theFarmer = farmers.get(i);
                break;
            }

        if(theShop != null && theFarmer != null) {
            theShop.addFarmer(theFarmer);
        }
        else {
            System.out.println("Farmer or shop not found");
        }
    }

    public void printShopFarmers(List<Shop> shops) {
        System.out.println("What shop?");
        Scanner sc = new Scanner(System.in);
        String shop = sc.next();
        Shop theShop = null;
        Farmer theFarmer = null;

        for(int i = 0; i < shops.size(); i++)
            if(shops.get(i).getName().equals(shop)) {
                theShop = shops.get(i);
                break;
            }

        theShop.printFarmers();
    }

    public void makeTransaction(List<Client> clients, List<Farmer> farmers, Set<Transaction> transactions) throws IOException {
        System.out.println("What client?");
        Scanner sc = new Scanner(System.in);
        String client = sc.next();
        System.out.println("What farmer?");
        String farmer= sc.next();
        System.out.println("What product?");
        String productName= sc.next();
        System.out.println("What quantity?");
        String productQuantity= sc.next();
        System.out.println("What measure?");
        String productMeasure= sc.next();

        Client theClient = null;
        Farmer theFarmer = null;
        Product theProduct = new Product(productName, productMeasure, productQuantity);

        for(int i = 0; i < clients.size(); i++)
            if(clients.get(i).getName().equals(client)) {
                theClient = clients.get(i);
                break;
            }

        for(int i = 0; i < farmers.size(); i++)
            if(farmers.get(i).getName().equals(farmer)) {
                theFarmer = farmers.get(i);
                break;
            }
        transactions.add(new Transaction(theFarmer, theClient, theProduct));
        data.addTransaction(client, farmer, productName, productQuantity, productMeasure);

    }

    public void printClients(List<Client> clients) {
        for (int i = 0; i < clients.size(); i++)
            System.out.println(clients.get(i).getName());
    }

    public void printTransactions(Set<Transaction> transactions) {
        Iterator<Transaction> iterator = transactions.iterator();
        while (iterator.hasNext())
            iterator.next().print();
    }
}
