package Negocios;

import Datos.Data;

import java.util.LinkedList;

public class DepartmentManager {
    private LinkedList<Department> departments = new LinkedList<>();
    private Data data;

    public DepartmentManager() throws Exception {
        try {
            this.data = Data.getInstance();
            this.data.load();
            if (this.data.get("departments") != null) {
                this.departments = (LinkedList<Department>) this.data.get("departments");
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void add(Department department) throws Exception {
        int position = this.search(department);

        if (position == -1) {
            try {
                this.departments.add(department);
                this.data.add("departments", this.departments);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("El departamento ya existe");
        }
    }

    public void edit(Department department) throws Exception {
        int position = this.search(department);

        if (position != -1) {
            try {
                this.departments.set(position, department);
                this.data.add("departments", this.departments);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró el departamento");
        }
    }

    public void remove(Department department) throws Exception {
        int position = this.search(department);

        if (position != -1) {
            try {
                this.departments.remove(position);
                this.data.add("departments", this.departments);
                this.data.save();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        } else {
            System.out.println("No se encontró el departamento");
        }
    }

    private int search(Department department) {
        for (int i = 0; i <= this.departments.size() - 1; i++) {
            if (this.departments.get(i).getAcronym().equals(department.getAcronym())) {
                return i;
            }
        }
        return -1; // No se encontró el departamento.
    }

    public LinkedList<Department> getDepartments() {
        return this.departments;
    }
}
