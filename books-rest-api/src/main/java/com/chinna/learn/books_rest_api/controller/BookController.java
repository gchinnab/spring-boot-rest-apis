package com.chinna.learn.books_rest_api.controller;

import com.chinna.learn.books_rest_api.entity.Book;
import com.chinna.learn.books_rest_api.request.BookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name="Books Rest API Endpoints", description = "Operations related to books" )
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

    @Operation(summary = "Get all the books", description = "Get the list of available books")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getBooks(@Parameter(description = "Optional category parameter to filter books") @RequestParam (required = false) String category){
       if(category == null)
            return books;
       return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @Operation(summary = "Get a book by Id", description = "Retrieve a book by specific Id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookById(@Parameter(description = "Id of the Book to get") @PathVariable @Min( value = 1) long id){
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);

    }

    @Operation(summary = "Create a new Book", description = "Add new book to List of Books available")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBook(@Valid @RequestBody BookRequest bookRequest) {
        long id = books.isEmpty() ? 1:  books.get( books.size() -1 ).getId() +1;
        Book book = convertToBook(id, bookRequest);

        books.add(book);

    }

    @Operation(summary = "Update a book", description = "Update the specific details using id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateBook(@Parameter(description = "Id of the Book to be Updated") @PathVariable @Min( value = 1) long id, @Valid @RequestBody BookRequest bookRequest){

        for(int i=0; i < books.size(); i++){
            if(books.get(i).getId() == id){
                Book updateBook = convertToBook(id, bookRequest);
                books.set(i, updateBook);
                return;
            }
        }

    }

    @Operation(summary = "Delete a Book", description = "Remove a book by id from List of available books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@Parameter(description = "Id of the Book to be Deleted") @PathVariable @Min( value = 1) long id){
        books.removeIf(book -> book.getId() == id);
    }


    private Book convertToBook(long id, BookRequest bookRequest){
        return new Book(
                id,
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getCategory(),
                bookRequest.getRating()
        );
    }
}
