module kursovaya.newkursovayaversion3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens kursovaya.newkursovayaversion3 to javafx.fxml;
    exports kursovaya.newkursovayaversion3;
}