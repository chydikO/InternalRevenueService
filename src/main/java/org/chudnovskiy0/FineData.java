package org.chudnovskiy0;

import java.util.HashMap;
import java.util.Map;

public class FineData {
    private final Map<String, String> data = new HashMap<>();

    FineData() {
        data.put("ST_117", "Нарушение установленного порядка взятия на учет (регистрации) в контролирующих органах ( ст. 117 НКУ)");
        data.put("ST_119", "Нарушение порядка подачи информации о физлицах ( ст. 119 НКУ)");
        data.put("ST_120", "Непредоставление или несвоевременное предоставление налоговой отчетности, несоблюдение требований по «самоисправлению» ( ст. 120 НКУ)");
        data.put("ST_1201_1", "Просрочка регистрации НН/РК в ЕРНН ( п. 1201.1 НКУ)");
        data.put("ST_120_2", "Отсутствие регистрации НН/РК в ЕРНН ( п. 1201.2 НКУ)");
        data.put("ST_1201_3", "Ошибки в обязательных реквизитах НН ( п. 1201.3 НКУ)");
        data.put("ST_1202", "Нарушение порядка регистрации акцизных накладных и РК к таким АН в ЕРАН ( ст. 1202 НКУ)");
        data.put("ST_121", "Нарушение сроков хранения учетных документов ( ст. 121 НКУ)");
    }

    public void printAllFineData() {
        System.out.println("\tсписок штрафов: ");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public Map<String, String> getData() {
        return data;
    }

    public String getFineByKey(String key) {
        String value = data.get(key);
        return value != null ? value : "нет такого штрафа";
    }
}
