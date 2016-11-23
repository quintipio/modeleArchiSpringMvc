package fr.quintipio.modelArchiSpringMvc.dao;


import fr.quintipio.modelArchiSpringMvc.model.Commune;
import fr.quintipio.modelArchiSpringMvc.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("communeDao")
public class CommuneDaoImpl extends AbstractDao<Integer, Commune> implements CommuneDao {
    @Override
    public List<Commune> findAllCommune() {
        /*Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("libelle"));
        return (List<Commune>)crit.list();*/

        Query q =getSession().createQuery("SELECT c FROM Commune c");
        return (List<Commune>)q.list();
    }

    @Override
    public Commune findCommune(int idCommune) {
        Commune commune = getByKey(idCommune);
       return commune;
    }
}
