package view.special;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import network.api.DashboardAPI;
import ui.UIView;
import utils.Response;

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
    private StackedBarChart quer9;

    public DashboardView() {
        super("/resources/xml/view_dashboard3.fxml");
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);
        new DashboardAPI().getData((response, data) -> {

        });
    }
}
