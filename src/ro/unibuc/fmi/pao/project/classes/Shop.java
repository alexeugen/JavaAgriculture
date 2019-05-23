package ro.unibuc.fmi.pao.project.classes;

import ro.unibuc.fmi.pao.project.abs.User;

import java.util.ArrayList;
import java.util.List;

public class Shop extends User {
    List<Farmer> farmers = new ArrayList<>();

    public Shop(String name) {
        super(name);
    }

    public void addFarmer(Farmer farmer) {
        farmers.add(farmer);
    }

    public void print() {
        System.out.println(this.getName());
    }

    public void printFarmers() {
        for(int i = 0; i < farmers.size(); i++) {
            farmers.get(i).print();
        }
    }
}
