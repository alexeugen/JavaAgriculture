package ro.unibuc.fmi.pao.project.Pages;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;

public class AddFarmer {




    public static void display(String title) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        final TextField name = new TextField();
        name.setPromptText("Enter your first name.");
        GridPane.setConstraints(name, 0, 0);
        grid.getChildren().add(name);



        final TextField city = new TextField();
        city.setPromptText("Enter your city.");
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
                //String query = "INSERT INTO farmers VALUES ('"+name.getText()+"','"+ city.getText() +"');";
                String query = "INSERT INTO farmers (`name`, `city`) VALUES ('gina', 'Moldova');";
                System.out.println(query);
                int count = myStmt.executeUpdate(query);
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
