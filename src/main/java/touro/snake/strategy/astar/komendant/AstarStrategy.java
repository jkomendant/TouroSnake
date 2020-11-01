package touro.snake.strategy.astar.komendant;

import touro.snake.*;
import touro.snake.strategy.SnakeStrategy;
import touro.snake.strategy.astar.Node;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AstarStrategy implements SnakeStrategy {

    List<Node> open = new ArrayList<>();
    List<Node> closed = new ArrayList<>();
    List<Square> path = new ArrayList<>();
    List<Square> searchSpace = new ArrayList<>();

    @Override
    public void turnSnake(Snake snake, Garden garden) {
        Direction[] directions = Direction.values();
        Food food = garden.getFood();
        Square head = snake.getHead();

        open.clear();
        closed.clear();
        path.clear();
        searchSpace.clear();

        if (food == null) {
            return;
        }

        open.add(new Node(snake.getHead()));

        findFoodPath(food, head, snake, directions);

    }

    public void findFoodPath(Food food, Square head, Snake snake, Direction[] directions){
        while (!open.isEmpty()) {
            Node current = getLowestCost(open);
            open.remove(current);
            closed.add(current);

            if (current.equals(food)) {
                Node firstChild = getFirstChild(head, current);
                Direction direction = head.directionTo(firstChild);
                snake.turnTo(direction);
                path.add(new Square(current.getX(), current.getY()));
                break;
            }
            addChildren(snake, food, directions, current);
        }
    }

    public void addChildren(Snake snake, Food food, Direction[] directions, Node current){
        for(Direction direction : directions){
            Node neighbor = new Node(current.moveTo(direction), current, food);
            if(!neighbor.inBounds() || snake.contains(neighbor) || closed.contains(neighbor)){
                continue;
            }
            int index = open.indexOf(neighbor);
            if(index != -1){
                Node oldNeighbor = open.get(index);
                if(neighbor.getCost() < oldNeighbor.getCost()){
                    open.remove(index);
                    open.add(neighbor);
                }
            }
            else {
                open.add(neighbor);
                searchSpace.add(new Square(neighbor.getX(), neighbor.getY()));
            }
        }
    }

    @Override
    public List<Square> getPath() {
        return path;
    }

    @Override
    public List<Square> getSearchSpace() {
        return searchSpace;
    }

    private Node getLowestCost(List<Node> nodes){
        return nodes.stream()
                .min(Comparator.comparingDouble(Node::getCost))
                .get();
    }

    public Node getFirstChild(Square head, Node end){
        Node n = end;
        while (!n.getParent().equals(head)){
            n = n.getParent();
        }
        return n;
    }
}
