package view.special;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import ui.UIView;

/**
 * Created by Antonio Zaitoun on 30/07/2018.
 */
public class SuperDashboardView extends UIView {

    @FXML
    private TextFlow comp_query6;

    @FXML
    private StackedBarChart<String,Integer> comp_query7A;

    @FXML
    private PieChart comp_query4;

    @FXML
    private TextFlow comp_query13A;

    @FXML
    private VBox comp_query3;

    @FXML
    private StackedBarChart<String,Integer> comp_query7B;


    public SuperDashboardView(){
        super("/resources/xml/view_dashboard4.fxml");
    }

}
