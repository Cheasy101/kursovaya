package kursovaya.newkursovayaversion3;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminLookAtVacancies extends AdminVacancy {
    private int numberOfOrder;
    private int login;
    private String surname;
    private String nextObject;

    private String typeOfWork;
    private Double salary;
    private double expirience;

    public String getNextObject() {
        return nextObject;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setExpirience(double expirience) {
        this.expirience = expirience;
    }


    public Double getSalary() {
        return salary;
    }

    public double getExpirience() {
        return expirience;
    }

    public void setNextObject(String nextObject) {
        this.nextObject = nextObject;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNumberOfOrder(int numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }

    public int getNumberOfOrder() {
        return numberOfOrder;
    }

    public String getSurname() {
        return surname;
    }

    //собсна ща будет метод как мы с такого хитрого файла читаем



//    public void inputData(Scanner scanner) {
//
//
//            setNumberOfOrder(scanner.nextInt());
//            setLogin(scanner.next());
//            setSurname(scanner.next());
//            setTypeOfWork(scanner.next());
//            setSalary(scanner.nextDouble());
//            setExpirience(scanner.nextDouble());
////        adminVacancies.add()
////        setNextObject(scanner.next());
//    }


    public void inputData(Scanner scanner) {

        setNumberOfOrder(scanner.nextInt());
        setLogin(scanner.nextInt());
        setSurname(scanner.next());

        String work = scanner.next();

        ArrayList<AdminLookAtVacancies> help = new ArrayList<>();
        String arr[] = work.split("_");

        for (int i = 0; i < arr.length; i++) {
            AdminLookAtVacancies worker = new AdminLookAtVacancies();
            String arr2[] = arr[i].split(",");

            for (int j = 0; j < arr2.length; j++) {
                if (j == 0) worker.setTypeOfWork(arr2[j]);
                if (j == 1) worker.setSalary(Double.parseDouble(arr2[j]));
                if (j == 2) worker.setExpirience(Double.parseDouble(arr2[j]));
            }
            help.add(worker);
        }
//        toNextObject(scanner.next()); //способ переместиться к другому объекту
        adminVacancies = help;
    }

    ArrayList<AdminLookAtVacancies> adminVacancies;

    String toNextObject;
    public void toNextObject(String toNextObject) {
        this.toNextObject = toNextObject;
    }

}