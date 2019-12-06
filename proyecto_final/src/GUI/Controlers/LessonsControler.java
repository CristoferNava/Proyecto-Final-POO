package GUI.Controlers;

import Negocios.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LessonsControler implements Initializable {
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
    public TextField txtHour;
    public TextField txtLessonID;
    public ComboBox<Subject> cmbSubjects;
    public ComboBox<Teacher> cmbTeachers;
    public ComboBox<Career> cmbCareers;


    private Action action = Action.NAVIGATE;
    private SubjectManager subjectManager= null;
    private TeacherManager teacherManager = null;
    private CareerManager careerManager = null;
    private LessonManager lessonManager = null;
    private int position = -1;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.subjectManager = new SubjectManager();
            this.teacherManager = new TeacherManager();
            this.careerManager = new CareerManager();
            this.lessonManager = new LessonManager();

            ObservableList<Subject> subjectsList = FXCollections.observableArrayList();
            for (Subject subject : this.subjectManager.getSubjects()) {
                subjectsList.add(subject);
            }

            ObservableList<Teacher> teachersList = FXCollections.observableArrayList();
            for (Teacher teacher : this.teacherManager.getTeachers()) {
                teachersList.add(teacher);
            }

            ObservableList<Career> careersList = FXCollections.observableArrayList();
            for (Career career : this.careerManager.getCareers()) {
                careersList.add(career);
            }

            this.cmbSubjects.setItems(subjectsList);
            this.cmbTeachers.setItems(teachersList);
            this.cmbCareers.setItems(careersList);
            this.controls();
            if (!this.lessonManager.getLessons().isEmpty()) {
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
        if (this.position> this.lessonManager.getLessons().size() - 1) {
            this.position = this.lessonManager.getLessons().size() - 1;
        }
        this.load();
    }

    public void clickLast(ActionEvent actionEvent) {
        this.position = this.lessonManager.getLessons().size() - 1;
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
            Lesson lesson = new Lesson(this.txtLessonID.getText(),
                    (Subject) this.cmbSubjects.getSelectionModel().getSelectedItem(),
                    (Teacher) this.cmbTeachers.getSelectionModel().getSelectedItem(),
                    (Career) this.cmbCareers.getSelectionModel().getSelectedItem(),
                    this.txtHour.getText());
            switch (this.action) {
                case ADD:
                    this.lessonManager.add(lesson);
                    this.position += 1;
                    break;
                case EDIT:
                    this.lessonManager.edit(lesson);
                    break;
                case REMOVE:
                    this.lessonManager.remove(lesson);
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
        this.cmbSubjects.setDisable(true);
        this.cmbTeachers.setDisable(true);
        this.cmbCareers.setDisable(true);
        this.txtHour.setDisable(true);
        this.txtLessonID.setDisable(true);

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
                this.txtLessonID.setDisable(false);
            case EDIT:
                this.cmbSubjects.setDisable(false);
                this.cmbTeachers.setDisable(false);
                this.cmbCareers.setDisable(false);
                this.txtHour.setDisable(false);
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
            this.txtLessonID.setText("");
            this.txtHour.setText("");
            this.counter.setText("0/0");
            return;
        }
        Lesson lesson = this.lessonManager.getLessons().get(this.position);
        this.txtHour.setText(lesson.getHour());
        this.txtLessonID.setText(lesson.getLessonID());

        for (int i = 0; i <= this.cmbSubjects.getItems().size() - 1; i++) {
            if (lesson.getSubject().getSubjectID().equals(this.cmbSubjects.getItems().get(i).getSubjectID())) {
                this.cmbSubjects.getSelectionModel().select(i);
                break;
            }
        }

        for (int i = 0; i <= this.cmbTeachers.getItems().size() - 1; i++) {
            if (lesson.getTeacher().getNUE().equals(this.cmbTeachers.getItems().get(i).getNUE())) {
                this.cmbTeachers.getSelectionModel().select(i);
                break;
            }
        }

        for (int i = 0; i <= this.cmbCareers.getItems().size() - 1; i++) {
            if (lesson.getCareer().getAcronym().equals(this.cmbCareers.getItems().get(i).getAcronym())) {
                this.cmbCareers.getSelectionModel().select(i);
                break;
            }
        }
        this.counter.setText((this.position + 1) + "/" + (this.lessonManager.getLessons().size()));
    }
}
