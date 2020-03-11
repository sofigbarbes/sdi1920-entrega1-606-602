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
	private String title;
	
	public Post(String fecha, String email, String title, String text) {
		super();
		this.date=fecha;
		this.email=email;
		this.text=text;
		this.title=title;
	}
	
	public Post() {
		
	}
	
//	Date date = Calendar.getInstance().getTime();  
//    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
//    String strDate = dateFormat.format(date);

}
