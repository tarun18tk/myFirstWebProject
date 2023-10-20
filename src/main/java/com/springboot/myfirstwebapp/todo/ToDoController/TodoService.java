package com.springboot.myfirstwebapp.todo.ToDoController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.myfirstwebapp.todo.Todo;

import jakarta.validation.Valid;

/* 
Concept:
    Basically our Todo Class and Objects can be used using the Services provided by
    TodoService class, and this class is providing you a list of todos objects.
    For demo purpose we have intialised a list on our own and we will be returing that list 
    with the help of a function
 */

//This required to intialise its object in TodoController class
@Service
public class TodoService {
    private static int count = 1;
    private static List<Todo> todos = new ArrayList<>();

    // static variables need to be intialised in static block
    static {
        todos.add(new Todo(++count, "Tarun", "Learn API",
                LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++count, "Tarun", "Wipro API",
                LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++count, "Tarun", "School Dikshant",
                LocalDate.now().plusYears(3), false));
        todos.add(new Todo(++count, "Karan", "School Dikshant",
                LocalDate.now().plusYears(3), false));
        todos.add(new Todo(++count, "Karan", "School Dikshant",
                LocalDate.now().plusYears(3), false));

    }

    // Service class has a method to return a list of Todos.
    public List<Todo> findByUsername(String username) {
        List<Todo> resList = new ArrayList<>();
        for (Todo l : todos) {
            if (l.getUsername().equalsIgnoreCase(username)) {
                resList.add(l);
            }
        }
        return resList;
    }

    public List<Todo> addToDO(String username, String description, LocalDate date, boolean done) {

        todos.add(new Todo(++count, username, description, date, done));

        return todos;

    }

    public void deleteToDo(int id) {
        for (Todo a : todos) {
            if (a.getId() == id) {
                todos.remove(a);
                break;
            }
            count--;
        }
        // return todos;
    }

    public void updateToDo(@Valid Todo todo) {

        deleteToDo(todo.getId());
        count++;
        todos.add(todo);

    }

    public Todo findById(int id) {

        for (Todo a : todos) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

}
