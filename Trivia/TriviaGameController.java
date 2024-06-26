import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Paint;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;

public class TriviaGameController implements Initializable
{
    @FXML
    private Label question, message;

    @FXML
    private Button newGameButton, endGameButton, newQuestionButton, exitButton;

    @FXML
    private RadioButton option1, option2, option3, option4;

    private final int numOptions = 4;
    private int score, numQuestion;
    private RadioButton[] options;
    private QuestionsList questionsList;

    @FXML
    public void initialize(URL location, ResourceBundle resources)
    {
        questionsList = new QuestionsList();
        options = new RadioButton[numOptions];
        options[0] = option1; options[1] = option2;
        options[2] = option3; options[3] = option4;
        newQuestionButton.setVisible(true); endGameButton.setVisible(true);
        newGameButton.setVisible(false); exitButton.setVisible(false);
        numQuestion = 0;
        newQuestion();
    }

    @FXML
    void newQuestion()
    {
        if (numQuestion == questionsList.getLength()) {
            question.setText("no more questions");
            endOfGame();
            return;
        }
        question.setText(questionsList.getQuestion(numQuestion).get_question());
        for (int i = 0; i < numOptions; i++)
        {
            options[i].setDisable(false);
            options[i].setSelected(false);
            options[i].setText(questionsList.getQuestion(numQuestion).get_options()[i]);
            options[i].setTextFill(Paint.valueOf("black"));
        }
        message.setText("");
    }

    @FXML
    void optionSelected(ActionEvent event)
    {
        RadioButton rightOption = options[questionsList.getQuestion(numQuestion).getRightAnswerIndex()];
        if (event.getSource().equals(rightOption))
        {
            message.setTextFill(Paint.valueOf("#21BA00"));
            message.setText("correct answer! :)");
            score += 10;
        }
        else
        {
            message.setTextFill(Paint.valueOf("red"));
            message.setText("incorrect answer :(");
            score -= 5;
        }
        rightOption.setTextFill(Paint.valueOf("green"));
        numQuestion++;
        for (RadioButton option : options)
            option.setDisable(true);
    }

    @FXML
    void endOfGame()
    {
        message.setTextFill(Paint.valueOf("#970EBA"));
        message.setText("thanks for playing! your score is: " + score);
        newQuestionButton.setVisible(false);
        endGameButton.setVisible(false);
        newGameButton.setVisible(true);
        exitButton.setVisible(true);
        score = 0;
    }

    public void newGame()
    {
        initialize(null, null);
    }

    @FXML
    void exitGame()
    {
        exit();
    }
}