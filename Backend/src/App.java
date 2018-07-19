import controller.LoginController;
import database.data_access.ShiftAccess;
import model.Shift;
import utils.RESTApplication;

import java.util.List;

/**
 * Created by Antonio Zaitoun on 13/07/2018.
 */
public class App extends RESTApplication {

    public static void main(String... args){
        /*
        try(Database db = new Database()) {
            List<Map<String,Object>> data = db.get("{call QUERY_12(?)}","343434343");
            System.out.println(data.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        */

        try(ShiftAccess access = new ShiftAccess()){
            List<Shift> data =  access.getById(1);
            System.out.println(data.size());
        }catch (Exception e){
            e.printStackTrace();
        }

        launch(8080);
    }

    @Override
    public void init() {
        post("/login",new LoginController());
    }
}
