package com.example.mine_swiper.Scene.MenuLeyout;

import com.example.mine_swiper.Controllers.MouseObserver;
import com.example.mine_swiper.Game;
import com.example.mine_swiper.Scene.Clickable;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Menu extends Group {
    private Rectangle rect;
    private List<Clickable> buttonList;
    public Menu(double width,double height) {
        super();
        rect = new Rectangle(width,height);
        getChildren().add(rect);
        buttonList = new ArrayList<>();
        Button newGame = new Button("New Game",0,0,width,50,this);
        newGame.setAction(()->Game.game.newGame());
        MouseObserver.addClickableElement(newGame);
        buttonList.add(newGame);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return rect.equals(menu.rect) && buttonList.equals(menu.buttonList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rect, buttonList);
    }
    public void close(){
        MouseObserver.removeClickableList(buttonList);
    }
}
