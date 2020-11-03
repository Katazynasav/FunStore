package Dao;

import Domain.Author;
import Utils.UtilsBooks;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AuthorDAO {
    public List<Author> getAllAuthors() {
        try{
            Session session = UtilsBooks.getSessionFactory().openSession();
            List<Author> autoriai = session.createQuery("from Author", Author.class).list();
            session.close();
            return autoriai;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Author getAuthor(Integer id){
        try {
            Session session = UtilsBooks.getSessionFactory().openSession();
            Author author = session.find(Author.class, id);
            session.close();
            return author;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateAuthor(Author author) {
        Transaction transaction = null;
        try {
            //create session
            Session session = UtilsBooks.getSessionFactory().openSession();
            //create transaction
            transaction = session.beginTransaction();
            // save person to database
            session.update(author);
            //commit transaction
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
        public void deletePerson(Author author) {
            Transaction transaction = null;
            try {
                transaction = null;
                // create session
                Session session = UtilsBooks.getSessionFactory().openSession();
                // create transaction
                transaction = session.beginTransaction();
                // save person to database
                session.delete(author);

                //commit transaction
                transaction.commit();
                session.close();
            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }
    }







