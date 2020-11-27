package lk.ijse.dep.sms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.sms.business.BOFactory;
import lk.ijse.dep.sms.business.BOTypes;
import lk.ijse.dep.sms.business.custom.StudentBO;
import lk.ijse.dep.sms.business.exception.AlreadyExistsInStudentException;
import lk.ijse.dep.sms.dto.StudentDTO;
import lk.ijse.dep.sms.util.StudentTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageStudentFormController {

    public AnchorPane root;
    public Label lblDate;
    public JFXTextField txtSearch;
    public JFXTextField txtSid;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtEmail;
    public JFXButton btnNewStudent;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnReport;
    public TableView<StudentTM> tblStudent;


    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void initialize(){

        lblDate.setText(LocalDate.now() + "");

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("sid"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("sname"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("email"));

        txtSid.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        txtEmail.setDisable(true);
        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        loadAllStudents();

        tblStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentTM>() {
            @Override
            public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM newValue) {

                StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    return;
                }

                btnSave.setDisable(true);
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtContactNo.setDisable(false);
                txtEmail.setDisable(false);
                txtSid.setText(selectedItem.getSid());
                txtName.setText(selectedItem.getSname());
                txtAddress.setText(selectedItem.getAddress());
                txtContactNo.setText(selectedItem.getContactNo());
                txtEmail.setText(selectedItem.getEmail());

            }

        });

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                try {
                    System.out.println("ok");
                    loadTable();
                }catch (Exception e){
                    new Alert(Alert.AlertType.INFORMATION,"Something went wrong..Contact DEPPO").show();
                    Logger.getLogger("lk.ijse.dep.pos").log(Level.SEVERE,null,e);
                }
            }
        });
    }

    public void loadTable() throws Exception{

        tblStudent.getItems().clear();

        List<StudentDTO> stdentInfo = studentBO.getStudentInfo('%'+txtSearch.getText()+'%');

        ObservableList<StudentTM> items = FXCollections.observableArrayList();

        for (StudentDTO student : stdentInfo) {

            items.add(new StudentTM(student.getSid()+"", student.getSname()+"", student.getAddress()+"",
                    student.getContactNo()+"", student.getEmail()+""));
        }
        System.out.println(items);
        tblStudent.setItems(items);
    }

    public void loadAllStudents(){
        try {
            List<StudentDTO> allStudents = studentBO.findAllStudents();
            ObservableList<StudentTM> students = tblStudent.getItems();
            tblStudent.getItems().clear();
            for (StudentDTO student : allStudents) {
                students.add(new StudentTM(student.getSid(), student.getSname(), student.getAddress(),
                        student.getContactNo(), student.getEmail()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
            Logger.getLogger("lk.ijse.dep.sms.controller").log(Level.SEVERE, null,e);
        }
    }

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {

        URL resource = this.getClass().getResource("/lk/ijse/dep/sms/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnNewStudent_OnAction(ActionEvent actionEvent) {

        txtSid.clear();
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtEmail.clear();
        tblStudent.getSelectionModel().clearSelection();
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        txtEmail.setDisable(false);
        txtName.requestFocus();
        btnSave.setDisable(false);

        // Generate a new sid

        int maxId = 0;

        try {
            String lastStudentId = studentBO.getLastStudentId();

            if (lastStudentId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastStudentId.replace("S", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "S00" + maxId;
            } else if (maxId < 100) {
                id = "S0" + maxId;
            } else {
                id = "S" + maxId;
            }
            txtSid.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
            Logger.getLogger("lk.ijse.dep.sms.controller").log(Level.SEVERE, null,e);
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {

        if (!txtName.getText().matches("[A-Za-z][A-Za-z. ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
            return;
        }

        if (!txtAddress.getText().matches("^\\w[A-Za-z,.-/0-9\\\\]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address").show();
            return;
        }

        if (!txtContactNo.getText().matches("(^\\d{3}-\\d{7})|(^[+]\\d{4}-\\d{7})")) {
            new Alert(Alert.AlertType.ERROR, "Invalid ContactNo").show();
            return;
        }

        if (!txtEmail.getText().matches("^\\w[A-Za-z,.-@/0-9\\\\]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            return;
        }

        if (btnSave.getText().equals("Save")) {
            ObservableList<StudentTM> students = tblStudent.getItems();
            StudentDTO newStudent = new StudentDTO(
                    txtSid.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtContactNo.getText(),
                    txtEmail.getText()
            );
            try {
                studentBO.saveStudent(newStudent);
                students.add(new StudentTM(newStudent.getSid(), newStudent.getSname(), newStudent.getAddress(),
                        newStudent.getContactNo(), newStudent.getEmail()));
                btnNewStudent_OnAction(actionEvent);
                new Alert(Alert.AlertType.CONFIRMATION,"Student saved successfully").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
                Logger.getLogger("lk.ijse.dep.sms.controller").log(Level.SEVERE, null,e);
            }
        } else {
            StudentTM selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
            try {
                studentBO.updateStudent(new StudentDTO(selectedStudent.getSid(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtContactNo.getText(),
                        txtEmail.getText()));
                selectedStudent.setSname(txtName.getText());
                selectedStudent.setAddress(txtAddress.getText());
                selectedStudent.setContactNo(txtContactNo.getText());
                selectedStudent.setEmail(txtEmail.getText());
                tblStudent.refresh();
                btnNewStudent_OnAction(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
                Logger.getLogger("lk.ijse.dep.sms.controller").log(Level.SEVERE, null,e);
            }
        }
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) throws Exception {

        boolean b = studentBO.updateStudent(new StudentDTO(txtSid.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContactNo.getText(),
                txtEmail.getText()));

        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Student updated successfully").show();
            loadAllStudents();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
        }
        tblStudent.refresh();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this student?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
            try {
                studentBO.deleteStudent(selectedItem.getSid());
                tblStudent.getItems().remove(selectedItem);
            }catch (AlreadyExistsInStudentException e){
                new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
                Logger.getLogger("lk.ijse.dep.sms.controller").log(Level.SEVERE, null,e);
            }
        }
    }

    public void btnReport_OnAction(ActionEvent actionEvent) throws JRException {

        JasperDesign jasperDesign = JRXmlLoader.
                load(ManageStudentFormController.class.
                        getResourceAsStream("/lk/ijse/dep/sms/report/StudentDetails.jrxml"));

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> params = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                params, new JRBeanCollectionDataSource(tblStudent.getItems()));

        JasperViewer.viewReport(jasperPrint,false);
    }
}
