package fr.quintipio.modelArchiSpringMvc.service;


import fr.quintipio.modelArchiSpringMvc.dao.CommuneDao;
import fr.quintipio.modelArchiSpringMvc.model.Commune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("communeService")
@Transactional
public class CommuneServiceImpl implements CommuneService {


    @Autowired
    private CommuneDao dao;


    @Override
    public List<Commune> findAllCommune() {
        return dao.findAllCommune();
    }

    @Override
    public Commune findCommune(int idCommune) {
        return dao.findCommune(idCommune);
    }
}
