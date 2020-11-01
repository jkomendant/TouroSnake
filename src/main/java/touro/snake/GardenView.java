package touro.snake;

import touro.snake.strategy.astar.komendant.AstarStrategy;

import javax.swing.*;
import java.awt.*;

public class GardenView extends JComponent {

    private final Garden garden;
    public static final int CELL_SIZE = 10;
    private final AstarStrategy aStarStrategy;

    public GardenView(Garden garden, AstarStrategy aStarStrategy) {
        this.garden = garden;
        this.aStarStrategy = aStarStrategy;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGrass(g);
        paintFood(g);
        paintSnake(g);
        paintSearchSpace(g);
        paintPath(g);
    }

    void paintGrass(Graphics g) {
        // Berger
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    void paintSnake(Graphics g) {
        g.setColor(Color.RED);
        for (Square s : garden.getSnake().getSquares()) {
            g.fillRect(s.getX()*CELL_SIZE, s.getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    void paintFood(Graphics g) {
        // Berger
        if (garden.getFood() != null) {
            Food food = garden.getFood();
            g.setColor(Color.LIGHT_GRAY);

            int x = food.getX() * CELL_SIZE;
            int y = food.getY() * CELL_SIZE;
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }
    }

    void paintPath(Graphics g){
        g.setColor(Color.YELLOW);
        for(Square s : aStarStrategy.getPath()){
            g.fillRect(s.getX()*CELL_SIZE, s.getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

    }

    void paintSearchSpace(Graphics g){
        g.setColor(Color.BLUE);
        for(Square s : aStarStrategy.getSearchSpace()){
            g.fillRect(s.getX()*CELL_SIZE, s.getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

    }
}
