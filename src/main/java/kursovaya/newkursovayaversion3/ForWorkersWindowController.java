package kursovaya.newkursovayaversion3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ForWorkersWindowController extends WorkerParams {
    @FXML
    private void addResume() throws IOException { //Открывает окно с добавлением резюме
        Stage stage1 = new Stage();
        stage1.setTitle("Добавляй меня");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddWorkerResume.fxml")));
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.show();
        AddWorkerResumeController.addList = list;
    }

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


    /**
     * ща будут методы, чтобы формировался список заинтересовавших пользователя вакансий
     */

    @FXML
    private TextField fio;
    private ArrayList<AdminVacancy> userClickOnAdminVacancies = new ArrayList<>();

    @FXML
    private void addtoOrder() throws IOException {
        int index = vacanciesTable.getSelectionModel().getSelectedIndex();

        AdminVacancy adminVacancy = new AdminVacancy();
        adminVacancy = vacancies.get(index);
//        vacancies.set(index, adminVacancy); вроде строчка без смысла
        userClickOnAdminVacancies.add(adminVacancy);

        /*
        Добавить алерт, что данные попали в заказ
         */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Все хорошо");
        alert.setHeaderText("вы успешно откликнулись на предложение");
        alert.setContentText("Данные успешно занесены");
        alert.showAndWait();

    }

    static User user;

    @FXML
    private void makeOrder() throws IOException {
        writeToFileOrder(userClickOnAdminVacancies, user);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Все кайф");
        alert.setHeaderText("Данные ваших заявок попали в файл");
        alert.setContentText("Данные успешно занесены");
        alert.showAndWait();
    }

    private void writeToFileOrder(ArrayList<AdminVacancy> list, User user) throws IOException {
        FileWriter writer = new FileWriter("C://Users//Bulat//IdeaProjects//NewKursovayaVersion3//baseOfClickedVacancies.txt", true);
        writer.write("\n");
        int id = (int) Math.abs ((Math.random() * (100 - (-100))) + (-100));
        String str = id + " " + user.getLogin() + " " + fio.getText() + " ";

        for (AdminVacancy AdminVacancy : list) {

            String typeOfWork = AdminVacancy.getTypeOfWork();
            Double salary = AdminVacancy.getSalary();
            Double experience = AdminVacancy.getExperience();

//            str = str + typeOfWork + "," + salary * experience + "_";
                        str = str + typeOfWork + "," + salary + "," + experience + "_";

        }
            writer.write(str);

        writer.close();
    }
    /*
    методы для записи в пулл заинтесоваших вакансий закончились
     */

    @FXML
    private TextField textField;
    @FXML
    private Label find;
    @FXML
    private void search() throws IOException {
        String name = textField.getText();
        boolean flag = false;

        for(int i = 0; i<  vacancies.size(); i++){
            if (name.equals(vacancies.get(i).getTypeOfWork().toLowerCase(Locale.ROOT)) |
                    name.equals(vacancies.get(i).getTypeOfWork())) flag = true;
        }

        if (flag) {
            find.setText("Такая работа есть!");
        } else {
            find.setText("Такой нет(");
        }
    }


    @FXML
    private void openAdminsPool() throws IOException { //Открывает окно с добавлением резюме
        Stage stage = new Stage();
        stage.setTitle("Каталог");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminLiked.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

//        AddVacancyController.vacancies = vacancies;
    }


}
