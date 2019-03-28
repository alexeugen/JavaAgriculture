package ro.unibuc.fmi.pao.project.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Handler {
    public void initialize(List<Farmer> farmers) {
        Scanner sc = null;
        String workingDir = System.getProperty("user.dir");
        try {
            sc = new Scanner(new File(workingDir + "/src/ro/unibuc/fmi/pao/project/files/farmers.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            Scanner s2 = new Scanner(sc.nextLine());

            while (s2.hasNext()) {

                String name = s2.next();
                String city = s2.next();
                Farmer ion = new Farmer(name, city);

                while (s2.hasNext()) {
                    String quantity = s2.next();
                    String measure = s2.next();
                    String namei = s2.next();
                    ion.addProduct(namei, measure, quantity);
                }
                farmers.add(ion);
            }
        }
    }

    public void initializeFarmers() {

    }

    public void print(List<Farmer> farmers) {
        for (int i=0; i<farmers.size(); i++) {
            farmers.get(i).print();
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
        System.out.println("1.Print all Farmers");
        System.out.println("2.Print all Farmers from ...");
        System.out.println("3.Print all Farmers that have ...");
        System.out.println("4.Print all Farmers that have a specific quantity of a product");
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
}
