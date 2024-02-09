package lk.ijse.carrental.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carrental.dto.BookingDto;
import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.service.custom.BookingService;
import lk.ijse.carrental.service.custom.ServiceFactory;
import lk.ijse.carrental.service.custom.ServiceType;

import java.io.IOException;
import java.sql.Date;

public class ReturningFormController {

    @FXML
    private TableColumn<?, ?> colBookingId;

    @FXML
    private TableColumn<?, ?> colDamadgeCost;

    @FXML
    private TableColumn<?, ?> colFinalAmount;

    @FXML
    private TableColumn<?, ?> colOverDueAmount;

    @FXML
    private TableColumn<?, ?> colOverDueDays;

    @FXML
    private TableColumn<?, ?> colReturnId;

    @FXML
    private TableColumn<?, ?> colReturnedDate;

    @FXML
    private DatePicker dateReturned;

    @FXML
    private AnchorPane returnNode;

    @FXML
    private TableView<?> tblReturn;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtBookDate;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtCarId;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtDamageCost;

    @FXML
    private TextField txtDueAmount;

    @FXML
    private TextField txtFinalAmount;

    @FXML
    private TextField txtOverdueDays;

    @FXML
    private TextField txtReturnDate;

    @FXML
    private TextField txtReturnId;

    private BookingService bookingService = ServiceFactory.getService(ServiceType.BOOKING);

    @FXML
    void btnSaveReturnOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchBookingOnAction(ActionEvent event) {
       try {
            String id = txtBookId.getText();
            BookingDto bookingDto = bookingService.search(id);
            if(bookingDto != null){
                txtCustId.setText(bookingDto.getCustId());
                txtCarId.setText(bookingDto.getCarId());
                txtBookDate.setText(String.valueOf(bookingDto.getBookDate()));
                txtReturnDate.setText(String.valueOf(bookingDto.getReturnDate()));
                txtBalance.setText(Double.toString(bookingDto.getBalance()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void tblSearchOnAction(MouseEvent event) {

    }

    @FXML
    void btnHomePageOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/homepage_form.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) this.returnNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home Page");
    }

}
