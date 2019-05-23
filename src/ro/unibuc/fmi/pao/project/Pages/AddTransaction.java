package ro.unibuc.fmi.pao.project.Pages;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ro.unibuc.fmi.pao.project.classes.Farmer;
import ro.unibuc.fmi.pao.project.classes.Storage;
import ro.unibuc.fmi.pao.project.classes.Transaction;

import java.sql.*;

public class AddTransaction {

    private static Storage storage;


    public static void display(String title) {
        try {
            storage = Storage.Storage();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);




        final ChoiceBox cbFarmers = new ChoiceBox(storage.oListFarmers);
        GridPane.setConstraints(cbFarmers, 0, 0);
        grid.getChildren().add(cbFarmers);
        final ChoiceBox cbClients = new ChoiceBox(storage.oListClients);
        GridPane.setConstraints(cbClients, 1, 0);
        grid.getChildren().add(cbClients);

        final TextField value = new TextField();
        value.setPromptText("Enter the value");
        GridPane.setConstraints(value, 0, 1);
        grid.getChildren().add(value);

        //Defining the Submit button
        Button submit = new Button("Submit");

        submit.setOnAction(e -> {
            Connection myConn = null;
            Statement myStmt = null;
            ResultSet myRs = null;
            try {
                myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root" , "123456");

                myStmt = myConn.createStatement();
                String farmer = cbFarmers.getSelectionModel().getSelectedItem().toString();
                String client = cbClients.getSelectionModel().getSelectedItem().toString();
                String valuei= value.getText();


                System.out.println(farmer + client + valuei);
                Transaction ion = new Transaction(farmer,client,valuei);
                storage.oListTransactions.add(ion);

                String query = "INSERT INTO transactions (`farmer`, `client`, `value`) VALUES ('"+farmer+"','"+ client +"','"+ valuei+"');";
                //String query = "INSERT INTO farmers (`name`, `city`) VALUES ('gina', 'Moldova');";
                System.out.println(query);
                int count = myStmt.executeUpdate(query);
                window.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        });


        GridPane.setConstraints(submit, 0, 2);
        grid.getChildren().add(submit);


        Scene scene = new Scene(grid, 400, 350);
        window.setScene(scene);
        window.showAndWait();
    }
}
