package client;

import javafx.scene.control.Alert;

public class GuiUtil {
    public static void alert(Alert.AlertType error, String s) {
        Alert alert = new Alert(error, s);
        alert.showAndWait();
    }
}
