package kursovaya.newkursovayaversion3;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class WorkerParams {

    String nameOfWorker;
    String profession;
    Double salary;
    Double workExperience;

    public void setNameOfWorker(String nameOfWorker) {
        this.nameOfWorker = nameOfWorker;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setWorkExperience(Double workExperience) {
        this.workExperience = workExperience;
    }

    public String getNameOfWorker() {
        return nameOfWorker;
    }

    public String getProfession() {
        return profession;
    }

    public Double getSalary() {
        return salary;
    }

    public Double getWorkExperience() {
        return workExperience;
    }

    public WorkerParams(){}

    public void inputData(Scanner scanner){

//        Locale.setDefault(new Locale("ru", "RU"));
        nameOfWorker = scanner.next();
        profession = scanner.next();
        salary = scanner.nextDouble();
        workExperience = scanner.nextDouble();
    }

    /*
    Попробую написать как я понимаю
     */
    public static void changeFileasdasd(ObservableList<WorkerParams> tableNow) throws IOException {
        FileWriter writer = new FileWriter("C://Users//Bulat//IdeaProjects//NewKursovayaVersion3//baseOfWorkers.txt");
        writer.write("");

        for (WorkerParams workParams : tableNow) {

            String profession = workParams.getProfession();
            String nameOfWorker = workParams.getNameOfWorker();
            Double workExperience = workParams.getWorkExperience();
            Double salary = workParams.getSalary();

            writer.write(profession+" "+nameOfWorker+" " + workExperience+" "+salary+"\n");
        }
        writer.close();
    }
    /*
    Вот как я понимаю
     */
//    public static void changeFile(ArrayList<WorkerParams> tableNow) throws IOException {
//        FileWriter writer = new FileWriter("C://Users//Bulat//IdeaProjects//NewKursovayaVersion3//baseOfWorkers.txt");
//        writer.write("");
//
//        for (WorkerParams workParams : tableNow) {
//
//            String profession = workParams.getProfession();
//            String nameOfWorker = workParams.getNameOfWorker();
//            Double workExperience = workParams.getWorkExperience();
//            Double salary = workParams.getSalary();
//
//            writer.write(profession+" "+nameOfWorker+" " + workExperience+" "+salary+"\n");
//        }
//        writer.close();
//    }
}
