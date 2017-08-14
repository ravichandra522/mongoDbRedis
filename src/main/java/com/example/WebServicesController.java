package com.example;
import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class WebServicesController {
    @Autowired
    BookRepository repository;
    @Autowired
    MongoTemplate mongoTemplate;
    
    
    @RequestMapping(value="/")
    public String testBook() {
    	
    	return("Hello World");
    	
    }
    
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Book saveBook(Book book) 
    {
        return repository.save(book);
    }
    
    
    @RequestMapping(value = "/book/{title}", method = RequestMethod.GET)
    @Cacheable ("findBookByTitle")
    public String findBookByTitle(@PathVariable String title) 
    {
        Book insertedBook = repository.findByTitle(title);
        return insertedBook.getTitle();

    }
}