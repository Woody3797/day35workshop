package ibf2022.csf.day35workshopserver.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.csf.day35workshopserver.model.Book;

@Repository
public class BooksRepository {
    
    @Autowired
    private MongoTemplate template;

    public List<Book> getBooksByTitle(String title) {
        Query query = Query.query(Criteria.where("title").regex(title, "i"));
        query.with(Sort.by(Sort.Direction.DESC, "average_rating")).limit(50);

        return template.find(query, Document.class, "books").stream().map(d -> Book.create(d)).toList();
    }
}