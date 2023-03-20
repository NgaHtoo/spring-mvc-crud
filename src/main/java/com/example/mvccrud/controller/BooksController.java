package com.example.mvccrud.controller;

import com.example.mvccrud.entity.Book;
import com.example.mvccrud.service.BookService;
import com.example.mvccrud.entity.Author;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BooksController {
    @Autowired
    private BookService bookservice;
    @GetMapping({"/","/home"})
    public ModelAndView index(ModelMap modelMap){
       return new ModelAndView("books","books",bookservice.listBooks());
    }
    @GetMapping("/author-form")
    public String authorForm(Model model){
        model.addAttribute("author",new Author());
        return "author-form";
    }
    @PostMapping("/author-form")
    public String saveAuthor(@Valid Author author, BindingResult result){

        if (result.hasErrors()){
            return "author-form";
        }
        bookservice.saveAuthor(author);
        return "redirect:/authors";

    }
    @GetMapping("/authors")
    public String listAuthor(Model model){
        model.addAttribute("authors",bookservice.listAuthors());
        return "authors";

    }
    @GetMapping("/book-form")
    public String bookForm(Model model){
        model.addAttribute("authors",bookservice.listAuthors());
        model.addAttribute("book",new Book());
        return "book-form";
    }
    @PostMapping("/book-form")
    public String saveBook(@Valid Book book, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
          model.addAttribute("authors",bookservice.listAuthors());
            return "book-form";
        }
        bookservice.saveBook(book);
        redirectAttributes.addFlashAttribute("success",true);

        return "redirect:/list-books";
    }
    @RequestMapping ("/list-books")
    public String listAllBooks(Model model){
        model.addAttribute("success",model.containsAttribute("success"));
        model.addAttribute("books",bookservice.listBooks());
        return "books";
    }
}
