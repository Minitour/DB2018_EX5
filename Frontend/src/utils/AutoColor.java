package utils;

import javafx.scene.paint.Color;

/**
 * Created By Tony on 01/08/2018
 */
public class AutoColor {
    public static String primaryColor = "#FFF";//"#3F51B5";
    public static String secondaryColor = "#009688";//"#FF4081";

    public static Color primaryColor(){
        return Color.web(primaryColor);
    }

    public static Color secondaryColor(){
        return Color.web(secondaryColor);
    }

    public static String colorToHex(Color color) {
        return colorChanelToHex(color.getRed())
                + colorChanelToHex(color.getGreen())
                + colorChanelToHex(color.getBlue())
                + colorChanelToHex(color.getOpacity());
    }

    private static String colorChanelToHex(double chanelValue) {
        String rtn = Integer.toHexString((int) Math.min(Math.round(chanelValue * 255), 255));
        if (rtn.length() == 1) {
            rtn = "0" + rtn;
        }
        return rtn;
    }



    public static boolean isColorDark(String color){

        Color webColor = Color.web(color);
        return !(webColor.getBrightness() > 0.75);
    }
}
