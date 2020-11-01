package touro.snake;

import org.junit.Test;
import touro.snake.strategy.komendant.AstarStrategy;

import java.awt.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static touro.snake.GardenView.CELL_SIZE;

public class GardenViewTest {

    @Test
    public void paintGrass() {
        //given
        Garden garden = mock(Garden.class);
        AstarStrategy aStarStrategy = new AstarStrategy();
        GardenView view = new GardenView(garden, aStarStrategy);
        int width = view.getWidth();
        int height = view.getHeight();

        Graphics g = mock(Graphics.class);

        //when
        view.paintGrass(g);

        //then
        verify(g).fillRect(0, 0, width, height);
    }

    @Test
    public void paintSnake() {
        //given
        Snake snake = mock(Snake.class);
        Garden garden = mock(Garden.class);
        AstarStrategy aStarStrategy = new AstarStrategy();
        GardenView view = new GardenView(garden, aStarStrategy);
        Graphics g = mock(Graphics.class);

        List<Square> squares = List.of(
                new Square(50, 20),
                new Square(51, 21),
                new Square(52, 22)
        );

        when(garden.getSnake()).thenReturn(snake); //getting the snake from the garden
        when(snake.getSquares()).thenReturn(squares); //getting the list of squares created above

        //when
        view.paintSnake(g);

        //then  --checking that the cells (for which the coordinates are found in the list created above) are filled
        verify(g).fillRect(50 * CELL_SIZE, 20 * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        verify(g).fillRect(51 * CELL_SIZE, 21 * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        verify(g).fillRect(52 * CELL_SIZE, 22 * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }


    @Test
    public void paintFood() {
        //given
        Garden garden = mock(Garden.class);
        AstarStrategy aStarStrategy = new AstarStrategy();
        GardenView view = new GardenView(garden, aStarStrategy);

        when(garden.getFood()).thenReturn(mock(Food.class));

        Food food = garden.getFood();
        when(food.getX()).thenReturn(5);
        when(food.getY()).thenReturn(10);
        int x = food.getX() * GardenView.CELL_SIZE;
        int y = food.getY() * GardenView.CELL_SIZE;

        Graphics g = mock(Graphics.class);

        //when
        view.paintFood(g);

        //then
        verify(g).fillRect(x,y, GardenView.CELL_SIZE, GardenView.CELL_SIZE);
    }

    @Test
    public void paintFood_nullFood() {
        //given
        Garden garden = mock(Garden.class);
        AstarStrategy aStarStrategy = new AstarStrategy();
        GardenView view = new GardenView(garden, aStarStrategy);
        Graphics g = mock(Graphics.class);

        //when
        view.paintFood(g);

        //then
        verifyNoInteractions(g);
    }
}