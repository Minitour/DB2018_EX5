package view;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ui.UIView;

/**
 * Created By Tony on 28/07/2018
 */
public class CardView extends UIView {

    @FXML
    private VBox main;

    private UIView contentView;

    public CardView(UIView view) {
        super("/resources/xml/card_view.fxml");
        main.getChildren().add(view);
        contentView = view;
    }

    public UIView getContentView() {
        return contentView;
    }
}
