package softc;

import java.io.Serializable;

public class Book implements Serializable{
    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private int year;
    
    public enum Column{
        TITLE,
        AUTHOR,
        PUBLISHER,
        ISBN,
        YEAR,
    }
    public String getTitle(){
        return  this.title;
    };
    public String getAuthor(){
        return this.author;
    };
    public String getPublisher(){
        return this.publisher;
    };
    public String getISBN(){
        return this.ISBN;
    };
    public int getYear(){
        return this.year;
    };
    public void setTitle(String title){
        if(title.length() == 0){
            throw new IllegalArgumentException();
        }else{
            this.title = title;
        }
    };
    public void setAuthor(String author){
        if(author.length() == 0){
            throw new IllegalArgumentException();
        }else{
            this.author = author;
        }
    };
    public void setPublisher(String publisher){
        if(publisher.length() == 0){
            throw new IllegalArgumentException();
        }else{
            this.publisher = publisher;
        }
    };
    public void setISBN(String ISBN){
        if(ISBN.length() == 0){
            throw new IllegalArgumentException();
        }else{
            this.ISBN = ISBN;
        }
    };
    public void  setYear(int year){
        if(year <= 0){
            throw new IllegalArgumentException();
        }else{
            this.year = year;
        }
    };

    public Book(String title, String author, String publisher, String ISBN, int year){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.year = year;
    }

    @Override
    public String toString(){
        String rtn;
        rtn = this.author + ", " + this.title + ", "+ this.publisher + ", " +this.ISBN + ", " +Integer.toString(this.year) + ".";
        return rtn;
    }


}
