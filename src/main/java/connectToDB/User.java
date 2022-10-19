package connectToDB;

public class User {
    private String id;
    private String name;
    private String password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void printUserInfo(){
        System.out.println("id : " + id);
        System.out.println("name : "+name);
        System.out.println("password : "+password);
    }
}