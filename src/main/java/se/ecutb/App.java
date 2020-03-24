package se.ecutb;


import se.ecutb.data.DataStorage;
import se.ecutb.model.Gender;
import se.ecutb.model.Person;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class App
{

    private static DataStorage dataStorage;

    static {
        dataStorage = DataStorage.INSTANCE;
    }

    public static void main( String[] args )
    {
        //one();
        //two();
        //three();
        //four();
        //five();
        //six();
        //seven();
        //eight();
        //nine();
        //ten();
        //eleven();
        //twelve();
        //thirteen();
    }

    public static void one() {
        List<Person> erik = dataStorage.findMany(person -> person.getFirstName().equals("Erik"));

        for (Person person: erik) {
            System.out.println(person.toString());
        }
    }

    public static void two() {
        List<Person> females = dataStorage.findMany(person -> person.getGender().equals(Gender.FEMALE));

        for (Person person: females) {
            System.out.println(person.toString());
        }
    }

    public static void three() {
        List<Person> born = dataStorage.findMany(person -> person.getBirthDate().isAfter(LocalDate.parse("1999-12-31")));

        for (Person person: born) {
            System.out.println(person.toString());
        }
    }

    public static void four() {
        System.out.println(dataStorage.findOne(person -> person.getId() == 123).toString());
    }

    public static void five() {
        System.out.println(dataStorage.findOneAndMapToString(
                person -> person.getId() == 456,
                person -> "Name: " + person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate()
                )
        );
    }

    public static void six() {
        List<String> result = dataStorage.findManyAndMapEachToString(
                person -> person.getFirstName().startsWith("E"),
                person -> person.getFirstName() + " " + person.getLastName()
        );

        for (String s: result) {
            System.out.println(s);
        }
    }

    public static void seven() {
        List<String> result = dataStorage.findManyAndMapEachToString(
                person -> Period.between(person.getBirthDate(), LocalDate.now()).getYears() < 10,
                person -> person.getFirstName() + " " + person.getLastName() + " " + Period.between(person.getBirthDate(), LocalDate.now()).getYears() + " years"
        );

        for (String s:result) {
            System.out.println(s);
        }
    }

    public static void eight() {
        dataStorage.findAndDo(
                person -> person.getFirstName().equals("Ulf"),
                person -> System.out.println(person.toString())
        );
    }

    public static void nine() {
        dataStorage.findAndDo(
                person -> person.getLastName().contains(person.getFirstName()),
                person -> System.out.println(person.toString())
        );
    }

    public static void ten() {
        dataStorage.findAndDo(
                person -> person.getFirstName().equalsIgnoreCase(new StringBuilder(person.getFirstName()).reverse().toString()),
                person -> System.out.println(person.getFirstName() + " " + person.getLastName())
        );
    }

    public static void eleven() {
        List<Person> result = dataStorage.findAndSort(person -> person.getFirstName().startsWith("A"),
                Comparator.comparing(person -> person.getBirthDate())
        );

        for (Person person : result) {
            System.out.println(person.toString());
        }
    }

    public static void twelve() {
        List<Person> result = dataStorage.findAndSort(person -> person.getBirthDate().isBefore(LocalDate.ofYearDay(1950, 1)),
                Collections.reverseOrder(Comparator.comparing(person -> person.getBirthDate()))
        );

        for (Person person : result) {
            System.out.println(person.toString());
        }
    }

    public static void thirteen() {
        List<Person> result = dataStorage.findAndSort(Comparator.comparing((Person person) -> person.getLastName())
                .thenComparing(person -> person.getFirstName())
                .thenComparing(person -> person.getBirthDate())
        );

        for (Person person: result) {
            System.out.println(person.toString());
        }
    }
}
