
public class UserBuilder {

	private String name;
	private int age;
	
	
	public UserBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	
	public UserBuilder age(int age) {
		this.age = age;
		return this;
	}
	
	
	public User build() {
		User user = new User();
		configure(user);
		return user;
	}

	private void configure(User user) {
		user.setAge(this.age);
		user.setName(this.name);
	}
}
