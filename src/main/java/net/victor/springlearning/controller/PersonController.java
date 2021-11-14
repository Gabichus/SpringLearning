package net.victor.springlearning.controller;

import net.victor.springlearning.model.Person;
import net.victor.springlearning.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/people")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> findAllPersons(){
        return personRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Person> getPerson(@PathVariable long id) {
        return personRepository.findById(id);
    }

    @PostMapping()
    public String addPerson(@RequestBody Person person) {

        if(person != null) {
            personRepository.save(person);
            return "Added a person";
        } else {
            return "Request does not contain a body";
        }
    }

    @DeleteMapping("{id}")
    public String deletePerson(@PathVariable("id") long id) {

        if(id > 0) {
            try{
                personRepository.deleteById(id);
                return "Deleted the person.";
            } catch (Exception e){
                System.out.println(e.getMessage());
                return "Cannot delete the person.";
            }
        }
        return "The id is invalid for the person.";
    }

    @PutMapping()
    public String updatePerson(@RequestBody Person person) {
        if(person != null) {
            try{
                personRepository.save(person);
                return "Updated person.";
            }catch (Exception e){
                System.out.println(e.getMessage());
                return "Person not updated";
            }
        } else {
            return "Request does not contain a body";
        }
    }



}
