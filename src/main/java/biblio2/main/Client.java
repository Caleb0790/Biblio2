package biblio2.main;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Client {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String firstname;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@ManyToOne
	private Book bookPref;
	
	public Book getBookPref() {
		return bookPref;
	}

	public void setBookPref(Book bookPref) {
		this.bookPref = bookPref;
	}

	public List<Book> getBooksAchete() {
		return booksAchete;
	}

	public void setBooksAchete(List<Book> booksAchete) {
		this.booksAchete = booksAchete;
	}

	@ManyToMany
	private List<Book> booksAchete;
	
	@Override
	public String toString() {
		return "Client [name=" + name + "]";
	}

	public Client() {
		super();
	}

	public Client(String n, String f, Gender g, Book b) {
		setName(n);
		setFirstname(f);
		setGender(g);
		setBookPref(b);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getId() {
		return this.id;
	}
}
