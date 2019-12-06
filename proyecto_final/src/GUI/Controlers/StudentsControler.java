package GUI.Controlers;

import Negocios.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsControler implements Initializable {
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
    public TextField txtNUA;
    public TextField txtName;
    public TextField txtFirstLastName;
    public TextField txtSecondLastName;
    public ComboBox<Career> cmbCareers;


    private Action action = Action.NAVIGATE;
    private CareerManager careerManager = null;
    private StudentManager studentManager = null;
    private int position = -1;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.studentManager = new StudentManager();
            this.careerManager = new CareerManager();
            ObservableList<Career> careersList = FXCollections.observableArrayList();
            for (Career career : this.careerManager.getCareers()) {
                careersList.add(career);
            }
            this.cmbCareers.setItems(careersList);
            this.controls();
            if (!this.studentManager.getStudents().isEmpty()) {
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
        if (this.position> this.studentManager.getStudents().size() - 1) {
            this.position = this.studentManager.getStudents().size() - 1;
        }
        this.load();
    }

    public void clickLast(ActionEvent actionEvent) {
        this.position = this.studentManager.getStudents().size() - 1;
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
            Student student = new Student(this.txtNUA.getText(), this.txtName.getText(), this.txtFirstLastName.getText(),
                    this.txtSecondLastName.getText(), (Career) this.cmbCareers.getSelectionModel().getSelectedItem());
            switch (this.action) {
                case ADD:
                    this.studentManager.add(student);
                    this.position += 1;
                    break;
                case EDIT:
                    this.studentManager.edit(student);
                    break;
                case REMOVE:
                    this.studentManager.remove(student);
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
        this.txtNUA.setDisable(true);
        this.txtName.setDisable(true);
        this.txtFirstLastName.setDisable(true);
        this.txtSecondLastName.setDisable(true);
        this.cmbCareers.setDisable(true);

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
                this.txtNUA.setDisable(false);
            case EDIT:
                this.txtName.setDisable(false);
                this.txtFirstLastName.setDisable(false);
                this.txtSecondLastName.setDisable(false);
                this.cmbCareers.setDisable(false);
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
            this.txtNUA.setText("");
            this.txtName.setText("");
            this.txtFirstLastName.setText("");
            this.txtSecondLastName.setText("");
            this.counter.setText("0/0");
            return;
        }
        Student student = this.studentManager.getStudents().get(this.position);
        this.txtNUA.setText(student.getNUA());
        this.txtName.setText(student.getName());
        this.txtFirstLastName.setText(student.getFirstLastName());
        this.txtSecondLastName.setText(student.getSecondLastName());
        for (int i = 0; i <= this.cmbCareers.getItems().size() - 1; i++) {
            if (student.getCareer().getAcronym().equals(this.cmbCareers.getItems().get(i).getAcronym())) {
                this.cmbCareers.getSelectionModel().select(i);
                break;
            }
        }
        this.counter.setText((this.position + 1) + "/" + (this.studentManager.getStudents().size()));
    }
}
