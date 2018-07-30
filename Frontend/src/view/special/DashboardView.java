package view.special;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Person;
import network.api.DashboardAPI;
import network.api.DoctorAPI;
import network.api.PatientsAPI;
import ui.UITableView;
import ui.UIView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created By Tony on 28/07/2018
 */
public class DashboardView extends UIView {


    @FXML
    private TextFlow query13;

    @FXML
    private TextFlow doctorsLabel;

    @FXML
    private PieChart query2;

    @FXML
    private PieChart query8;

    @FXML
    private TextFlow unknown1;

    @FXML
    private VBox query5_vbox;

    @FXML
    private StackedBarChart<String,Integer> query9;

    @FXML
    NumberAxis xAxis;

    @FXML
    CategoryAxis yAxis;

    UITableView<Person> personTableView;

    public DashboardView() {
        super("/resources/xml/view_dashboard3.fxml");
    }

    @Override
    public void layoutSubviews(ResourceBundle bundle) {
        super.layoutSubviews(bundle);

        new DoctorAPI().readAll((response, items) -> {
            Text text1=new Text("Doctors");
            text1.setStyle("-fx-font-weight: regular");

            Text text2=new Text("\n"+items.size());
            text2.setStyle("-fx-font-weight: bold; -fx-font-size: 24px");

            doctorsLabel.getChildren().addAll(text1, text2);
            //doctorsLabel.setText("Number Of Doctors\n"+items.size());
        });

        new DashboardAPI().getData((response, data) -> {
            data = data.get("data").getAsJsonObject();

            //========================================================================
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
            query2.setTitle("Income from departments");
            //========================================================================

            JsonArray query9result = data.get("query9_result").getAsJsonArray();


            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();

            yAxis.setLabel("Department");
            xAxis.setLabel("Empty Beds");

            query9.setAnimated(false);
            for (JsonElement element : query9result) {
                String depName = element
                        .getAsJsonObject()
                        .get("object_b")
                        .getAsJsonObject()
                        .get("departmentName")
                        .getAsString();

                int amount = element
                        .getAsJsonObject()
                        .get("freeBedsInDepartment")
                        .getAsInt();


                XYChart.Series<String,Integer> series1 = new XYChart.Series<>();
                series1.getData().add(new XYChart.Data<>(depName,amount));
                query9.getData().add(series1);
            }

            query9.setCategoryGap(5);
            query9.setLegendVisible(false);
            query9.setTitle("Empty Beds In Departments");

            //=========================================================================

            personTableView = new UITableView<Person>() {

                List<Person> people;

                @Override
                public void layoutSubviews(ResourceBundle bundle) {
                    super.layoutSubviews(bundle);
                    people = new ArrayList<>();
                    new PatientsAPI().readAll((response1, items) -> {
                        if(response.isOK())
                            people.addAll(items);
                        reloadData();
                    });
                }

                @Override
                public int numberOfColumns() {
                    return 4;
                }

                @Override
                public Collection<? extends Person> dataSource() {
                    return people;
                }

                @Override
                public String bundleIdForIndex(int index) {
                    switch (index){
                        case 0:
                            return "ID";
                        case 1:
                            return "First Name";
                        case 2:
                            return "Sur Name";
                        case 3:
                            return "Birth Date";
                    }
                    return null;
                }

                @Override
                public TableColumnValue<Person> cellValueForColumnAt(int index) {
                    switch (index){
                        case 0:
                            return Person::getID;
                        case 1:
                            return Person::getFirstName;
                        case 2:
                            return Person::getSurName;
                        case 3:
                            return Person::getDateOfBirth;
                    }
                    return null;
                }
            };
            query5_vbox.getChildren().addAll(personTableView);

            // =====================================================

            JsonArray query8result = data.get("query8_result").getAsJsonArray();
            ObservableList<PieChart.Data> query8data = FXCollections.observableArrayList();

            String[] stringTypes = new String[]{"A", "B", "AB", "O"};

            int typeA = 0;
            int typeB = 0;
            int typeAB = 0;
            int typeO = 0;

            for (JsonElement element : query8result) {
                switch (element.getAsJsonObject().get("bloodType").getAsString()){

                    case "A":
                        typeA++;
                    case "B":
                        typeB++;
                    case "AB":
                        typeAB++;
                    case "O":
                        typeO++;
                    default:
                        break;
                }

            }

            for (String type : stringTypes) {
                switch (type) {
                    case "A":
                        query8data.add(new PieChart.Data(type + " (" + typeA + ")",typeA));
                        break;
                    case "B":
                        query8data.add(new PieChart.Data(type + " (" + typeB + ")",typeB));
                        break;
                    case "AB":
                        query8data.add(new PieChart.Data(type + " (" + typeAB + ")",typeAB));
                        break;
                    case "O":
                        query8data.add(new PieChart.Data(type + " (" + typeO + ")",typeO));
                        break;
                    default:
                        break;
                }
            }

            query8.setData(query8data);
            query8.setLegendVisible(false);
            query8.setTitle("Potential Donors");

            // ====================================================================================

            JsonArray query13result = data.get("query13_result").getAsJsonArray();

            Text text1=new Text("Patients");
            text1.setStyle("-fx-font-weight: regular");

            Text text2=new Text("\n"+query13result.get(0).getAsJsonObject().get("number_of_patients").getAsInt());
            text2.setStyle("-fx-font-weight: bold; -fx-font-size: 24px");

            query13.getChildren().addAll(text1, text2);

        });




    }
}
