package GUI.Controlers;

import Negocios.Career;
import Negocios.CareerManager;
import Negocios.Department;
import Negocios.DepartmentManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentControler implements Initializable {
    public Button btnFirst;
    public Button btnPrevious;
    public Button btnNext;
    public Button btnLast;
    public Button btnAdd;
    public Button btnEdit;
    public Button btnRemove;
    public Button btnAccept;
    public Button btnCancel;
    public Label counter;
    public TextField txtName;
    public TextField txtAcronym;

    private Action action = Action.NAVIGATE;
    private DepartmentManager departmentManager = null;
    private int position = -1;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.departmentManager = new DepartmentManager();
            this.controls();
            if (!this.departmentManager.getDepartments().isEmpty()) {
                this.position = 0;
                this.load();
            }
        } catch (Exception ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            msg.show();
        }
    }
    public void clickFirst(ActionEvent actionEvent) {
        this.position = 0;
        this.load();
    }

    public void clickPrevious(ActionEvent actionEvent) {
        this.position -= 1;
        if (this.position < 0) {
            this.position = 0;
        }
        this.load();
    }

    public void clickNext(ActionEvent actionEvent) {
        this.position += 1;
        if (this.position> this.departmentManager.getDepartments().size() - 1) {
            this.position = this.departmentManager.getDepartments().size() - 1;
        }
        this.load();
    }

    public void clickLast(ActionEvent actionEvent) {
        this.position = this.departmentManager.getDepartments().size() - 1;
        this.load();
    }

    public void clickAdd(ActionEvent actionEvent) {
        this.action = Action.ADD;
        this.controls();
    }

    public void clickEdit(ActionEvent actionEvent) {
        this.action= Action.EDIT;
        this.controls();
    }

    public void clickRemove(ActionEvent actionEvent) {
        this.action = Action.REMOVE;
        this.controls();
    }

    public void clickAccept(ActionEvent actionEvent) {
        try {
            Department department = new Department(this.txtAcronym.getText(), this.txtName.getText());
            switch (this.action) {
                case ADD:
                    this.departmentManager.add(department);
                    this.position += 1;
                    break;
                case EDIT:
                    this.departmentManager.edit(department);
                    break;
                case REMOVE:
                    this.departmentManager.remove(department);
                    this.position -= 1;
                    break;
            }
            this.load();
            this.action = Action.NAVIGATE;
            this.controls();
        } catch (Exception ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            msg.show();
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        this.load();
        this.action = Action.NAVIGATE;
        this.controls();
    }

    public void controls() {
        this.txtName.setDisable(true);
        this.txtAcronym.setDisable(true);

        this.btnFirst.setDisable(true);
        this.btnPrevious.setDisable(true);
        this.btnNext.setDisable(true);
        this.btnLast.setDisable(true);

        this.btnAdd.setDisable(true);
        this.btnEdit.setDisable(true);
        this.btnRemove.setDisable(true);

        this.btnAccept.setDisable(true);
        this.btnCancel.setDisable(true);

        switch (this.action) {
            case NAVIGATE:
                this.btnFirst.setDisable(false);
                this.btnPrevious.setDisable(false);
                this.btnNext.setDisable(false);
                this.btnLast.setDisable(false);

                this.btnAdd.setDisable(false);
                this.btnEdit.setDisable(false);
                this.btnRemove.setDisable(false);
                break;
            case ADD:
                this.txtAcronym.setDisable(false);
            case EDIT:
                this.txtName.setDisable(false);
                this.btnAccept.setDisable(false);
                this.btnCancel.setDisable(false);
                break;
            case REMOVE:
                this.btnAccept.setDisable(false);
                this.btnCancel.setDisable(false);
                break;
        }
    }

    public void load() {
        if (this.position < 0) {
            this.txtAcronym.setText("");
            this.txtName.setText("");
            this.counter.setText("0/0");
            return;
        }
        Department department = this.departmentManager.getDepartments().get(position);
        this.txtAcronym.setText(department.getAcronym());
        this.txtName.setText(department.getName());
        this.counter.setText((this.position + 1) + "/" + (this.departmentManager.getDepartments().size()));
    }
}
