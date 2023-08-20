package skypro.emploeeBook.dto;

import java.util.Objects;

public class Employee {
    private String firthName;
    private String lastName;
    private int department;
    private double salary;

    public Employee(String firthName, String lastName, int department, double salary) {
        this.firthName = firthName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    public String getFirthName() {
        return firthName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firthName, employee.firthName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firthName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firthName='" + firthName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                ", salary=" + salary + '}';
    }
}
