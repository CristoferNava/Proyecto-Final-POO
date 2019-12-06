package Negocios;

import javafx.fxml.Initializable;

import java.io.Serializable;

public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    private String acronym;
    private String name;

    public Department() {}

    public Department(String acronym, String name) {
        this.acronym = acronym;
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
