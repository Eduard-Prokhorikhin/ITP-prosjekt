package app;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class GeneralController {

    @FXML
    private TextField bilagAmount;

    @FXML
    private DatePicker bilagDate;

    @FXML
    private TextField bilagTitle;

    private User user;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    void handleAddBilag(ActionEvent event) {
        LocalDate date = bilagDate.getValue();
        String title = bilagTitle.getText();
        double value = Double.valueOf(bilagAmount.getText());
        Bilag bilag;

        if (date==null) {
            bilag = new Bilag(title, value);
        }
        else {
            bilag = new Bilag(title, value, date);
        }
        user.addBilag(bilag);
        System.out.println(bilag.getTitle());
    }

    
    //metode som bytter mellom scener
    @FXML
    public void switchToLogIn(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
