class SimpleStartup {
    private int[] locationCells;
    private int numOfHits = 0;

    private int dud = 0;
    public void setLocationCells(int[] locs) {
        locationCells = locs;
    }

    public String checkYourself(int guess) {
        String result = "miss";
        for (int cell : locationCells) {
            if (guess == cell && dud != guess) {
                dud = guess;
                result = "hit";
                numOfHits++;
                break;
            } // end if
        } // end for
        if (numOfHits == locationCells.length) {
            result = "kill";
        } // end if
        System.out.println(result);
        return result;
    } // end method
} // close class
