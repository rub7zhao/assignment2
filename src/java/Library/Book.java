/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author rzhao
 */
@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title, description, isbn, author, publisher;

    public Book() {
    }

    /**
     * Creates a new instance of Book
     * @param id
     * @param title
     * @param description
     * @param isbn
     * @param author
     * @param publisher
     */
    
    public Book (Integer id, String title, String description, String isbn, 
            String author, String publisher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public int compareTo(Book other){
        return this.id - other.id;
    }
    
    @Override
    public String toString() {
        return "\n\nId: " + id.toString() + "\nISBN: " + isbn + "\nTitle: " + title + "\nAuthor: " + author + "\nPublisher: " + publisher + "\nDescription: " + description;
    }
    
    public String toListString(){
        return "\n\nId; " + id.toString() + " ISBN: " + isbn + " Title: " + title;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
