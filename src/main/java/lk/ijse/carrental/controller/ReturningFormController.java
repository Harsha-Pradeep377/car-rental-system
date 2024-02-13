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
import lk.ijse.carrental.dto.ReturnDto;
import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.service.custom.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    private ReturnService returnService = ServiceFactory.getService(ServiceType.RETURN);
    private CarService carService = ServiceFactory.getService(ServiceType.CAR);

    @FXML
    void btnSaveReturnOnAction(ActionEvent event) {
        String id = txtReturnId.getText();
        String bookId = txtBookId.getText();
        LocalDate returnedDate = dateReturned.getValue();
        Integer dueDays = Integer.parseInt(txtOverdueDays.getText());
        Double overDueAmount = Double.parseDouble(txtDueAmount.getText());
        Double damageCost = Double.parseDouble(txtDamageCost.getText());
        Double finalAmount = Double.parseDouble((txtFinalAmount.getText()));

        var returnDto = new ReturnDto(id, bookId, returnedDate,dueDays, overDueAmount, damageCost, finalAmount);
        BookingDto bookingDto = bookingService.search(returnDto.getBookedId());
        try {
            returnService.saveBooking(returnDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Return details Saved!").show();
            updateIsAvailability(bookingDto.getCarId(), true);
            clearFields();
            //initialize();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void clearFields() {

    }

    private void updateIsAvailability(String carId, boolean availability) {
        carService.updateIsAvailability(carId,availability);
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
    void calculateDuedaysOnAction(ActionEvent event) {
        LocalDate dateToBeReturned = LocalDate.parse(txtReturnDate.getText());
        LocalDate dateRetrurned = dateReturned.getValue();
        Integer dueDays = duration(dateToBeReturned,dateRetrurned);
        txtOverdueDays.setText(Integer.toString(dueDays));
    }

    @FXML
    void btnFinalAmountOnAction(ActionEvent event) {
        Double dueAmount = Double.parseDouble(txtDueAmount.getText());
        Double damageCost = Double.parseDouble(txtDamageCost.getText());
        Double finalAmount = (Double.parseDouble(txtBalance.getText()) + (Integer.parseInt(txtOverdueDays.getText())*dueAmount) + damageCost);
        txtFinalAmount.setText(Double.toString(finalAmount));
    }

    public Integer duration(LocalDate bookDate, LocalDate returnDate){
        Integer duration= Math.toIntExact(ChronoUnit.DAYS.between(bookDate, returnDate));
        return duration;
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
