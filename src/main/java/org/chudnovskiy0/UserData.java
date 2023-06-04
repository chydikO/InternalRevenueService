package org.chudnovskiy0;

import lombok.*;

import java.util.Set;
import java.util.function.Consumer;


@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class UserData {
    private FIO fio;
    private City city;
    private Set<Fine> fineDataSet;

    public boolean addFine(Fine fine) {
        if (fineDataSet.add(fine)) {
            System.out.println(" Штраф " + fine + " добавлен ");
            return true;
        }
        System.out.println(" Штраф " + fine + " уже добавлен");
        return false;
    }

    public void deleteFine(String fineID) {
        Fine fine;
        fine = fineDataSet.stream().filter(e -> e.getId().equals(fineID)).findFirst().orElse(null);
        if (fineDataSet.remove(fine)) {
            System.out.println(" Штраф " + fine + " удален ");
        } else {
            System.out.println(" Штраф не существует");
        }
    }

    public void printAllFineDataSet() {
        System.out.println(" $$$$$$ Список штрафов $$$$$$");
        fineDataSet.forEach((Consumer<? super Fine>) System.out::println);
        System.out.println(" $$$$$$");
    }

}
