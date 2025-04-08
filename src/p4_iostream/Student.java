package p4_iostream;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 8006830500836692963L;
    private String name;
    private int age;
    public Student() {
    }

    public Student(String name,int age) {
        this.name = name;
        this.age = age;
    }
}
