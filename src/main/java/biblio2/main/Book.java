package biblio2.main;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String title;

	@Column
	private String author;

	@OneToMany(mappedBy = "bookPref")
	private List<Client> clientsPref;
	
	@ManyToMany(mappedBy = "booksAchete")
	private List<Client> clientsAchete;

	public Book(String t, String a) {
		setTitle(t);
		setAuthor(a);
	}

	public Book() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Client> getClientsPref() {
		return clientsPref;
	}

	public void setClientsPref(List<Client> clientsPref) {
		this.clientsPref = clientsPref;
	}

	@Override
	public String toString() {
		return "\"" + title + "\" by " + author;
	}
	
	public Long getId() {
		return this.id;
	}
	
}
