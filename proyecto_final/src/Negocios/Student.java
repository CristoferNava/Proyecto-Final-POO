package Negocios;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String NUA;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private Career career;

    public Student() {}

    public Student(String NUA, String name, String firstLastName, String secondLastName, Career career) {
        this.NUA = NUA;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.career = career;
    }

    public String getNUA() {
        return NUA;
    }

    public void setNUA(String NUA) {
        this.NUA = NUA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
