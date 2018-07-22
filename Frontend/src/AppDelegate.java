import javafx.application.Application;
import javafx.stage.Stage;
import network.api.LoginAPI;
import network.api.PatientsAPI;
import network.generic.GenericAPI;
import utils.Response;

import java.util.List;

/**
 * Created by Antonio Zaitoun on 21/07/2018.
 */
public class AppDelegate extends Application {

    public static void main(String[] args){

        LoginAPI auth = new LoginAPI();
        auth.login("asdasdasdasd", "1212121212", (response, id, token, roleId) -> {

            PatientsAPI api = new PatientsAPI();
            api.ui().readAll((response1, items) -> {

            });

        });

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
