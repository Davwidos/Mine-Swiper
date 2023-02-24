package com.example.mine_swiper.Scene.GameObjects;

import com.example.mine_swiper.Scene.Clickable;
import com.example.mine_swiper.Game;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class Cell extends Group implements Clickable {
    private static final String BOMB = "\uD83D\uDCA3";
    private boolean isBomb = false;
    private boolean isReveled = false;
    private int countBombNeighbours;
    private Text text;
    private Rectangle rect;
    public Cell(double v, double v1, double v2, double v3) {
        super();
        rect = new Rectangle(v,v1,v2,v3);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        getChildren().add(rect);
        text = new Text();
        getChildren().add(text);
        setBomb(Game.getDifficulty());
    }
    public boolean isBomb(){return isBomb;}
    public void show(){
        if(isReveled) return;
        isReveled = true;
        if(isBomb) showBomb();
        else{
            showNotBomb();
            if(countBombNeighbours == 0)Game.gameField.showAround(this);
        }
        text.setFont(new Font(Math.min(rect.getWidth(),rect.getHeight())));
        text.setX(rect.getX());
        text.setY(rect.getY()+rect.getHeight());
    }
    private void showBomb(){
        text.setText(BOMB);
        rect.setFill(Color.RED);
    }
    private void showNotBomb(){
        text.setText(String.valueOf(countBombNeighbours));
        rect.setFill(Color.GREEN);
    }

    @Override
    public void clicked(MouseButton button) {
        if(button.equals(MouseButton.PRIMARY)) show();
    }
    public void setBomb(int difficulty){
        int random = (int)(Math.random()*100);
        isBomb = random <= difficulty;
    }

    public int getCountBombNeighbours() {
        return countBombNeighbours;
    }

    public void incrementBombNeighbours() {
        countBombNeighbours++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return isBomb == cell.isBomb && countBombNeighbours == cell.countBombNeighbours && text.equals(cell.text) && rect.equals(cell.rect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBomb, countBombNeighbours, text, rect);
    }
}
