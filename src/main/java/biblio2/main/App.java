package biblio2.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import biblio2.DAO.DatabaseHelper;

public class App {
	public static void main(String[] args) {

		List<Book> bl = new ArrayList<>();
		bl.add(new Book("Les aventures de Julie", "Tulie TANE"));
		bl.add(new Book("Les titis", "Paul TITI"));
		bl.add(new Book("Tutu et moi", "Marc MOI"));
		bl.add(new Book("To toto or not to toto", "Tata TOTO"));

		List<Client> cl = new ArrayList<>();
		cl.add(new Client("Julie", "TAMI", Gender.FEMME, bl.get(0)));
		cl.add(new Client("Jérémy", "TUWALY", Gender.HOMME, bl.get(1)));

		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		for (Book book : bl)
			em.persist(book);

		for (Client client : cl)
			em.persist(client);

		DatabaseHelper.commitTxAndClose(em);

		/* ============================================================== */
		em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		cl.get(0).setBooksAchete(Arrays.asList(bl.get(0), bl.get(1), bl.get(2)));
		cl.get(1).setBooksAchete(Arrays.asList(bl.get(0), bl.get(3)));

		em.merge(cl.get(0));
		em.merge(cl.get(1));
		DatabaseHelper.commitTxAndClose(em);

		
		
		System.out.println("====================================================================================================");
		em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		TypedQuery<Book> query = em.createQuery(
				"SELECT b " + "FROM Book b " + "left join fetch b.clientsAchete c " + "where c.id=:id", Book.class);
		query.setParameter("id", cl.get(1).getId());

		List<Book> bookl = query.getResultList();
		DatabaseHelper.commitTxAndClose(em);

		System.out.println(cl.get(1).getName() + " à acheté:");

		for (Book book : bookl) {
			System.out.println(book.toString());
		}

		
		System.out.println("====================================================================================================");
		em = DatabaseHelper.createEntityManager();

		TypedQuery<Client> query1 = em.createQuery(
				"SELECT c FROM Client c left join fetch c.booksAchete b where b.id=:id", Client.class);
		query1.setParameter("id", bl.get(1).getId());

		List<Client> clientl = query1.getResultList();
		em.close();

		System.out.println("\""+bl.get(1).getTitle() + "\" à été acheté par:");

		for (Client client : clientl) {
			System.out.println(client.toString());
		}
		
		
		System.out.println("====================================================================================================");
		em = DatabaseHelper.createEntityManager();

		TypedQuery<Book> query2 = em.createQuery(
				"SELECT distinct b FROM Book b inner join fetch b.clientsAchete c ", Book.class);

		List<Book> bookl2 = query2.getResultList();
		em.close();

		for (Book book : bookl2) {
			System.out.println(book.toString() + " à été acheté");
		}
		
	}
}
