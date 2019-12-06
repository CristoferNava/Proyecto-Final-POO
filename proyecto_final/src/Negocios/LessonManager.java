package Negocios;

import Datos.Data;

import java.util.LinkedList;

public class LessonManager {
    private LinkedList<Lesson> lessons = new LinkedList<>();
    private Data data;

    public LessonManager() throws Exception {
        try {
            this.data = Data.getInstance();
            this.data.load();
            if (this.data.get("lessons") != null) {
                this.lessons = (LinkedList<Lesson>) this.data.get("lessons");
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void add(Lesson lesson) throws Exception {
        int position = this.search(lesson);

        if (position == -1) {
            try {
                this.lessons.add(lesson);
                this.data.add("lessons", this.lessons);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("La lección ya existe");
        }
    }

    public void edit(Lesson lesson) throws Exception {
        int position = this.search(lesson);

        if (position != -1) {
            try {
                this.lessons.set(position, lesson);
                this.data.add("lessons", this.lessons);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró la clase");
        }
    }

    public void remove(Lesson lesson) throws Exception {
        int position = this.search(lesson);

        if (position != -1) {
            try {
                this.lessons.remove(position);
                this.data.add("lessons", this.lessons);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró la clase");
        }
    }

    private int search(Lesson lesson) {
        for (int i = 0; i <= this.lessons.size() - 1; i++) {
            if (this.lessons.get(i).getLessonID().equals(lesson.getLessonID())) {
                return i;
            }
        }
        return -1; // No se encontró la clase.
    }

    public LinkedList<Lesson> getLessons() {
        return this.lessons;
    }
}
