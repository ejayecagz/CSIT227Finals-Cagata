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

                String name = tfName.getText();
                int age = 0;
                int work = 0;
                double salary = 0.0;
                Person person = null;

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(btnSave, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    age = Integer.parseInt(tfAge.getText());

                    if (age <= 0) {
                        throw new NumberFormatException("Invalid age.");
                    }

                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(btnSave, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    tfAge.setText("");
                    return;
                }

                if (rbClerk.isSelected() || rbManager.isSelected()) {
                    try {
                        work = Integer.parseInt(tfMonths.getText());

                        if (work <= 0) {
                            throw new NumberFormatException("Invalid number of months worked.");
                        }

                    } catch (NumberFormatException exc) {
                        JOptionPane.showMessageDialog(btnSave, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        tfMonths.setText("");
                        return;
                    }

                    try {
                        salary = Double.parseDouble(tfSalary.getText());

                        if (salary <= 0) {
                            throw new NumberFormatException("Invalid amount of salary.");
                        }

                    } catch (NumberFormatException exc) {
                        JOptionPane.showMessageDialog(btnSave, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        tfSalary.setText("");
                        return;
                    }
                }

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
