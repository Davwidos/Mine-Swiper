package com.example.mine_swiper.Controllers;

import com.example.mine_swiper.Scene.Clickable;
import com.example.mine_swiper.Scene.GameField;

import com.example.mine_swiper.Scene.GameObjects.Cell;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class MouseObserver {

    public static void addClickable(List<Clickable> nodes){
        for(Clickable clickable:nodes) {
            if(!(clickable instanceof Node)) continue;
            ((Node)clickable).addEventFilter(MouseEvent.ANY,event->mouseEvent(event));
        }
    }

    public static void mouseEvent(MouseEvent event){
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
            mouseClicked(event.getButton(),event.getTarget());
        }
    }
    private static void mouseClicked(MouseButton button,EventTarget target){
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
