package SysDesign;

import java.util.Deque;
import java.util.LinkedList;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

// leetcode 353
/**
 * Explanation
SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
snakeGame.move("R"); // return 0
snakeGame.move("D"); // return 0
snakeGame.move("R"); // return 1, snake eats the first piece of food. The second piece of food appears at (0, 1).
snakeGame.move("U"); // return 1
snakeGame.move("L"); // return 2, snake eats the second food. No more food appears.
snakeGame.move("U"); // return -1, game over because snake collides with border
 */
public class SnakeGame {

    private int[][] m;
    private int[][] food;
    private int foodIndex;
    private int count;
    private Deque<Pair<Integer, Integer>> snake = new LinkedList<>();
    public SnakeGame(int width, int height, int[][] food){
        m = new int[height][width];
        this.food = food;
        this.foodIndex = 0;
        this.count = 0;
        snake.add(new ImmutablePair<Integer, Integer>(0, 0));
    }

    public int move(String direction){
        // cal next head position
        Pair<Integer, Integer> head = snake.peek();
        int x = head.getLeft();
        int y = head.getRight();
        if(direction == "R"){
            y++;
        } else if (direction == "D"){
            x++;
        } else if (direction == "L"){
            y--;
        } else if (direction == "U"){
            x--;
        }
        System.out.println(head);
        System.out.println(snake);

        if(x < 0 || y < 0 || x >= m.length || y >= m[0].length){
            return -1;
        }
        Pair<Integer, Integer> nextPos = new ImmutablePair<Integer, Integer>(x, y);
        // if has food
        if (foodIndex < food.length && food[foodIndex][0] == x && food[foodIndex][1] == y){
            count++;
            foodIndex++;
            snake.addFirst(nextPos);
            return count;
        } else {
            // else see can move
            snake.removeLast();
            if (snake.contains(nextPos)){
                return -1;
            }

            snake.addFirst(nextPos);
        }
        return count;
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][]{{1,2}, {0,1}});
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("U"));
        System.out.println("test");
    }
}
