package lk.ijse.carrental.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carrental.dto.BookingDto;
import lk.ijse.carrental.dto.CarDto;
import lk.ijse.carrental.dto.CustomerDto;
import lk.ijse.carrental.service.custom.BookingService;
import lk.ijse.carrental.service.custom.CarService;
import lk.ijse.carrental.service.custom.ServiceFactory;
import lk.ijse.carrental.service.custom.ServiceType;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class BookingFormController {
    @FXML
    private AnchorPane bookingNode;

    @FXML
    private DatePicker dateBooking;

    @FXML
    private DatePicker dateReturning;

    @FXML
    private TextField txtAdvance;

    @FXML
    private TextField txtAvailability;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtCarId;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtRate;

    @FXML
    private TextField txtTotal;

    private CarService carService = ServiceFactory.getService(ServiceType.CAR);
    private BookingService bookingService = ServiceFactory.getService(ServiceType.BOOKING);

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtBookId.getText();
        String custId = txtCustId.getText();
        String carId = txtCarId.getText();
        LocalDate bookingDate = dateBooking.getValue();
        LocalDate returningDate = dateReturning.getValue();
        Double rate = Double.parseDouble(txtRate.getText());
        Double total = Double.parseDouble(txtTotal.getText());
        Double advance  = Double.parseDouble(txtAdvance.getText());
        Double balance = Double.parseDouble((txtBalance.getText()));

        var bookingDto = new BookingDto(id,custId,carId,bookingDate,returningDate,rate,total,advance,balance);

        try {
            bookingService.saveBooking(bookingDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Booking details Saved!").show();
            carisBooked(bookingDto);
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void carisBooked(BookingDto bookingDto) {
        CarDto carDto = carService.search(bookingDto.getCarId());
        carDto.setIsAvailability(false);
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    @FXML
    void txtCheckAvailabilityOnAction(ActionEvent event) {
        String id = txtCarId.getText();
        CarDto carDto = carService.search(id);
        if(carDto.getIsAvailability()){
            txtAvailability.setText("Yes");
        }else {
            txtAvailability.setText("No");
        }
    }

    private void clearFields(){
        txtBookId.setText("");
        txtCustId.setText("");
        txtCustId.setText("");
        txtAvailability.setText("");
        dateBooking.setValue(null);
        dateReturning.setValue(null);
        txtRate.setText("");
        txtTotal.setText("");
        txtAdvance.setText("");
        txtBalance.setText("");
    }

    public Integer duration(LocalDate bookDate, LocalDate returnDate){
        Integer duration= Math.toIntExact(ChronoUnit.DAYS.between(bookDate, returnDate));
        return duration;
    }

    @FXML
    void CheckDurationAndCalculateTotalOnAction(ActionEvent event) {
        LocalDate bookDate = dateBooking.getValue();
        LocalDate returnDate = dateReturning.getValue();
        Integer duration = duration(bookDate,returnDate);

        if(duration>30){
            new Alert(Alert.AlertType.WARNING,"The duration should be less than 30 days").show();
        }else{
            Double total = (duration*(Double.parseDouble(txtRate.getText())));
            txtTotal.setText(Double.toString(total));
        }
    }
    @FXML
    void calculateBalanceOnAction(ActionEvent event) {
        Double balance = (Double.parseDouble(txtTotal.getText())) - (Double.parseDouble(txtAdvance.getText()));
        txtBalance.setText(Double.toString(balance));
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }


    @FXML
    void btnHomePageOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/homepage_form.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) this.bookingNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home Page");
    }

}
