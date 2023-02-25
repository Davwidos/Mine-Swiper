package com.example.mine_swiper.Scene;

import com.example.mine_swiper.Controllers.MouseObserver;
import com.example.mine_swiper.Game;
import com.example.mine_swiper.Scene.GameObjects.Cell;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GameField extends Group {
    private static final int SIZE = 10;
    private double width;
    private double height;
    private Cell[][] grid;
    private int flagsAvailable;
    public GameField(double width,double height) {
        super();
        this.width = width;
        this.height = height;
        createGrid();
    }
    private void createGrid(){
        grid = new Cell[SIZE][SIZE];
        double width = this.width/SIZE;
        double height = this.height/SIZE;
        flagsAvailable = 0;
        for(int i=0;i<SIZE;i++){
            double xPos = i*width;
            for(int j=0;j<SIZE;j++){
                double yPos = j*height;
                Cell cell = new Cell(xPos,yPos,width,height,this);
                grid[i][j] = cell;
                if(cell.isBomb()) flagsAvailable ++;
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
    public boolean useFlag(){
        if(flagsAvailable > 0){
            flagsAvailable--;
            return true;
        }
        return false;
    }
    public void releaseFlag(){
        flagsAvailable++;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameField gameField = (GameField) o;
        return Double.compare(gameField.width, width) == 0 && Double.compare(gameField.height, height) == 0 && flagsAvailable == gameField.flagsAvailable && Arrays.equals(grid, gameField.grid);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(width, height, flagsAvailable);
        result = 31 * result + Arrays.hashCode(grid);
        return result;
    }
}
