package ru.evasmall.tm;

import java.lang.reflect.Field;
import java.util.*;
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
        //Проверка списка объектов на принадлежность к одному типу.
        for (Object subject : subjects) {
            if (subject.getClass() != clazz) {
                throw new IllegalArgumentException("THE LIST CONTAINS OBJECTS OF DIFFERENT TYPES. FAIL.");
            }
        }

        //Определение свойств всех полей объекта.
        List<List<Description>> listDescriptions = new ArrayList<>();
        for (Object subject : subjects) {
            List<Description> descriptions = new ArrayList<>();
            clazz = subject.getClass();
            //Проверка на родительские классы.
            while (clazz != null) {
                //Структурный разбор полей объекта.
                Field[] fields = clazz.getDeclaredFields();
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
                clazz = clazz.getSuperclass();
            }
            listDescriptions.add(descriptions);
        }
        return listDescriptions;
    }

}
