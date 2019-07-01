/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author rzhao
 */
public class Repository {
    private ConcurrentMap<Integer, Book> bookCollection;
    private AtomicInteger mapKey;
    static Repository repo = null;
       
    public Repository(){
        bookCollection = new ConcurrentHashMap<Integer, Book>();
        mapKey = new AtomicInteger();
    }
    
    //create a new book
    public Book createBook(String title, String description, String isbn, String author, String publisher){
        Book book = new Book(mapKey.addAndGet(1), title, description,isbn, author, publisher);
        bookCollection.put(book.getId(), book);
        return book;
    }
    
    //get book by id
    public Book getBook(Integer id) {
        Book book = bookCollection.get(id);
        return book;
    }
    
        
    public List<Book> list() throws Exception {
        ArrayList<Book> bookList = new ArrayList(bookCollection.values());
        for(int i = 0; i < bookList.size(); i++) {
            bookList.set(i, bookList.get(i));
        }
        return bookList;
    }
    
    //update book by id
    public boolean updateBook(Integer id, String title, String description, String isbn, String author, String publisher) {
        Book book = getBook(id);
        boolean updateFlag = false;
        if (book != null) {
            book.setTitle(title);
            book.setDescription(description);
            book.setIsbn(isbn);
            book.setAuthor(author);
            book.setPublisher(publisher);       
            bookCollection.put(id, book);
            updateFlag = true;
        }
        return updateFlag;
    }
    
    //delete book by id
    public boolean deleteBook(Integer id) {
        Book book = getBook(id);
        boolean deleteFlag = false;
        if (book != null) {
            bookCollection.remove(id);
            deleteFlag = true;
        }   
        return deleteFlag;
    }
    
    //get book ID by isbn
    public Book getBookByIsbn(String isbn){
        Integer key = null;
        for(ConcurrentMap.Entry entry : bookCollection.entrySet()){
            Book book = (Book) entry.getValue();
            if(isbn.equals(book.getIsbn())){
                key = book.getId();
            }
        }
        Book iBook = getBook(key);
        return iBook;
    }
    
    //singleton
    private static Repository instance = null;
    
    public static Repository getInstance(UriInfo context){
        return instance == null && context != null? 
            (instance = new Repository()): instance;
    }
}
