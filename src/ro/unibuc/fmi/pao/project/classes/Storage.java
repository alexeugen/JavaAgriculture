package ro.unibuc.fmi.pao.project.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.sql.*;

public class Storage {
    private static Storage single_instance = null;

    Connection myConn = null;



    public List<String> options = new ArrayList<>();
    public List<Farmer> farmers = new ArrayList<>();
    public List<Shop> shops = new ArrayList<>();
    public List<Client> clients = new ArrayList<>();
    public Set<Transaction> transactions = new TreeSet<>(new Comparator<Transaction>() {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.getQuantity()-o2.getQuantity();
        }
    });

    private ObservableList oListFarmers, oListClients, oListShops;
    public ListView listViewFarmers, listViewClients, listViewShops;

    private Storage() throws SQLException {
        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root" , "123456");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

        this.initializeOptions();
        this.initializeFarmers(farmers);
        this.initializeShops(shops);
        this.initializeClients(clients);
        this.initializeTransactions(transactions);
        this.initializeComponents();
    }

    private void initializeComponents() {
        oListFarmers = FXCollections.observableArrayList(farmers);
        listViewFarmers = new ListView(oListFarmers);
        listViewFarmers.setPrefSize(200, 250);
        listViewFarmers.setEditable(true);

        oListClients = FXCollections.observableArrayList(clients);
        listViewClients = new ListView(oListClients);
        listViewClients.setPrefSize(200, 250);
        listViewClients.setEditable(true);

        oListShops = FXCollections.observableArrayList(shops);
        listViewShops= new ListView(oListShops);
        listViewShops.setPrefSize(200, 250);
        listViewShops.setEditable(true);
    }

    public static Storage Storage() throws SQLException {
        if (single_instance == null) {
            single_instance = new Storage();
        }
        return single_instance;
    }

    private void initializeClients(List<Client> clients) throws SQLException {
        // 3. Execute SQL query
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from clients");

        while (myRs.next()) {
                String name = myRs.getString("name");
                Client ion = new Client(name);
                clients.add(ion);
        }
    }

    private  void initializeOptions() {
        options.add("0.Exit");
        options.add("1.Print all Farmers");
        options.add("2.Print all Farmers from ...");
        options.add("3.Print all Farmers that have ...");
        options.add("4.Print all Farmers that have a specific quantity of a product");
        options.add("5.Print all Shops");
        options.add("6.Add farmer to a shop");
        options.add("7.Print all farmers from a shop");
        options.add("8.Make Transaction");
        options.add("9.Print Transactions");
        options.add("10.Print Clients");
        options.add("11.Add farmer");
        options.add("12.Add shop");
        options.add("13.Add client");
    }

    private void initializeShops(List<Shop> shops) throws SQLException {
        // 3. Execute SQL query
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from shops");

        while (myRs.next()) {
            String name = myRs.getString("name");
            Shop ion = new Shop(name);
            shops.add(ion);
        }
    }

    private void initializeFarmers(List<Farmer> farmers) throws SQLException {
        // 3. Execute SQL query
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from farmers");

        // 4. Process the result set
        while (myRs.next()) {

                String name = myRs.getString("name");
                String city = myRs.getString("city");
                String id = myRs.getString("id");
                Farmer ion = new Farmer(name, city);
            Statement myStmtP = myConn.createStatement();
            ResultSet myRsP = myStmtP.executeQuery("select * from products where farmer_id = " + id);
                while (myRsP.next()) {
                    String quantity = myRsP.getString("quantity");
                    String measure = myRsP.getString("unit");
                    String namei = myRsP.getString("name");
                    ion.addProduct(namei, measure, quantity);
                }
                farmers.add(ion);
            }
        }


    private void initializeTransactions(Set<Transaction> transactions) {
        Scanner sc = null;
        String workingDir = System.getProperty("user.dir");
        try {
            sc = new Scanner(new File(workingDir + "/src/ro/unibuc/fmi/pao/project/files/transactions.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            List<String> line = getRecordFromLine(sc.nextLine());
            ListIterator<String> iter = line.listIterator();
            while (iter.hasNext()) {

                String client = iter.next();
                String farmer = iter.next();
                String productName = iter.next();
                String productQuantity = iter.next();
                String productMeasure = iter.next();

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
            }
        }

    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public void addFarmer() throws IOException {
        Scanner sc = new Scanner(System.in);

        String lineString = sc.nextLine();
        List<String> line = getRecordFromLine(lineString);
        ListIterator<String> iter = line.listIterator();

        while (iter.hasNext()) {
            String name = iter.next();
            String city = iter.next();
            Farmer ion = new Farmer(name, city);

            while (iter.hasNext()) {
                String quantity = iter.next();
                String measure = iter.next();
                String namei = iter.next();
                ion.addProduct(namei, measure, quantity);
            }
            farmers.add(ion);
        }


        String workingDir = System.getProperty("user.dir");
        FileWriter pw = new FileWriter(workingDir + "/src/ro/unibuc/fmi/pao/project/files/farmers.csv", true);


        pw.append(lineString);
        pw.append("\n");

        pw.flush();
        pw.close();
    }

    public void addShop() throws IOException {
        Scanner sc = new Scanner(System.in);

        String lineString = sc.nextLine();

        Shop ion = new Shop(lineString);
        shops.add(ion);



        String workingDir = System.getProperty("user.dir");
        FileWriter pw = new FileWriter(workingDir + "/src/ro/unibuc/fmi/pao/project/files/shops.csv", true);


        pw.append(lineString);
        pw.append("\n");

        pw.flush();
        pw.close();
    }

    public void addClient() throws IOException {
        Scanner sc = new Scanner(System.in);

        String lineString = sc.nextLine();

        Client ion = new Client(lineString);
        clients.add(ion);



        String workingDir = System.getProperty("user.dir");
        FileWriter pw = new FileWriter(workingDir + "/src/ro/unibuc/fmi/pao/project/files/clients.csv", true);


        pw.append(lineString);
        pw.append("\n");

        pw.flush();
        pw.close();
    }

    public void addTransaction(String client, String farmer, String productName, String productQuantity, String productMeasure) throws IOException {

        String lineString = client + "," + farmer + "," + productName + "," + productQuantity + "," + productMeasure;

        String workingDir = System.getProperty("user.dir");
        FileWriter pw = new FileWriter(workingDir + "/src/ro/unibuc/fmi/pao/project/files/transactions.csv", true);


        pw.append(lineString);
        pw.append("\n");

        pw.flush();
        pw.close();
    }


    public void logOption(int i) throws IOException {
        String workingDir = System.getProperty("user.dir");
        FileWriter pw = new FileWriter(workingDir + "/src/ro/unibuc/fmi/pao/project/files/logs.csv", true);


        pw.append(options.get(i));
        pw.append("\n");

        pw.flush();
        pw.close();
    }

}
