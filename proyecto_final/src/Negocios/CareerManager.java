package Negocios;

import Datos.Data;

import java.util.LinkedList;

public class CareerManager {
    private LinkedList<Career> careers = new LinkedList<>();
    private Data data;

    public CareerManager() throws Exception {
        try {
            this.data = Data.getInstance();
            this.data.load();
            if (this.data.get("careers") != null) {
                this.careers = (LinkedList<Career>) this.data.get("careers");
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void add(Career career) throws Exception {
        int position = this.search(career);

        if (position == -1) {
            try {
                this.careers.add(career);
                this.data.add("careers", this.careers);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("La carrera ya existe");
        }
    }

    public void edit(Career career) throws Exception {
        int position = this.search(career);

        if (position != -1) {
            try {
                this.careers.set(position, career);
                this.data.add("careers", this.careers);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró la carrera");
        }
    }

    public void remove(Career career) throws Exception {
        int position = this.search(career);

        if (position != -1) {
            try {
                this.careers.remove(position);
                this.data.add("careers", this.careers);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró la carrera");
        }
    }

    private int search(Career career) {
        for (int i = 0; i <= this.careers.size() - 1; i++) {
            if (this.careers.get(i).getAcronym().equals(career.getAcronym())) {
                return i;
            }
        }
        return -1; // No se encontró la carrera.
    }

    public LinkedList<Career> getCareers() {
        return this.careers;
    }
}