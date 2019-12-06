package Negocios;

import Datos.Data;

import java.util.LinkedList;

public class StudentManager {
    private LinkedList<Student> students = new LinkedList<>();
    private Data data;

    public StudentManager() throws Exception {
        try {
            this.data = Data.getInstance();
            this.data.load();
            if (this.data.get("students") != null) {
                this.students = (LinkedList<Student>) this.data.get("students");
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void add(Student student) throws Exception {
        int position = this.search(student);

        if (position == -1) {
            try {
                this.students.add(student);
                this.data.add("students", this.students);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("El estudiante ya existe");
        }
    }

    public void edit(Student student) throws Exception {
        int position = this.search(student);

        if (position != -1) {
            try {
                this.students.set(position, student);
                this.data.add("students", this.students);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró al estudiante");
        }
    }

    public void remove(Student student) throws Exception {
        int position = this.search(student);

        if (position != -1) {
            try {
                this.students.remove(position);
                this.data.add("students", this.students);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró al estudiante");
        }
    }

    private int search(Student student) {
        for (int i = 0; i <= this.students.size() - 1; i++) {
            if (this.students.get(i).getNUA().equals(student.getNUA())) {
                return i;
            }
        }
        return -1; // No se encontró al estudiante.
    }

    public LinkedList<Student> getStudents() {
        return this.students;
    }
}
