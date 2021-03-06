import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import ro.unibuc.fmi.pao.project.Pages.EditFarmer;
import ro.unibuc.fmi.pao.project.classes.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Stage;
import ro.unibuc.fmi.pao.project.components.AgriMenuBar;

import java.io.IOException;
import java.util.*;
import java.sql.*;


public class Main extends Application {
    private static Storage storage;
    BorderPane layout;
    AgriMenuBar menuBar;

    @Override
    public void start(Stage stage) {
        stage.setTitle("AgriHug");

        menuBar = new AgriMenuBar();
        layout = new BorderPane();



        layout.setTop(menuBar);

        menuBar.listFarmers.setOnAction(e -> {
            layout.setCenter(storage.listViewFarmers);
            layout.setBottom(storage.btnDeleteFarmers);
        });
        menuBar.listClients.setOnAction(e -> {
            layout.setCenter(storage.listViewClients);
            layout.setBottom(storage.btnDeleteClients);
        });
        menuBar.listShops.setOnAction(e -> {
            layout.setCenter(storage.listViewShops);
            layout.setBottom(storage.btnDeleteShops);
        });
        menuBar.listTransactions.setOnAction(e -> {
            layout.setCenter(storage.listViewTransactions);
//            layout.setBottom(storage.btnDeleteShops);
        });

        storage.listViewFarmers.setOnMouseClicked( e -> {
            if(e.getClickCount() == 2) {
                EditFarmer.display((Farmer)storage.listViewFarmers.getSelectionModel().getSelectedItem());
            }
        });

        Scene scene = new Scene(layout, 400, 350);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, SQLException {
        storage = Storage.Storage();

        Handler handler = new Handler();
        handler.printFarmers(storage.farmers);
        launch(args);

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
