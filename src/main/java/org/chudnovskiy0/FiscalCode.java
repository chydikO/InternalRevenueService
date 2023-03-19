package org.chudnovskiy0;

import java.util.UUID;

public class FiscalCode {
    private final int id;

    public FiscalCode(FIO fio) {
        this.id = generateUniqueId(fio);
    }

    public int getId() {
        return id;
    }

    private int generateUniqueId(FIO fio) {
        UUID randomUUID = UUID.randomUUID();
        String str = fio.getName() + fio.getSoname() + randomUUID;
        int uid = str.hashCode();
        str = Integer.toString(uid).replaceAll("-", "");
        return Integer.parseInt(str);
    }
}
