package kursovaya.newkursovayaversion3;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddVacancyController {
    public static ObservableList<AdminVacancy> vacancies;


    @FXML
    private TextField requiredWork;
    @FXML
    private TextField requiredSalary;
    @FXML
    private TextField requiredExp;
    @FXML
    private Button addWorkRequest;
    @FXML
    private Button cancerAdd;
    private Stage dialogStage;


    @FXML
    public void add() throws IOException { //добавляет резюме в файл

        if (isInputValid()) {

            AdminVacancy adminVacancy = new AdminVacancy();

            adminVacancy.setTypeOfWork(requiredWork.getText());
            adminVacancy.setSalary(Double.valueOf(requiredSalary.getText()));
            adminVacancy.setExperience(Double.parseDouble(requiredExp.getText()));


            /*
//            System.out.println(workParams.getNameOfWorker() + " " + workParams.profession + " "
//                    + workParams.getSalary() + " " + workParams.getWorkExperience());

            Просто проверка с кайфом ли все работает
*/

            vacancies.add(adminVacancy);
            AdminVacancy.changeFile(vacancies); //перезаписываем файл с данными

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Все с кайфом");
            alert.setHeaderText("Сообщение о том, что все хорошо");
            alert.setContentText("Данные успешно занесены");
            alert.showAndWait();

            addWorkRequest.getScene().getWindow().hide();
        }
        cancerAdd.getScene().getWindow().hide();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (requiredWork.getText() == null || requiredWork.getText().length() == 0)
            errorMessage += "Нет Такого имени\n";
        if (requiredSalary.getText() == null || requiredSalary.getText().length() == 0)
            errorMessage += "Нет такой профессии\n";
        if (requiredExp.getText() == null || requiredExp.getText().length() == 0)
            errorMessage += "Таких денег не бываеь\n";

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Неккоректные поля");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }


}
