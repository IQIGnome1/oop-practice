module com.example.valuutakalkulaator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.valuutakalkulaator to javafx.fxml;
    exports com.example.valuutakalkulaator;
}