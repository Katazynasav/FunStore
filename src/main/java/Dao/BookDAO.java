package Dao;

import Domain.Book;
import Utils.UtilsBooks;
import org.hibernate.Session;

import java.util.List;

public class BookDAO {
    public List<Book> getAllBooks() {
        try{
            Session session = UtilsBooks.getSessionFactory().openSession();
            List<Book> knygos = session.createQuery("from Book", Book.class).list();
            session.close();
            return knygos;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
