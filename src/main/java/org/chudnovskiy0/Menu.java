package org.chudnovskiy0;

public enum Menu {
    PRINT_DATA("1. Полная распечатка базы данных."),
    GET_BY_CODE("2. Распечатка данных по конкретному коду."),
    GET_BY_FINE("3. Распечатка данных по конкретному типу штрафа."),
    GET_BY_CITY("4. Распечатка данных по конкретному городу."),
    ADD_NEW_USER("5. Добавление нового человека с информацией о нем."),
    UPDATE_RECORD("6. Добавление новых штрафов для уже существующей записи."),
    DELETE_FINE("7. Удаление штрафа."),
    REPLACING_PERSON_INFORMATION("8. Замена информации о человеке."),
    EXIT("9. Выход.");

    public final String label;

    Menu(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
