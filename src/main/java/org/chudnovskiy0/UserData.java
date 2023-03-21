package org.chudnovskiy0;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
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

    public boolean deleteFine(FineData fine) {
        if (fineDataSet.remove(fine)) {
            System.out.println(" Штраф " + fine + " удален ");
            return true;
        }
        System.out.println(" Штраф " + fine + " не существует");
        return false;
    }

    public void printAllFineDataSet() {
        System.out.println(" $$$$$$ Список штрафов $$$$$$");
        fineDataSet.stream().forEach((Consumer<? super Fine>) System.out::println);
        System.out.println(" $$$$$$");
    }

}
