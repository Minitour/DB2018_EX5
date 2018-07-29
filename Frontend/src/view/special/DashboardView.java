package view.special;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import network.api.DashboardAPI;
import network.api.DoctorAPI;
import ui.UIView;

import java.util.ResourceBundle;

/**
 * Created By Tony on 28/07/2018
 */
public class DashboardView extends UIView {

    @FXML
    private Label query13;

    @FXML
    private Label doctorsLabel;

    @FXML
    private PieChart query2;

    @FXML
    private PieChart query3;

    @FXML
    private Label unknown1;

    @FXML
    private VBox query5_vbox;

    @FXML
    private StackedBarChart query9;

    public DashboardView() {
        super("/resources/xml/view_dashboard3.fxml");
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        new DoctorAPI().readAll((response, items) -> {
            doctorsLabel.setText("Number Of Doctors\n"+items.size());
        });

        new DashboardAPI().getData((response, data) -> {
            data = data.get("data").getAsJsonObject();
            JsonArray query6result = data.get("query6_result").getAsJsonArray();
            ObservableList<PieChart.Data> query6data = FXCollections.observableArrayList();
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


                query6data.add(new PieChart.Data(depName,amount));
            }
            query2.setData(query6data);
            query2.setLegendSide(Side.RIGHT);

        });
    }
}
