package tp.appliSpring.bank.persistence.entity;


import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name="Task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="numero")
    private Long numero;

    @Column(name="title" , length = 64)
    private String title;

	private String request;

	private String response; //may be null

	private Date timestamp; //may be null

    private String author; //may be null

	public TaskEntity(Long numero, String title, String request, String response, Date timestamp, String author) {
		this.numero = numero;
		this.title = title;
		this.request = request;
		this.response = response;
		this.timestamp = timestamp;
		this.author = author;
	}

	public TaskEntity(Long numero, String title, String request){
		this(numero, title, request, null, null, null);
	}
	public TaskEntity(){
		this(null, null, null, null, null, null);
	}

	@Override
	public String toString() {
		return "TaskEntity{" +
				"numero=" + numero +
				", title='" + title + '\'' +
				", request='" + request + '\'' +
				", response='" + response + '\'' +
				", timestamp=" + timestamp +
				", author='" + author + '\'' +
				'}';
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}