class WordleConfig {
    public static final int MAX_GUESSES = 5;
    public static final int WORD_LENGTH = 5;
    public static final String WORDS_FILE = "words.txt";
    public static final char MARKER_CHAR = '*';
    
    // Color codes
    public static final String COLOR_GREEN = "\u001B[42m";
    public static final String COLOR_YELLOW = "\u001B[43m";
    public static final String COLOR_GRAY = "\u001B[100m";
    public static final String COLOR_RESET = "\u001B[0m";
    
    // Feedback codes
    public static final char GREEN_CODE = 'G';
    public static final char YELLOW_CODE = 'Y';
    public static final char GRAY_CODE = 'X';
}
