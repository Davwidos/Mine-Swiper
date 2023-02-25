package com.example.mine_swiper.Scene.MenuLeyout;

import com.example.mine_swiper.Scene.Clickable;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;

public class Button extends Group implements Clickable {
    private Rectangle rect;
    private Text text;
    private ActionOnClicked action;
    public Button(String text, double x,double y,double width,double height,Menu menu) {
        super();
        rect = new Rectangle(x,y,width,height);
        rect.setFill(Color.BLUE);
        getChildren().add(rect);
        this.text = new Text(text);
        this.text.setFont(new Font(height));
        this.text.setY(rect.getHeight());
        getChildren().add(this.text);
        menu.getChildren().add(this);
    }

    @Override
    public void clicked(MouseButton button) {
        System.out.println(this);
        if(action == null) return;
        if(button.equals(MouseButton.PRIMARY)) action.action();
    }

    public void setAction(ActionOnClicked action) {
        this.action = action;
    }
}
