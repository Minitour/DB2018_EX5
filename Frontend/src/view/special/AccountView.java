package view.special;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Hospital;
import network.api.AuthAPI;
import network.api.HospitalAPI;
import network.generic.GenericAPI;
import ui.UIView;
import utils.AutoSignIn;
import utils.Response;

import java.util.List;
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
            new AuthAPI().updatePassword(password.getText(),newPassword.getText(),response -> {
                JFXSnackbar bar = new JFXSnackbar(this);
                bar.enqueue(new JFXSnackbar.SnackbarEvent(response.isOK() ? "Successfully Updated Password" : "Failed to update password"));
                password.setText("");
                newPassword.setText("");
            });
        });

        new HospitalAPI().readAll((response, items) -> {
            for (Hospital item : items) {
                if(AutoSignIn.HOSPITAL_ID.equals(item.getHospitalID())){
                    hospital.setText(item.getName());
                    break;
                }
            }
        });

        String roleLiteral;
        switch (AutoSignIn.ROLE_ID){
            case 1:
                roleLiteral = "Patient";break;
            case 2:
                roleLiteral = "Secretary";break;
            case 3:
                roleLiteral = "Doctor";break;
            case 4:
                roleLiteral = "Doctor Manager";break;
            case 5:
                roleLiteral = "Admin";break;
            case 6:
                roleLiteral = "Super User";break;
            default:
                roleLiteral = "Unknown";break;
        }

        role.setText(roleLiteral);
    }
}
