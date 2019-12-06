package Negocios;

import Datos.Data;

import java.util.LinkedList;

public class SubjectManager {
    private LinkedList<Subject> subjects = new LinkedList<>();
    private Data data;

    public SubjectManager() throws Exception {
        try {
            this.data = Data.getInstance();
            this.data.load();
            if (this.data.get("subjects") != null) {
                this.subjects = (LinkedList<Subject>) this.data.get("subjects");
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void add(Subject subject) throws Exception {
        int position = this.search(subject);

        if (position == -1) {
            try {
                this.subjects.add(subject);
                this.data.add("subjects", this.subjects);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("La materia ya existe");
        }
    }

    public void edit(Subject subject) throws Exception {
        int position = this.search(subject);

        if (position != -1) {
            try {
                this.subjects.set(position, subject);
                this.data.add("subjects", this.subjects);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró la materia");
        }
    }

    public void remove(Subject subject) throws Exception {
        int position = this.search(subject);

        if (position != -1) {
            try {
                this.subjects.remove(position);
                this.data.add("subjects", this.subjects);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró la materia");
        }
    }

    private int search(Subject subject) {
        for (int i = 0; i <= this.subjects.size() - 1; i++) {
            if (this.subjects.get(i).getSubjectID().equals(subject.getSubjectID())) {
                return i;
            }
        }
        return -1; // No se encontró la materia.
    }

    public LinkedList<Subject> getSubjects() {
        return this.subjects;
    }
}
