package GUI.Controlers;

import Negocios.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SubjectsControler implements Initializable {
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
    public TextField txtSubjectID;
    public TextField txtName;
    public ComboBox<Teacher> cmbTeachers;
    public ComboBox<Career> cmbCareers;
    public ComboBox<Department> cmbDepartments;


    private Action action = Action.NAVIGATE;
    private CareerManager careerManager = null;
    private SubjectManager subjectManager = null;;
    private TeacherManager teacherManager = null;

    private int position = -1;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.careerManager = new CareerManager();
            this.subjectManager = new SubjectManager();
            this.teacherManager = new TeacherManager();

            ObservableList<Career> careersList = FXCollections.observableArrayList();
            ObservableList<Teacher> teachersList = FXCollections.observableArrayList();

            for (Career career : this.careerManager.getCareers()) {
                careersList.add(career);
            }

            for (Teacher teacher : this.teacherManager.getTeachers()) {
                teachersList.add(teacher);
            }

            this.cmbCareers.setItems(careersList);
            this.cmbTeachers.setItems(teachersList);
            this.controls();
            if (!this.subjectManager.getSubjects().isEmpty()) {
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
        if (this.position> this.subjectManager.getSubjects().size() - 1) {
            this.position = this.subjectManager.getSubjects().size() - 1;
        }
        this.load();
    }

    public void clickLast(ActionEvent actionEvent) {
        this.position = this.subjectManager.getSubjects().size() - 1;
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
            Subject subject = new Subject(this.txtSubjectID.getText(), this.txtName.getText(),
                    (Teacher) this.cmbTeachers.getSelectionModel().getSelectedItem(),
                    (Career) this.cmbCareers.getSelectionModel().getSelectedItem());
            switch (this.action) {
                case ADD:
                    this.subjectManager.add(subject);
                    this.position += 1;
                    break;
                case EDIT:
                    this.subjectManager.edit(subject);
                    break;
                case REMOVE:
                    this.subjectManager.remove(subject);
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
        this.txtSubjectID.setDisable(true);
        this.txtName.setDisable(true);
        this.cmbTeachers.setDisable(true);
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
                this.txtSubjectID.setDisable(false);
            case EDIT:
                this.txtName.setDisable(false);
                this.cmbTeachers.setDisable(false);
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
            this.txtSubjectID.setText("");
            this.txtName.setText("");
            this.counter.setText("0/0");
            return;
        }
        Subject subject = this.subjectManager.getSubjects().get(this.position);
        this.txtSubjectID.setText(subject.getSubjectID());
        this.txtName.setText(subject.getName());

        for (int i = 0; i <= this.cmbTeachers.getItems().size() - 1; i++) {
            if (subject.getTeacher().getNUE().equals(this.cmbTeachers.getItems().get(i).getNUE())) {
                this.cmbTeachers.getSelectionModel().select(i);
                break;
            }
        }

        for (int i = 0; i <= this.cmbCareers.getItems().size() - 1; i++) {
            if (subject.getCareer().getAcronym().equals(this.cmbCareers.getItems().get(i).getAcronym())) {
                this.cmbCareers.getSelectionModel().select(i);
                break;
            }
        }
        this.counter.setText((this.position + 1) + "/" + (this.subjectManager.getSubjects().size()));
    }
}
