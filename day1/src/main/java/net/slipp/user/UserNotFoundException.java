package net.slipp.user;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 7616451532316195717L;

    public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String arg0) {
		super(arg0);
	}
}
