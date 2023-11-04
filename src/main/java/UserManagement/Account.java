package UserManagement;

public class Account {
    private int id;
    private String username;
    private String password;
    Account(String _username, String _password){
        //Optional: @Shafiay set id from database or else remove this data member
        username = _username;
        password = _password;
    }
    public String getUsername(){
        return (username);
    }
    public String getPassword(){
        return (password);
    }
}
