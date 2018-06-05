package tbo.ynov.com.projetandroidtbo.Models;

/**
 * Created by Trax6 on 05/06/2018.
 */

public class User {

    private String login;
    private String password;

    public User(String username, String password){
        this.login = username;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
