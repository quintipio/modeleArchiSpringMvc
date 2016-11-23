package fr.quintipio.modelArchiSpringMvc.service;

import fr.quintipio.modelArchiSpringMvc.model.Commune;

import java.util.List;



public interface CommuneService {

    List<Commune> findAllCommune();

    Commune findCommune(int idCommune);
}
