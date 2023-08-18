package in.fssa.kaithari.model;

public class User {
	    
	private String name;
    private String email;
    private String password;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    
//    @Override
//    public int compareTo(UserEntity other) {
//		return Integer.compare(this.id, other.getId());
//    }

    @Override
    public String toString() {
        return "UserEntity [name=" + name + ", email=" + email + ", password="
                + password + ", id=" + id + "]";
    }



}
