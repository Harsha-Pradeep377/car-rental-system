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
import lk.ijse.carrental.dto.ReturnDto;
import lk.ijse.carrental.dto.UserDto;
import lk.ijse.carrental.dto.tm.BookingTm;
import lk.ijse.carrental.dto.tm.ReturnTm;
import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.service.custom.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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
    private TableView<ReturnTm> tblReturn;

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

    public void initialize(){
        setCellValueFactory();
        getAllReturnDetails();
    }

    private void setCellValueFactory() {
        colReturnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookedId"));
        colReturnedDate.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));
        colOverDueDays.setCellValueFactory(new PropertyValueFactory<>("overdueDays"));
        colOverDueAmount.setCellValueFactory(new PropertyValueFactory<>("overdueAmount"));
        colDamadgeCost.setCellValueFactory(new PropertyValueFactory<>("damageCost"));
        colFinalAmount.setCellValueFactory(new PropertyValueFactory<>("finalAmount"));
    }

    private void getAllReturnDetails() {
        try {
            ObservableList<ReturnTm> observableList = FXCollections.observableArrayList();
            List<ReturnDto> returnDtos = returnService.getAll();

            for (ReturnDto dto:returnDtos) {
                var returnTm = new ReturnTm(dto.getId(), dto.getBookedId(), dto.getReturnedDate(), dto.getOverdueDays(),dto.getOverdueAmount(),dto.getDamageCost(), dto.getFinalAmount());
                observableList.add(returnTm);
            }
            tblReturn.setItems(observableList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveReturnOnAction(ActionEvent event) {
        String id = txtReturnId.getText();
        String bookId = txtBookId.getText();
        LocalDate returnedDate = dateReturned.getValue();
        String textDuedays = txtOverdueDays.getText();
        String textDueAmount = txtDueAmount.getText();
        String textDamageCost = txtDamageCost.getText();
        String textFinalAmount = txtFinalAmount.getText();
        
        if (!id.isEmpty() && !bookId.isEmpty() && returnedDate != null && !textDuedays.isEmpty() && !textDueAmount.isEmpty() && !textDamageCost.isEmpty() && !textFinalAmount.isEmpty()) {
            Integer dueDays  = Integer.parseInt(textDuedays);
            Double overDueAmount  = Double.parseDouble(textDueAmount);
            Double damageCost = Double.parseDouble(textDamageCost);
            Double finalAmount = Double.parseDouble(textFinalAmount);

            var returnDto = new ReturnDto(id, bookId, returnedDate,dueDays, overDueAmount, damageCost, finalAmount);
            BookingDto bookingDto = bookingService.search(returnDto.getBookedId());
            try {
                returnService.saveReturnDetails(returnDto);
                new Alert(Alert.AlertType.CONFIRMATION,"Return details Saved!").show();
                updateIsAvailability(bookingDto.getCarId(), true);
                clearFields();
                initialize();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please fill the required details!").show();
        }
    }

    private void clearFields() {
        txtBookId.setText("");
        txtBookDate.setText("");
        txtReturnDate.setText("");
        txtCustId.setText("");
        txtCarId.setText("");
        txtBalance.setText("");
        txtReturnId.setText("");
        dateReturned.setValue(null);
        txtOverdueDays.setText("");
        txtDueAmount.setText("");
        txtDamageCost.setText("");
        txtFinalAmount.setText("");
    }

    private void updateIsAvailability(String carId, boolean availability) {
        carService.updateIsAvailability(carId,availability);
    }

    @FXML
    void btnSearchBookingOnAction(ActionEvent event) {
        String id = txtBookId.getText();
        if (!id.isEmpty()) {
            try {
                BookingDto bookingDto = bookingService.search(id);
                if(bookingDto != null){
                    txtCustId.setText(bookingDto.getCustId());
                    txtCarId.setText(bookingDto.getCarId());
                    txtBookDate.setText(String.valueOf(bookingDto.getBookDate()));
                    txtReturnDate.setText(String.valueOf(bookingDto.getReturnDate()));
                    txtBalance.setText(Double.toString(bookingDto.getBalance()));
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING,"Booking id not found!").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a booking id!").show();
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
        try {
            String id = tblReturn.getSelectionModel().getSelectedItem().getId();
            ReturnDto returnDto = returnService.search(id);
            if(returnDto != null) {
                txtBookId.setText(returnDto.getBookedId());
                txtReturnId.setText(returnDto.getId());
                dateReturned.setValue(returnDto.getReturnedDate());
                txtOverdueDays.setText(Integer.toString(returnDto.getOverdueDays()));
                txtDueAmount.setText(Double.toString(returnDto.getOverdueAmount()));
                txtDamageCost.setText(Double.toString(returnDto.getDamageCost()));
                txtFinalAmount.setText(Double.toString(returnDto.getFinalAmount()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteReturnDetailsOnAction(ActionEvent event) {
        String id = txtReturnId.getText();
        if(!id.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Do you want to delete this return details?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> type = alert.showAndWait();
            if(type.isPresent() && type.get() == ButtonType.YES){
                try {
                    ReturnDto returnDto = returnService.search(id);
                    returnService.deleteReturnDetails(returnDto);
                    new Alert(Alert.AlertType.CONFIRMATION,"Return details deleted!",ButtonType.OK).show();
                    clearFields();
                    initialize();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Please select the required return id to delete!").show();
        }
    }

    @FXML
    void btnClearFieldsOnAction(ActionEvent event) {
        clearFields();
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
