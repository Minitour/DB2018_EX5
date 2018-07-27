package view.special;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import model.Person;
import network.api.ProfileAPI;
import ui.UIView;
import utils.AutoSignIn;

import java.util.ResourceBundle;

/**
 * Created By Tony on 25/07/2018
 */
public class ProfileView extends UIView {

    @FXML private JFXTextField id;
    @FXML private JFXTextField firstName;
    @FXML private JFXTextField surName;
    @FXML private JFXTextField dateOfBirth;
    @FXML private JFXTextField city;
    @FXML private JFXTextField street;
    @FXML private JFXTextField gender;
    @FXML private JFXTextField phone;
    @FXML private JFXTextField bloodType;
    @FXML private JFXTextField careFacility;
    @FXML private JFXTextField contactID;

    public ProfileView() {
        super("/resources/xml/view_profile.fxml");
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        ProfileAPI api = new ProfileAPI();
        api.setRunOnUi(true);

        api.readAll((response, items) -> {
            if(response.isOK()) {
                items.removeIf(person -> !AutoSignIn.ID.equals(person.getACCOUNT_ID()));
                Person p = items.get(0);

                id.setText(p.getID());
                firstName.setText(p.getFirstName());
                surName.setText(p.getSurName());
                dateOfBirth.setText(p.getDateOfBirth().toString());
                city.setText(p.getCity());
                street.setText(p.getStreet());
                gender.setText(p.getGender().toString());
                phone.setText(p.getPhone());
                bloodType.setText(p.getBloodType());
                careFacility.setText(p.getCareFacility());
                contactID.setText(p.getContactID());
            }
        });
    }
}
