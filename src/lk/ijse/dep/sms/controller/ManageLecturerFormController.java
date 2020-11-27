package lk.ijse.dep.sms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ManageLecturerFormController {
    public AnchorPane root;
    public Label lblDate;
    public TableView tblLecturer;
    public JFXTextField txtLecId;
    public JFXTextField txtSearch;
    public JFXTextField txtName;
    public JFXTextField txtContactNo;
    public JFXTextField txtEmail;
    public JFXButton btnNewLecturer;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnReport;

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {

        URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnNewLecturer_OnAction(ActionEvent actionEvent) {
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

    public void btnReport_OnAction(ActionEvent actionEvent) {
    }
}
