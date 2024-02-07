package lk.ijse.carrental.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carrental.dto.CategoryDto;
import lk.ijse.carrental.dto.tm.CategoryTm;
import lk.ijse.carrental.service.custom.CategoryService;
import lk.ijse.carrental.service.custom.ServiceFactory;
import lk.ijse.carrental.service.custom.ServiceType;

import java.io.IOException;
import java.util.List;


public class CategoryFormController {

    public AnchorPane categoryNode;

    @FXML
    private TextField txtCatId;

    @FXML
    private TextField txtCatName;

    @FXML
    private TableColumn<?, ?> colCatId;

    @FXML
    private TableColumn<?, ?> colCatName;

    @FXML
    private TableView<CategoryTm> tblCategory;

    private CategoryService categoryService = ServiceFactory.getService(ServiceType.CATEGORY);

    public void initialize() {
        setCellValueFactory();
        getAllCategories();
    }

    private void setCellValueFactory() {
        colCatId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCatName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void getAllCategories() {
        try {
            ObservableList<CategoryTm> observableList = FXCollections.observableArrayList();
            List<CategoryDto> categoryDtos = categoryService.getAll();

            for (CategoryDto dto:categoryDtos) {
                var categoryTm = new CategoryTm(dto.getId(), dto.getName());
                observableList.add(categoryTm);
            }
            tblCategory.setItems(observableList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtCatId.getText();
        String name = txtCatName.getText();

        var catDto = new CategoryDto(id, name);

        try {
            categoryService.saveCategory(catDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Category Saved!").show();
            clearFields();
            initialize();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void btnHomePageOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/homepage_form.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) this.categoryNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home Page");
    }

    private void clearFields() {
        txtCatId.setText("");
        txtCatName.setText("");
    }

    @FXML
    void tblSearchCategoryOnAction(MouseEvent event) {
        try {
            String catId = tblCategory.getSelectionModel().getSelectedItem().getId();
            CategoryDto categoryDto = categoryService.search(catId);
            if(categoryDto != null) {
                txtCatId.setText(categoryDto.getId());
                txtCatName.setText(categoryDto.getName());

            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtCatId.getText();
        String name = txtCatName.getText();

        var catDto = new CategoryDto(id,name);
        try {
            categoryService.updateCategory(catDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Category Updated!").show();
            clearFields();
            initialize();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtCatId.getText();
        try {
            CategoryDto catdto = categoryService.search(id);
            categoryService.deleteCategory(catdto);
            new Alert(Alert.AlertType.CONFIRMATION,"Category Deleted!").show();
            clearFields();
            initialize();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

}
