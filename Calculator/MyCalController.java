import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MyCalController implements Initializable
{

    @FXML
    private GridPane cal;

    @FXML
    private ComboBox<Integer> monthPicker, yearPicker;

    @FXML
    private Label msgLabel;

    @FXML
    private ImageView img;

    @FXML
    private TextArea meetingInfo;

    @FXML
    private Button savebtn;

    private HashMap<Calendar, String> meetings;
    private Button[][] calender;
    private Calendar c = Calendar.getInstance();

    public void initialize(URL location, ResourceBundle resources)
    {
        meetings = new HashMap<>();
        calender = new Button[6][7];
        for (int i = 2040; i >= 2000; i--)
            yearPicker.getItems().add(i);
        for (int i = 1; i <= 12; i++)
            monthPicker.getItems().add(i);
        for(Node n : cal.getChildren())
            if(n instanceof Button)
                calender[GridPane.getRowIndex(n)-1][GridPane.getColumnIndex(n)] = ((Button)n);
        yearPicker.setValue(c.get(Calendar.YEAR));
        monthPicker.setValue(c.get(Calendar.MONTH)+1);
        setCal();
    }

    @FXML
    void setCal()
    {
        c.set(yearPicker.getValue(), monthPicker.getValue()-1, 1);
        int day = 1 , j;
        for (Button[] buttons : calender)
            for (Button button : buttons) {
                button.setText("");
                button.setStyle("-fx-background-color: black;");
            }
        j = c.get(Calendar.DAY_OF_WEEK) - 1;
        for (Button[] buttons : calender) {
            for (; j < buttons.length && day <= c.getActualMaximum(Calendar.DATE); j++, day++) {
                buttons[j].setText(day + "");
                buttons[j].setStyle("-fx-background-color: #80bcb4;");
            }
            j = 0;
        }
    }

    @FXML
    void datePicked(ActionEvent event) {
        if(!(((Button)event.getSource()).getText()).equals("") && !savebtn.isVisible()) {
            img.setVisible(false);
            meetingInfo.setVisible(true);
            savebtn.setVisible(true);
            c.set(yearPicker.getValue(), monthPicker.getValue() - 1, Integer.parseInt(((Button) event.getSource()).getText()));
            if (meetings.containsKey(c)) {
                msgLabel.setText(printDate(c) + "\nYour meetings for today are: \nYou can edit them and than press \"save\"");
                meetingInfo.setText(meetings.get(c));
            }
            else
                msgLabel.setText(printDate(c) + "\nYou don't have meetings yet. \nEnter meeting info and than press \"save\"");
        }
    }


    @FXML
    void saveMeeting() {
        meetings.put(c, meetingInfo.getText());
        meetingInfo.setText("");
        img.setVisible(true);
        meetingInfo.setVisible(false);
        savebtn.setVisible(false);
        msgLabel.setText("You can choose a month \n" +
                "and a year to show.\n" +
                "To see or edit your \n" +
                "meetings click on a day above.");
    }

    private String printDate(Calendar c)
    {
        return c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR);
    }
}