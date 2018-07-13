import controller.LoginController;
import utils.RESTApplication;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class App extends RESTApplication {

    public static void main(String... args){
        launch(8080);
    }

    @Override
    public void init() {
        post("/login",new LoginController());
    }
}
