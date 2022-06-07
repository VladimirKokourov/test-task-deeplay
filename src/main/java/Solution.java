import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class Solution {

    public static int getResult(String playField, String creature)
            throws NullPointerException, IllegalArgumentException {

        final int FIELD_SIZE = 16;
        final String HUMAN = "Human";
        final String SWAMPER = "Swamper";
        final String WOODMAN = "Woodman";

        // check for null
        if (creature == null) {
            throw new NullPointerException("creature is null");
        }

        // local abstract class for creatures
        abstract class Creature {

            protected static final String SWAMP = "S";
            protected static final String WATER = "W";
            protected static final String TREE = "T";
            protected static final String PLAIN = "P";

            protected static final String SLOW = "5";
            protected static final String MEDIUM = "3";
            protected static final String FAST = "2";
            protected static final String VERY_FAST = "1";

            protected String swampTime;
            protected String waterTime;
            protected String treeTime;
            protected String plainTime;
            protected String name;

            // create an array of cell path times
            protected int[] createArrayTimeOfCell(String playField) throws IllegalArgumentException, NullPointerException {
                // check for null
                if (playField == null) {
                    throw new NullPointerException("playField is null");
                }
                // validation of playField string length
                if (playField.length() != FIELD_SIZE) {
                    throw new IllegalArgumentException("string length playField must be " + FIELD_SIZE + " characters");
                }
                // validation of playField chars
                if (Arrays.stream(playField.split("")).anyMatch(s ->
                        !Objects.equals(s, SWAMP) &&
                        !Objects.equals(s, WATER) &&
                        !Objects.equals(s, TREE) &&
                        !Objects.equals(s, PLAIN))) {
                    throw new IllegalArgumentException("playingField chars is not valid");
                }
                // change string to array digits
                return Arrays.stream(playField.split(""))
                        .map(s -> s.replace(SWAMP, swampTime))
                        .map(s -> s.replace(WATER, waterTime))
                        .map(s -> s.replace(TREE, treeTime))
                        .map(s -> s.replace(PLAIN, plainTime))
                        .mapToInt(Integer::parseInt).toArray();
            }

            @Override
            public String toString() {
                return name;
            }
        }

        class Human extends Creature {
            Human() {
                this.swampTime = SLOW;
                this.waterTime = FAST;
                this.treeTime = MEDIUM;
                this.plainTime = VERY_FAST;
                this.name = HUMAN;
            }
        }

        class Swamper extends Creature {
            Swamper() {
                this.swampTime = FAST;
                this.waterTime = FAST;
                this.treeTime = SLOW;
                this.plainTime = FAST;
                this.name = SWAMPER;
            }
        }

        class Woodman extends Creature {
            Woodman() {
                this.swampTime = MEDIUM;
                this.waterTime = MEDIUM;
                this.treeTime = FAST;
                this.plainTime = FAST;
                this.name = WOODMAN;
            }
        }

        // create reference for creature
        Creature curCreature;
        // create object for creature
        switch (creature) {
            case HUMAN:
                curCreature = new Human();
                break;
            case SWAMPER:
                curCreature = new Swamper();
                break;
            case WOODMAN:
                curCreature = new Woodman();
                break;
            default:
                throw new IllegalArgumentException("creature is not valid");
        }

        // initialization and fill array for current path time
        int[] result = new int[FIELD_SIZE];
        Arrays.fill(result, Integer.MAX_VALUE);

        // initialization and fill array for time depending on cell type
        int[] timeOfCell = curCreature.createArrayTimeOfCell(playField);

        // initialization and fill array for adjacency lists
        LinkedList<Integer>[] adjList = new LinkedList[FIELD_SIZE];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new LinkedList<>();
        }
        for (int i = 0; i < adjList.length - 1; i++) {
            if ((i + 1) % 4 == 0) {
                adjList[i].add(i + 4);
            } else if (i > 11) {
                adjList[i].add(i + 1);
            } else {
                adjList[i].add(i + 4);
                adjList[i].add(i + 1);
            }
        }

        // initialization queue for cells
        LinkedList<Integer> queue = new LinkedList<>();

        // initialization start cell
        queue.addFirst(0);
        result[0] = 0;

        // Dijkstra's algorithm
        while (!queue.isEmpty()) {
            // remember and remove the current cell from the queue
            int curCell = queue.removeLast();
            // iterate over adjacency list
            // if the path time is longer, change it to a shorter one
            // after adding the cell to the queue
            for (int adjCell : adjList[curCell]) {
                if (result[curCell] + timeOfCell[adjCell] < result[adjCell]) {
                    result[adjCell] = result[curCell] + timeOfCell[adjCell];
                    queue.addFirst(adjCell);
                }
            }
        }
        return result[FIELD_SIZE - 1];
    }
}
