package ibf2022.day35workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.day35workshop.model.Book;
import ibf2022.day35workshop.repository.BooksRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

@Controller
@RequestMapping(path = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class BooksController {
    
    @Autowired
    private BooksRepository repository;

    // GET /api/books/search?query=title
    @GetMapping(path = "/search")
    @ResponseBody
    public ResponseEntity<String> getBooksByTitle(@RequestParam String title) {
        List<Book> books = repository.getBooksByTitle(title);
        List<JsonObject> resp = books.stream().map(
            b -> b.toJson()
        ).toList();
        JsonArray arr = Json.createArrayBuilder(resp).build();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(arr.toString());
    }
}
