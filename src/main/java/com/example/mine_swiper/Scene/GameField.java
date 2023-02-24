package com.example.mine_swiper.Scene;

import com.example.mine_swiper.Controllers.MouseObserver;
import com.example.mine_swiper.Scene.GameObjects.Cell;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameField extends Group {
    private MouseObserver controller;
    private static final int SIZE = 10;
    private double width;
    private double height;
    private Cell[][] grid = new Cell[SIZE][SIZE];
    public GameField(int width,int height) {
        super();
        this.width = width;
        this.height = height;
        createGrid();
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
        for (int i=0;i<SIZE;i++) for(int j=0;j<SIZE;j++){
            if(grid[i][j].isBomb()){
                for (int k=-1;k<=1;k++) for(int l=-1;l<=1;l++){
                    if(k+i<0 || l+j<0 || k+i>=SIZE || l+j>=SIZE) continue;
                    if(k!=0 || l!=0) grid[i+k][j+l].incrementBombNeighbours();
                }
            }
        }
    }
    public Cell[][] getGrid(){return grid;}
    public List<Clickable> getClickable(){
        List<Clickable> clickable = new ArrayList<>();
        for(Cell[] cells:grid) for (Cell cell:cells) clickable.add(cell);
        return clickable;
    }
    public void showAround(Cell cell){

        for(int i=0;i<SIZE;i++) for(int j=0;j<SIZE;j++){
            if(cell.equals(grid[i][j])){
                for(int k=-1;k<=1;k++) for(int l=-1;l<=1;l++){
                    if(i+k<0 || j+l<0 || i+k>=SIZE || j+l>=SIZE) continue;
                    grid[i+k][j+l].show();
                }
                return;
            }
        }
    }
}
