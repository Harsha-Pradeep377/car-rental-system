package lk.ijse.carrental.controller;

import jakarta.transaction.Transactional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carrental.dto.CustomerDto;
import lk.ijse.carrental.dto.UserDto;
import lk.ijse.carrental.service.custom.ServiceFactory;
import lk.ijse.carrental.service.custom.ServiceType;
import lk.ijse.carrental.service.custom.UserService;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class UserFormController {
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassward;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane userNode;

    private UserService userService = ServiceFactory.getService(ServiceType.USER);

    @FXML
    void btnCreateUserOnAction(ActionEvent event) {
        String id = txtUserId.getText();
        String name = txtName.getText();
        String userName = txtUserName.getText();
        String pass = txtPassward.getText();
        String hashPassward = hashPassword(pass);
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();

        if(!id.isEmpty() && !name.isEmpty() && !userName.isEmpty() && !pass.isEmpty() && !email.isEmpty() && !mobile.isEmpty() ){
            if(email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
                if(mobile.matches("^\\d{10}$")){
                    var userDto = new UserDto(id,name,userName,hashPassward,email,mobile);

                    try {
                        userService.saveUser(userDto);
                        new Alert(Alert.AlertType.CONFIRMATION,"User Saved!").show();
                        clearFields();
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                    }
                }else{
                    new Alert(Alert.AlertType.WARNING,"Please insert a valid mobile number!").show();
                }
            }else{
                new Alert(Alert.AlertType.WARNING,"Please insert a valid email!").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Please fill the required details!").show();
        }

    }

    private void clearFields() {
        txtUserId.setText("");
        txtName.setText("");
        txtUserName.setText("");
        txtPassward.setText("");
        txtEmail.setText("");
        txtMobile.setText("");
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    void txtSearchUserOnAction(ActionEvent event) {
        String id = txtUserId.getText();
        if (!id.isEmpty()) {
            try {
                UserDto userDto = userService.search(id);
                if (userDto != null) {
                    txtName.setText(userDto.getName());
                    txtUserName.setText(userDto.getUserName());
                    txtPassward.setText(userDto.getPass());
                    txtEmail.setText(userDto.getEmail());
                    txtMobile.setText(userDto.getMobile());
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING,"User not found!").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a user ID!").show();
        }
    }
    @FXML
    void btnDeleteUserOnAction(ActionEvent event) {
        String id = txtUserId.getText();
        if(!id.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Do you want to delete this user?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> type = alert.showAndWait();
            if(type.isPresent() && type.get() == ButtonType.YES){
                try {
                    UserDto dto = userService.search(id);
                    userService.deleteUser(dto);
                    new Alert(Alert.AlertType.CONFIRMATION,"User Deleted!",ButtonType.OK).show();
                    clearFields();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Please select the required user id to delete!").show();
        }
    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) {
        String id = txtUserId.getText();
        String name = txtName.getText();
        String userName = txtUserName.getText();
        String pass = txtPassward.getText();
        String hashPassward = hashPassword(pass);
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();

        if(!id.isEmpty() && !name.isEmpty() && !userName.isEmpty() && !pass.isEmpty() && !email.isEmpty() && !mobile.isEmpty() ){
            if(email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
                if(mobile.matches("^\\d{10}$")){
                    var userDto = new UserDto(id,name,userName,hashPassward,email,mobile);

                    try {
                        userService.updateUser(userDto);
                        new Alert(Alert.AlertType.CONFIRMATION,"User Updated!").show();
                        clearFields();
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                    }
                }else{
                    new Alert(Alert.AlertType.WARNING,"Please insert a valid mobile number!").show();
                }
            }else{
                new Alert(Alert.AlertType.WARNING,"Please insert a valid email!").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Please fill the required details!").show();
        }
    }

    @FXML
    void btnClearFieldsOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnLoginFormOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) this.userNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

}
