package lk.ijse.dep.sms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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

public class ManageBatchFormController {
    public AnchorPane root;
    public JFXTextField txtBno;
    public JFXTextField txtCid;
    public JFXTextField txtFee;
    public JFXComboBox cmbStatus;
    public JFXDatePicker dtpSdate;
    public JFXDatePicker dtpCdate;
    public TableView tblBatch;
    public JFXTextField txtSearch;
    public Label lblDate;
    public JFXButton btnNewBatch;
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

    public void btnNewBatch_OnAction(ActionEvent actionEvent) {
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
