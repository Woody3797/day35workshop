package ibf2022.day35workshop.model;

public class DBQueries {
    
    public static String QUERY = "db.books.find({ title: {$regex: 'title', $options: 'i' }}).projection({ title: 1, bookID: 1, _id: 0}).limit(20).sort({ title: 1 })";
}
