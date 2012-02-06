package net.slipp.user;

public class User {
	private String userId = null;
	private String password = null;
	private String name = null;
	private String email = null;

	public User() {
	}

	public User(String userId, String password, String name, String email) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isMatchPassword(String inputPassword) {
		if (getPassword().equals(inputPassword)) {
			return true;
		} else {
			return false;
		}
	}
}
