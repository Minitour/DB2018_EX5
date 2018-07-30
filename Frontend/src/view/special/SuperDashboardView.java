package view.special;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import network.api.DashboardAPI;
import ui.UIView;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
    private StackedBarChart<String,Integer> comp_query7B;


    public SuperDashboardView(){
        super("/resources/xml/view_dashboard4.fxml");
    }


    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        init_comp_query6();
        init_comp_query13A();
        init_comp_query4();

    }


    void init_comp_query6() {
        new DashboardAPI().getData(((response, data) -> {

            data = data.get("data").getAsJsonObject();

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


        }));
    }

    void init_comp_query7A() {

    }

    void init_comp_query4() {

        new DashboardAPI().getData(((response, data) -> {
            data = data.get("data").getAsJsonObject();
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

        }));

    }

    void init_comp_query13A() {

        new DashboardAPI().getData((response, data) -> {
            data = data.get("data").getAsJsonObject();
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
            Text totalAmountLabel = new Text("Total Income");
            totalAmountLabel.setStyle("-fx-font-weight: regular");

            Text totalAmountValue =new Text("\n$" +totalAmount + ".00");
            totalAmountValue.setStyle("-fx-font-weight: bold; -fx-font-size: 24px");
            comp_query13A.getChildren().addAll(totalAmountLabel,totalAmountValue);


        });


    }

    void init_comp_query3() {

    }

    void init_comp_query7B() {

    }
}
