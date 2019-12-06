package GUI.Controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Home {
    public void scene(String path, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            msg.show();
        }
    }

    public void clickCareers(ActionEvent actionEvent) {
        this.scene("../Views/Careers.fxml", "Carreras");
    }

    public void clickStudents(ActionEvent actionEvent) {
        this.scene("../Views/Students.fxml", "Estudiantes");
    }

    public void clickSubjects(ActionEvent actionEvent) {
        this.scene("../Views/Subjects.fxml", "Materias");
    }

    public void clickTeachers(ActionEvent actionEvent) {
        this.scene("../Views/Teachers.fxml", "Maestros");
    }

    public void clickLessons(ActionEvent actionEvent) {
        this.scene("../Views/Lessons.fxml", "Clases");
    }

    public void clickReporte(ActionEvent actionEvent) {
        this.scene("../Views/Reporte.fxml", "Reporte de Clases");
    }
}