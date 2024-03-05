package com.codegym.applicationsandlibraries.controller;

import com.codegym.applicationsandlibraries.model.Book;
import com.codegym.applicationsandlibraries.model.User;
import com.codegym.applicationsandlibraries.service.BookService;
import com.codegym.applicationsandlibraries.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;


    @GetMapping("showBook/{id}")
    public ModelAndView addBook(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/book/showBook");
        Optional<Book> book = bookService.findById(id);
        Book book1 = new Book(book.get().getId(), book.get().getName(), book.get().getImage(), book.get().getNumberBook());
        modelAndView.addObject("book", book1);
        modelAndView.addObject("user", new User());
        return modelAndView;
    }


    @PostMapping("save")
    public String addBook(@ModelAttribute("book") Book book) throws Exception {
        Optional<Book> bookFind = bookService.findById(book.getId());
        User user = new User(book);
        Book book1 = new Book(bookFind.get().getId(), bookFind.get().getName(), bookFind.get().getImage(), bookFind.get().getNumberBook());
        if (book1.getNumberBook()>=1){
            book1.setNumberBook(book1.getNumberBook() - 1);
            bookService.save(book1);
            userService.save(user);

            return "redirect:/";
        }else {
            throw new Exception();
        }
    }

    @GetMapping("showWarehouseBookUser")
    public String showWarehouseBookUser(Model model) {
        Iterable<User> listUser = userService.findAll();
        List<User> listUserHaveBooks = new ArrayList<>();
        for (User user : listUser) {
            if (user.getBook() != null) {
                listUserHaveBooks.add(user);
            }
        }
        model.addAttribute("bookUser", listUserHaveBooks);
        return "/user/bookUser";
    }


    @GetMapping("remote/{id}")
    public ModelAndView remote(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/book/showBookRemote");
        Optional<User> user = userService.findById(id);
        modelAndView.addObject("user", user.get());
        return modelAndView;
    }

    @PostMapping("delete")
    public String deleteBook(@ModelAttribute User user) {
        Optional<User> user1 = userService.findById(user.getId());
        int idBook = user1.get().getBook().getId();
        Optional<Book> book = bookService.findById(idBook);
        int numberBook = book.get().getNumberBook() + 1;
        Book book1 = new Book(idBook, book.get().getName(), book.get().getImage(), numberBook);
        bookService.save(book1);
        userService.deleteById(user.getId());
        return "redirect:/";
    }
}
