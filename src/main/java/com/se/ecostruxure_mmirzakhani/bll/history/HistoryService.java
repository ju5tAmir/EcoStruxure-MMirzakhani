package com.se.ecostruxure_mmirzakhani.bll.history;

import com.se.ecostruxure_mmirzakhani.be.Change;

import java.lang.reflect.Field;
import java.util.List;

public class HistoryService {
    /**
     * Note for future version of me: I think you can do it with generic type.
     */
    public static <T> void getChanges(T object){
        Field[] fields = object.getClass().getDeclaredFields();

        // Iterate over fields
        for (Field field: fields){
            System.out.println(field.getName());
        }
        System.out.println(fields.length);

    }


    // Get toString and check with regex
//    public <T> void getContractChanges(T object){;
//    }
}
