import java.util.Random;

public class Question
{
    private String _question;
    private String[] _options;
    private int rightAnswerIndex;
    private final int numOptions = 4;

    public Question(String[] question)
    {
        Random r = new Random();
        int randomValue = r.nextInt(4);
        rightAnswerIndex = r.nextInt(4);
        _options = new String[numOptions];
        _question = question[0];
        _options[rightAnswerIndex] = question[1];
        question[1] = null;
        for (int i = 1; i <= numOptions; i++) {
            while(_options[randomValue] != null)
                randomValue = r.nextInt(4);
            _options[randomValue] = question[i];
        }
    }

    public String[] get_options()
    {
        return _options;
    }

    public String get_question()
    {
        return _question;
    }

    public int getRightAnswerIndex()
    {
        return rightAnswerIndex;
    }
}
