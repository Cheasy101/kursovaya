package kursovaya.newkursovayaversion3;

import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AdminVacancy {

    private String typeOfWork;
    private Double experience;
    private Double salary;

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public Double getExperience() {
        return experience;
    }

    public Double getSalary() {
        return salary;
    }

    public void inputData(Scanner scanner){

//        Locale.setDefault(new Locale("ru", "RU"));
        typeOfWork = scanner.next();
        salary = scanner.nextDouble();
        experience = scanner.nextDouble();
    }

    /*
    Попробую написать как я понимаю
     */
    public static void changeFile(ObservableList<AdminVacancy> tableNow) throws IOException {
        FileWriter writer = new FileWriter("C://Users//Bulat//IdeaProjects//NewKursovayaVersion3//baseOfVacancy.txt");
        writer.write("");

        for (AdminVacancy adminVacancy : tableNow) {
            String typeOfWork = adminVacancy.getTypeOfWork();
            Double salary = adminVacancy.getSalary();
            Double experience = adminVacancy.getExperience();

            writer.write(typeOfWork+" "+salary+" " + experience+"\n");
        }
        writer.close();
    }
}
