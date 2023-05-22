import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {

    private static final String ALPHABET = "abcdefg";
    private static final int GRID_LENGTH = 7;
    private static final int GRID_SIZE = 49;
    private static final int MAX_ATTEMPTS = 2000;
    static final int HORIZONTAL_INCREMENT = 1;
    static final int VERTICAL_INCREMENT = GRID_LENGTH;

    private final int[] grid = new int[GRID_SIZE];
    private final Random random = new Random();
    private int planeCount = 0;
    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    public ArrayList<String> placePlane(int planeSize) {
        // holds index to grid (0-48)
        int[] planeCoords = new int[planeSize];
        int attempts = 0;
        boolean success = false;

        planeCount++;
        int increment = getIncrement();

        while (!success & attempts++ < MAX_ATTEMPTS) {
            int location = random.nextInt(GRID_SIZE);
            for(int i = 0; i < planeCoords.length; i++) {
                planeCoords[i] = location;
                location += increment;
            }
            //   System.out.println("Trying: " + Arrays.toString(startupCoords));

            if(startupFits(planeCoords, increment)) {
                success = coordsAvailable(planeCoords);
            }
        }
        savePositionToGrid(planeCoords);
        ArrayList<String> alphaCells = convertCoordsToAlphaFormat(planeCoords);
       // System.out.println("Placed at: "+ alphaCells);             // reveal locations
        return alphaCells;
    }  //end placePlane

    private boolean startupFits(int[] startupCoords, int increment) {
        int finalLocation = startupCoords[startupCoords.length - 1];
        if (increment == HORIZONTAL_INCREMENT) {
            //check end is on the same row as start
            return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation);
        } else {
            return finalLocation < GRID_SIZE;
        }
    }

    private boolean coordsAvailable(int[] planeCoords) {
        for (int coord : planeCoords) {
            if (grid[coord] != 0) {
                //System.out.println("position:" + coord + " already taken.");
                return false;
            }
        }
        return true;
    }

    private void savePositionToGrid(int[] planeCoords) {
        for (int index : planeCoords) {
            grid[index] = 1;
        }
    }

    private ArrayList<String> convertCoordsToAlphaFormat(int[] planeCoords) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        for (int index : planeCoords) {
            String alphaCoords = getAlphaCoordsFromIndex(index);
            alphaCells.add(alphaCoords);
        }
        return alphaCells;
    }
    private String getAlphaCoordsFromIndex(int index) {
        int row = calcRowFromIndex(index);
        int column = index % GRID_LENGTH;
        String letter = ALPHABET.substring(column, column + 1);
        return letter + row;
    }

    private int calcRowFromIndex(int index) {
        return index / GRID_LENGTH;
    }

    private int getIncrement() {
        if(planeCount % 2 == 0) {
            return HORIZONTAL_INCREMENT;
        } else {
            return VERTICAL_INCREMENT;
        }
    }
}
