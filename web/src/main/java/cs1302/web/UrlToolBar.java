package cs1302.web;

import java.net.URI;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class UrlToolBar extends HBox {

    private TextField textField;
    private Button button;

    public UrlToolBar(String textFieldText, String buttonText) {
        super();
        this.textField = new TextField(textFieldText);
        this.button = new Button(buttonText);
        this.getChildren().addAll(this.textField, this.button);
    } // UrlToolBar

    public TextField getTextField() {
        return this.textField;
    } // getTextField

    public Button getButton() {
        return this.button;
    } // getButton

    public String getUrl() {
        return this.textField.getText().trim();
    } // getUrl

    public URI getUri() {
        return URI.create(this.getUrl());
    } // getUri

    public void disableButton() {
        this.button.setDisable(true);
    } // disableButton

    public void enableButton() {
        this.button.setDisable(false);
    } // enableButton

} // ToolBar
