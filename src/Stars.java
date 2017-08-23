
public class Stars {
	private int id;
	private String first_name;
	private String last_name;
	private String date;
	private String photo_url;
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDate() {
		return date;
	}
	public String getFullName() {
		return first_name + " " + last_name;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
