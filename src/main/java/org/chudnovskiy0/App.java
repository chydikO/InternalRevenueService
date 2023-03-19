package org.chudnovskiy0;

import java.util.*;

/**
 * Реализовать базу данных налоговой инспекции по штрафам. Идентифицировать конкретного человека будет
 * его персональный идентификационный код. У одного человека может быть много штрафов.
 * Реализовать:
 * 1. Полная распечатка базы данных.
 * 2. Распечатка данных по конкретному коду.
 * 3. Распечатка данных по конкретному типу штрафа.
 * 4. Распечатка данных по конкретному городу.
 * 5. Добавление нового человека с информацией о нем.
 * 6. Добавление новых штрафов для уже существующей записи.
 * 7. Удаление штрафа.
 * 8. Замена информации о человеке и его штрафах.
 */
public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<City, User> users = new HashMap<>();
    private static final Set<City> cites = new HashSet<>();
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
                initCity();
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
        String name;
        String soname;

        do {
            System.out.print("Введите имя:\t");
            name = scanner.nextLine();
            System.out.print("Введите фамилию:\t");
            soname = scanner.nextLine();
        } while (name.isBlank() && soname.isBlank());
        FIO fio = new FIO(name, soname);

        // enter city
        for (City value :cites) {
            System.out.println(value.toString());
        }
        City cityByIndex;
        do {
            System.out.print("Введите индекс города:\t");
            final String cityIndex = scanner.nextLine().trim();
            cityByIndex = cites.stream()
                    .filter(city -> cityIndex.equals(Integer.toString(city.getValue())))
                    .findAny()
                    .orElse(null);
        } while (cityByIndex == null);

        User user = new User(fio, new HashSet<>());

        //TODO: isUserPresent() by key and value

        try {
            users.put(cityByIndex, user);
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }

        //TODO: if user have fine -> add or continue;

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

//        for(City city : users.keySet()) {
//            System.out.println(users.get(city) + "->" + city.getValue() + " " + city.getLabel());
//        }

        for (Map.Entry<City, User> entry : users.entrySet()) {
            System.out.println(entry.getKey() + "/");
            System.out.println(entry.getValue().getFio());
            entry.getValue().printAllFineDataSet();
        }
        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.PRINT_DATA.label.length())));
    }


    private static void exit() {
        System.out.println("-= Выход =-");
    }

    private static String menuTitle(String title) {
        return "\t-= " + title + " =-";
    }

    private static void initCity() {
        cites.add(new City("Черновцы", 65000));
        cites.add(new City("Днепропетровск", 53271));
        cites.add(new City("Ивано-Франковск", 77556));
        cites.add(new City("Каменец-Подольский", 32300));
        cites.add(new City("Харьков", 64341));
        cites.add(new City("Херсон", 74830));
        cites.add(new City("Киев", 01001));
        cites.add(new City("Кривой Рог", 50000));
        cites.add(new City("Львов", 79000));
        cites.add(new City("Николаев", 54001));
        cites.add(new City("Одесса", 65000));
    }
}
