module com.example.oopjavafxg1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
//    requires com.almasb.fxgl.all;

    opens com.example.oopjavafxg1 to javafx.fxml;
    exports com.example.oopjavafxg1;
    exports com.example.oopjavafxg1.controllers;
    exports com.example.oopjavafxg1.models;
    opens com.example.oopjavafxg1.controllers to javafx.fxml;
}