package main;

import Dao.AuthorDAO;
import Dao.BookDAO;
import Domain.Author;
import Domain.Book;
import org.hibernate.Session;
import Utils.UtilsBooks;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Session session = UtilsBooks.getSessionFactory().openSession();

            Author author = new Author("Fiodor", "Dostojevski", 87);
            Author secondAuthor = new Author("Carper", "Lee", 56);
            Author thirdAuthor = new Author("Adam", "Mickiewicz", 78);

            Book book1 = new Book("Anna Karenina", 20.0, LocalDate.of(2015, 3, 7));
            Book book2 = new Book("Karas ir taika", 15.0, LocalDate.of(2000, 6, 10));
            Book book3 = new Book("Idiotas", 20.0, LocalDate.of(2014, 9, 24));
            Book book4 = new Book("Gyvūlių ūkis", 20.0, LocalDate.of(2010, 7, 19));
            Book book5 = new Book("Triumfo arka", 20.0, LocalDate.of(2020, 1, 14));

            // Save to database
            book1.setAuthor(author);
            book2.setAuthor(author);

            book3.setAuthor(secondAuthor);
            book4.setAuthor(secondAuthor);

            book5.setAuthor(thirdAuthor);

            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(author);
                session.save(secondAuthor);
                session.save(thirdAuthor);

                session.save(book1);
                session.save(book2);
                session.save(book3);
                session.save(book4);
                session.save(book5);

                transaction.commit();
                session.close();
            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                ex.printStackTrace();
            }

            //select all
            AuthorDAO authorDAO = new AuthorDAO();
            List<Author> autoriai = authorDAO.getAllAuthors();
            for (Author author1 : autoriai) {
                System.out.println(author1);
            }
            BookDAO booksDAO = new BookDAO();
            List<Book> knygos = booksDAO.getAllBooks();
            for (Book knyga : knygos) {
                System.out.println(knyga);
            }

            //update author
            author = authorDAO.getAuthor(1);
            author.setName("Naujas");
            author.setLastName("Naujausias");
            author.setAge(100);
            authorDAO.updateAuthor(author);

            //delete author
            author = authorDAO.getAuthor(1);
            authorDAO.deletePerson(author);
    }
}