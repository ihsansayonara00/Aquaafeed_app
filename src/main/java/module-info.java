module com.example.apkpemberianpakanikan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.apkpemberianpakanikan to javafx.fxml;
    exports com.example.apkpemberianpakanikan;
}