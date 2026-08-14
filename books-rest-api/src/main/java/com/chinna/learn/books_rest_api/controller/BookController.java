package com.chinna.learn.books_rest_api.controller;

import com.chinna.learn.books_rest_api.entity.Book;
import com.chinna.learn.books_rest_api.request.BookRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    private void initializeBooks() {
        books.addAll(List.of(
                new Book(1, "Computer Science Pro", "Albert", "Computers",5),
                new Book(2, "Compiler Intro", "Srikanth", "Computers",4),
                new Book(3, "Discrete Maths", "Albert", "math",2),
                new Book(4, "Physical Fitness", "Raghu", "social",3),
                new Book(5, "Mathematics", "Usain", "math",5),
                new Book(6, "Chemistry Pro", "Harish", "chem",1)
        ));
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam (required = false) String category){
       if(category == null)
            return books;
       return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id){
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);

    }

    @PostMapping
    public void createBook(@RequestBody BookRequest bookRequest) {
        long id = books.isEmpty() ? 1:  books.get( books.size() -1 ).getId() +1;
        Book book = convertToBook(id, bookRequest);

        books.add(book);

    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable long id, @RequestBody BookRequest bookRequest){

        for(int i=0; i < books.size(); i++){
            if(books.get(i).getId() == id){
                Book updateBook = convertToBook(id, bookRequest);
                books.set(i, updateBook);
                return;
            }
        }

    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        books.removeIf(book -> book.getId() == id);
    }


    private Book convertToBook(long id, BookRequest bookRequest){
        return new Book(id, bookRequest.getTitle(), bookRequest.getAuthor(),
                bookRequest.getCategory(), bookRequest.getRating());
    }
}
