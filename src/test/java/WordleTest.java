
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WordleTest {

    @Test
    @DisplayName("Yellow letters should not be overcounted - Original test case")
    void testYellowNotOvercounted() {
        // OTTER vs WATER → T,E,R match exactly at positions 2,3,4
        String feedback = Wordle.getFeedbackCode("OTTER", "WATER");
        // O=X, T=X, T=G, E=G, R=G
        assertEquals("XXGGG", feedback);
    }

    @Test
    @DisplayName("Greens and yellows work correctly - original test case")
    void testGreensAndYellows() {
        // EAGLE vs WATER → E=Y, A=G, G=X, L=X, E=X
        String feedback = Wordle.getFeedbackCode("EAGLE", "WATER");
        // E=Y, A=G, G=X, L=X, E=X (last E can't be yellow because position 3 E already used)
        assertEquals("YGXXX", feedback);
    }

    @Test
    @DisplayName("Perfect match should return all greens")
    void testAllGreen() {
        String feedback = Wordle.getFeedbackCode("WATER", "WATER");
        assertEquals("GGGGG", feedback);
    }

    @Test
    @DisplayName("No matching letters should return all grays")
    void testNoMatches() {
        String feedback = Wordle.getFeedbackCode("PIZZA", "HOUND");
        assertEquals("XXXXX", feedback);
    }

    @Test
    @DisplayName("Complex case: repeated letters with mixed positions")
    void testRepeatedLettersMixed() {
        // SPEED vs ERASE → S=Y, P=X, E=Y, E=Y, D=X
        String feedback = Wordle.getFeedbackCode("SPEED", "ERASE");
        assertEquals("YXYYX", feedback);
    }

    @Test
    @DisplayName("Letter appears more times in guess than in answer")
    void testMoreInGuessThanAnswer() {
        // HELLO vs WATER → H=X, E=Y, L=X, L=X, O=X
        String feedback = Wordle.getFeedbackCode("HELLO", "WATER");
        assertEquals("XYXXX", feedback);
    }

    @Test
    @DisplayName("Letter appears more times in answer than in guess")
    void testMoreInAnswerThanGuess() {
        // PAPER vs ERASE → P=X, A=Y, P=X, E=Y, R=Y
        String feedback = Wordle.getFeedbackCode("PAPER", "ERASE");
        assertEquals("XYXYY", feedback);
    }

    @Test
    @DisplayName("All different letters, some in wrong positions")
    void testAllDifferentSomeWrong() {
        // FRUIT vs WATER → F=X, R=Y, U=X, I=X, T=Y
        String feedback = Wordle.getFeedbackCode("FRUIT", "WATER");
        assertEquals("XYXXY", feedback);
    }

    @Test
    @DisplayName("Complex repeated letter scenario")
    void testComplexRepeatedScenario() {
        // OTTER vs WATER → T,E,R match exactly at positions 2,3,4
        String feedback = Wordle.getFeedbackCode("OTTER", "WATER");
        assertEquals("XXGGG", feedback);
    }

    @Test
    @DisplayName("Green takes priority over yellow")
    void testGreenPriority() {
        // EAGLE vs EAGLE → should be all green, not yellow
        String feedback = Wordle.getFeedbackCode("EAGLE", "EAGLE");
        assertEquals("GGGGG", feedback);
    }

    @Test
    @DisplayName("Edge case: same letter in multiple positions")
    void testSameLetterMultiplePositions() {
        // PIZZA vs PAPER → P=G, I=X, Z=X, Z=X, A=Y
        String feedback = Wordle.getFeedbackCode("PIZZA", "PAPER");
        assertEquals("GXXXY", feedback);
    }

    @Test
    @DisplayName("Verify actual colored output contains expected letters")
    void testColoredOutputContainsLetters() {
        String coloredFeedback = Wordle.feedback("OTTER", "WATER");
        // Should contain the guessed letters
        assertTrue(coloredFeedback.contains("O"));
        assertTrue(coloredFeedback.contains("T"));
        assertTrue(coloredFeedback.contains("E"));
        assertTrue(coloredFeedback.contains("R"));
    }
}