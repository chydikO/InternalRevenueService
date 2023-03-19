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

public class User {
    private FIO fio;
    private final int fiscalCode;
    private Set<FineData> fineDataSet;

    public User(FIO fio, HashSet<FineData> fineDataSet) {
        this.fio = fio;
        this.fiscalCode = generateUniqueId();
        this.fineDataSet = fineDataSet;
    }

    public boolean addFine(FineData fine) {
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
        fineDataSet.stream().forEach((Consumer<? super FineData>) System.out::println);
        System.out.println(" $$$$$$");
    }

    private int generateUniqueId() {
        UUID randomUUID = UUID.randomUUID();
        String str = fio.getName() + fio.getSoname() + randomUUID;
        int uid = str.hashCode();
        str = Integer.toString(uid).replaceAll("-", "");
        return Integer.parseInt(str);
    }
}
