import javax.xml.stream.events.StartDocument;

public class MazeSolver {
    private char[][] maze;

    public MazeSolver(char[][] maze) {
        this.maze = maze;
    }
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }
    public boolean solve() {
        int startRow = -1;
        int startCol = -1;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
            if (startRow != -1) {
                break;
            }
        }
        if (startRow != -1) {
            return solve(startRow, startCol);
        }
        return false;
}

private boolean solve(int row, int col) {
    if (row < 0 || row >= maze.length || col < 0 || col >= maze[row].length) {
        return false;
    } 
    if (maze[row][col] == '#' || maze [row][col] == '.') {
        return false;
    }
    if (maze[row][col] == 'F') {
        return true;
    }
    char original = maze[row][col];
    maze[row][col] = '.';

    if (solve(row - 1, col)) {
        return true;
    }
    if (solve(row, col + 1)) {
        return true;
    }
    if (solve(row + 1, col)) {
        return true;
    }
    if (solve(row, col - 1)) {
        return true;
    }

    maze[row][col] = original;
    return false;
}

public static void main(String[] args) {
    char[][] mazeToSolve = {
        {'#', '#', '#', '#', '#', '#', '#'},
        {'#', 'S', ' ', ' ', '#', 'F', '#'},
        {'#', ' ', '#', ' ', '#', ' ', '#'},
        {'#', ' ', '#', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', '#', '#', ' ', '#'},
        {'#', '#', '#', '#', '#', '#', '#'}
    };
    MazeSolver solver = new MazeSolver(mazeToSolve);
    System.out.println("Original Maze:");

    solver.printMaze();
    if (solver.solve()) {
        System.out.println("Solution Found!");
        } else {
        System.out.println("No Solution Found.");
        }
    solver.printMaze();
     
    char[][] mazeWithoutSolution = {
        {'#', '#', '#', '#', '#'},
        {'#', 'S', '#', 'F', '#'},
        {'#', '#', '#', '#', '#'}
        };
    MazeSolver solver2 = new MazeSolver(mazeWithoutSolution);
    System.out.println("Maze Without a Solution:");
    solver2.printMaze();
    if (solver2.solve()) {
        System.out.println("Solution Found!");
    } else {
        System.out.println("No Solution Found.");
    }
    solver2.printMaze();
    }
}
