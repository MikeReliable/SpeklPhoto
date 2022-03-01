package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

    File file;
    FileChooser fileChooser = new FileChooser();
    SoSelection selection = new SoSelection();

    @FXML
    private TextField s01;

    @FXML
    private TextField s02;

    @FXML
    private TextField step;

    @FXML
    private TextArea text;

    @FXML
    private TextField index;

    @FXML
    private TextArea locat;

    @FXML
    void open(ActionEvent event) {
        file = fileChooser.showOpenDialog(new Stage());
        locat.clear();
        locat.appendText(file.getAbsolutePath());
    }

    @FXML
    void action(MouseEvent event) {
        text.clear();
        if (s01.getText().equals("")) {
            text.appendText("укажите начало интервала S0\n");
        } else if (s02.getText().equals("")) {
            text.appendText("укажите конец интервала S0\n");
        } else if (step.getText().equals("")) {
            text.appendText("укажите шаг интервала S0\n");
        } else if (index.getText().equals("")) {
            text.appendText("укажите показатель n\n");
        } else if (file == null) {
            text.appendText("файл не выбран\n");
        } else {
            String result = selection.find(Integer.parseInt(s01.getText()), Integer.parseInt(s02.getText()), Integer.parseInt(step.getText()), index.getText(), file);
            text.clear();
            text.appendText(result);
        }
    }

    @FXML
    void about(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("about.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("about");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        fileChooser.setInitialDirectory(new File("src/sample/"));
        assert s01 != null : "fx:id=\"s01\" was not injected: check your FXML file 'form.fxml'.";
        assert s02 != null : "fx:id=\"s02\" was not injected: check your FXML file 'form.fxml'.";
        assert step != null : "fx:id=\"step\" was not injected: check your FXML file 'form.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'form.fxml'.";

    }
}