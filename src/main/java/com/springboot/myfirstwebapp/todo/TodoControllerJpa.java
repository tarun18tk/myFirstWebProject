package com.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

// import org.springframework.aot.hint.BindingReflectionHintsRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name") // needs to be mentioned in places where its used
public class TodoControllerJpa {

    @Autowired
    private TodoRepository todoRepository;

    private int count = 1008;
    // either i can initialise the todoService with @Service (Business logic) or
    // @Componend and @Autowired
    // or we can use a parameterized constructor like below but
    // it also requires service/component.
    // TodoControllerJpa(TodoService todoService, TodoRepository todoRepository) {
    // super();
    // this.todoRepository=todoRepository;
    // this.todoService = todoService;
    // }

    // url= list-todos
    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        // findByUserName method returns the list->todos and we can give it any
        // username, but here
        // we need to get rid of hardcoded value and obtain value from the spring
        // security or SessionAttributes model.
        String username = extracted(model);

        // here we are populating the list todos using todoService but now since we want
        // it from the database we will do it using
        // todoRepository and for that we must define a method in our interface
        // List<Todo> todos = todoService.findByUsername(username);
        List<Todo> todos = todoRepository.findByUsername(username);

        // took the list in todos variable and send it to jsp file using model method,
        // with attribute name "todos"
        model.addAttribute("todos", todos);
        // here we are making the attribute info from here to go to jsp
        // later you will see we take attribute info from form to controller here to add
        // obj
        return "listTodos";
    }

    private String extracted(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    /*
     * above we know that we are mapping the todo variable with the form in jsp
     * using to model.put method, add data to model and then that can be acccessed
     * in jsp and thats why if we put values in the above field we also see them in
     * the
     * form as default
     */

    // This handles get request. If only this is mentioned then if we submit the
    // form
    // we will get the same page back because POST method is calling the same method
    // and returnig jsp file thus we get the same jsp file without any task
    // performed....

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        Todo todo = new Todo(1, "default value", "this value is taken from the mapped method in controller",
                LocalDate.now().plusYears(1), true);
        model.put("todo", todo); // here the name should be same as modelAttribute thats in the form in jsp page
        return "todo";
    }

    /*
     * Creating a POST method for the add-todo link because it contains a form
     * and that form gets a data as POST method and then this method will be invoked
     * where we are taking data in using model method.
     */

    /*
     * This is a normal way to add data by taking from the form, now below this we
     * are going to map the coming data to bean of the Todo class and todo class obj
     * will get the value from the form to use that we have made some changes in the
     * todo jsp
     * page, used Spring Form tags to work with this
     */

    /*
     * @RequestMapping(value = "add-todo", method = RequestMethod.POST)
     * public String addNewTodo(@RequestParam String username, @RequestParam String
     * description, @RequestParam int years) {
     * 
     * // return "listTodos"; this gives empty list, coz we havent intialised the
     * list.
     * 
     * // This is also working because we are returning the string here too
     * // that function is populating the list and returning the jsp page
     * 
     * // return listAllTodos(model);
     * 
     * // Another way to redirect is to use the method name
     * 
     * todoService.addToDO(username, description, years);
     * return "redirect:list-todos";
     * }
     * 
     */

    /*
     * Now we wont be taking all the data from form using RequstParam again and
     * again we just take a bean of todo class and add that
     */

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(@Valid Todo todo, BindingResult res, ModelMap model) {
        // this Todo name must match with the form modelAttribute="todo", and any
        // function that is
        // reverting back to todo page must have a todo bean that needs to be validated.
        // String username = (String) model.get("name");

        // here when we return to todo page , we are also taking some error values with
        // us those values can be taken in jsp form using ->
        if (res.hasErrors()) {
            return "todo";
        }
        String username = extracted(model);

        // since we are using Repository we need to populate the Database with todo
        // and so we do
        todo.setUsername(username); // this because bydefault we are not taking username as input
        // to create a todo, here the Todo bean has no username and we are also not
        // defining anything bydefault

        count++;
        todo.setId(count);

        todoRepository.save(todo);

        // todoService.addToDO(username, todo.getDescription(), todo.getTargetDate(),
        // todo.isDone());
        // since the todo object has been mapped with modelAttributes, its properties
        // have been intialised from the form
        // @Valid tag is used to validate any condition that has been applied on any
        // property, if not valid then error jsp page will come with given description.
        // Here we
        // used @Size validation there are many
        // now to make use of this condition that is to make use of not valid and valid
        // answers we can use BindingResult result, so result will be false if not valid
        // and then we can
        // simply go to any other page.
        return "redirect:list-todos";
    }

    /* Delete Method */
    // since in the a href we gave the url that has the parametere included in that
    // as delete-todo?id=${todo.id}
    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id) {

        todoRepository.deleteById(id);

        // todoService.deleteToDo(id);
        // return "listTodos";

        // i could have done this to get the listTodos page but here, in the
        // listTodo.jsp page
        // we required a todos list and that we are gettting from the function
        // listAllTodos because
        // it is usind modelAttribute to map todos list to the jsp page, and thus the
        // jsp form is able to retreive info
        // from the list of todos, but since we are not calling that method and directly
        // going in the HTML page, we are not
        // giving the form the attribute details it needs to show data hence its not
        // showing.
        // so we must return the list to method and for that we need to redirect
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {

        // Todo todo = todoService.findById(id);
        Todo todo = todoRepository.findById(id).get();
        deleteTodo(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    // Spring Boot will use the URL mapping of the controller methods to determine
    // where to submit the form
    // for these two functions the URL seems to be "update-todo"
    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    // @PostMapping("update-todo") //fount this awsome new way to declare that this
    // is post method call on the same url
    public String updateTodo(@Valid Todo todo, BindingResult res, ModelMap model) {
        if (res.hasErrors()) {
            // the todo has been checked for Size() with size min = 5
            return "todo";
        }
        // todoService.updateToDo(todo);

        // here we already have the todo information, i.e. new todo info so now we need
        // to
        // add this to database
        String username = extracted(model);
        todo.setUsername(username);
        todo.setId(count);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

}
