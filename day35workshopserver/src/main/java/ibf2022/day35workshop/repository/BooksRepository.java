package ibf2022.day35workshop.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.day35workshop.model.Book;

@Repository
public class BooksRepository {
    
    @Autowired
    private MongoTemplate template;

    public List<Book> getBooksByTitle(String title) {
        Query query = Query.query(Criteria.where("title").regex(title, "i"));
        query.with(Sort.by(Sort.Direction.ASC, "title")).limit(50);

        return template.find(query, Document.class, "books").stream().map(d -> Book.create(d)).toList();
    }
}
