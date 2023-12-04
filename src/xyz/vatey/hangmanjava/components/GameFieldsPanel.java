package xyz.vatey.hangmanjava.components;


import javax.swing.*;

import xyz.vatey.hangmanjava.managers.GuessButtonListener;
import xyz.vatey.hangmanjava.managers.HangmanGame;

import java.awt.*;

/**
 * GameFieldsPanel is a JPanel which handles all user inputs
 * and displaying game related data within the hangman jframe window.
 */
public class GameFieldsPanel extends JPanel {

    private HangmanGame hangmanGame;

    private JPanel userResponsePanel;
    private JPanel textInputPanel;
    private JPanel headerInfoPanel;
    private JPanel guessedWordsPanel;

    private JLabel wordGuessLabel;
    private JLabel progressLabel;
    private JLabel characterCountLabel;
    private JLabel guessedLettersLabel;
    private JLabel lettersGuessed;
    private JLabel inputResponseMessage;
    private JLabel remainingGuessesLabel;
    private JButton wordGuessButton;
    private JTextField wordGuessField;

    /**
     * Constructs the panel with displays for word progress, character count,
     * a field to input guesses, and incorrect letters guessed. This Panel is drawn
     * in HangmanFrame.
     * @param numberLetters         Number of letters in the word to guess
     * @param guessButtonListener   Event listner class for guess button pressed/enter hit on text input field
     * @see   HangmanFrame
     */
    public GameFieldsPanel(int numberLetters, GuessButtonListener guessButtonListener) {
        //setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setLayout(new GridLayout(3, 1));

        //HEADER INFO PANEL
        headerInfoPanel = new JPanel();
        headerInfoPanel.setLayout(new BorderLayout());

        progressLabel = new JLabel("", SwingConstants.CENTER);
        progressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerInfoPanel.add(progressLabel, BorderLayout.NORTH);

        characterCountLabel = new JLabel("Your word has " + Integer.toString(numberLetters) + " letters", SwingConstants.CENTER);
        characterCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerInfoPanel.add(characterCountLabel, BorderLayout.CENTER);

        wordGuessLabel = new JLabel("Enter letter or guess word: ", SwingConstants.CENTER);
        wordGuessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerInfoPanel.add(wordGuessLabel, BorderLayout.SOUTH);

        add(headerInfoPanel);

        //USER INPUT PANEL
        userResponsePanel = new JPanel();
        userResponsePanel.setLayout(new BoxLayout(userResponsePanel, BoxLayout.Y_AXIS));

        textInputPanel = new JPanel();
        textInputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        wordGuessField = new JTextField(14);
        wordGuessField.addActionListener(guessButtonListener);
        textInputPanel.add(wordGuessField);
        wordGuessButton = new JButton("guess");
        wordGuessButton.addActionListener(guessButtonListener);
        textInputPanel.add(wordGuessButton);

        userResponsePanel.add(textInputPanel);
        userResponsePanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        remainingGuessesLabel = new JLabel("- guesses remaining!", SwingConstants.CENTER);
        remainingGuessesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userResponsePanel.add(remainingGuessesLabel);

        inputResponseMessage = new JLabel("", SwingConstants.CENTER);
        inputResponseMessage.setForeground(Color.RED);
        inputResponseMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputResponseMessage.setAlignmentY(Component.TOP_ALIGNMENT);

        userResponsePanel.add(inputResponseMessage, BorderLayout.SOUTH);

        add(userResponsePanel);

        //MORE DETAILS PANEL
        guessedWordsPanel = new JPanel();
        guessedWordsPanel.setLayout(new BoxLayout(guessedWordsPanel, BoxLayout.Y_AXIS));

        guessedLettersLabel = new JLabel("Letters guessed:", SwingConstants.CENTER);
        guessedLettersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessedWordsPanel.add(guessedLettersLabel);

        lettersGuessed = new JLabel("",SwingConstants.CENTER);
        lettersGuessed.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessedWordsPanel.add(lettersGuessed);

        add(guessedWordsPanel);

    }

    /**
     * Updates red alert message displayed below the input field
     * @param text  The new text to be displayed
     * @see HangmanFrame
     */
    public void updateInputResponseMessage(String text) {
        inputResponseMessage.setText(text);
        inputResponseMessage.repaint();
    }

    /**
     * Updates the word guess progress JLabel located at the top of the window.
     * This progress string is updated by makeGuess() inside the HangmanGame manager class
     * when a correct letter was guessed.
     * @param newProgress  The new progress string generated by HangmanGame
     * @see   managers.HangmanGame
     */
    public void updateProgress(String newProgress) {
        progressLabel.setText("Progress: " + newProgress);
        progressLabel.repaint();
    }

    /**
     * Updates the incorrect letters guessed JLabel at the bottom of the window.
     * This string is generated within HangmanFrame after being passed an incorrect
     * letters guessed array from HangmanGame.
     * @param text  Letters guessed string generated by HangmanFrame
     * @see   HangmanFrame
     */
    public void updateLettersGuessed(String text) {
        lettersGuessed.setText(text);
        lettersGuessed.repaint();
    }

    /**
     * Updates the text displayed above incorrect letters guessed to display
     * "guessCount guesses remaining!"
     * @param guessCount    Number of guesses remaining
     */
    public void updateLettersGuessed(int guessCount) {
        remainingGuessesLabel.setText(Integer.toString(guessCount) + " guesses remaining!");
        remainingGuessesLabel.repaint();
    }

    /**
     * Clears the letter input JField, typically refreshed by makeGuess
     * @see   HangmanGame
     */
    public void clearInputField() {
        wordGuessField.setText("");
        wordGuessField.repaint();
    }

    /**
     * Returns the current text typed in the input field to be used
     * when the Guess button pressed event is occured.
     * Whenever makeGuess() with no parameters is called this function is used by HangmanFrame.
     * @return  Returns the current text typed in the input field.
     * @see     HangmanFrame
     */
    public String getInputField() {
        return wordGuessField.getText();
    }
}
