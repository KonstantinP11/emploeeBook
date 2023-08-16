package skypro.emploeeBook.dto;

import java.util.Objects;

public class Employee {
    private String firthName;
    private String lastName;

    public Employee(String firthName, String lastName) {
        this.firthName = firthName;
        this.lastName = lastName;
    }

    public String getFirthName() {
        return firthName;
    }

    public String getLastName() {
        return lastName;
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
                '}';
    }
}
