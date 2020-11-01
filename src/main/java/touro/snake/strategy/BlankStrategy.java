package touro.snake.strategy;

import touro.snake.Garden;
import touro.snake.Snake;
import touro.snake.Square;

import java.util.Collections;
import java.util.List;

/**
 * An implementation of SnakeStrategy that has no effect.
 */
public class BlankStrategy implements SnakeStrategy {
    @Override
    public void turnSnake(Snake snake, Garden garden) {

    }

    @Override
    public List<Square> getPath() {
        return Collections.emptyList();
    }

    @Override
    public List<Square> getSearchSpace() {
        return Collections.emptyList();
    }
}
