package kursovaya.newkursovayaversion3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AdminLookAtVacanciesController {
    private ObservableList<AdminLookAtVacancies> observableList = FXCollections.observableArrayList();
    int index;
    /*
    метод при запуске
     */
    @FXML
    private TableView<AdminLookAtVacancies> catalogTable;
    @FXML
    private TableColumn<AdminLookAtVacancies, Integer> userId;
    @FXML
    private TableColumn<AdminLookAtVacancies, String> userSurname;

    public void initialize() throws IOException {

        String path = "C:/Users/Bulat/IdeaProjects/NewKursovayaVersion3/baseOfClickedVacancies.txt";
        try (
                Scanner scanner = new Scanner(new File(path)).useLocale(Locale.US)) {
            while (scanner.hasNext()) {

                AdminLookAtVacancies lookAtVacancies = new AdminLookAtVacancies();

                lookAtVacancies.inputData(scanner);

                observableList.add(lookAtVacancies);

                userId.setCellValueFactory(new PropertyValueFactory<>("numberOfOrder"));
                userSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
                catalogTable.setItems(observableList);
                catalogTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPurchaseDetails(newValue));
            }
        } catch (
                IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    private TableView<AdminVacancy> dunnow;
    @FXML
    private TableColumn<AdminVacancy, String> typeOFWork;
    @FXML
    private TableColumn<AdminVacancy, Double> salary;
    @FXML
    private TableColumn<AdminVacancy, Double> experience;

    private void showPurchaseDetails(AdminLookAtVacancies adminLookAtVacancies) {
        if (adminLookAtVacancies == null) {

        } else {
            ObservableList<AdminVacancy> list1 = FXCollections.observableArrayList();

            for (int i = 0; i < adminLookAtVacancies.adminVacancies.size(); i++) {
                list1.add(adminLookAtVacancies.adminVacancies.get(i));
            }

            typeOFWork.setCellValueFactory(new PropertyValueFactory<>("typeOfWork"));
            salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
            experience.setCellValueFactory(new PropertyValueFactory<>("expirience"));
            dunnow.setItems(list1);

            index = catalogTable.getSelectionModel().getSelectedIndex();


            //            int count = 0;
//            int kolvo = 0;
//            for(int i = 0;i <adminLookAtVacancies.adminVacancies.size();i++){
//                count += adminLookAtVacancies.adminVacancies.get(i). productSum;
//            }
//            count = count;
//            combobox1.setValue(adminLookAtVacancies.toNextObject);
//            labelId.setText(String.valueOf(adminLookAtVacancies.number));
//            labelSum.setText(String.valueOf(count));
        }
    }





    @FXML
    private TextField textField;
    @FXML
    private Label find;
    @FXML
    private void search() throws IOException {
        String name = textField.getText();
        boolean flag = false;

        for(int i = 0; i<  observableList.size(); i++){
            if (name.equals(observableList.get(i).getSurname().toLowerCase(Locale.ROOT)) |
                    name.equals(observableList.get(i).getSurname())) flag = true;
        }

        if (flag) {
            find.setText("Такая сотрудник есть!");
        } else {
            find.setText("Такого нет(");
        }
    }


}
