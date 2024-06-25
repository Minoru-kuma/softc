package softc;

import java.io.Serializable;
import java.util.ArrayList;

import softc.Book.Column;

public class Bookshelf implements Serializable{ 
    private ArrayList<Book> bs = new ArrayList<>();  
    public void  add(Book book){
        this.bs.add(book);
    }
    public ArrayList<Book> lookup(Column column, String value){
        ArrayList<Book> rtn = new ArrayList<>();
    	switch(column){
            case TITLE:
                for(Book b: bs){
                    String tmptx = b.getTitle();
                    if(tmptx.contains(value)){
                        rtn.add(b);
                    }
                }
                break;
            case AUTHOR:
                for(Book b: bs){
                    String tmptx = b.getAuthor();
                    if(tmptx.contains(value)){
                        rtn.add(b);
                    }
                }
                break;  
            case PUBLISHER:
                for(Book b: bs){
                    String tmptx = b.getPublisher();
                    if(tmptx.contains(value)){
                        rtn.add(b);
                    }
                }
                break;
            case ISBN:
                for(Book b: bs){
                    String tmptx = b.getISBN();
                    if(tmptx.contains(value)){
                        rtn.add(b);
                    }
                }
                break;
            case YEAR:
                try {
                    for(Book b: bs){
                        int tmptx = 0;
                        tmptx = Integer.valueOf(value).intValue();
                        if(tmptx == b.getYear()){
                            rtn.add(b);
                            }
                }
                }catch(NumberFormatException e){
                    for(Book b: bs){
                        String tmptx = String.valueOf(b.getYear());
                        if(tmptx.contains(value)){
                            rtn.add(b);
                        }
                }
                break;
            }
           
    }
 return rtn;
}
}

 