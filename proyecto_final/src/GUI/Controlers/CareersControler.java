package GUI.Controlers;

import Negocios.Career;
import Negocios.CareerManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CareersControler implements Initializable {
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
    public TextField txtCareerName;
    public TextField txtAcronym;

    private Action action = Action.NAVIGATE;
    private CareerManager careerManager = null;
    private int position = -1;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.careerManager = new CareerManager();
            this.controls();
            if (!this.careerManager.getCareers().isEmpty()) {
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
        if (this.position> this.careerManager.getCareers().size() - 1) {
            this.position = this.careerManager.getCareers().size() - 1;
        }
        this.load();
    }

    public void clickLast(ActionEvent actionEvent) {
        this.position = this.careerManager.getCareers().size() - 1;
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
            Career career = new Career(this.txtCareerName.getText(), this.txtAcronym.getText());
            switch (this.action) {
                case ADD:
                    this.careerManager.add(career);
                    this.position += 1;
                    break;
                case EDIT:
                    this.careerManager.edit(career);
                    break;
                case REMOVE:
                    this.careerManager.remove(career);
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
        this.txtCareerName.setDisable(true);
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
                this.txtCareerName.setDisable(false);
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
            this.txtCareerName.setText("");
            this.counter.setText("0/0");
            return;
        }
        Career career = careerManager.getCareers().get(position);
        this.txtAcronym.setText(career.getAcronym());
        this.txtCareerName.setText(career.getName());
        this.counter.setText((this.position + 1) + "/" + (this.careerManager.getCareers().size()));
    }
}
