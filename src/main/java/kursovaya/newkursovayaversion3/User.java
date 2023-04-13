package kursovaya.newkursovayaversion3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String login;
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User() throws FileNotFoundException {
    }



    public ArrayList<User> fillTheUser() throws FileNotFoundException {
        ArrayList<User> students = new ArrayList<>();
        Scanner fileProp = new Scanner(new File("C://Users//Bulat//IdeaProjects//NewKursovayaVersion3//baseForReg.txt")); // в файле храню лог пароль
        while (fileProp.hasNext()) {
            User user = new User();
            user.inputUserParams(fileProp);
            students.add(user);
        }
        return students;
    }


    public void printUserParams(User user) {
        String filePath = ("C://Users//Bulat//IdeaProjects//NewKursovayaVersion3//baseForReg.txt");
        String info = "\n" + user.getLogin() + " " + user.getPassword();

        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(info);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(User user) throws IOException {
        String filePath = ("C://Users//Bulat//IdeaProjects//NewKursovayaVersion3//baseForReg.txt");
        String text ="\n"+ user.getLogin()+" "+user.getPassword();

        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }


    public void inputUserParams(Scanner fileProp) {
        setLogin(fileProp.next());
        setPassword(fileProp.next());
    }

    public boolean comparison(User user, ArrayList<User> students) { // потом дописать. тут будет сравнение
        boolean flag = false;
        for (User e : students) {
            if (e.getLogin().equals(user.getLogin()) && e.getPassword().equals(user.getPassword())) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
