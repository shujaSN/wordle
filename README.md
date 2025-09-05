# Wordle CLI

A command-line implementation of the popular word-guessing game, built with Java and emphasizing clean architecture and comprehensive testing.

## Features

- **Accurate Game Logic**: Implements official Wordle rules including proper handling of duplicate letters
- **Visual Feedback**: ANSI color-coded responses (🟩 green, 🟨 yellow, ⬜ gray)
- **Configurable**: Easily adjustable game parameters via dedicated configuration class
- **Well-Tested**: 12 comprehensive unit tests covering all edge cases
- **Production-Ready**: Proper resource management, exception handling, and input validation

## Quick Start

```bash
# Build
mvn clean compile

# Run the game
mvn exec:java

# Run tests
mvn test
```

## Project Structure

```
├── Wordle.java          # Core game logic and main entry point
├── WordleConfig.java    # Centralized configuration and constants
├── WordleTest.java      # Comprehensive test suite
└── words.txt            # Customizable word list
```

## Testing

The test suite validates critical game rules including:
- Perfect matches and complete misses
- Complex duplicate letter scenarios
- Priority of green over yellow highlights
- Edge cases with repeated letters
