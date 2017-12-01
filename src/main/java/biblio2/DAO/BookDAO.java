package biblio2.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import biblio2.DAO.DatabaseHelper;
import biblio2.main.Book;

public class BookDAO {
	public  static void create(Book b) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(b);
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public  static void create(List<Book> b) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		
		for (Book book : b)
			em.persist(book);
		
		DatabaseHelper.commitTxAndClose(em);
	}
	
}
