package com.example.mine_swiper.Scene;

import com.example.mine_swiper.Controllers.GameFiledController;
import com.example.mine_swiper.Game;
import com.example.mine_swiper.Scene.GameObjects.Cell;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.event.MouseEvent;
import java.beans.EventHandler;

public class GameField extends Group {
    private GameFiledController controller;
    private static final int SIZE = 10;
    private double width;
    private double height;
    private Cell[][] grid = new Cell[SIZE][SIZE];
    public GameField(int width,int height) {
        super();
        this.width = width;
        this.height = height;
        createGrid();
        controller = new GameFiledController(this);

    }
    private void createGrid(){
        double width = this.width/SIZE;
        double height = this.height/SIZE;
        for(int i=0;i<SIZE;i++){
            double xPos = i*width;
            for(int j=0;j<SIZE;j++){
                double yPos = j*height;
                Cell cell = new Cell(xPos,yPos,width,height);
                getChildren().add(cell);
                grid[i][j] = cell;
            }
        }
    }
    public Cell[][] getGrid(){return grid;}
}
