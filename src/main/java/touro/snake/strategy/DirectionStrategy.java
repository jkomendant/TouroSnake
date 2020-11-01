package touro.snake.strategy;

import touro.snake.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Chooses a Direction based on which neighbor is closest to the Food.
 */
public class DirectionStrategy implements SnakeStrategy {
    @Override
    public void turnSnake(Snake snake, Garden garden) {
        Direction[] directions = Direction.values();
        Food food = garden.getFood();
        Square head = snake.getHead();

        Direction direction = Arrays.stream(directions)
                .filter((Direction d) -> {
                    Square neighbor = head.moveTo(d);
                    return !snake.contains(neighbor) && neighbor.inBounds();
                })
                .min(Comparator.comparingDouble((Direction d) -> {
                    Square neighbor = head.moveTo(d);
                    return neighbor.distance(food);
                }))
                .get();

        snake.turnTo(direction);
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
