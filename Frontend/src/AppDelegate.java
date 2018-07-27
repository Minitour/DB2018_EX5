import controller.LoginController;
import controller.master.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.UIViewController;
import utils.AutoSignIn;

/**
 * Created by Antonio Zaitoun on 21/07/2018.
 */
public class AppDelegate extends Application {

    private final LoginController loginController =initLoginController();

    private Stage loginStage;
    private Stage mainStage = new Stage();

    public LoginController initLoginController(){
        LoginController controller = new LoginController();
        controller.setOnExit(event -> loginStage.close());
        controller.setOnAuth((role,ex)-> {
            if(role != -1) {
                onLoginSuccess(role);
            }
        });
        return controller;
    }





    void onLoginSuccess(int role){
        MasterMenuController controller = null;
        switch (role){
            case 1:
                //patient view
                controller = new PatientMaster();
                break;
            case 2:
                //secretary view
                controller = new SecretaryMaster();
                break;
            case 3:
                //doctor view
                controller = new DoctorMaster();
                break;
            case 4:
                //doctor manager
                controller = new DoctorManagerMaster();
                break;
            case 5:
                //admin view
                controller = new AdminMaster();
                break;
            case 6:
                //super user view
                controller = new SuperUserMaster();
                break;


        }

        assert controller != null;
        controller.setOnLogout(event -> {
            mainStage.close();
            AutoSignIn.reset();
            loginStage.show();
        });

        //loginStage.getScene().setRoot(controller.view);
        loginStage.close();
        showLoggedInStage(controller);
    }

    void showLoggedInStage(UIViewController controller){
        mainStage.setScene(new Scene(controller.view,1200,800));
        mainStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.loginStage = primaryStage;
        loginStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(loginController.view));
        primaryStage.show();

        primaryStage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });

        mainStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

    }

    public static void main(String[] args){
        launch(args);
    }
}
