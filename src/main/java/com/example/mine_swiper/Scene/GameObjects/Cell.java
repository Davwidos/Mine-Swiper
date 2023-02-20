package com.example.mine_swiper.Scene.GameObjects;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;


public class Cell extends Rectangle {
    private static int SIDE = 100;
    public Cell(Scene scene) {
        super(scene.getHeight(),scene.getWidth());
    }
}
