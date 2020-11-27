package lk.ijse.dep.sms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.ijse.dep.sms.db.DBConnection;

import java.net.URL;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppInitializer extends Application {

    public static void main(String[] args) {

        launch(args);
//        try {
//            System.out.println("Shutting down the connection");
//            DBConnection.getInstance().getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
      //  }
    }

    @Override
    public void start(Stage primaryStage)  {
        try {

            // Let's setup the root logger

            Logger rootLogger = Logger.getLogger("");
            FileHandler fileHandler = new FileHandler("error.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);

           // DBConnection.getInstance().getConnection();
            URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/LoginForm.fxml");
            Parent root = FXMLLoader.load(resource);
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("JDBC SMS - 2019");
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.show();

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
            Logger.getLogger("lk.ijse.dep.sms").log(Level.SEVERE, null,e);
        }
    }
    }

