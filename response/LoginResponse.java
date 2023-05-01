package response;

public class LoginResponse extends Response{
    String userName,firstName,lastName,email;
    int id;
    public LoginResponse(String userName,String firstName, String lastName, String email, int id) {
        this.userName=userName;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.id=id;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
