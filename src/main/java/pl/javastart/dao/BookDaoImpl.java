package pl.javastart.dao;

import org.springframework.stereotype.Repository;
import pl.javastart.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

// to samo co component - spring dodaje go do
// konteneru i mozemy go pobierac do zaleznosci
@Repository
public class BookDaoImpl implements BookDao{

    // automatycznie wstrzykiwana zależność
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public BookDaoImpl() {
    }

    @Override
    public void save(Book book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(book);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public Book get(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Book book = entityManager.find(Book.class, id);
        entityManager.close();
        return book;
    }
}
