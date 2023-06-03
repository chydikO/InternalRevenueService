package org.chudnovskiy0;

import java.util.*;
import java.util.stream.Stream;

/**
 * Реализовать базу данных налоговой инспекции по штрафам. Идентифицировать конкретного человека будет
 * его персональный идентификационный код. У одного человека может быть много штрафов.
 * Реализовать:
 * +1. Полная распечатка базы данных.
 * +2. Распечатка данных по конкретному коду.
 * +3. Распечатка данных по конкретному типу штрафа.
 * 4. Распечатка данных по конкретному городу.
 * +5. Добавление нового человека с информацией о нем.
 * +6. Добавление новых штрафов для уже существующей записи.
 * 7. Удаление штрафа.
 * 8. Замена информации о человеке и его штрафах.
 */
public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<FiscalCode, UserData> users = new HashMap<>();
    private static final Set<City> cites = new HashSet<>();
    private static final String MENU_SEPARATOR = "-";
    private static final FineData fineData = new FineData();

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
                    case 1 -> printUsers(users);
                    case 2 -> getByCode();
                    case 3 -> getByFine();
                    case 4 -> getByCity();
                    case 5 -> addNewUser();
                    case 6 -> UpdateFinesRecord();
                    case 7 -> deleteFine();
                    case 8 -> replacingPersonInformation();
                    case 9 -> exit();
                }
            } catch (Exception ex) {
                System.out.println("Пожалуйста сделайте свой выбор от 1 до " + menu.length);
                scanner.next();
            }
        } while (option > menu.length || option < 1);
        return option;
    }

    //TODO:8. Замена информации о человеке и его штрафах.
    private static void replacingPersonInformation() {

    }


    private static void getByCode() {
        System.out.println(menuTitle(Menu.GET_BY_CODE.label));
        UserData userData = getUserByFiscalCode();
        if (userData != null) {
            System.out.println(userData);
            userData.printAllFineDataSet();
        }
        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.GET_BY_CODE.label.length())));
    }


    private static void getByFine() {
        System.out.println(menuTitle(Menu.GET_BY_FINE.label));

        Map<FiscalCode, UserData> usersWithFine;
        fineData.printAllFineData();
        String fineArticle = getFineArticle();

        if (fineData.getData().containsKey(fineArticle)) {
            usersWithFine = new HashMap<>();
            for (Map.Entry<FiscalCode, UserData> entry : users.entrySet()) {
                if (entry.getValue().getFineDataSet().stream().filter(e -> e.getId().equals(fineArticle))
                        .findFirst()
                        .orElse(null) != null) {
                    usersWithFine.put(entry.getKey(), entry.getValue());
                }
            }
            printUsers(usersWithFine);

        } else {
            System.out.println("Код штрафа не найден");
        }
        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.GET_BY_FINE.label.length())));
    }

    //TODO:4. Распечатка данных по конкретному городу.
    private static void getByCity() {
        System.out.println(menuTitle(Menu.GET_BY_CITY.label));
        Map<FiscalCode, UserData> usersData = getUsersByCity();
        if (usersData != null) {
            //TODO: print Map<FiscalCode, UserData> usersData
            usersData.forEach((key, value) -> System.out.println(key.getId() + " : " + value.toString()));
        } else {
            System.out.println("в городе: нет штрафов");
        }
        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.GET_BY_CITY.label.length())));
    }

    private static Map<FiscalCode, UserData> getUsersByCity() {
        City inputCity = getCityByIndex();
        Map<FiscalCode, UserData> usersByCity = new HashMap<>();
        for (UserData userData: users.values()) {
            if (userData.getCity() == inputCity) {
                FiscalCode userFiscalCode = getKeyByValue(users, userData);
                usersByCity.put(userFiscalCode, userData);
            }
        }
        return usersByCity;
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);
    }

    private static void addNewUser() {
        System.out.println(menuTitle(Menu.ADD_NEW_USER.label));
        UserData userData;
        FIO fio = getFioFromConsole();

        //TODO: if user have fine -> add or continue;
        System.out.println("-=??? Добавить штрафы для пользователя ???=-");
        System.out.println("-=??? Y - да, любая буква - НЕТ ???=-");

        if (scanner.nextLine().equals("Y".toLowerCase())) {
            Set<Fine> fineSet = getFinesFromConsole();
            userData = new UserData(fio, getCityByIndex(), fineSet);
        } else {
            userData = new UserData(fio, getCityByIndex(), new HashSet<>());
        }

        try {
            users.put(new FiscalCode(fio), userData);
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.ADD_NEW_USER.label.length())));
    }

    //Добавление новых штрафов для уже существующей записи.
    private static void UpdateFinesRecord() {
        System.out.println(menuTitle(Menu.UPDATE_RECORD.label));
        //TODO: по налоговому номеру взять user и добавить ему штраф
        UserData userData = getUserByFiscalCode();
        if (userData != null) {
            System.out.println("-=??? Добавить штрафы для пользователя ???=-");
            System.out.println("-=??? Y - да, любая буква - НЕТ ???=-");

            if (scanner.nextLine().equals("Y".toLowerCase())) {
                Set<Fine> fineSet = getFinesFromConsole();
                if (userData.getFineDataSet().addAll(fineSet)) {
                    System.out.println("Штрафы добавлены");
                } else {
                    System.out.println("!!! Штрафы НЕ добавлены !!!");
                }
            }
        } else {
            System.out.println("нет такого пользователя");
        }

        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.UPDATE_RECORD.label.length())));
    }

    //TODO: 7. Удаление штрафа.
    private static void deleteFine() {
        System.out.println(menuTitle(Menu.DELETE_FINE.label));
        System.out.println("-=??? Введите ID пользователя ???=-");
        System.out.println("-=??? Y - да, любая буква - НЕТ ???=-");

        if (scanner.nextLine().equals("Y".toLowerCase())) {

        } else {
        }

        System.out.println(menuTitle(MENU_SEPARATOR.repeat(Menu.DELETE_FINE.label.length())));
    }

    private static void printUsers(Map<FiscalCode, UserData> users) {
        System.out.println(menuTitle(Menu.PRINT_DATA.label));
        for (Map.Entry<FiscalCode, UserData> entry : users.entrySet()) {
            System.out.println("\tНалоговый номер: " + entry.getKey().getId());
            System.out.println(entry.getValue().getFio());
            System.out.println(entry.getValue().getCity().toString());
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

    private static City getCityByIndex() {
        printCites();
        City cityByIndex;
        do {
            System.out.print("Введите индекс города:\t");
            final String cityIndex = scanner.nextLine().trim();
            cityByIndex = cites.stream()
                    .filter(city -> cityIndex.equals(Integer.toString(city.getValue())))
                    .findAny()
                    .orElse(null);
        } while (cityByIndex == null);
        return cityByIndex;
    }

    private static UserData getUserByFiscalCode() {
        System.out.print("Введите налоговый код:\t");
        //TODO: проверка на то что введено число, если нет - печать ошибки и выход
        final int inputFiscalCode = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        Set<FiscalCode> fiscalCodeSet = users.keySet();
        UserData userData = null;

        FiscalCode fiscalCode = fiscalCodeSet.stream()
                .filter(code -> code.getId() == inputFiscalCode)
                .findFirst()
                .orElse(null);
        if (fiscalCode != null) {
            userData = users.get(fiscalCode);
        } else {
            System.out.println("нет такого налогоплательщика");
        }
        return userData;
    }

    private static void printCites() {
        for (City value : cites) {
            System.out.println(value.toString());
        }
    }

    private static Set<Fine> getFinesFromConsole() {
        String answer;
        Set<Fine> fineSet = new HashSet<>();
        fineData.printAllFineData();
        do {
            String fineArticle = getFineArticle();
            //TODO: проверка что код такой есть в справочнике
            if (fineData.getData().containsKey(fineArticle)) {
                fineSet.add(new Fine(fineArticle, fineData.getFineByKey(fineArticle)));
            } else {
                System.out.println("Код штрафа не найден");
            }
            System.out.println("Хотите продолжать вводить штрафы? Y - да");
            answer = scanner.nextLine();
        } while (answer.equals("Y".toLowerCase()));
        return fineSet;
    }

    private static FIO getFioFromConsole() {
        String name;
        String soname;
        do {
            System.out.print("Введите имя:\t");
            name = scanner.nextLine();
            System.out.print("Введите фамилию:\t");
            soname = scanner.nextLine();
        } while (name.isBlank() && soname.isBlank());
        return new FIO(name, soname);
    }

    private static String getFineArticle() {
        System.out.println("введите статью штрафа");
        return scanner.nextLine();
    }
}

/*
List<UserData> userDataFromDB = users.entrySet().stream()
                .filter(entry -> entry.getValue().getFineDataSet() == inputFiscalCode)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList()); // or .toList() for Java 16+
 */
//        result = users.keySet().stream()
//                .filter(key -> key.getId() == inputFiscalCode);
//
//        Optional<String> optionalUser = users.entrySet().stream()
//                .filter(key -> key.getKey().getId() == inputFiscalCode)
//                .findFirst().
//
//        if (userDataFromDB.isEmpty()) {
//            System.err.println("No data found");
//            //throw new SpecificException("No data found with the given details");
//        }