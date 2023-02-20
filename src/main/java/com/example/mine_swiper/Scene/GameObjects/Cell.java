package com.example.mine_swiper.Scene.GameObjects;

import com.example.mine_swiper.Clickable;
import com.example.mine_swiper.Game;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cell extends Group implements Clickable {
    private static final String BOMB = "\uD83D\uDCA3";
    private boolean isBomb = false;
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
    private void show(){
        if(isBomb) showBomb();
        text.setFont(new Font(Math.min(rect.getWidth(),rect.getHeight())));
        text.setX(rect.getX());
        text.setY(rect.getY()+rect.getHeight());
    }
    private void showBomb(){
        text.setText(BOMB);
        rect.setFill(Color.RED);
    }

    @Override
    public void clicked(MouseButton button) {
        if(button.equals(MouseButton.PRIMARY)) show();
    }
    public void setBomb(int difficulty){
        int random = (int)(Math.random()*100);
        isBomb = random <= difficulty;
    }
}
