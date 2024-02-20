/* 
 * A class intended to correctly follow the 4 rules of John Conway's GameOfLife
 * using both public and private static methods in order to efficiently test and
 * print the GameOfLife after 3 generations and applied rules
 */

public class GameOfLife {
    
	/* 
	 * An optional main method which tests figure 2 on the assignment 1 instructions
	 * by the utilization of boolean in which true means there is a living cell
	 * and false for when there is no living cell
	 */
	public static void main(String[] args) {
        boolean[][] generation = {
                {true, false, false, false, true, false},
                {false, false, false, false, true, true},
                {false, true, true, false, false, false},
                {false, true, false, false, true, false},
                {false, true, false, true, false, false},
                {false, false, false, false, false, false},
        };
        //will go up to three generations
        int numGens = 3; 
        for (int i = 0; i < numGens; i++) {
        	//esthetics 
        	System.out.println("------------------------------------");
        	//prints header for generations
        	System.out.println("\nGeneration " + (i + 1) + ":");
        	//esthetics
        	System.out.println("_________________________\n");
        	//prints current generation
        	printGeneration(generation);
        	//creates next generation to print
        	generation = createGeneration(generation);
        }
    }
    
	/* 
	 * A private static method in which is utilized by the main method to print the 
	 * grid in 'X''O' format while also printing the living CellLocations in (row, column)
	 * format 
	 */
    private static void printGeneration(boolean[][] generation) {
    	//prints the grid for the GameOfLife
    	for (int i = 0; i < generation.length; i++) {
    		for (int j = 0; j < generation[0].length; j++) {
    			System.out.print("|");
    			System.out.print(generation[i][j] ? " X " : " O ");
    		}
    		System.out.println("|");
    	}
    		int[][] livingCellLocations = findLivingCellLocations(generation);
    		//printing the living cell locations in (row, column) format after locating them
    		if (livingCellLocations.length > 0) {
    			//esthetics and printing
    			System.out.print("_________________________");
    			System.out.println("\n");
    			System.out.println("Living cell locations: (row, column) ");
    			System.out.println();
    			for (int[] location : livingCellLocations) {
    				int row = location[0];
    				int col = location[1];
    				System.out.printf("(%d, %d)\n", row, col);
    			}
    			System.out.println();
    		}
        	
        }

    /*
     * a public static method that creates the next generation based on the current generation
     * after applied rules
     */
    public static boolean[][] createGeneration(boolean[][] generation){
        int rows = generation.length;
        int cols = generation.length;
        boolean[][] nextGeneration = new boolean[rows][cols];
        //Applying the rules to every cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int livingNeighbors = countLivingNeighbors(generation, i, j);
                nextGeneration[i][j] = applyRules(generation[i][j], livingNeighbors);
            }
        }
        return nextGeneration;
    }
    /*
     * a public static method intended to find the location of the cells that are alive
     */
    public static int[][] findLivingCellLocations(boolean[][] generation){
    	//counts the cells that are alive
    	int livingCells = 0;
        for (int i = 0; i < generation.length; i++) {
        	for (int j = 0; j < generation[0].length; j++) {
        		if (generation[i][j]) {
        			livingCells++;
        			}
        		}
        	}
        //storing the locations of the cells that are alive
        int[][] livingCellLocations =new int[livingCells][2];
        int index = 0;
        for (int i = 0; i < generation.length; i++) {
        	for (int j = 0; j < generation[0].length; j++) {
        		if (generation[i][j]) {
        			livingCellLocations[index][0] = i;
        			livingCellLocations[index][1] = j;
        			index ++;
        			}
        		}
        	}
        return livingCellLocations;
        }
    /*
     * a private static method that counts the neighbors that are alive of each cell
     */
    private static int countLivingNeighbors(boolean[][] generation, int row, int col) {
    	int count = 0;
    	for (int i = row - 1; i <= row + 1; i++) {
    		for (int j = col - 1; j <= col + 1; j++) {
    			if (i >=0 && i < generation.length && j >= 0 && j < generation[0].length && !(i ==row && j == col) && generation[i][j]) {
    				count++;
    				}
    			}
    		}
    	return count;
    	}
    /*
     * a private static method that applies the rules of the GameOfLife based on whether cells are alive or have the living 
     * neighbor requirements
     */
    private static boolean applyRules(boolean isAlive, int livingNeighbors) {
    	if (isAlive) {
    		return livingNeighbors == 2 || livingNeighbors == 3;
    		}
    	else {
    		return livingNeighbors == 3;
    		}
    	}
    }

