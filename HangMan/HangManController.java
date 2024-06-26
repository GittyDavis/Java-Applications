import java.net.URL;
import java.io.File;
import javafx.fxml.FXML;
import java.util.Random;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Ellipse;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import static javafx.application.Platform.exit;

public class HangManController implements Initializable
{
    @FXML
    private Ellipse line5;
    @FXML
    private Button yes, no;
    @FXML
    private ComboBox<Character> abc;
    @FXML
    private Label guessWord, usedChars, newGameLabel;
    @FXML
    private Line line0, line1, line2, line3, line4, line6, line7, line8, line9, line10;
    private Shape[] hangMan;
    private String wordToGuess;
    private int numWrongGuesses;
    private final int maxWrongGuesses = 11;

    public void initialize(URL location, ResourceBundle resources)
    {
        hangMan = new Shape[maxWrongGuesses];
        hangMan[0] = line0; hangMan[1] = line1; hangMan[2] = line2;
        hangMan[3] = line3; hangMan[4] = line4; hangMan[5] = line5;
        hangMan[6] = line6; hangMan[7] = line7; hangMan[8] = line8;
        hangMan[9] = line9; hangMan[10] = line10;
        for(char i = 'a'; i <= 'z'; i++)
            abc.getItems().add(i);
        chooseWord();
    }

    public void chooseWord()
    {
        String fileText = openFile(), word = "Word to guess: ";
        String[] wordList = fileText.split("\n");
        Random r = new Random();
        wordToGuess = wordList[r.nextInt(wordList.length)];
        for (int i = 0; i < wordToGuess.length(); i++)
            word += "_";
        guessWord.setText(word);
    }

    public String openFile()
    {
        try {
            File guessWordsFile = new File("guessWords.txt");
            FileReader fr = new FileReader(guessWordsFile);
            char[] data = new char[(int)guessWordsFile.length()];
            fr.read(data);
            String s = new String(data);
            fr.close();
            return s;
        }catch (IOException e) {
            return "Error";
        }
    }

    @FXML
    void addCharacter(ActionEvent event)
    {
        int originalTextLength = 15;
        boolean found = false;
        char curChar = ((ComboBox<Character>)event.getSource()).getValue();
        for (int i = 0; i < wordToGuess.length(); i++)
            if (wordToGuess.charAt(i) == curChar && guessWord.getText().charAt(15+i) == '_')
            {
                guessWord.setText(guessWord.getText().substring(0, originalTextLength + i)
                        + wordToGuess.charAt(i) + guessWord.getText().substring(originalTextLength + 1 + i));
                found = true;
            }
        if (!found)
        {
            hangMan[numWrongGuesses].setVisible(true);
            numWrongGuesses++;
        }
        usedChars.setText(usedChars.getText() + curChar + ", ");
        if(guessWord.getText().substring(originalTextLength).equals(wordToGuess) || numWrongGuesses == maxWrongGuesses) {
            if (numWrongGuesses == maxWrongGuesses)
                newGameLabel.setText("\nGame-over! :( \n\nDo you want to play again?");
            else
                newGameLabel.setText("\nHooray!! you did it! \n\nDo you want to play again?");
            newGameLabel.setVisible(true);
            yes.setVisible(true);
            no.setVisible(true);
        }
    }

    @FXML
    void newGame(ActionEvent event)
    {
        if(event.getSource().equals(yes)) {
            numWrongGuesses = 0;
            usedChars.setText("you used already: \n");
            yes.setVisible(false);
            no.setVisible(false);
            newGameLabel.setVisible(false);
            for (Shape shape : hangMan)
                shape.setVisible(false);
            chooseWord();
        }
        else {
            exit();
        }
    }
}