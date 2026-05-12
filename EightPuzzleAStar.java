import java.util.*;

class Node {
    int[][] board;
    int emptyRow, emptyCol;
    int gCost, hCost;
    Node parent;

    // Constructor
    Node(int[][] board, int emptyRow, int emptyCol,
         int gCost, Node parent) {

        this.board = new int[3][3];

        for (int i = 0; i < 3; i++)
            this.board[i] = board[i].clone();

        this.emptyRow = emptyRow;
        this.emptyCol = emptyCol;
        this.gCost = gCost;
        this.hCost = calculateHeuristic();
        this.parent = parent;
    }

    // f(n) = g(n) + h(n)
    int getFCost() {
        return gCost + hCost;
    }

    // Manhattan Distance Heuristic
    int calculateHeuristic() {
        int distance = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                int value = board[i][j];

                if (value != 0) {

                    int targetRow = (value - 1) / 3;
                    int targetCol = (value - 1) % 3;

                    distance += Math.abs(i - targetRow)
                              + Math.abs(j - targetCol);
                }
            }
        }

        return distance;
    }

    // Convert board to string for hashing
    String boardToString() {
        StringBuilder sb = new StringBuilder();

        for (int[] row : board) {
            for (int val : row) {
                sb.append(val);
            }
        }

        return sb.toString();
    }
}

public class EightPuzzleAStar {

    // Goal State
    static int[][] goal = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 0, 8}
    };

    // Possible Moves
    static int[] rowMoves = {-1, 1, 0, 0};
    static int[] colMoves = {0, 0, -1, 1};

    public static void solve(int[][] initialBoard,
                             int emptyRow,
                             int emptyCol) {

        PriorityQueue<Node> openList =
                new PriorityQueue<>(Comparator.comparingInt(Node::getFCost));

        Set<String> closedList = new HashSet<>();

        Node start = new Node(initialBoard,
                              emptyRow,
                              emptyCol,
                              0,
                              null);

        openList.add(start);

        while (!openList.isEmpty()) {

            Node current = openList.poll();

            // Goal Check
            if (current.hCost == 0) {
                printSolution(current);
                return;
            }

            closedList.add(current.boardToString());

            // Generate all moves
            for (int i = 0; i < 4; i++) {

                int newRow = current.emptyRow + rowMoves[i];
                int newCol = current.emptyCol + colMoves[i];

                // Valid move check
                if (isValid(newRow, newCol)) {

                    int[][] newBoard = copyBoard(current.board);

                    // Swap empty tile
                    newBoard[current.emptyRow][current.emptyCol] =
                            newBoard[newRow][newCol];

                    newBoard[newRow][newCol] = 0;

                    Node child = new Node(
                            newBoard,
                            newRow,
                            newCol,
                            current.gCost + 1,
                            current
                    );

                    if (!closedList.contains(child.boardToString())) {
                        openList.add(child);
                    }
                }
            }
        }

        System.out.println("No Solution Found!");
    }

    // Check valid index
    static boolean isValid(int row, int col) {
        return row >= 0 && row < 3 &&
               col >= 0 && col < 3;
    }

    // Copy board
    static int[][] copyBoard(int[][] board) {

        int[][] newBoard = new int[3][3];

        for (int i = 0; i < 3; i++) {
            newBoard[i] = board[i].clone();
        }

        return newBoard;
    }

    // Print final solution path
    static void printSolution(Node node) {

        List<Node> path = new ArrayList<>();

        while (node != null) {
            path.add(node);
            node = node.parent;
        }

        Collections.reverse(path);

        System.out.println("Solution Steps:\n");

        for (Node n : path) {

            for (int[] row : n.board) {

                for (int val : row) {
                    System.out.print(val + " ");
                }

                System.out.println();
            }

            System.out.println("-------------");
        }

        System.out.println("Total Moves = " + (path.size() - 1));
    }

    public static void main(String[] args) {

        int[][] initialBoard = {
            {1, 2, 3},
            {4, 0, 6},
            {7, 5, 8}
        };

        solve(initialBoard, 1, 1);
    }
}