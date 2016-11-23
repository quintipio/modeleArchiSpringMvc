package fr.quintipio.modelArchiSpringMvc.dao;

import fr.quintipio.modelArchiSpringMvc.model.Commune;

import java.util.List;

public interface CommuneDao {

    public List<Commune> findAllCommune();

    public Commune findCommune(int idCommune);

}
