package touro.snake.strategy;

import touro.snake.Garden;
import touro.snake.Snake;
import touro.snake.Square;

import java.util.List;

/**
 * Interface for the Strategy Design Pattern used to turn the Snake.
 * https://en.wikipedia.org/wiki/Strategy_pattern
 */
public interface SnakeStrategy {

    /**
     * Turn the Snake based on parameters in the Garden.
     * @param snake
     * @param garden
     */
    void turnSnake(Snake snake, Garden garden);

    /**
     * @return a list of Squares that the Snake will traverse to the Food
     */
    List<Square> getPath();

    /**
     * @return a list of Squares that the Snake considered.
     */
    List<Square> getSearchSpace();

}
