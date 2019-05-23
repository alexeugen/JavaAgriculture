package ro.unibuc.fmi.pao.project.components;


import  javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import ro.unibuc.fmi.pao.project.Pages.AddFarmer;
import ro.unibuc.fmi.pao.project.Pages.AddTransaction;

public class AgriMenuBar extends MenuBar {
    public Menu menuFarmers;
    public Menu menuShops;
    public Menu menuTransactions;
    public Menu menuClients;

    public MenuItem listFarmers;
    public MenuItem listShops;
    public MenuItem listClients;
    public MenuItem listTransactions;

    public MenuItem addFarmer;
    public MenuItem addTransaction;

    public AgriMenuBar() {
        menuFarmers = new javafx.scene.control.Menu("Farmers");
        menuShops = new javafx.scene.control.Menu("Shops");
        menuTransactions = new javafx.scene.control.Menu("Transactions");
        menuClients= new javafx.scene.control.Menu("Clients");

        listFarmers = new MenuItem("List Farmers");
        menuFarmers.getItems().addAll(listFarmers);

        listTransactions= new MenuItem("List Transactions");
        menuTransactions.getItems().addAll(listTransactions);

        addFarmer = new MenuItem("Add Farmer");
        addFarmer.setOnAction(e -> AddFarmer.display("Add farmer"));
        menuFarmers.getItems().addAll(addFarmer);

        addTransaction = new MenuItem("Add Transaction");
        addTransaction.setOnAction(e -> AddTransaction.display("Add transaction"));
        menuTransactions.getItems().addAll(addTransaction);


        listShops= new MenuItem("List Shops");
        menuShops.getItems().addAll(listShops);

        listClients= new MenuItem("List Clients");
        menuClients.getItems().addAll(listClients);

        this.getMenus().addAll(menuFarmers, menuShops, menuTransactions, menuClients);
    }
}
