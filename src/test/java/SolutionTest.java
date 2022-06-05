import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private static final String HUMAN = "Human";
    private static final String SWAMPER = "Swamper";
    private static final String WOODMAN = "Woodman";
    private static final String PLAY_FIELD_1 = "STWSWTPPTPTTPWPP";
    private static final String PLAY_FIELD_2 = "TSPTWWPTSTPSWPTS";
    private static final String PLAY_FIELD_3 = "SSSSSSSSSSSSSSSS";
    private static final String PLAY_FIELD_4 = "WWWWWWWWWWWWWWWW";
    private static final String PLAY_FIELD_5 = "TTTTTTTTTTTTTTTT";
    private static final String PLAY_FIELD_6 = "PPPPPPPPPPPPPPPP";

    @Test
    void testForHuman() {
        assertEquals(10, Solution.getResult(PLAY_FIELD_1, HUMAN));
        assertEquals(14, Solution.getResult(PLAY_FIELD_2, HUMAN));
        assertEquals(30, Solution.getResult(PLAY_FIELD_3, HUMAN));
        assertEquals(12, Solution.getResult(PLAY_FIELD_4, HUMAN));
        assertEquals(18, Solution.getResult(PLAY_FIELD_5, HUMAN));
        assertEquals(6, Solution.getResult(PLAY_FIELD_6, HUMAN));
    }

    @Test
    void testForSwamper() {
        assertEquals(15, Solution.getResult(PLAY_FIELD_1, SWAMPER));
        assertEquals(12, Solution.getResult(PLAY_FIELD_2, SWAMPER));
        assertEquals(12, Solution.getResult(PLAY_FIELD_3, SWAMPER));
        assertEquals(12, Solution.getResult(PLAY_FIELD_4, SWAMPER));
        assertEquals(30, Solution.getResult(PLAY_FIELD_5, SWAMPER));
        assertEquals(12, Solution.getResult(PLAY_FIELD_6, SWAMPER));
    }

    @Test
    void testForWoodman() {
        assertEquals(12, Solution.getResult(PLAY_FIELD_1, WOODMAN));
        assertEquals(14, Solution.getResult(PLAY_FIELD_2, WOODMAN));
        assertEquals(18, Solution.getResult(PLAY_FIELD_3, WOODMAN));
        assertEquals(18, Solution.getResult(PLAY_FIELD_4, WOODMAN));
        assertEquals(12, Solution.getResult(PLAY_FIELD_5, WOODMAN));
        assertEquals(12, Solution.getResult(PLAY_FIELD_6, WOODMAN));
    }

    @Test
    void checkForNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                Solution.getResult(null, HUMAN));
        assertThrows(NullPointerException.class, () ->
                Solution.getResult(PLAY_FIELD_1, null));
    }

    @Test
    void checkForIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                Solution.getResult("", HUMAN));
        assertThrows(IllegalArgumentException.class, () ->
                Solution.getResult("SSSSSSSSSSSSSSS", HUMAN));
        assertThrows(IllegalArgumentException.class, () ->
                Solution.getResult("SSSSSSSSSSSSSSSs", HUMAN));
        assertThrows(IllegalArgumentException.class, () ->
                Solution.getResult(PLAY_FIELD_1, ""));
        assertThrows(IllegalArgumentException.class, () ->
                Solution.getResult(PLAY_FIELD_1, "HUMAN"));
        assertThrows(IllegalArgumentException.class, () ->
                Solution.getResult(PLAY_FIELD_1, "human"));
    }
}
