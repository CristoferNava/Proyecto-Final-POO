package GUI.Controlers;

import Negocios.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class ReporteControler implements Initializable {
    @FXML
    private TableView<Lesson> tableReporte;
    private ObservableList<Lesson> lessons;
    private ObservableList<Subject> subjects;
    private ObservableList<Teacher> teachers;
    private ObservableList<Career> careers;
    private LessonManager lessonManager;
    private SubjectManager subjectManager;
    private TeacherManager teacherManager;
    private CareerManager careerManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.lessonManager = new LessonManager();
            this.subjectManager = new SubjectManager();
            this.teacherManager = new TeacherManager();
            this.careerManager = new CareerManager();

            this.lessons = FXCollections.observableArrayList(this.lessonManager.getLessons());
            for (Lesson lesson : this.lessonManager.getLessons()) {
                this.lessonManager.add(lesson);
            }
            this.subjects = FXCollections.observableArrayList(this.subjectManager.getSubjects());
            for (Subject subject : this.subjectManager.getSubjects()) {
                this.subjectManager.add(subject);
            }

            this.teachers = FXCollections.observableArrayList(this.teacherManager.getTeachers());
            for (Teacher teacher : this.teacherManager.getTeachers()) {
                this.teachers.add(teacher);
            }

            this.careers = FXCollections.observableArrayList(this.careerManager.getCareers());
            for (Career career : this.careerManager.getCareers()) {
                this.careers.add(career);
            }

            TableColumn<Lesson, String> lessonID = new TableColumn<>("Identificador");
            lessonID.setCellValueFactory(new PropertyValueFactory<>("lessonID"));

            TableColumn<Lesson, String> hour = new TableColumn<>("Hora");
            hour.setCellValueFactory(new PropertyValueFactory<>("hour"));
            hour.setCellFactory(TextFieldTableCell.<Lesson>forTableColumn());
            hour.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Lesson, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lesson, String> event) {
                    try {
                        ((Lesson) event.getTableView().getItems().get(event.getTablePosition().getRow())).setHour(event.getNewValue());
                        lessonManager.edit(((Lesson) event.getTableView().getItems().get(event.getTablePosition().getRow())));
                    } catch (Exception ex) {

                    }
                }
            });

            TableColumn<Lesson, Subject> subject = new TableColumn<>("Materia");
            subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
            subject.setCellFactory(ComboBoxTableCell.<Lesson, Subject>forTableColumn(this.subjects));
            subject.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Lesson, Subject>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lesson, Subject> event) {
                    try {
                        ((Lesson) event.getTableView().getItems().get(event.getTablePosition().getRow())).setSubject(event.getNewValue());
                        lessonManager.edit(((Lesson) event.getTableView().getItems().get(event.getTablePosition().getRow())));
                    } catch (Exception ex) {

                    }
                }
            });

            TableColumn<Lesson, Teacher> teacher = new TableColumn<>("Maestro");
            teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
            teacher.setCellFactory(ComboBoxTableCell.<Lesson, Teacher>forTableColumn(this.teachers));
            teacher.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Lesson, Teacher>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lesson, Teacher> event) {
                    try {
                        ((Lesson) event.getTableView().getItems().get(event.getTablePosition().getRow())).setTeacher(event.getNewValue());
                        lessonManager.edit(((Lesson) event.getTableView().getItems().get(event.getTablePosition().getRow())));
                    } catch (Exception ex) {

                    }
                }
            });

            TableColumn<Lesson, Career> career = new TableColumn<>("Carrera");
            career.setCellValueFactory(new PropertyValueFactory<>("career"));
            career.setCellFactory(ComboBoxTableCell.<Lesson, Career>forTableColumn(this.careers));
            career.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Lesson, Career>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lesson, Career> event) {
                    try {
                        ((Lesson) event.getTableView().getItems().get(event.getTablePosition().getRow())).setCareer(event.getNewValue());
                        lessonManager.edit(((Lesson) event.getTableView().getItems().get(event.getTablePosition().getRow())));
                    } catch (Exception ex) {

                    }
                }
            });

            tableReporte.getColumns().addAll(lessonID, hour, subject, teacher, career);
            tableReporte.setItems(this.lessons);
            tableReporte.setEditable(true);
        } catch (Exception ex) {

        }
    }
}