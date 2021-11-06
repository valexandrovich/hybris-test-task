package com.valexa;


import com.valexa.service.menu.MainMenu;
import com.valexa.utils.HibernateUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App  {

    public static void main(String[] args) {

        if (HibernateUtil.checkDbConnection()) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.startMenu();

//            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-app.xml");



        } else {
            System.out.println("Check Database Connection!");
        }
    }


}