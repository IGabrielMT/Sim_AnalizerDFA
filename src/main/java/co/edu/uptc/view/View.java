package co.edu.uptc.view;

import java.util.Scanner;

public class View {
    Scanner sc = new Scanner(System.in);

    public void showMessage(String message){
        System.out.println(message);
    }
    public String showMessage(String[] messages) {
        for (String message : messages) {
            System.out.println(message + " \n");
        }
        System.out.print("> ");
        return sc.nextLine();
    }
}
