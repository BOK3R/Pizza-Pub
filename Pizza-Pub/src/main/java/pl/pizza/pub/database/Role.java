package pl.pizza.pub.database;

import javax.persistence.*;

/**
 * Mapping role table from database
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int idRole;

    @Column(name = "role")
    private String role;

    public Role() {
    }

    public Role(int idRole, String role) {
        this.idRole = idRole;
        this.role = role;
    }

    /**
     * Gets ID of Role
     *
     * @return This Role id
     */
    public int getIdRole() {
        return idRole;
    }

    /**
     * Sets ID of Role
     *
     * @param idRole idRole to set
     */
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    /**
     * Gets role of Role
     *
     * @return This Role role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role of Role
     *
     * @param role role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
