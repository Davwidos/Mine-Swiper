package com.example.mine_swiper.Controllers;

import com.example.mine_swiper.Clickable;
import com.example.mine_swiper.Game;
import com.example.mine_swiper.Scene.GameField;

import com.example.mine_swiper.Scene.GameObjects.Cell;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GameFiledController{
    private GameField gameField;
    public GameFiledController(GameField gameField){
        this.gameField = gameField;
        gameField.addEventFilter(KeyEvent.ANY,event ->keyboardEvent(event));
        Cell[][] grid = gameField.getGrid();
        for(Cell[] g:grid) for (Cell cell:g) cell.addEventFilter(MouseEvent.ANY,event->mouseEvent(event));
    }

    public void mouseEvent(MouseEvent event){
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
            EventTarget eventTarget = event.getTarget();
            mouseClicked(event.getButton(),eventTarget);
        }
    }
    public void keyboardEvent(KeyEvent event){

    }
    private void mouseClicked(MouseButton button,EventTarget target){
        if(target instanceof Clickable){
            Clickable clickable = (Clickable) target;
            clickable.clicked(button);
            return;
        }
        if(target instanceof Node){
            Node parent = ((Node) target).getParent();
            if(parent != null) mouseClicked(button,parent);
        }
    }

}
