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
import lk.ijse.dep.sms.business.custom.PaymentBO;
import lk.ijse.dep.sms.business.custom.StudentBO;
import lk.ijse.dep.sms.business.exception.AlreadyExistsInStudentException;
import lk.ijse.dep.sms.dto.PaymentDTO;
import lk.ijse.dep.sms.dto.StudentDTO;
import lk.ijse.dep.sms.entity.PaymentPK;
import lk.ijse.dep.sms.util.PaymentTM;
import lk.ijse.dep.sms.util.StudentTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagePaymentFormController {
    public AnchorPane root;
    public Label lblDate;
    public JFXTextField txtSearch;
    public JFXTextField txtPno;
    public JFXTextField txtBno;
    public JFXTextField txtBalance;
    public JFXTextField txtSid;
    public JFXTextField txtFee;
    public JFXTextField txtCash;
    public JFXButton btnNewPayment;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnReport;

    @FXML
    private TableView<PaymentTM> tblPayment;

    private PaymentBO paymentBO = BOFactory.getInstance().getBO(BOTypes.PAYMENT);

    public void initialize() {

        lblDate.setText(LocalDate.now() + "");

        tblPayment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("pno"));
        tblPayment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        tblPayment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblPayment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("totalFee"));

        txtPno.setDisable(true);
        txtBno.setDisable(true);
        txtSid.setDisable(true);
        txtFee.setDisable(true);
        txtCash.setDisable(true);
        txtBalance.setDisable(true);
        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        
        loadAllPayments();

        tblPayment.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PaymentTM>() {
            @Override
            public void changed(ObservableValue<? extends PaymentTM> observable, PaymentTM oldValue, PaymentTM newValue) {

                PaymentTM selectedItem = tblPayment.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    return;
                }

                btnSave.setDisable(true);
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                txtPno.setDisable(false);
                txtBno.setDisable(false);
                txtSid.setDisable(false);
                txtFee.setDisable(false);
                txtCash.setDisable(false);
                txtBalance.setDisable(false);
                txtPno.setText(selectedItem.getPno());
                txtBno.setText(selectedItem.getBatchNo());
                txtSid.setText(selectedItem.getStudentId());
                txtFee.setText(String.valueOf(selectedItem.getTotalFee()));
            }
        });
    }

    private void loadAllPayments() {

        try {
            System.out.println(paymentBO);
            List<PaymentDTO> allPayments = paymentBO.findAllPayments();
            ObservableList<PaymentTM> payments = tblPayment.getItems();
            //ObservableList<PaymentTM> payments = FXCollections.observableArrayList();
            tblPayment.getItems().clear();
            for (PaymentDTO payment : allPayments) {
                payments.add(new PaymentTM(payment.getPno(), payment.getBatchNo(), payment.getStudentId(),
                        payment.getTotalFee()));
            }
            System.out.println(allPayments);

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

    public void btnNewPayment_OnAction(ActionEvent actionEvent) {

        txtBno.clear();
        txtSid.clear();
        txtPno.clear();
        txtFee.clear();
        txtCash.clear();
        txtBalance.clear();
        tblPayment.getSelectionModel().clearSelection();
        txtBno.setDisable(false);
        txtSid.setDisable(false);
        txtPno.setDisable(false);
        txtFee.setDisable(false);
        txtCash.setDisable(false);
        txtBalance.setDisable(false);
        txtBno.requestFocus();
        btnSave.setDisable(false);

        // Generate a new pno

        int maxId = 0;

        try {
            String lastPaymentId = paymentBO.getLastPaymentId();

            if (lastPaymentId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastPaymentId.replace("P", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "P00" + maxId;
            } else if (maxId < 100) {
                id = "P0" + maxId;
            } else {
                id = "P" + maxId;
            }
            txtPno.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
            Logger.getLogger("lk.ijse.dep.sms.controller").log(Level.SEVERE, null,e);
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {

        if (btnSave.getText().equals("Save")) {
            ObservableList<PaymentTM> payments = tblPayment.getItems();
            PaymentDTO newPayment = new PaymentDTO(
                    txtPno.getText(),
                    txtBno.getText(),
                    txtSid.getText(),
                    Double.parseDouble(txtFee.getText())
            );
            try {
                paymentBO.savePayment(newPayment);
                payments.add(new PaymentTM(newPayment.getPno(), newPayment.getBatchNo(), newPayment.getStudentId(),
                        newPayment.getTotalFee()));
                btnNewPayment_OnAction(actionEvent);
                new Alert(Alert.AlertType.CONFIRMATION,"Payment saved successfully").show();
                loadAllPayments();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
                Logger.getLogger("lk.ijse.dep.sms.controller").log(Level.SEVERE, null,e);

            }
        } else {
            PaymentTM selectedPayment = tblPayment.getSelectionModel().getSelectedItem();
            try {
                paymentBO.updatePayment(new PaymentDTO(selectedPayment.getPno(),
                        txtBno.getText(),
                        txtSid.getText(),
                        Double.parseDouble(txtFee.getText())));
                selectedPayment.setPno(txtPno.getText());
                selectedPayment.setBatchNo(txtBno.getText());
                selectedPayment.setStudentId(txtSid.getText());
                selectedPayment.setTotalFee(Double.parseDouble(txtFee.getText()));
                tblPayment.refresh();
                btnNewPayment_OnAction(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
                Logger.getLogger("lk.ijse.dep.sms.controller").log(Level.SEVERE, null,e);
            }
        }
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) throws Exception {

        boolean b = paymentBO.updatePayment(new PaymentDTO(txtPno.getText(),
                txtBno.getText(),
                txtSid.getText(),
                Double.parseDouble(txtFee.getText())));

        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Payment updated successfully").show();
            loadAllPayments();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong, please contact DEPPO").show();
        }
        tblPayment.refresh();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this payment?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            PaymentTM selectedItem = tblPayment.getSelectionModel().getSelectedItem();
            try {
                paymentBO.deletePayment(new PaymentPK(selectedItem.getBatchNo()));
                tblPayment.getItems().remove(selectedItem);
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
                        getResourceAsStream("/lk/ijse/dep/sms/report/PaymentDetails.jrxml"));

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> params = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                params, new JRBeanCollectionDataSource(tblPayment.getItems()));

        JasperViewer.viewReport(jasperPrint,false);
    }
}
