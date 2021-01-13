package pl.javastart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.javastart.dao.BookDao;
import pl.javastart.model.Book;

@Configuration
@ComponentScan
public class SpringJpaApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringJpaApplication.class);
        BookDao bookDao = context.getBean(BookDao.class);

        //zapisujemy
        Book book = new Book("1234567890468", "Spring is so cool", "Javastart");
        bookDao.save(book);

        //odczyt
        Book book1 = bookDao.get(1L);
        System.out.println(book1);
        context.close();
    }


}
