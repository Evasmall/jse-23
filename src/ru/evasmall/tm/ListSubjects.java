package ru.evasmall.tm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListSubjects {

    public static final Logger logger = Logger.getLogger(ListSubjects.class.getName());

    public static List<List<Description>> getDescription(List<Object> subjects) {
        //Проверка на пустой список объектов.
        if (subjects == null || subjects.isEmpty()) {
            logger.log(Level.SEVERE, "SUBJECT IS EMPTY.");
            return Collections.emptyList();
        }
        //Определение типа объекта.
        Class<?> clazz = subjects.get(0).getClass();
        //Определение свойств всех полей объекта.
        List<List<Description>> listDescriptions = new ArrayList<>();
        for (Object subject : subjects) {
            List<Description> descriptions = new ArrayList<>();
            Class<?> clazzSubject = subject.getClass();
            //Проверка списка объектов на принадлежность к одному типу.
            if (clazzSubject != clazz) {
                throw new IllegalArgumentException("THE LIST CONTAINS OBJECTS OF DIFFERENT TYPES. FAIL.");
            }

            //Проверка на родительские классы.
            while (clazzSubject != null) {
                //Структурный разбор полей объекта.
                Field[] fields = clazzSubject.getDeclaredFields();
                boolean hasValue = true;
                for (Field field : fields) {
                    try {
                        field.setAccessible(true);
                        if (field.get(subject) == null) {
                            hasValue  = false;
                        }
                        descriptions.add(new Description(field.getName(), field.getType().getName(), hasValue));
                    } catch (IllegalAccessException e) {
                        logger.log(Level.SEVERE,e.getMessage());
                    }
                }
                clazzSubject = clazzSubject.getSuperclass();
            }
            listDescriptions.add(descriptions);
        }
        return listDescriptions;
    }

}
