import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionsList
{
    private ArrayList<Question> _questionList;
    private final int questionLength = 5;

    public QuestionsList()
    {
        try {
            _questionList = new ArrayList<>();
            String[] question = new String[questionLength];
            Scanner input = new Scanner(new File("trivia.txt"));
            while (input.hasNext()) {
                for (int i = 0; i < questionLength; i++)
                    question[i] = input.nextLine();
                _questionList.add(new Question(question));
            }
            input.close();
        } catch (FileNotFoundException e){
            System.out.println("Error. couldn't find file");
        }
    }

    public Question getQuestion(int i)
    {
        return _questionList.get(i);
    }

    public int getLength()
    {
        return _questionList.size();
    }
}
