package com.example.mine_swiper.Controllers;

import com.example.mine_swiper.Game;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import org.w3c.dom.events.EventTarget;

public class KeyboardObserver {
    public static void addHandler(Scene target){
        target.addEventHandler(KeyEvent.ANY,event->keyboardEvent(event));
    }
    public static void keyboardEvent(KeyEvent event){
        if(event.getEventType().equals(KeyEvent.KEY_PRESSED)){
            switch (event.getCode()){
                case ESCAPE : Game.game.menu();break;
            }
        }
    }
}
