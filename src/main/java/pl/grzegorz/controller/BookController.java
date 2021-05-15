package pl.grzegorz.controller;

//przykładowa metoda API

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.grzegorz.model.Book;
import pl.grzegorz.service.MemoryBookService;

import java.util.List;

@RestController //to jest równoznaczne z tym, że nad każdą metodą daję @ResponseBody
@RequestMapping("/books")
public class BookController {

    @Autowired //żeby przypisało
    private MemoryBookService memoryBookService;

    //http://localhost:8080/books/helloBook
    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping
    public List<Book> bookList() {
        return memoryBookService.getAllBooks();
    }

    @PutMapping
    public Book edit(@RequestBody Book book) {
        return memoryBookService.editBook(book);
    }

    @PostMapping
    public Book add(@RequestBody Book book) {
        return memoryBookService.addNew(book);
    }

    @GetMapping("/{id:\\d+}")
    public Book getId(@PathVariable long id) {
        return memoryBookService.getBookById(id);
    }

    @DeleteMapping("/{id:\\d+}")
    public Book delete(@PathVariable long id) {
        return memoryBookService.deleteBook(id);
    }
}
