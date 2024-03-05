package com.codegym.applicationsandlibraries.controller;

import com.codegym.applicationsandlibraries.model.Book;
import com.codegym.applicationsandlibraries.model.BookFile;
import com.codegym.applicationsandlibraries.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookService iBookService;
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private HttpSession httpSession;
    private static int counter=0;
    @RequestMapping("")
    private ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView("/book/index");
        counter++;
//        Cookie cookie = new Cookie("counter",counter.toString());
//        cookie.setMaxAge(30);
//        httpServletResponse.addCookie(cookie);
        Iterable<Book> books=iBookService.findAll();
//        int count = 0;
        // Duyệt qua mảng và đếm số đối tượng không null
//        for (Object obj : books) {
//            if (obj != null) {
//                count++;
//            }
//        }
//        modelAndView.addObject("count",count);
        modelAndView.addObject("books",books);
        modelAndView.addObject("cookie",counter);
        return modelAndView;
    }
//    @RequestMapping("")
//    private ModelAndView show(@CookieValue(value = "counter",defaultValue = "0")Long counter, HttpServletResponse httpServletResponse){
//        ModelAndView modelAndView = new ModelAndView("/book/index");
//        counter++;
//        Cookie cookie = new Cookie("counter",counter.toString());
//        cookie.setMaxAge(30);
//        httpServletResponse.addCookie(cookie);
//        Iterable<Book> books=iBookService.findAll();
//        int count = 0;
//        // Duyệt qua mảng và đếm số đối tượng không null
//        for (Object obj : books) {
//            if (obj != null) {
//                count++;
//            }
//        }
//        modelAndView.addObject("count",count);
//        modelAndView.addObject("books",books);
//        modelAndView.addObject("cookie", cookie.getValue());
//        return modelAndView;
//    }
    @GetMapping("showAdd")
    private ModelAndView showAddBook(){
        ModelAndView modelAndView = new ModelAndView("/book/add");
        modelAndView.addObject("bookFile",new BookFile());
        return modelAndView;
    }
    @PostMapping("save")
    private String saveBook(@ModelAttribute  BookFile bookFile) {
        MultipartFile multipartFile = bookFile.getImage();
        String fileName=multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(bookFile.getImage().getBytes(),new File(fileUpload + fileName));
        }catch (IOException ex){
            ex.printStackTrace();
        }
        Book book = new Book(bookFile.getId(),bookFile.getName(),fileName,bookFile.getNumberBook());
        httpSession.setAttribute("books",book);
        iBookService.save(book);

        return "redirect:/";
    }

    @GetMapping("showLiseBook")
    public ModelAndView showBook(){
        ModelAndView modelAndView = new ModelAndView("/book/info");
        Book book =(Book) httpSession.getAttribute("books");
        modelAndView.addObject("books",book);
        return modelAndView;
    }
    @PostMapping("saveBook")
    private ResponseEntity<Book> saveBook(@RequestBody  Book book) {
        return new ResponseEntity<>(iBookService.save(book), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Book> showById(@PathVariable int id,@RequestBody Book book){
        Optional<Book> book1 = iBookService.findById(id);
        Book book2 = new Book(book1.get().getId(),book1.get().getName(),book1.get().getImage(),book1.get().getNumberBook());
        if (!book1.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book2,HttpStatus.OK);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Book> updateById(@PathVariable int id,@RequestBody Book book){
        Optional<Book> book1 = iBookService.findById(id);

        if (!book1.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iBookService.save(book),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Book> deleteById(@PathVariable int id,@RequestBody Book book){
        Optional<Book> book1 = iBookService.findById(id);
        if (!book1.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iBookService.deleteById(id);
        return new ResponseEntity<>(book1.get(),HttpStatus.OK);
    }
}
