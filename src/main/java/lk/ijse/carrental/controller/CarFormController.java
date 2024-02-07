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
import lk.ijse.carrental.dto.CarDto;
import lk.ijse.carrental.dto.CategoryDto;
import lk.ijse.carrental.dto.tm.CarTm;
import lk.ijse.carrental.service.custom.CarService;
import lk.ijse.carrental.service.custom.CategoryService;
import lk.ijse.carrental.service.custom.ServiceFactory;
import lk.ijse.carrental.service.custom.ServiceType;

import java.io.IOException;
import java.util.List;

public class CarFormController {

    @FXML
    private AnchorPane carNode;

    @FXML
    private ComboBox<String> cmbCategory;


    @FXML
    private TableView<CarTm> tblCar;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colColour;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colVehicleNo;

    @FXML
    private TableColumn<?, ?> colYear;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtColour;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtVehicleNo;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtStatus;

    @FXML
    private Label lblCategory;

    private CategoryService categoryService = ServiceFactory.getService(ServiceType.CATEGORY);
    private CarService carService = ServiceFactory.getService(ServiceType.CAR);

    public void initialize() {
        setCellValueFactory();
        getAllCars();
        loadAllCategories();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void getAllCars() {
        try {
            ObservableList<CarTm> observableList = FXCollections.observableArrayList();
            List<CarDto> carDtos = carService.getAll();

            for (CarDto dto:carDtos) {
                var carTm = new CarTm(dto.getId(), dto.getBrand(), dto.getModel(), dto.getColour(), dto.getVehicleNo(), dto.getYear(), dto.getPrice(), dto.getCatId(),dto.getIsAvailability() ? "Available" : "Not Available");
                observableList.add(carTm);
            }
            tblCar.setItems(observableList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void loadAllCategories() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            List<CategoryDto> categories = categoryService.getAll();

            for (CategoryDto category:categories) {
                observableList.add(category.getId());
            }
            cmbCategory.setItems(observableList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        String colour = txtColour.getText();
        String vehicleNo = txtVehicleNo.getText();
        Integer year = Integer.parseInt(txtYear.getText());
        Double price = Double.parseDouble(txtPrice.getText());
        String catId  = txtCategory.getText();



        var cartDto = new CarDto(id,brand,model,colour,vehicleNo,year,price,catId);
        cartDto.setIsAvailability(true);

        try {
            carService.saveCar(cartDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Car details Saved!").show();
            clearFields();
            initialize();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void cmbOnAction(ActionEvent event) {
        String catId = cmbCategory.getValue();
        try {
            CategoryDto categoryDto = categoryService.search(catId);
            if(categoryDto != null) {
                txtCategory.setText(categoryDto.getId());
                lblCategory.setText(categoryDto.getName());

            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields(){
        txtId.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtColour.setText("");
        txtVehicleNo.setText("");
        txtYear.setText("");
        txtPrice.setText("");
        txtCategory.setText("");
    }

    @FXML
    void tblSearchCarOnAction(MouseEvent event) {
        try {
            String id = tblCar.getSelectionModel().getSelectedItem().getId();
            CarDto carDto = carService.search(id);
            if(carDto != null) {
                txtId.setText(carDto.getId());
                txtBrand.setText(carDto.getBrand());
                txtModel.setText(carDto.getModel());
                txtColour.setText(carDto.getColour());
                txtVehicleNo.setText(carDto.getVehicleNo());
                txtYear.setText(Integer.toString(carDto.getYear()));
                txtPrice.setText(Double.toString(carDto.getPrice()));
                txtCategory.setText(carDto.getCatId());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            CarDto carDto = carService.search(id);
            carService.deleteCar(carDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Car Deleted!").show();
            clearFields();
            initialize();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        String colour = txtColour.getText();
        String vehicleNo = txtVehicleNo.getText();
        Integer year = Integer.parseInt(txtYear.getText());
        Double price = Double.parseDouble(txtPrice.getText());
        String catId  = txtCategory.getText();


        var cartDto = new CarDto(id,brand,model,colour,vehicleNo,year,price,catId);

        try {
            carService.updateCar(cartDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Car details Updated!").show();
            clearFields();
            initialize();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
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

        Stage stage = (Stage) this.carNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home Page");
    }
}
