package fr.quintipio.modelArchiSpringMvc.service;

import fr.quintipio.modelArchiSpringMvc.dao.UserDao;
import fr.quintipio.modelArchiSpringMvc.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User findById(int id) {
        return dao.findById(id);
    }

    @Override
    public User findBySSO(String sso) {
        return dao.findBySSO(sso);
    }

    @Override
    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    @Override
    public void updateUser(User user) {
        // pas besoin d'appeler hibernate car la méthode est transactionnelle, la mise à jour s'effectuera à la fin de la transaction
        User entity = dao.findById(user.getId());
        if(entity!=null){
            entity.setSsoId(user.getSsoId());
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
            entity.setBirthDate(user.getBirthDate());
            entity.setVille(user.getVille());
            if(!user.getPassword().equals(entity.getPassword())){
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }
}
