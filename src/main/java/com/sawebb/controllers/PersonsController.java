package com.sawebb.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sawebb.models.Person;
import com.sawebb.services.Redis;

import io.lettuce.core.api.StatefulRedisConnection;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

@Validated
@Controller("/persons")
public class PersonsController {

    List<Person> persons = new ArrayList<>();

    @Post
    public Person add(Person person) {
        StatefulRedisConnection<String, String> r = Redis.getConnection();
        Map<String, String> map = new HashMap<>();
        map.put("name", person.getFirstName());
        r.sync().hset("test", map);
        System.out.println("adding person: " + person);
        person.setId(persons.size() + 1);
        persons.add(person);
        return person;
    }

    @Get("/{id}")
    public Optional<Person> findById(Integer id) {
        return persons.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Get
    public List<Person> findAll() {
        return persons;
    }

}