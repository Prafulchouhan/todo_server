package com.server.todoapp.controller;

import com.server.todoapp.model.Todo;
import com.server.todoapp.model.User;
import com.server.todoapp.repository.TodoRepository;
import com.server.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/user/")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TodoRepository todoRepository;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id){
//        return userRepository.findById(id).orElseThrow(()->new NoSuchElementException());
//    }

    @GetMapping("/{username}")
    public User getUserByUserName(@PathVariable String username){
        return userRepository.findByUsername(username);
    }
    @GetMapping("/{username}/")
    public List<Todo> getTodoByUsername(@PathVariable String username){
        return getUserByUserName(username).getTodoList();
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PostMapping("/{username}/todo")
    public User addTodo(@PathVariable String username,@RequestBody Todo todo){
        Todo todo1=todoRepository.save(todo);
        User user=getUserByUserName(username);
        user.getTodoList().add(todo1);
        return userRepository.save(user);
    }

    @PostMapping("/Todo/{id}")
    public void togelTodo(@PathVariable Long id){
        Todo todo=todoRepository.findById(id).orElseThrow(()->new NoSuchElementException());
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable Long id){
        Todo todo=todoRepository.findById(id).orElseThrow(()->new NoSuchElementException());
        todoRepository.delete(todo);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        User user=userRepository.findById(id).orElseThrow(()->new NoSuchElementException());
        userRepository.delete(user);
    }
}
