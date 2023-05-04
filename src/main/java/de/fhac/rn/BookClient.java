package de.fhac.rn;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import java.io.*;
import java.util.*;

public class BookClient {

    // TODO declare book container
    List<Book> Books = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    // TODO declare bookFile
    File obj = new File("C:\\Users\\Administrateur\\Desktop\\book_serialisirung\\book_serialisirung\\src\\main\\java\\de\\fhac\\rn\\bookFile.json");
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BookClient bookClient = new BookClient();
        int input = -1;
        while (input != 0) {
            System.out.println("Choose");
            System.out.println(" (0) Quit program");
            System.out.println(" (1) Load books from file");
            System.out.println(" (2) Show Books");
            System.out.println(" (3) Add Book");
            System.out.println(" (4) Delete Book");
            System.out.println(" (5) Save books in file");
            input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 0:
                    System.out.println("program exit");
                    break;
                case 1:
                    bookClient.loadBooks();
                    break;
                case 2:
                    bookClient.showBooks();
                    break;
                case 3:
                    bookClient.addBook();
                    break;
                case 4:
                    bookClient.deleteBook();
                    break;
                case 5:
                    bookClient.saveBooks();
                    break;
                default:
                    System.err.println("invalid input");
                    break;
            }
        }
        scanner.close();
    }

    /**
     * shows the books saved in the container given as field
     */
    void showBooks() throws IOException {
        if (Books.isEmpty()){
            System.out.println("The book list is empty");
        } else {
            for (Book b: Books) {

                System.out.println("ISBN : "+b.getISBN());
                System.out.println("Titel : "+b.getTitel());
                System.out.println("Name : "+b.getA().getName());
                System.out.println("Vorname : "+b.getA().getVorname());
                System.out.println("*****");
            }
        }
    }

    /**
     * adds a book to the container given as field
     */
    void addBook() throws IOException {
        // TODO read the necessary infos for creating a instance of book
        Book newBook = new Book();
        Author newAuthor = new Author();
        System.out.print("ISBN Eingeben: ");
        newBook.setISBN(scanner.nextLine());
        System.out.print("Titel Eingeben: ");
        newBook.setTitel(scanner.nextLine());
        System.out.print("Name Eingeben: ");
        newAuthor.setName(scanner.nextLine());
        System.out.print("Vorname Eingeben: ");
        newAuthor.setVorname(scanner.nextLine());
        newBook.setA(newAuthor);
        newBook.setName(newBook.getA().getName());
        newBook.setVorname(newBook.getA().getVorname());

        // TODO add the book to the container

        ObjectOutputStream objout = new ObjectOutputStream((new FileOutputStream(obj)));
        objout.writeObject(newBook);
        objout.close();
        Books.add(newBook);

    }

    /**
     * deletes a book from the container given as field
     */
    void deleteBook() throws IOException {
        // TODO delete book from container via isbn
        System.out.println("Was ist die ISBN : ");
        String ISBN = scanner.nextLine();
        for (Book b: Books){
            System.out.println(b.getISBN());
            if(b.getISBN().equals(ISBN)){
                Books.remove(b);
                break;
            }
        }


    }

    /**
     * save the books into the file given as field
     */
    void saveBooks() throws IOException {
        // TODO
        mapper.writeValue(new File("C:\\Users\\Administrateur\\Desktop\\book_serialisirung\\book_serialisirung\\src\\main\\java\\de\\fhac\\rn\\bookFile.json"), Books);
        System.out.println("Books are saved in a JSON File");
    }

    /**
     * loads the books from the file given as field
     */
    void loadBooks() throws IOException, ClassNotFoundException {
        List <Book> Bookss = mapper.readValue(new File("C:\\Users\\Administrateur\\Desktop\\book_serialisirung\\book_serialisirung\\src\\main\\java\\de\\fhac\\rn\\bookFile.json"),
                new TypeReference<List<Book>>() {});
        Books = Bookss;
        System.out.println(Books);
    }

}
