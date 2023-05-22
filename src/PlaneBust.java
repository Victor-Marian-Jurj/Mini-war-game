import java.util.ArrayList;

public class PlaneBust {


    private int numOfGuesses = 0;
    private GameHelper helper = new GameHelper();
    private ArrayList<Plane> planes = new ArrayList<Plane>();

    void setUpGame() {

        Plane one = new Plane();
        one.setName("Black pearl");
        Plane two = new Plane();
        two.setName("White pearl");
        Plane three = new Plane();
        three.setName("Red pearl");

        planes.add(one);
        planes.add(two);
        planes.add(three);

        for(Plane startup : planes) {
            ArrayList<String> newLocation = helper.placePlane(3);
            startup.setLocationCells(newLocation);
        }
    }


    void  startPlaying (){
        while(!planes.isEmpty()) {
            String userGuess = helper.getUserInput("Enter a guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess){
        numOfGuesses++;
        String result = "miss";
        for( Plane planeToTest : planes) {
            result = planeToTest.checkYourself(userGuess);

            if(result.equals("hit")) {
                break;
            }
            if(result.equals("kill")){
                planes.remove(planeToTest);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame(){
        System.out.println("All Planes are dead!");
        if(numOfGuesses < 18){
            System.out.println("It only took you " + numOfGuesses + " guesses.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
        }
    }

    public static void main(String[] args){
        PlaneBust game = new PlaneBust();
        game.setUpGame();
        game.startPlaying();
    }
}




