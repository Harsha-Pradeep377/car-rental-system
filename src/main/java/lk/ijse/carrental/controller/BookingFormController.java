package lk.ijse.carrental.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carrental.dto.BookingDto;
import lk.ijse.carrental.dto.CarDto;
import lk.ijse.carrental.dto.CustomerDto;
import lk.ijse.carrental.dto.tm.BookingTm;
import lk.ijse.carrental.dto.tm.CarTm;
import lk.ijse.carrental.entity.CarEntity;
import lk.ijse.carrental.service.custom.BookingService;
import lk.ijse.carrental.service.custom.CarService;
import lk.ijse.carrental.service.custom.ServiceFactory;
import lk.ijse.carrental.service.custom.ServiceType;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Optional;

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
    @FXML
    private TableColumn<?, ?> colAdvance;
    @FXML
    private TableColumn<?, ?> colBalance;
    @FXML
    private TableColumn<?, ?> colBookDate;
    @FXML
    private TableColumn<?, ?> colBookingId;
    @FXML
    private TableColumn<?, ?> colCarId;
    @FXML
    private TableColumn<?, ?> colCustId;
    @FXML
    private TableColumn<?, ?> colReturnDate;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private TableColumn<?, ?> colrate;
    @FXML
    private TableColumn<?, ?> colStatus;
    @FXML
    private TableView<BookingTm> tblBooking;


    private CarService carService = ServiceFactory.getService(ServiceType.CAR);
    private BookingService bookingService = ServiceFactory.getService(ServiceType.BOOKING);

    public void initialize(){
        setCellValueFactory();
        getAllbookings();
    }

    private void setCellValueFactory() {
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCarId.setCellValueFactory(new PropertyValueFactory<>("carId"));
        colBookDate.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colrate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void getAllbookings() {
        try {
            ObservableList<BookingTm> observableList = FXCollections.observableArrayList();
            List<BookingDto> bookingDtos = bookingService.getAll();

            for (BookingDto dto:bookingDtos) {
                CarDto carDto = carService.search(dto.getCarId());
                var bookingTm = new BookingTm(dto.getId(), dto.getCustId(), dto.getCarId(), dto.getBookDate(),dto.getReturnDate(),dto.getRate(), dto.getTotal(), dto.getAdvance(), dto.getBalance(), carDto.getIsAvailability() ? "Returned" : "Booked");
                observableList.add(bookingTm);
            }
            tblBooking.setItems(observableList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtBookId.getText();
        String custId = txtCustId.getText();
        String carId = txtCarId.getText();
        LocalDate bookingDate = dateBooking.getValue();
        LocalDate returningDate = dateReturning.getValue();
        String textRate = txtRate.getText();
        String textTotal = txtTotal.getText();
        String textAdvance = txtAdvance.getText();
        String textBalance = txtBalance.getText();

        if (!id.isEmpty() && !custId.isEmpty() && !carId.isEmpty() && bookingDate != null && returningDate != null && !textRate.isEmpty() && !textTotal.isEmpty() && !textAdvance.isEmpty() && !textBalance.isEmpty()) {
            Double rate = Double.parseDouble(textRate);
            Double advance  = Double.parseDouble(textAdvance);
            Double total = Double.parseDouble(textTotal);
            Double balance = Double.parseDouble(textBalance);

            var bookingDto = new BookingDto(id,custId,carId,bookingDate,returningDate,rate,total,advance,balance);

            try {
                bookingService.saveBooking(bookingDto);
                new Alert(Alert.AlertType.CONFIRMATION,"Booking details Saved!").show();
                updateIsAvailability(bookingDto.getCarId(), false);
                clearFields();
                initialize();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please fill the required details!").show();
        }
    }

    private void updateIsAvailability(String carId, boolean availability) {
        carService.updateIsAvailability(carId,availability);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtBookId.getText();
        String custId = txtCustId.getText();
        String carId = txtCarId.getText();
        LocalDate bookingDate = dateBooking.getValue();
        LocalDate returningDate = dateReturning.getValue();
        String textRate = txtRate.getText();
        String textTotal = txtTotal.getText();
        String textAdvance = txtAdvance.getText();
        String textBalance = txtBalance.getText();

        if (!id.isEmpty() && !custId.isEmpty() && !carId.isEmpty() && bookingDate != null && returningDate != null && !textRate.isEmpty() && !textTotal.isEmpty() && !textAdvance.isEmpty() && !textBalance.isEmpty()) {
            Double rate = Double.parseDouble(textRate);
            Double advance  = Double.parseDouble(textAdvance);
            Double total = Double.parseDouble(textTotal);
            Double balance = Double.parseDouble(textBalance);

            var bookingDto = new BookingDto(id,custId,carId,bookingDate,returningDate,rate,total,advance,balance);

            try {
                bookingService.updateBooking(bookingDto);
                new Alert(Alert.AlertType.CONFIRMATION,"Booking details updated!").show();
                clearFields();
                initialize();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please fill the required details!").show();
        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtBookId.getText();
        if(!id.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Do you want to delete this Booking?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> type = alert.showAndWait();
            if(type.isPresent() && type.get() == ButtonType.YES) {
                try {
                    BookingDto bookingDto = bookingService.search(id);
                    bookingService.deleteBooking(bookingDto);
                    new Alert(Alert.AlertType.CONFIRMATION, "Booking details deleted!",ButtonType.OK).show();
                    clearFields();
                    initialize();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Please select the required booking id to delete!").show();
        }
    }

    @FXML
    void tblSearchOnAction(MouseEvent event) {
        try {
            String id = tblBooking.getSelectionModel().getSelectedItem().getId();
            BookingDto bookingDto = bookingService.search(id);
            if(bookingDto != null) {
                txtBookId.setText(bookingDto.getId());
                txtCustId.setText(bookingDto.getCustId());
                txtCarId.setText(bookingDto.getCarId());
                dateBooking.setValue(bookingDto.getBookDate());
                dateReturning.setValue(bookingDto.getReturnDate());
                txtRate.setText(Double.toString(bookingDto.getRate()));
                txtTotal.setText(Double.toString(bookingDto.getTotal()));
                txtAdvance.setText(Double.toString(bookingDto.getAdvance()));
                txtBalance.setText(Double.toString(bookingDto.getBalance()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields(){
        txtBookId.setText("");
        txtCustId.setText("");
        txtCarId.setText("");
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

        String textRate = txtRate.getText();
        if(textRate.matches("^-?\\d*\\.?\\d+$")) {
            if (duration > 30) {
                new Alert(Alert.AlertType.WARNING, "The duration should be less than 30 days").show();
            } else {
                Double total = (duration * (Double.parseDouble(txtRate.getText())));
                txtTotal.setText(Double.toString(total));
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Please insert a valid rate!").show();
        }
    }
    @FXML
    void calculateBalanceOnAction(ActionEvent event) {
        String textAdvance = txtAdvance.getText();
        if(textAdvance.matches("^-?\\d*\\.?\\d+$")) {
            Double balance = (Double.parseDouble(txtTotal.getText())) - (Double.parseDouble(txtAdvance.getText()));
            txtBalance.setText(Double.toString(balance));
        }else{
            new Alert(Alert.AlertType.WARNING, "Please insert a valid advance!").show();
        }
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
