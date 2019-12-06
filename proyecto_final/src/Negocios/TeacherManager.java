package Negocios;

import Datos.Data;

import java.util.LinkedList;

public class TeacherManager {
    private LinkedList<Teacher> teachers = new LinkedList<>();
    private Data data;

    public TeacherManager() throws Exception {
        try {
            this.data = Data.getInstance();
            this.data.load();
            if (this.data.get("teachers") != null) {
                this.teachers = (LinkedList<Teacher>) this.data.get("teachers");
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void add(Teacher teacher) throws Exception {
        int position = this.search(teacher);

        if (position == -1) {
            try {
                this.teachers.add(teacher);
                this.data.add("teachers", this.teachers);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("El profesor ya existe");
        }
    }

    public void edit(Teacher teacher) throws Exception {
        int position = this.search(teacher);

        if (position != -1) {
            try {
                this.teachers.set(position, teacher);
                this.data.add("teachers", this.teachers);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró al profesor");
        }
    }

    public void remove(Teacher teacher) throws Exception {
        int position = this.search(teacher);

        if (position != -1) {
            try {
                this.teachers.remove(position);
                this.data.add("teachers", this.teachers);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró al profesor");
        }
    }

    private int search(Teacher teacher) {
        for (int i = 0; i <= this.teachers.size() - 1; i++) {
            if (this.teachers.get(i).getNUE().equals(teacher.getNUE())) {
                return i;
            }
        }
        return -1; // No se encontró al profesor.
    }

    public LinkedList<Teacher> getTeachers() {
        return this.teachers;
    }
}
