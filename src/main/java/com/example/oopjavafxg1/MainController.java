package com.example.oopjavafxg1;

import com.example.oopjavafxg1.models.Person;
import com.example.oopjavafxg1.repositories.PersonRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSurname;

    @FXML
    private TextArea areaComment;

    @FXML
    private DatePicker dpBirthdate;

    public void onSubmit() {
        Person person = new Person(
                txtName.getText(),
                txtSurname.getText(),
                areaComment.getText(),
                dpBirthdate.getValue()
        );
        PersonRepository repository = new PersonRepository();
        repository.add(person);
    }

    public void showConsole() {
        System.out.printf("Name: %s%n", txtName.getText());
        System.out.printf("Surname: %s%n", txtSurname.getText());
        System.out.printf("Comment: %s%n", areaComment.getText());
        System.out.printf("Birthdate: %s%n", dpBirthdate.getValue());
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("User data");
        alert.setHeaderText("The data entered: ");
        alert.setContentText(
                String.format("Name: %s%nSurname: %s%nComment: %s%nBirthdate: %s%n",
                        txtName.getText(),
                        txtSurname.getText(),
                        areaComment.getText(),
                        dpBirthdate.getValue()
                )
        );
        showConsole();
        alert.show();

    }

    public void reset(ActionEvent actionEvent) {
        txtName.setText("");
        txtSurname.setText("");
        areaComment.setText("");
        dpBirthdate.setValue(null);
    }
}











