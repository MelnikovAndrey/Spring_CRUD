package ru.melnikov.springCourse.dao;

import org.springframework.stereotype.Component;
import ru.melnikov.springCourse.model.Person;

import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDao {
    private static int PERSON_COUNT = 0;
    private final List<Person> personList;

    {
        personList = new ArrayList<>();

        personList.add(new Person(++PERSON_COUNT, "Tom"));
        personList.add(new Person(++PERSON_COUNT, "Bob"));
        personList.add(new Person(++PERSON_COUNT, "Mike"));
        personList.add(new Person(++PERSON_COUNT, "Jack"));
    }

    public List<Person> showAll(){
        return personList;
    }

    public Person showOne(int id){
        return personList.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PERSON_COUNT);
        personList.add(person);
    }

    public void update(int id, Person person){
        Person updatePerson = showOne(id);
        updatePerson.setName(person.getName());
    }

    public void delete(int id){
        personList.removeIf(x->x.getId() == id);
    }
}
