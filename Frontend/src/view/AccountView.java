package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.UIView;
import utils.AutoSignIn;

import java.util.ResourceBundle;

/**
 * Created By Tony on 25/07/2018
 */
public class AccountView extends UIView {

    @FXML
    private Label hospital;

    @FXML
    private Label role;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField newPassword;

    @FXML
    private JFXButton update;

    public AccountView() {
        super("/resources/xml/view_account.fxml");
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        //TODO: add update password functionality.

        email.setEditable(false);

        email.setText(AutoSignIn.EMAIL);
        role.setText("" + AutoSignIn.ROLE_ID);

        update.setOnAction(e -> {
            //TODO: update password
        });
    }
}
