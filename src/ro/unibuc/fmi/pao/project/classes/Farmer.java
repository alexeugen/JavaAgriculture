package ro.unibuc.fmi.pao.project.classes;
import ro.unibuc.fmi.pao.project.abs.User;

import java.util.ArrayList;
import java.util.List;

public class Farmer extends User {
    private Location location;
    private List<Product> products = new ArrayList<>();

    public Farmer(String name) {
        super(name);
    }

    public Farmer(String name, String city) {
        super(name);
        location = new Location(city);
    }

    public void addProduct(String name, String measure, String quantity) {
        products.add(new Product(name, measure, quantity));
    }

    public void print() {
        System.out.println(this.getName() + ": un simplu fermier din judetul " + this.getCity());
        for (int i=0; i<products.size(); i++) {
            products.get(i).print();
        }
    }

    public String getCity() {
        return this.location.getCity();
    }

    public boolean hasProduct(String product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(product))
                return true;
        }
        return false;
    }

    public boolean hasProductQuantity(String product, int quantity) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(product) && Integer.parseInt(products.get(i).getQuantity()) > quantity)
                return true;
        }
        return false;
    }

    public int getProduction() {
        int s = 0;
        for (int i = 0; i < products.size(); i++) {
           s+=Integer.parseInt(products.get(i).getQuantity());

        }
        return s;
    }
}
