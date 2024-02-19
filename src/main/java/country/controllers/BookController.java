package country.controllers;

import country.dao.BookDao;
import country.dao.PersonDAO;
import country.model.Book;
import country.model.Person;
import country.util.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;
    private final BookValidator bookValidator;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDao bookDao, BookValidator bookValidator, PersonDAO personDAO) {
        this.bookDao = bookDao;
        this.bookValidator = bookValidator;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "books/index.html";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new.html";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/new.html";
        }
        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.showOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDao.update(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id, Model model,
                          @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDao.showOne(id));
        Optional<Person> bookOwner = bookDao.getBookOwner(id);
        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", personDAO.index());
        }

        return "books/one";
    }
    @PatchMapping("/{id}/set_free")
    public String setFree(@PathVariable("id") int id) {
        bookDao.setFree(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookDao.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }

}
