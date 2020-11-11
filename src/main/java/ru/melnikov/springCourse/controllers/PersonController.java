package ru.melnikov.springCourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.melnikov.springCourse.dao.PersonDao;
import ru.melnikov.springCourse.model.Person;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDao personDao;
    @Autowired
    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public String showAll(Model model){
        // Отображение всех пользователей из DAO
        model.addAttribute("people", personDao.showAll());
        return "/people/showAll";
    }
    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id, Model model){
        // Отображение одного пользователя по id из DAO
        model.addAttribute("person", personDao.showOne(id));
        return "/people/showOne";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }
    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") Person person){
        personDao.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDao.showOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personDao.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDao.delete(id);
        return "redirect:/people";
    }
}
