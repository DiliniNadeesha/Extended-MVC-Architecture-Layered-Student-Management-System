package lk.ijse.dep.sms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.sms.business.BOFactory;
import lk.ijse.dep.sms.business.BOTypes;
import lk.ijse.dep.sms.business.custom.LoginBO;
import lk.ijse.dep.sms.dao.DAOFactory;
import lk.ijse.dep.sms.dao.DAOTypes;
import lk.ijse.dep.sms.dao.custom.LoginDAO;
import lk.ijse.dep.sms.db.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public AnchorPane root;
    public JFXComboBox<String> cmbUserType;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;

    private LoginBO loginBO = BOFactory.getInstance().getBO(BOTypes.LOGIN);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> userTypes = cmbUserType.getItems();
        try {
            List<String> userTypes1 = loginBO.getUserTypes();
            ObservableList<String> utypes = FXCollections.observableArrayList(userTypes1);
            cmbUserType.setItems(utypes);
            System.out.println(utypes);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public void btnLogin_OnAction(ActionEvent actionEvent) throws IOException {

        String selectedUserType = cmbUserType.getSelectionModel().getSelectedItem();
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            boolean result = loginBO.isUser(selectedUserType, username, password);
            if(result){
                URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/MainForm.fxml");
                Parent root = FXMLLoader.load(resource);
                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) (this.root.getScene().getWindow());
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();

            }else{
                new  Alert(Alert.AlertType.ERROR,"Please Check your login credentials", ButtonType.OK).show();
                cmbUserType.getSelectionModel().clearSelection();
                txtUserName.clear();
                txtPassword.clear();
                cmbUserType.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
