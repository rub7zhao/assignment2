/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author rzhao
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {
    @Context
    private UriInfo context;
    
    @WebMethod(operationName = "listAllBooks")
    public String listAllBooks(){
        try {
            Repository repo = Repository.getInstance(context);
            StringBuilder sb = new StringBuilder();
            for(Book book : repo.list()) {
               sb.append(book.toListString());
            }
            return sb.toString();
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
    
    /**
     *
     * @param Id
     * @return
     */
    @WebMethod(operationName = "getBookInfo")
    public String getBookInfo(@WebParam(name = "Id") Integer Id){
        try{
            Repository repo = Repository.getInstance(context);
            StringBuilder sb = new StringBuilder();
            sb.append(repo.getBook(Id).toString());
            return sb.toString();
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
    
    /**
     *
     * @param Isbn
     * @return
     */
    @WebMethod(operationName = "getBookInfoByISBN")
    public String getBookInfoByISBN(@WebParam(name = "Isbn") String Isbn){
        try{
            Repository repo = Repository.getInstance(context);
            StringBuilder sb = new StringBuilder();
            sb.append(repo.getBookByIsbn(Isbn).toString());
            return sb.toString();
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
    
    /**
     *
     * @param title
     * @param description
     * @param isbn
     * @param author
     * @param publisher
     * @return
     */
    @WebMethod(operationName = "addBook")
    public String addBook(@WebParam(name="title") String title, 
        @WebParam(name="description") String description,
        @WebParam(name="isbn") String isbn,
        @WebParam(name="author") String author,
        @WebParam(name="publisher") String publisher){
        try{
            Repository repo = Repository.getInstance(context);
            Book book = repo.createBook(title, description, isbn, author, publisher);
            return "Added Book: " + book.toString();
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
    
    /**
     *
     * @param Id
     * @param title
     * @param description
     * @param isbn
     * @param author
     * @param publisher
     * @return
     */
    @WebMethod(operationName = "updateBookInfo")
    public String updateBookInfo(@WebParam(name = "Id") Integer Id,
        @WebParam(name = "title") String title, 
        @WebParam(name = "description") String description,
        @WebParam(name = "isbn") String isbn,
        @WebParam(name = "author") String author,
        @WebParam(name = "publisher") String publisher){
        try{
            Repository repo = Repository.getInstance(context);
            if(repo.updateBook(Id, title, description, isbn, author, publisher)){
                return "Book " + Id + " Updated";
            }else{
                return "Book Update Failed";
            }
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
    
    /**
     *
     * @param Id
     * @return
     */
    @WebMethod(operationName = "deleteBook")
    public String deleteBook(@WebParam(name = "Id") Integer Id){
        try {
            Repository repo = Repository.getInstance(context);
            if(repo.deleteBook(Id)){
                    return "Book " + Id + " Deleted";
            }else{
                return "Book Delete Failed";
            }
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
}
