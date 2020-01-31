package pl.pizza.pub.database;

import javax.persistence.*;

/**
 * Mapping employees table from database
 */
@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private int idEmployee;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "role_ibfk_1"))
    private Role role;

    public Employees() {
    }

    public Employees(int idEmployee, String name, String surname, String login, String password, Role role) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets ID of Employee.
     *
     * @return This Employee id
     */
    public int getIdEmployee() {
        return idEmployee;
    }

    /**
     * Sets ID of Employee
     *
     * @param idEmployee idEmployee to set
     */
    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    /**
     * Gets Name of Employee
     *
     * @return This Employee name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets Name of Employee
     *
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets Surname of Employee
     *
     * @return This Employee surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets Surname of Employee
     *
     * @param surname surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets Login of Employee
     *
     * @return This Employee login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets Login of Employee
     *
     * @param login login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets Password of Employee
     *
     * @return This Employee password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets Password of Employee
     *
     * @param password password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets Role of Employee
     *
     * @return This Employee role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets Role of Employee
     *
     * @param role role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
