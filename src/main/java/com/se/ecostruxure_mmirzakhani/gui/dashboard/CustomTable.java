package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CustomTable extends VBox {
    @FXML
    private VBox rows;

    @FXML
    private List<Node> nodes = new ArrayList<>();
    public CustomTable(){
    }

    public void setRowsBox(VBox rowsBox){
        this.rows = rowsBox;
    }

    public void setItems(List<Node> nodes){
        this.nodes = nodes;
    }

    public void addItem(Node node){
        this.nodes.add(node);

        execute();
    }
    public void execute(){
        rows.getChildren().setAll(nodes);
    }
}
