package com.kttg.aurelia.editor.actions.mouse;

public class MouseLClick {
    public static void update() {
        if (!Mouse.getIsClicking()) {
           updatePlaceObject();
           updateSelectObject();
        }
    }
    public static void updatePlaceObject(){
        if (Mouse.isPlacingObject && !Mouse.getIsOverUI()) {
            PlaceObject.click();
            Mouse.setIsClicking(true);
        }
    }
    public static void updateSelectObject(){
        if (Mouse.isSelectingObject && !Mouse.getIsOverUI()){
            SelectObject.update();
        }
    }

}
