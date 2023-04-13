package kursovaya.newkursovayaversion3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class AdminWorkWindowController {
    @FXML
    public static ObservableList<WorkerParams> list = FXCollections.observableArrayList();//параметры сотрудника
    //Лист, который хранит current значения

    //Описание параметров работника, которые читаются с файла
    @FXML
    private TableView<WorkerParams> WorkerTable;
    @FXML
    private TableColumn<WorkerParams, String> workerNameField;
    @FXML
    private TableColumn<WorkerParams, String> workerProfessionField;
    @FXML
    private TableColumn<WorkerParams, Double> workerSalaryField;
    @FXML
    private TableColumn<WorkerParams, Double> workerExpirenceField;




    public void initialize() { // TODO: 27.11.2022  заполняет таблицу данными с файла
        try (
                Scanner scanner = new Scanner(new File("C://Users//Bulat//IdeaProjects//NewKursovayaVersion3//baseOfWorkers.txt")).useLocale(Locale.US)) {
            while (scanner.hasNext()) {
                WorkerParams workerParams = new WorkerParams();
                workerParams.inputData(scanner);
                list.add(workerParams);
            }
        } catch (
                IOException exception) {
            System.out.println(exception.getMessage());
        }

        workerNameField.setCellValueFactory(new PropertyValueFactory<>("nameOfWorker"));
        workerProfessionField.setCellValueFactory(new PropertyValueFactory<>("profession"));
        workerSalaryField.setCellValueFactory(new PropertyValueFactory<>("salary"));
        workerExpirenceField.setCellValueFactory(new PropertyValueFactory<>("workExperience"));

        WorkerTable.setItems(list);

        /* --------------------- Абсолютно такой же блок для другой таблицы --------------------------- */

        try (
                Scanner scanner = new Scanner(new File("C://Users//Bulat//IdeaProjects//NewKursovayaVersion3//baseOfVacancy.txt")).useLocale(Locale.US)) {
            while (scanner.hasNext()) {
                AdminVacancy adminVacancy = new AdminVacancy();
                adminVacancy.inputData(scanner);
                vacancies.add(adminVacancy);
            }
        } catch (
                IOException exception) {
            System.out.println(exception.getMessage());
        }

        vacanciesRequired.setCellValueFactory(new PropertyValueFactory<>("typeOfWork"));
        salaryRequired.setCellValueFactory(new PropertyValueFactory<>("salary"));
        expirienceRequired.setCellValueFactory(new PropertyValueFactory<>("experience"));

        vacanciesTable.setItems(vacancies);

    }

    /**
     * Сейчас опишу таблицу вакансий
     */
    @FXML
    private TableView<AdminVacancy> vacanciesTable;
    @FXML
    private TableColumn<AdminVacancy, String> vacanciesRequired;
    @FXML
    private TableColumn<AdminVacancy, Double> salaryRequired;
    @FXML
    private TableColumn<AdminVacancy, Double> expirienceRequired;



    @FXML
    public static ObservableList<AdminVacancy> vacancies = FXCollections.observableArrayList();

    @FXML
    private void addVacancyButton() throws IOException { //Открывает окно с добавлением резюме
            Stage stage1 = new Stage();
            stage1.setTitle("Добавляй меня");
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddVacancy.fxml")));
            Scene scene = new Scene(root);
            stage1.setScene(scene);
            stage1.show();
            AddVacancyController.vacancies = vacancies;
        }

    /*
    Ща будут методы для добавления интересующих вакансий в корзину !
     */

    private ArrayList<WorkerParams> adminClickedOnVacancies = new ArrayList<>();

    @FXML
    private void addtoOrder() throws IOException {
        int index = WorkerTable.getSelectionModel().getSelectedIndex();

        WorkerParams workerParams = new WorkerParams();
        workerParams = list.get(index); // получаем значения тока конкретного чела
//        vacancies.set(index, adminVacancy); вроде строчка без смысла
        adminClickedOnVacancies.add(workerParams);

        /*
        Добавить алерт, что данные попали в заказ
         */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Все хорошо");
        alert.setHeaderText("заинтересовавший вас работник добавлен");
        alert.setContentText("Данные успешно занесены");
        alert.showAndWait();

    }

    @FXML
    private void makeOrder() throws IOException {
        writeToFileOrder(adminClickedOnVacancies);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Все кайф");
        alert.setHeaderText("Данные потенциальных сотрудников");
        alert.setContentText("Данные успешно занесены");
        alert.showAndWait();
    }

    private void writeToFileOrder(ArrayList<WorkerParams> list) throws IOException {
        FileWriter writer = new FileWriter("C://Users//Bulat//IdeaProjects//NewKursovayaVersion3//baseOfAdminClickedResume.txt", true);
        writer.write("\n");
        String str = "Admin" + " ";

        for (WorkerParams WorkerParams : list) {

            String nameOfWorker = WorkerParams.getNameOfWorker();
            String profession = WorkerParams.getProfession();
            Double salary = WorkerParams.getSalary();
            Double workExperience = WorkerParams.getWorkExperience();



            writer.write( str + nameOfWorker + "," + profession + "," + salary + "," + workExperience + "_");
        }
        writer.close();
    }

    /*
    Чтоб кнопка открывала пул ищущих работу
     */

    @FXML
    private void openWorkersPool() throws IOException { //Открывает окно с добавлением резюме
        Stage stage = new Stage();
        stage.setTitle("Каталог");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminLookAtVacancies.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

//        AddVacancyController.vacancies = vacancies;
    }


}
