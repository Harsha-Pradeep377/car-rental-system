package lk.ijse.carrental.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomepageFormController {

    public AnchorPane rootNode;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    public void initialize() {
        showTime();
        showDate();
    }

    private void showTime() {
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
                String fTime = currentDateTime.format(tf);
                lblTime.setText(fTime);
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void showDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fDate = df.format(date);
        lblDate.setText(fDate);
    }

    @FXML
    void btnCustomerManageOnAction(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml"));
       Scene scene = new Scene(root);

       Stage primaryStage = (Stage) this.rootNode.getScene().getWindow();
       primaryStage.setScene(scene);
       primaryStage.centerOnScreen();
       primaryStage.setTitle("Customer Form");


    }

    @FXML
    void btnCategoryManageOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/category_form.fxml"));
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Category Form");
    }

    @FXML
    void btnCarManageOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/car_form.fxml"));
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Car Form");
    }

    @FXML
    void btnBookingManageOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/booking_form.fxml"));
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Booking Form");
    }
    @FXML
    void btnReturnManageOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/returning_form.fxml"));
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Returning Form");
    }


    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) this.rootNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Login Form");
    }


}
