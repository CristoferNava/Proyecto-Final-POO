package Negocios;

import java.io.Serializable;

public class Career implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String acronym;

    public Career() {}

    public Career(String name, String acronym) {
        this.name = name;
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
