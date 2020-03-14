package socialNetwork.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private long id;
	private String email;
	private String date;
	private String text;
	private String fileName;
	private String title;

	public Post(String email, String fecha, String title, String text, String fileName) {
		super();
		this.date = fecha;
		this.email = email;
		this.text = text;
		this.title = title;
		this.fileName = fileName;
	}

	public Post(String email, String fecha, String title, String text) {
		super();
		this.date = fecha;
		this.email = email;
		this.text = text;
		this.title = title;

	}

	public Post() {

	}

	public String getFileName() {
		return fileName;
	}

	public long getId() {
		return id;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public String getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	public void setDate(String strDate) {
		this.date = strDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	Date date = Calendar.getInstance().getTime();  
//    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
//    String strDate = dateFormat.format(date);

}
