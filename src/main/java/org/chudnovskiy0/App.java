package org.chudnovskiy0;

import java.util.*;

/**
 *
 Реализовать базу данных налоговой инспекции по штрафам. Идентифицировать конкретного человека бу-
 дет его персональный идентификационный код. У одного человека может быть много штрафов.
 Реализовать:
 1. Полная распечатка базы данных.
 2. Распечатка данных по конкретному коду.
 3. Распечатка данных по конкретному типу штрафа.
 4. Распечатка данных по конкретному городу.
 5. Добавление нового человека с информацией о нем.
 6. Добавление новых штрафов для уже существующей записи.
 7. Удаление штрафа.
 8. Замена информации о человеке и его штрафах.

 */
public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<User, FineList> users = new TreeMap<>();
    private static final String MENU_SEPARATOR = "-";

    public static void main(String[] args) {
        while (start() != Menu.values().length) ;
    }

    private static int start() {
        final Menu[] menu = Menu.values();
        int option = 1;
        do {
            for (Menu value : menu) {
                System.out.println(value.label);
            }
            try {
                option = scanner.nextInt();
                scanner.nextLine();  // Consume newline left-over

                switch (option) {
                    case 1 -> printUsers();
                    case 2 -> getByCode();
                    case 3 -> getByFine();
                    case 4 -> getByCity();
                    case 5 -> addNewUser();
                    case 6 -> UpdateRecord();
                    case 7 -> deleteFine();
                    case 8 -> exit();
                }
            } catch (Exception ex) {
                System.out.println("Пожалуйста сделайте свой выбор от 1 до " + menu.length);
                scanner.next();
            }
        } while (option > menu.length || option < 1);
        return option;
    }

    private static void getByCode() {
        System.out.println(menuTitle(Menu.GET_BY_CODE.label));

        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.GET_BY_CODE.label.length())));
    }


    private static void getByFine() {
        System.out.println(menuTitle(Menu.GET_BY_FINE.label));

        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.GET_BY_FINE.label.length())));
    }

    private static void getByCity() {
        System.out.println(menuTitle(Menu.GET_BY_CITY.label));

        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.GET_BY_CITY.label.length())));
    }


    private static void addNewUser() {
        System.out.println(menuTitle(Menu.ADD_NEW_USER.label));

        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.ADD_NEW_USER.label.length())));
    }


    private static void UpdateRecord() {
        System.out.println(menuTitle(Menu.UPDATE_RECORD.label));

        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.UPDATE_RECORD.label.length())));
    }

    private static void deleteFine() {
        System.out.println(menuTitle(Menu.DELETE_FINE.label));

        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.DELETE_FINE.label.length())));
    }

    private static void printUsers() {
        System.out.println(menuTitle(Menu.PRINT_DATA.label));

//        for (User user : users) {
//            System.out.println(user);
//        }
        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.PRINT_DATA.label.length())));
    }
    

    private static void exit() {
        System.out.println("-= Выход =-");
    }
    private static String menuTitle(String title) {
        return new StringBuilder().append("\t-= ").append(title).append(" =-").toString();
    }
}
