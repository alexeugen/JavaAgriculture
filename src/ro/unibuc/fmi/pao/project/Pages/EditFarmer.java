package ro.unibuc.fmi.pao.project.Pages;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ro.unibuc.fmi.pao.project.classes.Farmer;
import ro.unibuc.fmi.pao.project.classes.Storage;

import java.sql.*;

public class EditFarmer {

    private static Storage storage;


    public static void display(Farmer ion) {
        try {
            storage = Storage.Storage();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        final TextField name = new TextField();
        name.setPromptText("Enter your first name.");
        name.setText(ion.getName());
        GridPane.setConstraints(name, 0, 0);
        grid.getChildren().add(name);



        final TextField city = new TextField();
        city.setPromptText("Enter your city.");
        city.setText(ion.getCity());
        GridPane.setConstraints(city, 0, 1);
        grid.getChildren().add(city);

        //Defining the Submit button
        Button submit = new Button("Submit");

        submit.setOnAction(e -> {
            Connection myConn = null;
            Statement myStmt = null;
            ResultSet myRs = null;
            try {
                myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root" , "123456");

                myStmt = myConn.createStatement();


                String query = "UPDATE farmers SET name='"+name.getText()+"', city='"+city.getText()+"' WHERE id='"+ion.getId()+"'";
                ion.setName(name.getText());
                ion.setCity(city.getText());
                storage.oListFarmers.set(storage.oListFarmers.indexOf(ion), ion);
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
