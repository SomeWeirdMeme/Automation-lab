import java.util.Arrays;
import java.util.Random;
/**
 * Model a 1D elementary cellular automaton.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class Automaton
{
    // The number of cells.
    private final int numberOfCells;
    // The state of the cells.
    private int[] state;
    private int[] stateTable;
    private int wolframCode;
    //question 38
    
    /**
     * Create a 1D automaton consisting of the given number of cells.
     * @param numberOfCells The number of cells in the automaton.
     */
    public Automaton(int numberOfCells, int wolframCode)
    {
        this.numberOfCells = numberOfCells;
        //question 38
        state = new int[numberOfCells];
        this.wolframCode = wolframCode;
        // Seed the automaton with a single 'on' cell in the middle.
        state[numberOfCells / 2] = 1;
        stateTable = new int [8];
        for(int i = 0; i < 8; i++){
            stateTable[i] = (wolframCode >> i ) & 1;
        }
    }
    
    /**
     * Print the current state of the automaton.
     */
    public void print()
    {
        for(int cellValue : state) {
            if(cellValue == 1) {
                System.out.print("*");
            }
            else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }   
    
    /**
     * Update the automaton to its next state.
     */
    public void update()
    {
        // Build the new state in a separate array
        int[] nextState = new int[state.length];
        // Naively update the state of each cell
        // based on the state of its two neighbors.
        // question 30 and 31 and 32
        for (int i = 0; i < state.length; i++){
            int left = (i == 0) ? state[state.length - 1] : state[i-1];
            int center = state[i];
            int right = (i == state.length - 1) ? state[0] : state[i+1];
            nextState[i] = calculateNextState(left, center, right);
        }
        state = nextState;
    }
    
    private int calculateNextState(int left, int center, int right){
        // question 34.
        int index = (left << 2) | (center << 1) | right;
        return stateTable[index];
        
    }
    
    /**
     * Reset the automaton.
     */
    public void reset()
    {
        Arrays.fill(state, 0);
        Random random = new Random();
        // Seed the automaton with a single 'on' cell.
        //question 29
        int numActive = state.length / 10;
        for(int i = 0; i < numActive; i++){
            int index = random.nextInt(state.length);
            state[index] = 1;
        }
    }
}
