import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {
    private JPanel pnlMain;
    private JRadioButton rbCustomer;
    private JRadioButton rbClerk;
    private JRadioButton rbManager;
    private JTextField tfName;
    private JTextArea taPersons;
    private JButton btnSave;
    private JTextField tfAge;
    private JTextField tfMonths;
    private JTextField tfSalary;
    private JButton btnClear;
    private JTextField tfLoad;
    private JButton btnLoad;
    private JButton btnSayHi;
    private JButton btnSavePerson;
    private JButton btnLoadPerson;
    private JButton btnReward;

    private List<Person> persons;

    public App() {
        persons = new ArrayList<>();
        // TODO add implementations for all milestones here

        ButtonGroup br = new ButtonGroup ();
        br.add(rbCustomer);
        br.add(rbClerk);
        br.add(rbManager);

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfName.setText(null);
                tfAge.setText(null);
                tfMonths.setText(null);
                tfSalary.setText(null);
            }
        });
    }

    public static void main(String[] args) {
        // add here how to make GUI visible
        App app = new App();
        app.setContentPane(app.pnlMain);
        app.setSize(500, 500);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    void giveReward(int n) {
        Person person = persons.get(n);
        if (person instanceof Employee) {
            Employee employee = (Employee) person;
            double thirteenthMonth = employee.getThirteenthMonth();
            JOptionPane.showMessageDialog(App.this, "The thirteenth month of " + employee.getName() + " is " + thirteenthMonth, "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
