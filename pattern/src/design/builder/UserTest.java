
public class UserTest {

	public static void main(String[] args) {
		User user = new UserBuilder().age(12).name("zhangsan").build();
		System.err.println(user.toString());
	}

}
