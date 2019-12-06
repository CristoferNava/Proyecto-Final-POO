package Negocios;

import java.io.Serializable;

public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    private String NUE;
    private String name;
    private String firstLastName;
    private String secondLastName;

    public Teacher() {}

    public Teacher(String NUE, String name, String firstLastName, String secondLastName) {
        this.NUE = NUE;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
    }

    public String getNUE() {
        return NUE;
    }

    public void setNUE(String NUE) {
        this.NUE = NUE;
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

    @Override
    public String toString() {
        return this.name;
    }
}
