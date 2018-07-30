package view.special;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Hospital;
import model.Person;
import network.api.DashboardAPI;
import network.api.ProfileAPI;
import network.generic.GenericAPI;
import ui.UITableView;
import ui.UIView;
import utils.Response;

import java.util.*;

/**
 * Created by Antonio Zaitoun on 30/07/2018.
 */
public class SuperDashboardView extends UIView {

    @FXML
    private TextFlow comp_query6; // done

    @FXML
    private StackedBarChart<String,Integer> comp_query7A;

    @FXML
    private PieChart comp_query4; // done

    @FXML
    private TextFlow comp_query13A; // done

    @FXML
    private VBox comp_query3;

    @FXML
    private BarChart comp_query7B;

    @FXML
    private CategoryAxis query7a_x;

    @FXML
    private NumberAxis query7a_y;


    public SuperDashboardView(){
        super("/resources/xml/view_dashboard4.fxml");
    }


    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        new DashboardAPI().getData((response, data) -> {
            data = data.get("data").getAsJsonObject();

            init_comp_query6(data);
            init_comp_query13A(data);
            init_comp_query4(data);

            init_comp_query7A(data);
            init_comp_query3(data);
            init_comp_query7B(data);
        });



    }


    void init_comp_query6(JsonObject data) {

        JsonArray query6result = data.get("query6_result").getAsJsonArray();

        int totalAmount = 0;

        for (JsonElement element : query6result) {
            String depName = element
                    .getAsJsonObject()
                    .get("object_b")
                    .getAsJsonObject()
                    .get("departmentName")
                    .getAsString();

            int amount = element
                    .getAsJsonObject()
                    .get("amount")
                    .getAsInt();


            totalAmount += amount;
        }
        Text totalAmountLabel = new Text("Total Income");
        totalAmountLabel.setStyle("-fx-font-weight: regular");

        Text totalAmountValue =new Text("\n$" +totalAmount + ".00");
        totalAmountValue.setStyle("-fx-font-weight: bold; -fx-font-size: 24px");
        comp_query6.getChildren().addAll(totalAmountLabel,totalAmountValue);
    }

    void init_comp_query7A(JsonObject data) {

        comp_query7A.setAnimated(false);
        JsonArray result = data.get("query7A_result").getAsJsonArray();
        Map<String,Integer> map = new HashMap<>();

        for (JsonElement element : result) {
            String hospitalName = element
                    .getAsJsonObject()
                    .get("object_a")
                    .getAsJsonObject()
                    .get("name")
                    .getAsString();

            int numOfDoctors = element
                    .getAsJsonObject()
                    .get("numOfDoctors")
                    .getAsInt();

            if(map.containsKey(hospitalName)){
                int val = map.get(hospitalName);
                map.put(hospitalName,numOfDoctors + val);
            }else{
                map.put(hospitalName,numOfDoctors);
            }
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            XYChart.Series<String,Integer> series1 = new XYChart.Series<>();
            series1.getData().add(new XYChart.Data<>(stringIntegerEntry.getKey(),stringIntegerEntry.getValue()));
            comp_query7A.getData().add(series1);
        }
    }

    void init_comp_query4(JsonObject data) {

        JsonArray query4Aresult = data.get("query4_result").getAsJsonArray();
        ObservableList<PieChart.Data> query4data = FXCollections.observableArrayList();

        HashMap<String, Integer> list = new HashMap<>();

        for (JsonElement element : query4Aresult) {

            String hos = element.getAsJsonObject().get("object_a").getAsJsonObject().get("object_a").getAsJsonObject().get("name").getAsString();
            if (list.get(hos) != null) {

                int value = list.get(hos);
                list.put(hos, value++);

            }
            else {
                list.put(hos, 1);
            }

        }

        for (Map.Entry<String,Integer> item : list.entrySet()){
            query4data.add(new PieChart.Data(item.getKey(),item.getValue()));

        }

        comp_query4.setData(query4data);
        comp_query4.setLegendVisible(true);
        comp_query4.setTitle("Busy Doctors");

    }

    void init_comp_query13A(JsonObject data) {

        JsonArray query13Aresult = data.get("query13A_result").getAsJsonArray();

        int totalAmount = 0;

        for (JsonElement element : query13Aresult) {
            String hospitalName = element
                    .getAsJsonObject()
                    .get("name")
                    .getAsString();

            int amount = element
                    .getAsJsonObject()
                    .get("number_of_patients")
                    .getAsInt();


            totalAmount += amount;

        }
        Text totalAmountLabel = new Text("Total Patients");
        totalAmountLabel.setStyle("-fx-font-weight: regular");

        Text totalAmountValue =new Text("\n" +totalAmount);
        totalAmountValue.setStyle("-fx-font-weight: bold; -fx-font-size: 24px");
        comp_query13A.getChildren().addAll(totalAmountLabel,totalAmountValue);



    }

    void init_comp_query3(JsonObject data) {
        JsonArray results = data.get("query3_result").getAsJsonArray();



        class HospitalStatus {
            private String hospitalName;
            private String hospitalStatus;

            public HospitalStatus(String hospitalName, String hospitalStatus) {
                this.hospitalName = hospitalName;
                this.hospitalStatus = hospitalStatus;
            }

            String getHospitalName() {
                return hospitalName;
            }

            String getHospitalStatus() {
                return hospitalStatus;
            }
        }


        final List<HospitalStatus> dataSource = new ArrayList<>();

        for (JsonElement result : results) {
            String status = result
                    .getAsJsonObject()
                    .get("hospitalStatus")
                    .getAsString();

            String name = result
                    .getAsJsonObject()
                    .get("object_a")
                    .getAsJsonObject()
                    .get("object_a")
                    .getAsJsonObject()
                    .get("name")
                    .getAsString();
            dataSource.add(new HospitalStatus(name,status));
        }

        UITableView<HospitalStatus> tableView = new UITableView<HospitalStatus>() {
            @Override
            public int numberOfColumns() {
                return 2;
            }

            @Override
            public Collection<? extends HospitalStatus> dataSource() {
                return dataSource;
            }

            @Override
            public String bundleIdForIndex(int index) {
                switch (index){
                    case 0:
                        return "Hospital Name";
                    case 1:
                        return "Status";

                }
                return null;
            }

            @Override
            public TableColumnValue<HospitalStatus> cellValueForColumnAt(int index) {
                switch (index){
                    case 0:
                        return HospitalStatus::getHospitalName;
                    case 1:
                        return HospitalStatus::getHospitalStatus;
                }
                return null;
            }
        };

        comp_query3.getChildren().add(tableView);
    }

    void init_comp_query7B(JsonObject data) {
        comp_query7B.setAnimated(false);
        new ProfileAPI().readAll((response, items) -> {
            Map<String,Integer> map = new HashMap<>();

            for (Person element : items) {

                String cityName = element.getCity();

                if(map.containsKey(cityName)){
                    int val = map.get(cityName);
                    map.put(cityName,1 + val);
                }else{
                    map.put(cityName,1);
                }
            }

            for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                XYChart.Series<String,Integer> series1 = new XYChart.Series<>();
                series1.getData().add(new XYChart.Data<>(stringIntegerEntry.getKey(),stringIntegerEntry.getValue()));
                comp_query7B.getData().add(series1);
            }
        });
    }
}
