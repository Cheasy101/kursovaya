package kursovaya.newkursovayaversion3;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddWorkerResumeController implements Initializable {

    public static ObservableList<WorkerParams> addList;

    /*
    Описание кнопок для формы добавления
     */

    @FXML
    private TextField workerName;
    @FXML
    private TextField workerProfession;
    @FXML
    private TextField workerSalary;
    @FXML
    private TextField workerExperience;
    @FXML
    private Button applyAddButton;
    @FXML
    private Button cancerAdd;
    private Stage dialogStage;


    @FXML
    public void add() throws IOException { //добавляет резюме в файл

        if (isInputValid()) {

            WorkerParams workParams = new WorkerParams();

            workParams.setNameOfWorker(workerName.getText());
            workParams.setProfession(workerProfession.getText());
            workParams.setSalary(Double.parseDouble(workerSalary.getText()));
            workParams.setWorkExperience(Double.parseDouble(workerExperience.getText()));


            /*
//            System.out.println(workParams.getNameOfWorker() + " " + workParams.profession + " "
//                    + workParams.getSalary() + " " + workParams.getWorkExperience());

            Просто проверка с кайфом ли все работает
*/

            addList.add(workParams);
            WorkerParams.changeFileasdasd(addList); //перезаписываем файл с данными

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Все с кайфом");
            alert.setHeaderText("Сообщение о том, что все хорошо");
            alert.setContentText("Данные успешно занесены");
            alert.showAndWait();

            applyAddButton.getScene().getWindow().hide();
        }
        cancerAdd.getScene().getWindow().hide();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (workerName.getText() == null || workerName.getText().length() == 0)
            errorMessage += "Нет Такого имени\n";
        if (workerProfession.getText() == null || workerProfession.getText().length() == 0)
            errorMessage += "Нет такой профессии\n";
        if (workerSalary.getText() == null || workerSalary.getText().length() == 0)
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
