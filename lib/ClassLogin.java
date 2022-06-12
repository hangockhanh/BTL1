package ClassLogin;

public class ClassLogin {
    public String code;
    public String message;
    public Data data;
}

class Data{
    public String access_token;
    public User user;
    public String token_type;
    public Integer expire_in;
    public Long exp;
}
class User{
    public String name;
    public String role;
    public String avatar;
    public String email;
    public String address;
    public String phone;
    public String user_id;
}
