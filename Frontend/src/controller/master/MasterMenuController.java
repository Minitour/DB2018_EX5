package controller.master;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Hospital;
import network.api.HospitalAPI;
import ui.UIListViewCell;
import ui.UIView;
import ui.UIViewController;
import utils.AutoColor;
import utils.AutoSignIn;
import view.DialogView;

import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created By Tony on 14/02/2018
 */
public abstract class MasterMenuController extends UIViewController {

    @FXML
    private ListView<MenuItem> listView;

    @FXML
    private AnchorPane rightMenu;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Button logout;

    @FXML
    private Pane navBar;

    public MasterMenuController() {
        super("/resources/xml/controller_master.fxml");
        welcomeLabel.setText("Loading...");
        if (AutoSignIn.ROLE_ID != 6) {
            new HospitalAPI().read(new Hospital(AutoSignIn.HOSPITAL_ID), (response, object) -> {
                if (object != null)
                    welcomeLabel.setText(object.getName());
                else
                    welcomeLabel.setText("Hospital Name");
            });
        }else
            welcomeLabel.setText("Super User");
    }

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);

        usernameLabel.setText(AutoSignIn.EMAIL);

        listView.setCellFactory(param -> new Cell());

        listView.getItems().addAll(Arrays.asList(itemsForMenu()));

        listView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue)
                -> onListItemChanged(newValue.intValue()));

        setNavBar(AutoColor.primaryColor,AutoColor.isColorDark(AutoColor.primaryColor));

        logout.setStyle("-fx-text-fill: " + AutoColor.secondaryColor + ";");

        String roleLiteral;
        switch (AutoSignIn.ROLE_ID){
            case 1:
                roleLiteral = "Patient";break;
            case 2:
                roleLiteral = "Secretary";break;
            case 3:
                roleLiteral = "Doctor";break;
            case 4:
                roleLiteral = "Doctor Manager";break;
            case 5:
                roleLiteral = "Admin";break;
            case 6:
                roleLiteral = "User";break;
            default:
                roleLiteral = "Unknown";break;
        }

        roleLabel.setText(roleLiteral);

    }

    protected DialogView makeDialog(String title, String message){
        DialogView dialogView = new DialogView();
        dialogView.setTitle(title);
        dialogView.setMessage(message);
        dialogView.getPostiveButton().setText("Done");
        dialogView.setNegativeEventHandler(null);
        return dialogView;
    }

    protected void setNavBar(String color, boolean isDark){
        Color c = isDark ? Color.web("#FFF") : Color.web("#000");
        navBar.setStyle("-fx-background-color: "+color+";");

        welcomeLabel.setTextFill(c);
        usernameLabel.setTextFill(c);
        roleLabel.setTextFill(c);
    }

    public void setOnLogout(EventHandler<ActionEvent> eventHandler){
        logout.setOnAction(eventHandler);
    }

    protected void onListItemChanged(int index){
        showView(viewForIndexAt(index));
    }

    public abstract UIView viewForIndexAt(int index);

    public abstract MenuItem[] itemsForMenu();

    private void showView(UIView view){
        rightMenu.getChildren().clear();
        if(view != null){
            AnchorPane.setTopAnchor(view,8.0);
            AnchorPane.setBottomAnchor(view,8.0);
            AnchorPane.setRightAnchor(view,8.0);
            AnchorPane.setLeftAnchor(view,8.0);
            rightMenu.getChildren().add(view);
            view.setDelegate(this);
        }
    }

    public static class MenuItem {
        private String title;
        private String image;

        public MenuItem(String title, String image) {
            this.title = title;
            this.image = image;
        }
    }

    static class Cell extends UIListViewCell<MenuItem, UIView> {


        private String defStyle;
        private String highlighted;

        @Override
        public UIView load(MenuItem item) {

            defStyle = getStyle();
            highlighted = "-fx-background-color:  #c7c9c9 !important;-fx-background-radius: 5";
            //setStyle(":selected{-fx-background-color:  #97ff8e !important;}");
            changeBackgroundOnHoverUsingBinding(this);
            return new CellView().setData(item.title,item.image);
        }

        void changeBackgroundOnHoverUsingBinding(Node node) {
            node.styleProperty().bind(
                    Bindings
                            .when(Bindings.or(node.hoverProperty(),node.focusedProperty()))
                            .then(
                                    new SimpleStringProperty(highlighted)
                            )
                            .otherwise(
                                    new SimpleStringProperty(defStyle)
                            )
            );

        }
    }

    static class CellView extends UIView {

        @FXML
        private Label menu;

        @FXML
        private ImageView imageView;

        @FXML
        private HBox vbox;

        public CellView setData(String str,String image) {
            this.menu.setText(str);
            if(image != null && !image.isEmpty()){
                this.imageView.setImage(new Image(image));
            }else {
                vbox.getChildren().remove(imageView);
            }
            return this;
        }


        @Override
        public void layoutSubviews(ResourceBundle bundle) {
            menu.setTextFill(Color.BLACK);
        }

        @Override
        public void layoutBundle(ResourceBundle bundle) {

        }

        @Override
        public String resource() {
            return "/resources/xml/list_item.fxml";
        }
    }
}
