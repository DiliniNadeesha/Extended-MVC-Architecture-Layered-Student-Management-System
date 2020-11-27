package lk.ijse.dep.sms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController {
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgStudent;
    public ImageView imgCourse;
    public ImageView imgBatch;
    public ImageView imgPayment;
    public ImageView imgSubject;
    public ImageView imgLecturer;
    public ImageView imgExam;
    public ProgressIndicator pgb;
    public JFXButton btnRestore;
    public JFXButton btnBackup;
    public AnchorPane root;


    // Initializes the lk.ijse.dep.sms.controller class.

    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        pgb.setVisible(false);
    }

    public void btnRestore_OnAction(ActionEvent actionEvent) {
    }

    public void btnBackup_OnAction(ActionEvent actionEvent) {
    }

    public void navigate(MouseEvent mouseEvent) throws IOException {

        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "imgStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/sms/view/ManageStudentForm.fxml"));
                    break;
                case "imgCourse":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/sms/view/ManageCourseForm.fxml"));
                    break;
                case "imgBatch":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/sms/view/ManageBatchForm.fxml"));
                    break;
                case "imgPayment":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/dep/sms/view/ManagePaymentForm.fxml"));
                    root = fxmlLoader.load();
                    break;
                case "imgSubject":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/dep/sms/view/ManageSubjectForm.fxml"));
                    root = fxmlLoader.load();
                    break;
                case "imgLecturer":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/dep/sms/view/ManageLecturerForm.fxml"));
                    root = fxmlLoader.load();
                    break;
                case "imgExam":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/dep/sms/view/ManageExamForm.fxml"));
                    root = fxmlLoader.load();
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();

                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "imgStudent":
                    lblMenu.setText("Manage Students");
                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.sms.view students");
                    break;
                case "imgCourse":
                    lblMenu.setText("Manage Courses");
                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.sms.view courses");
                    break;
                case "imgBatch":
                    lblMenu.setText("Manage Batch");
                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.sms.view batches");
                    break;
                case "imgPayment":
                    lblMenu.setText("Manage Payments");
                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.sms.view payments");
                    break;
                case "imgSubject":
                    lblMenu.setText("Manage Subjects");
                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.sms.view subjects");
                    break;
                case "imgLecturer":
                    lblMenu.setText("Manage Lecturers");
                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.sms.view lecturers");
                    break;
                case "imgExam":
                    lblMenu.setText("Manage Exams");
                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.dep.sms.view exms");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }
}
