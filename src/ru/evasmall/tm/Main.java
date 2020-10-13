package ru.evasmall.tm;

import ru.evasmall.tm.entity.Person;
import ru.evasmall.tm.entity.PersonChild;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        System.out.println("PROGRAM BEGIN.");
        List<Object> subjects  = new ArrayList<>();
        subjects.add(new PersonChild("Василий", "Чапаев", LocalDate.of(1887, 1, 28), "chapaev_vi@gmail.com", 3));
        subjects.add(new PersonChild("Пётр", "Исаев", LocalDate.of(1890, 9, 5), "isaev_ps@gmail.com", 1));
        subjects.add(new PersonChild("Дмитрий", "Фурманов", LocalDate.of(1891, 10, 26), "furmanov_da@gmail.com",1));
        subjects.add(new PersonChild("Анна", null, null, null, 0));
        System.out.println(subjects);

        //Пример работы с однородным списком объектов.
        try {
            for (List<Description> descriptions : ListSubjects.getDescription(subjects)) {
                for (Description description : descriptions) {
                    System.out.println(description);
                }
                System.out.println();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        //Пример выбрасывания исключения IllegalArgumentException при объектах разного типа.
        subjects.clear();
        subjects.add(new PersonChild("Dick", "Send", LocalDate.of(1900, 6, 29), "kapitan@freemail.com", 0));
        subjects.add(new Person("Negoro", null, null, null));
        System.out.println(subjects);
        try {
            ListSubjects.getDescription(subjects);
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        System.out.println("PROGRAM END.");
    }

}
