import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Wordle {
    private final String answer;
    private final Scanner in = new Scanner(System.in);

    public Wordle(List<String> words) {
        this.answer = words.get(new Random().nextInt(words.size())).toUpperCase();
    }

    public static void main(String[] args) {
        try (InputStream is = Wordle.class.getClassLoader().getResourceAsStream(WordleConfig.WORDS_FILE);
            Scanner fileScanner = new Scanner(is)) {
            
            if (is == null) {
                throw new IllegalStateException("Could not find " + WordleConfig.WORDS_FILE + " in resources");
            }
            
            List<String> words = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                words.add(fileScanner.nextLine().trim());
            }
            
            System.out.println("Welcome to Wordle...! Guess the " + WordleConfig.WORD_LENGTH + "-letter word in " + WordleConfig.MAX_GUESSES + " tries.");
            new Wordle(words).play();
            
        } catch (Exception e) {
            System.err.println("Error loading game: " + e.getMessage());
            System.exit(1);
        }
    }

    void play() {
        for (int g = 1; g <= WordleConfig.MAX_GUESSES; g++) {
            System.out.print("Guess " + g + ": ");
            String guess = in.nextLine().trim().toUpperCase();
            
            if (!guess.matches("[A-Za-z]{" + WordleConfig.WORD_LENGTH + "}")) {
                System.out.println("Must be exactly " + WordleConfig.WORD_LENGTH + " letters (A-Z only).");
                g--; 
                continue;
            }
            
            String feedback = feedback(guess, answer);
            System.out.println(feedback);

            if (guess.equals(answer)) { 
                System.out.println("You win :)"); 
                return; 
            }
        }
        System.out.println("Sorry, Out of guesses, Corect word was: " + answer);
    }

    static String getFeedbackCode(String guess, String answer) {
        char[] res = new char[WordleConfig.WORD_LENGTH];
        char[] answerArray = answer.toCharArray();
        char[] guessArray = guess.toCharArray();

        for (int i = 0; i < WordleConfig.WORD_LENGTH; i++) {
            if (guessArray[i] == answerArray[i]) {
                res[i] = WordleConfig.GREEN_CODE;
                answerArray[i] = WordleConfig.MARKER_CHAR; 
                guessArray[i] = WordleConfig.MARKER_CHAR; 
            }
        }

        for (int i = 0; i < WordleConfig.WORD_LENGTH; i++) {
            if (guessArray[i] != WordleConfig.MARKER_CHAR) { 
                boolean found = false;
                for (int j = 0; j < WordleConfig.WORD_LENGTH; j++) {
                    if (answerArray[j] == guessArray[i]) {
                        res[i] = WordleConfig.YELLOW_CODE;
                        answerArray[j] = WordleConfig.MARKER_CHAR; 
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    res[i] = WordleConfig.GRAY_CODE;
                }
            }
        }
        return new String(res);
    }

    static String feedback(String guess, String answer) {
        String feedbackCode = getFeedbackCode(guess, answer);
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < WordleConfig.WORD_LENGTH; i++) {
            char c = guess.charAt(i);
            switch (feedbackCode.charAt(i)) {
                case WordleConfig.GREEN_CODE -> 
                    sb.append(WordleConfig.COLOR_GREEN).append(" ").append(c).append(" ").append(WordleConfig.COLOR_RESET);
                case WordleConfig.YELLOW_CODE -> 
                    sb.append(WordleConfig.COLOR_YELLOW).append(" ").append(c).append(" ").append(WordleConfig.COLOR_RESET);
                default -> 
                    sb.append(WordleConfig.COLOR_GRAY).append(" ").append(c).append(" ").append(WordleConfig.COLOR_RESET);
            }
        }
        return sb.toString();
    }
}
