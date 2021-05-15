package pl.grzegorz.service;

import org.springframework.stereotype.Service;
import pl.grzegorz.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //muszę wpisać tu service bo to jest oznaczenie klasy
public class MemoryBookService {

    private List<Book> books;
    private long tmpId = 4;

    //dodaje ksiązki na start
    public MemoryBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    //daje wszystkie książki
    public List<Book> getAllBooks() {
        return List.copyOf(books);
    }

    //dodaje nową ksiązkę
    public Book addNew(Book book) {
        book.setId(tmpId); //w takiej sytuacji nie ma znaczenia id z konstruktora
        books.add(book);
        tmpId++;
        return book;
    }

    //do obrobienia
    public Book getBookById(long id) {
        Optional<Book> book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst(); //na optionalu zwraca wartość lub nulla
        if (book.isPresent()) {
            return book.get();
        } else {
            return new Book();
        }
    }

    //edytuje tylko wpisane dane
    public Book editBook(Book book) {
        for (Book b : books) {
            if (b.getId().equals(book.getId())) {
                Book tmpBook = getBookById(book.getId());
                if (book.getAuthor() != null) {
                    tmpBook.setAuthor(book.getAuthor());
                }
                if (book.getIsbn() != null) {
                    tmpBook.setIsbn(book.getIsbn());
                }
                if (book.getPublisher() != null) {
                    tmpBook.setPublisher(book.getPublisher());
                }
                if (book.getTitle() != null) {
                    tmpBook.setTitle(book.getTitle());
                }
                if (book.getType() != null) {
                    tmpBook.setType(book.getType());
                }
                book = tmpBook;
            }
        }
        return book;
    }

    //usuwa konkretną książkę
    public Book deleteBook(long id) {
        Book book = getBookById(id);
        books.remove(book);
        return book;
    }
}
