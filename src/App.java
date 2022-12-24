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
                if (rbCustomer.isSelected()) {
                    person = new Customer(name, age);
                } else if (rbClerk.isSelected()) {
                    person = new Clerk(name, age, work, salary);
                } else if (rbManager.isSelected()) {
                    person = new Manager(name, age, work, salary);
                }
                persons.add(person);
                taPersons.append(persons.size() + ". " + person.getClass().getSimpleName() + " - " + name + " (" + age + ")\n");
                tfName.setText("");
                tfAge.setText("");
                tfMonths.setText("");
                tfSalary.setText("");
            }
        });

        // to make the text area uneditable
        taPersons.setEditable(false);

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear input fields
                tfName.setText("");
                tfAge.setText("");
                tfMonths.setText("");
                tfSalary.setText("");
                tfLoad.setText("");
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i = Integer.parseInt(tfLoad.getText()) - 1;
                    Person person = persons.get(i);

                    tfName.setText(person.getName());
                    tfAge.setText(Integer.toString(person.getAge()));

                    if (person instanceof Customer) {
                        rbCustomer.setSelected(true);
                    } else if (person instanceof Employee) {
                        Employee employee = (Employee) person;
                        if (person instanceof Clerk) {
                            rbClerk.setSelected(true);
                        } else if (person instanceof Manager) {
                            rbManager.setSelected(true);
                        }
                        tfMonths.setText(Integer.toString(employee.getMonths_worked()));
                        tfSalary.setText(Double.toString(employee.getSalary()));
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(btnLoad, "You must choose a number from the list.");
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(btnLoad, "You must input a number starting from 1.");
                }
            }
        });

        btnSayHi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // loop for the message
                for (Person person : persons) {
                    System.out.println(person.toString());
                }
            }
        });

        rbCustomer.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (rbCustomer.isSelected()) {
                    tfMonths.setEnabled(!rbCustomer.isSelected());
                    tfSalary.setEnabled(!rbCustomer.isSelected());
                } else {
                    tfMonths.setEnabled(true);
                    tfSalary.setEnabled(true);
                }
            }
        });

        btnReward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(tfLoad.getText()) - 1;
                    if (index >= 0 && index < persons.size()) {
                        giveReward(index);
                    } else {
                        throw new IndexOutOfBoundsException("The index is out of bounds.");
                    }
                } catch (NumberFormatException ex) {
                    tfLoad.setText("");
                    JOptionPane.showMessageDialog(btnReward, "The index is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IndexOutOfBoundsException ex) {
                    tfLoad.setText("");
                    JOptionPane.showMessageDialog(btnReward, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        btnSavePerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter fw = new FileWriter("persons.txt");
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (Person person : persons) {
                        bw.write(person.getName() + "," + person.getAge() + ",");
                        if (person instanceof Employee) {
                            Employee employee = (Employee) person;
                            bw.write(employee.getMonths_worked() + "," + employee.getSalary() + ",");
                            if (person instanceof Clerk) {
                                bw.write("Clerk");
                            } else if (person instanceof Manager) {
                                bw.write("Manager");
                            }
                        } else if (person instanceof Customer) {
                            bw.write("Customer");
                        }
                        bw.newLine();
                    }
                    bw.close();
                    JOptionPane.showMessageDialog(btnSavePerson, "Persons saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(btnSavePerson, "Error saving persons.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLoadPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileReader fr = new FileReader("persons.txt");
                    BufferedReader br = new BufferedReader(fr);

                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] values = line.split(",");

                        Person person;
                        if (values[values.length - 1].equals("Customer")) {
                            person = new Customer(values[0], Integer.parseInt(values[1]));
                        } else if (values[values.length - 1].equals("Clerk")) {
                            person = new Clerk(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]),Double.parseDouble(values[3]));
                        } else if (values[values.length - 1].equals("Manager")) {
                            person = new Manager(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]), Double.parseDouble(values[3]));
                        } else {
                            throw new Exception("Invalid person type");
                        }

                        persons.add(person);
                        taPersons.append(persons.size() + ". " + person.getClass().getSimpleName() + " - " + person.getName() + " (" + person.getAge() + ")\n");
                    }

                    br.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(btnLoadPerson, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (rbCustomer.isSelected()) {
                    person = new Customer(name, age);
                } else if (rbClerk.isSelected()) {
                    person = new Clerk(name, age, work, salary);
                } else if (rbManager.isSelected()) {
                    person = new Manager(name, age, work, salary);
                }
                persons.add(person);
                taPersons.append(persons.size() + ". " + person.getClass().getSimpleName() + " - " + name + " (" + age + ")\n");
                tfName.setText("");
                tfAge.setText("");
                tfMonths.setText("");
                tfSalary.setText("");
            }
        });
        taPersons.setEditable(false);

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfName.setText("");
                tfAge.setText("");
                tfMonths.setText("");
                tfSalary.setText("");
                tfLoad.setText("");
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
