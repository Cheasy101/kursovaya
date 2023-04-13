package kursovaya.newkursovayaversion3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartedWindowController {
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private Button allowButton;

    /*
    Ща будут поля для окошка регистрации
     */
    @FXML
    private TextField registrateLoginField;
    @FXML
    private TextField registratePasswordField;
    @FXML
    private Button registrButton;

    //метод открыть формы
    @FXML
    protected void onAllowButtonClick() throws IOException {
        User user = new User();
        user.setLogin(textField1.getText());
        user.setPassword(textField2.getText());

        boolean forFan = user.comparison(user, user.fillTheUser());

        if (textField1.getText().equals("admin") && textField2.getText().equals("admin")) {
            allowButton.getScene().getWindow().hide();
            Stage stage1 = new Stage();
            stage1.setTitle("Окно редактирования");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminWorkWindow.fxml")));
            // TODO: 27.11.2022 поменять файлы на другие сверстанные
            Scene scene = new Scene(root);
            stage1.setScene(scene);
            stage1.show();

        } else if (forFan) {
            ForWorkersWindowController.user = user;
            Stage stage = new Stage();
            stage.setTitle("Пул профессий");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ForWorkersWindow.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            allowButton.getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Сообщение об ошибке");
            alert.setContentText("Проверьте введенные логин и пароль");
            alert.showAndWait();
        }
    }



    @FXML
    protected void clear() {
        textField1.clear();
        textField2.clear();
    }

    /*
    Открываем окно регистрации
     */
    @FXML
    protected void registration() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Регистрация");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegistrationWindow.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    protected void registrAction () throws IOException {
        User user = new User();
        user.setLogin(registrateLoginField.getText());
        user.setPassword(registratePasswordField.getText());
        boolean forFan = user.comparison(user, user.fillTheUser());
        if (forFan) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Something wrong!");
            alert.setContentText("Данный пользователь уже зарегестрирован!");
            alert.showAndWait();

        } else {
            user.write(user);
            Stage stage = (Stage) registrButton.getScene().getWindow();
            stage.close();
        }
    }
}
