package org.chudnovskiy0;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class User {
    private FIO fio;
    private List<FineData> fineDataList;

}
