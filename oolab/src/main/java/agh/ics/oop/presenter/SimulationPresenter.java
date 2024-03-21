package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulationPresenter implements MapChangeListener {

    private static final int CELL_WIDTH = 50;
    private static final int CELL_HEIGHT = 50;

    private WorldMap map;
    @FXML
    public Label infoLabel;
    @FXML
    public javafx.scene.control.TextField movesList;
    @FXML
    public Label lastMove;
    @FXML
    public GridPane mapGrid;


    public void setWorldMap(WorldMap map){
        this.map = map;
    }


    public void drawMap(){
        clearGrid();
        Boundary boundary = map.getCurrentBounds();
        int map_width = boundary.upper_right().getX() - boundary.lower_left().getX();
        int map_height = boundary.upper_right().getY() - boundary.lower_left().getY();

        for (int i=0; i<=map_width+1; i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
        for (int i=0; i<=map_height+1; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }

        Label legend = new Label("y\\x");
        mapGrid.add(legend, 0, 0);
        GridPane.setHalignment(legend, HPos.CENTER);

        for (int i=boundary.lower_left().getX(); i<=boundary.upper_right().getX(); i++){
            Label label = new Label(String.valueOf(i));
            mapGrid.add(label, i - boundary.lower_left().getX()  + 1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i=boundary.upper_right().getY(); i>=boundary.lower_left().getY(); i--){
            Label label = new Label(String.valueOf(i));
            mapGrid.add(label, 0, boundary.upper_right().getY() - i + 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        Map<Vector2d, WorldElement> elements = map.getElements();
        elements.forEach((position, element) -> {
            Label label = new Label(element.toString());
            mapGrid.add(label, position.getX() - boundary.lower_left().getX() + 1, boundary.upper_right().getY() - position.getY() + 1);
            GridPane.setHalignment(label, HPos.CENTER);
        });
    }

    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        Platform.runLater(() -> {
            lastMove.setText(message);
            drawMap();
        });
    }

    public void onSimulationStartClicked() {
        infoLabel.setVisible(false);
        mapGrid.setVisible(true);
        mapGrid.setGridLinesVisible(true);
        java.util.List<Vector2d> positions = java.util.List.of(new Vector2d(2,2), new Vector2d(3,4));
        String[] parameters = movesList.getText().split(" ");
        List<MoveDirection> directions = OptionsParser.argsToEnum(parameters);
        Simulation simulation = new Simulation(positions, directions, map);
        List<Simulation> simulations = new ArrayList<>();
        simulations.add(simulation);
        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runAsync();
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
}
