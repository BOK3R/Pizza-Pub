package pl.pizza.pub.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.pizza.pub.database.Role;
import pl.pizza.pub.utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Class used to perform tasks associated with the Roles table
 */
public class RoleModel {

    private ObservableList<RoleFx> roleFxObservableList = FXCollections.observableArrayList();

    /**
     * Initializes list of records from Role table
     */
    public void initRoleList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.select(root);
        Query<Role> q = session.createQuery(query);
        List<Role> roleList = q.getResultList();
        roleFxObservableList.clear();
        for (Role role : roleList){
            RoleFx roleFx = new RoleFx();
            roleFx.setIdRole(role.getIdRole());
            roleFx.setRole(role.getRole());
            roleFxObservableList.add(roleFx);
        }

        session.getTransaction().commit();
    }

    /**
     * Gets list of records from Role table
     * @return roleFxObservableList
     */
    public ObservableList<RoleFx> getRoleFxObservableList() {
        return roleFxObservableList;
    }

    /**
     * Sets list of records from Role table
     * @param roleFxObservableList roleFxObservableList to set
     */
    public void setRoleFxObservableList(ObservableList<RoleFx> roleFxObservableList) {
        this.roleFxObservableList = roleFxObservableList;
    }
}
