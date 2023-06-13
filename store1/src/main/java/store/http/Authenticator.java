package store.http;

import com.sun.net.httpserver.BasicAuthenticator;

public class Authenticator extends BasicAuthenticator {
    private static final String AUTHENTICATION_USERNAME = "admin";
    private static final String AUTHENTICATION_PASSWORD = "password";

    public Authenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals(AUTHENTICATION_USERNAME) && password.equals(AUTHENTICATION_PASSWORD);
    }
}