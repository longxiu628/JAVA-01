package day3.week04;

import java.util.concurrent.Callable;

public class CallableB implements Callable<Person> {

    Person person = new Person();
    public CallableB(Person person) {
        super();
        this.person = person;
    }


    public Person call() throws Exception {
        person.setName("Hello");
        person.setSchool("Harward");
        return person;
    }
}
