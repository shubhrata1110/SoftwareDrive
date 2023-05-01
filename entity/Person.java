package entity;

import com.mysql.cj.protocol.a.SqlDateValueEncoder;

import java.io.Serializable;

public class Person implements Serializable {
    private String userName;
    private String name;
    public Person(String userName, String name){
        this.userName=userName;
        this.name=name;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }
}
