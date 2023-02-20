module com.example.mine_swiper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.mine_swiper to javafx.fxml;
    exports com.example.mine_swiper;
}