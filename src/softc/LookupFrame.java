package softc;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import softc.Book.Column;


public class LookupFrame extends JFrame implements ActionListener, ItemListener{
    private Container pane;
    private JComboBox<String> box = new JComboBox<>();
    private JButton searchButton = new JButton("検索");
    private JButton previousButton = new JButton("←");
    private JButton nextButton = new JButton("→");
    private JButton saveButton = new JButton("検索結果を保存");
    private JLabel resultLabel = new JLabel("0/0");
    private JLabel titlelabel = new JLabel("書店:");
    private JLabel authorlabel = new JLabel("著者:");
    private JLabel publisherLabel = new JLabel("出版社:");
    private JLabel isbnlabel = new JLabel("ISBN:");
    private JLabel yearLabel =  new JLabel("発行年");
    private JTextField searchField = new JTextField();
    private JTextField titleField = new JTextField();
    private JTextField authorField = new JTextField();
    private JTextField publisherField = new JTextField();
    private JTextField isbnField = new JTextField();
    private JTextField yearField = new JTextField();

    private Bookshelf bookShelf = new Bookshelf();
    private ArrayList<Book> books = new ArrayList<>();
    private Column column = Book.Column.TITLE;
    private int index = 0;
    private String wfile;

    public LookupFrame(String title){
        super(title);
        this.setBounds(100, 100, 500, 300);
    }
    /**
     * 初期処理
     * @param bookshelf 本棚データ取り込み 
     */
    public void setup(Bookshelf bookshelf){
        pane = getContentPane();
        GroupLayout layout = new GroupLayout(pane);
        pane.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        box.addItem("書名");
        box.addItem("著者");
        box.addItem("出版社");
        box.addItem("ISBN");
        box.addItem("発行年");
        box.setSelectedIndex(0);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGroup(
            layout.createParallelGroup(Alignment.TRAILING).
            addComponent(box).
            addComponent(titlelabel).
            addComponent(authorlabel).
            addComponent(publisherLabel).
            addComponent(isbnlabel).
            addComponent(yearLabel)
        );
        hGroup.addGroup(
            layout.createParallelGroup(Alignment.TRAILING).
            addGroup(
                layout.createSequentialGroup().
                addComponent(searchField,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE, Short.MAX_VALUE).
                addComponent(searchButton)
            ).
            addComponent(titleField).
            addComponent(authorField).
            addComponent(publisherField).
            addComponent(isbnField).
            addComponent(yearField).
            addGroup(
            	layout.createParallelGroup(Alignment.CENTER).
            	addGroup(
            		layout.createSequentialGroup().
                    addComponent(resultLabel)
            	)
            ).
            addGroup(
                layout.createParallelGroup(Alignment.TRAILING).
                addGroup(
                    layout.createSequentialGroup().
                    addComponent(previousButton).
                    addComponent(nextButton).
                    addComponent(saveButton)
                )
            )
        );
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(
            layout.createParallelGroup(Alignment.BASELINE).
            addComponent(box).
            addComponent(searchField).
            addComponent(searchButton)
        );
        vGroup.addGroup(
            layout.createParallelGroup(Alignment.BASELINE).
            addComponent(titlelabel).
            addComponent(titleField)
        );
        vGroup.addGroup(
            layout.createParallelGroup(Alignment.BASELINE).
            addComponent(authorlabel).
            addComponent(authorField)
        );
        vGroup.addGroup(
            layout.createParallelGroup(Alignment.BASELINE).
            addComponent(publisherLabel).
            addComponent(publisherField)
        );
        vGroup.addGroup(
            layout.createParallelGroup(Alignment.BASELINE).
            addComponent(isbnlabel).
            addComponent(isbnField)
        );
        vGroup.addGroup(
            layout.createParallelGroup(Alignment.BASELINE).
            addComponent(yearLabel).
            addComponent(yearField)
        );
        vGroup.addGroup(
            layout.createParallelGroup(Alignment.BASELINE).
            addComponent(resultLabel)
        );
        vGroup.addGroup(
            layout.createParallelGroup(Alignment.BASELINE).
            addComponent(previousButton).
            addComponent(nextButton).
            addComponent(saveButton)
        );
        searchButton.addActionListener(this);
        searchField.addActionListener(this);
        nextButton.addActionListener(this);
        previousButton.addActionListener(this);
        box.addActionListener(this);
        saveButton.addActionListener(this);

        layout.setVerticalGroup(vGroup);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.bookShelf = bookshelf;
    }
    /**
     * 本の表示（Fieldの状態変化）
     * @param index Allay ID
     */
    public void showBook(int index){        
        if(this.books.size() < 1){
            this.titleField.setText("");
            this.resultLabel.setText("0/0");
            this.authorField.setText("");
            this.publisherField.setText("");
            this.isbnField.setText("");
            this.yearField.setText("");
        }else{
            this.titleField.setText(this.books.get(index).getTitle());
            this.authorField.setText(this.books.get(index).getAuthor());
            this.publisherField.setText(this.books.get(index).getPublisher());
            this.isbnField.setText(this.books.get(index).getISBN());
            this.yearField.setText(Integer.toString(this.books.get(index).getYear()));
            this.resultLabel.setText(index + 1 + "/" + this.books.size());
        }
    }
    /**
     * 状態変更
     */
    public void itemStateChanged(ItemEvent e){
        switch(this.box.getSelectedIndex()){
            case 0 :
                this.column = Book.Column.TITLE;
                break;
            case 1 :
                this.column = Book.Column.AUTHOR;
                break;
            case 2 :
                this.column = Book.Column.PUBLISHER;
                break;
            case 3 :
                this.column = Book.Column.ISBN;
                break;
            case 4 :
                this.column = Book.Column.YEAR;
                break;
        }
    }
    /**
     * 制御関係
     */
    public void actionPerformed(ActionEvent e){
        itemStateChanged(null);
        if(e.getSource() == searchButton || e.getSource() == searchField){
            this.index = 0;            
            books = bookShelf.lookup(this.column,this.searchField.getText());
            showBook(this.index);
        }
        if(e.getSource() == nextButton){
            if(this.index < this.books.size() - 1)
                this.index++;
            else{
                index = 0;
            }
            showBook(this.index);
        }
        else if(e.getSource() == previousButton){
            if(this.index > 0) 
                this.index--;
            else{
                index = this.books.size() - 1;
            }
            showBook(this.index);            
        }
        if(e.getSource() == saveButton){
            export("foundbook.txt");
           } 
    }
    void export(String filename){
        int count = 0;
        if(this.books.size() == 0){
            count = 0;
        }else{
        try(BufferedWriter buffWriter = new BufferedWriter(new FileWriter(filename))){
            for(Book b: books){
                count++;
                this.wfile = b.toString();
                buffWriter.write(this.wfile);
                if(this.books.size() != count)
                    buffWriter.newLine();
            }}
        catch(IOException e){
                System.out.println("書き込みエラー");
        }
        catch(Exception e){
                System.out.println("終了処理エラー");
            }
        }
    }
}

