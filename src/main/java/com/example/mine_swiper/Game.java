package com.example.mine_swiper;

import com.example.mine_swiper.Controllers.GameFiledController;
import com.example.mine_swiper.Scene.GameField;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.FileSystem;
import java.util.Properties;

public class Game extends Application {
    private static int HEIGHT = 600;
    private static int WIDTH = 600;

    private static int difficulty = 10;
    public static int getDifficulty() {
        return difficulty;
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadSettings();
        GameField root = new GameField(WIDTH,HEIGHT);
        Scene scene = new Scene(root);
        stage.setTitle("Mine Swiper");
        stage.setScene(scene);
        stage.show();
    }
    private void loadSettings() throws IOException {
        JFileChooser fc = new JFileChooser();
        FileSystemView fw = fc.getFileSystemView();
        String settingsPath = fw.getDefaultDirectory()+"\\Mine Swiper\\settings.ini";
        File file = new File(settingsPath);
        Properties properties = new Properties();
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            properties.setProperty("height",String.valueOf(HEIGHT));
            properties.setProperty("width",String.valueOf(WIDTH));
            properties.setProperty("difficulty",String.valueOf(difficulty));
            properties.store(outputStream,null);
            outputStream.close();
        }
        else {
            InputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
            HEIGHT = Integer.parseInt(properties.getProperty("height").trim());
            WIDTH = Integer.parseInt(properties.getProperty("width").trim());
            difficulty = Integer.parseInt(properties.getProperty("difficulty").trim());
            inputStream.close();
        }
    }
    private void createGrid(){

    }

}
