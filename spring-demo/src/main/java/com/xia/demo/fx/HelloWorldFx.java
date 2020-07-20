package com.xia.demo.fx;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author xiafb
 * @date Created in 2020/5/8 17:43
 * description
 * modified By
 * version
 */
public class HelloWorldFx extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.show();
        primaryStage.setTitle("summer");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
