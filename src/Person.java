public class Person {
    // TODO implement Person and its subclasses in other Java files
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Hello, my name is " + name + ".";
    }
}

class Customer extends Person {
    public Customer(String name, int age) {

        super(name, age);
    }
}

abstract class Employee extends Person {
    private int months_worked;
    private double salary;

    public Employee(String name, int age, int months_worked, double salary) {
        super(name, age);
        this.months_worked = months_worked;
        this.salary = salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setMonths_worked(int months_worked) {
        this.months_worked = months_worked;
    }
    public int getMonths_worked() {
        return months_worked;
    }

    public double getSalary() {
        return salary;
    }

    public double getThirteenthMonth() {
        return salary * (12.0 / months_worked);
    }
}

class Clerk extends Employee {
    public Clerk(String name, int age, int months_worked, double salary) {
        super(name, age, months_worked, salary);
    }

    @Override
    public String toString() {
        return super.toString() + " How may I help you?";
    }
}

class Manager extends Employee {
    public Manager(String name, int age, int months_worked, double salary) {
        super(name, age, months_worked, salary);
    }
}
