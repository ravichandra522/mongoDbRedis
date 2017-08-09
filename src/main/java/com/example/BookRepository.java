package com.example;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface BookRepository  extends MongoRepository<Book, String>
{

@CacheEvict (value = "book", key = "#title")
	Book findByTitle(String title);
    void delete(String title);
}
