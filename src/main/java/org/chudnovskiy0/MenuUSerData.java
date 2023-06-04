package org.chudnovskiy0;

enum MenuUSerData {
    EDIT_NAME("1. Редактировать Имя."),
    EDIT_SONAME("2. Редактировать Фамилию."),
    EDIT_CITY("3. Редактировать Город."),
    EXIT("4. Выход.");

    public final String label;

    MenuUSerData(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
