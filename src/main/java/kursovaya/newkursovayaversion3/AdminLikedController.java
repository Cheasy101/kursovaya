package kursovaya.newkursovayaversion3;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AdminLikedController {

    @FXML
    private TableView catalogTable;
    @FXML
    private TableColumn<AdminLikedController, String> userName;
    @FXML
    private TableColumn<AdminLikedController, String> userProfession;
    @FXML
    private TableColumn<AdminLikedController, Double> userSalary;
    @FXML
    private TableColumn<AdminLikedController, Double> userExpirience;

    private String userNameName;
    private String userProfessionProfession;
    private Double userSalarySalary;
    private Double userExpirienceExpirience;

    public void setUserNameName(String userNameName) {
        this.userNameName = userNameName;
    }

    public void setUserProfessionProfession(String userProfessionProfession) {
        this.userProfessionProfession = userProfessionProfession;
    }

    public void setUserSalarySalary(Double userSalarySalary) {
        this.userSalarySalary = userSalarySalary;
    }

    public void setUserExpirienceExpirience(Double userExpirienceExpirience) {
        this.userExpirienceExpirience = userExpirienceExpirience;
    }

    public void initialize() throws IOException {

        String path = "C:/Users/Bulat/IdeaProjects/NewKursovayaVersion3/baseOfAdminClickedResume.txt";
        try (
                Scanner scanner = new Scanner(new File(path)).useLocale(Locale.US)) {
            while (scanner.hasNext()) {

                AdminLikedController likedController = new AdminLikedController();

                likedController.inputData(scanner);

//                observableList.add(likedController);
//
//                userId.setCellValueFactory(new PropertyValueFactory<>("numberOfOrder"));
//                userSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
//                catalogTable.setItems(observableList);
//                catalogTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPurchaseDetails(newValue));
            }
        } catch (
                IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void inputData(Scanner scanner) {
        String kent = scanner.nextLine(); //получили всю строку

        ArrayList<AdminLikedController> help = new ArrayList<>();
        String arr[] = kent.split("_");

        for (int i = 0; i < arr.length; i++) {
            AdminLikedController likedController = new AdminLikedController();
            String arr2[] = arr[i].split(",");

            for (int j = 0; j < arr2.length; j++) {
                if (j == 1) likedController.setUserProfessionProfession(arr2[j]);
                if (j == 2) likedController.setUserNameName(arr2[j]);
                if (j == 3) likedController.setUserSalarySalary(Double.parseDouble(arr2[j]));
                if (j == 4) likedController.setUserExpirienceExpirience(Double.parseDouble(arr2[j]));
            }
            help.add(likedController);
        }
        likedKents = help;
    }
    ArrayList<AdminLikedController> likedKents;

}
