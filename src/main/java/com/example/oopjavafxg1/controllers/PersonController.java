package com.example.oopjavafxg1.controllers;

import com.example.oopjavafxg1.models.Person;
import com.example.oopjavafxg1.repositories.PersonRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.List;

public class PersonController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSurname;

    @FXML
    private TextArea areaComment;

    @FXML
    private DatePicker dpBirthdate;

    @FXML
    private ListView<Person> listPersons;

    @FXML
    private TableView<Person> tablePersons;

    private List<Person> personList = null;
    private ObservableList<Person> observablePeople = null;
    private PersonRepository repository = null;
    private Person selectedPerson = null;

    public void initialize() {
        repository = new PersonRepository();
        observablePeople = FXCollections.observableArrayList();
        initializeTableView();
        onRefresh();
    }

    public void initializeTableView() {
        TableColumn<Person, Integer> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Person, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Person, String> colSurname = new TableColumn<>("Surname");
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Person, String> colComment = new TableColumn<>("Comment");
        colComment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        TableColumn<Person, LocalDate> colBirthdate = new TableColumn<>("Birthdate");
        colBirthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));

        tablePersons.getColumns().addAll(colId, colName, colSurname, colComment, colBirthdate);

        tablePersons.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    Person person = tablePersons.getSelectionModel().getSelectedItem();
                    if (person != null) {
                        txtName.setText(person.getName());
                        txtSurname.setText(person.getSurname());
                        areaComment.setText(person.getComment());
                        dpBirthdate.setValue(person.getBirthdate());
                        selectedPerson = person;
                    }
                }
            }
        });
    }

    public void onRefresh() {
        personList = repository.findAll();
        observablePeople.clear();
        observablePeople.addAll(personList);
        listPersons.setItems(observablePeople);
        tablePersons.setItems(observablePeople);
    }

    public void onSubmit() {
        Person person = new Person(
                txtName.getText(),
                txtSurname.getText(),
                areaComment.getText(),
                dpBirthdate.getValue()
        );
        if (selectedPerson == null)
            repository.add(person);
        else
            repository.modify(selectedPerson.getId(), person);

        onRefresh();
        reset();
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

    public void reset() {
        txtName.setText("");
        txtSurname.setText("");
        areaComment.setText("");
        dpBirthdate.setValue(null);
        selectedPerson = null;
    }

    public void delete() {
        if (selectedPerson != null) {
            repository.remove(selectedPerson.getId());
            onRefresh();
            reset();
        }
    }
}











