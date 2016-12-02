package fr.quintipio.modelArchiSpringMvc.controller;


import java.util.List;
import java.util.Locale;
import javax.validation.Valid;

import fr.quintipio.modelArchiSpringMvc.model.Commune;
import fr.quintipio.modelArchiSpringMvc.model.User;
import fr.quintipio.modelArchiSpringMvc.model.UserProfile;
import fr.quintipio.modelArchiSpringMvc.service.CommuneService;
import fr.quintipio.modelArchiSpringMvc.service.UserProfileService;
import fr.quintipio.modelArchiSpringMvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

/**
 * Controlleur pour la gestion des utilisateur
 */
@Controller //@RestController pour un controlleur full ajax
//@RequestMapping("/") //si on veut mettre un autre préfixe avant chacun des liens du controlleur
@SessionAttributes({"roles","listeCommune"}) // pour les models autre et la session http
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    CommuneService communeService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;


    /**ADMIN **/

    /**
     * Retourne la page de liste des utilisateurs
     * @param model
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = {"/list" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userslist";
    }


    /**
     * Pour supprimer un utilisateur
     * @param id l'id de l'utilisateur à supprimer
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = { "/delete-user-{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id) {
        try {
            int idint = 0;
            idint = Integer.parseInt(id);
            userService.deleteUserById(idint);
        }catch(Exception ex) {

        }
        return "redirect:/list";
    }

    /**
     * Pour que l'admin modifie un utilisateur (accès à la page)
     * @param id l'id de l'utilisateur à modifier
     * @param model
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String id,ModelMap model) {
        int idint = 0;
        idint = Integer.parseInt(id);
        if(idint != 0 ){
            User user = userService.findById(idint);
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            model.addAttribute("editAnotherUser",true);
            model.addAttribute("loggedinuser", getPrincipal());
            return "create";
        }
        return "redirect:/list";
    }

    /**
     * Action de modification d'un utilisateur
     * @param id l'id de l'utilisateur à modifier
     * @param user les nouvelles données utilisateurs
     * @param result
     * @param model
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.POST)
    public String editUser(@PathVariable String id,@Valid User user, BindingResult result, ModelMap model) {

        model.addAttribute("loggedinuser", getPrincipal());

        int idint = 0;
        idint = Integer.parseInt(id);
        if(idint != 0 ){
            User userFound = userService.findById(idint);

            if (result.hasErrors() || userFound == null) {
                return "create";
            }

            userFound.setFirstName(user.getFirstName());
            userFound.setLastName(user.getLastName());
            userFound.setVille(user.getVille());
            userFound.setUserProfiles(user.getUserProfiles());
            userFound.setEmail(user.getEmail());
            userFound.setBirthDate(user.getBirthDate());

            userService.updateUser(user);

            return "redirect:/list";
        }
        return "create";
    }

    /**UTILISATEUR **/

    /**
     * Accès à la page de mise à jour des données du compte
     * @param model
     * @return
     */
    @Secured({"ROLE_USER","ROLE_DBA","ROLE_ADMIN"})
    @RequestMapping(value = { "/update" }, method = RequestMethod.GET)
    public String editUser(ModelMap model) {
        User user = userService.findBySSO(getPrincipal());
        if(user != null) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            model.addAttribute("editAnotherUser",false);
            model.addAttribute("loggedinuser", getPrincipal());
            return "create";
        }
        return "login";
    }

    /**
     * Modifie les données du compte
     * @param user les nouvelles données utilisateurs
     * @param result
     * @param model
     * @return
     */
    @Secured({"ROLE_USER","ROLE_DBA","ROLE_ADMIN"})
    @RequestMapping(value = { "/update" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, ModelMap model) {

        User userFound = userService.findBySSO(getPrincipal());

        if (result.hasErrors() || userFound == null) {
            return "create";
        }

        userFound.setFirstName(user.getFirstName());
        userFound.setLastName(user.getLastName());
        userFound.setVille(user.getVille());
        userFound.setUserProfiles(user.getUserProfiles());
        userFound.setEmail(user.getEmail());
        userFound.setBirthDate(user.getBirthDate());

        userService.updateUser(user);

        model.addAttribute("loggedinuser", getPrincipal());
        return "login";
    }


    /**
     * Affiche la page de recherche
     * @return
     */
    @Secured({"ROLE_USER","ROLE_DBA","ROLE_ADMIN"})
    @RequestMapping(value = { "/search" }, method = RequestMethod.GET)
    public String search(ModelMap model) {

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "search";
    }


    @Secured({"ROLE_USER","ROLE_DBA","ROLE_ADMIN"})
    @RequestMapping(value = "/search/getResult", method=RequestMethod.POST)
    @ResponseBody
    public List<User> getSearchResultViaAjax(@RequestBody String nom) {
            List<User> res = userService.getUserByName(nom);
        if(res != null) {
            res.forEach(x -> x.setUserProfiles(null)); //un set ne passe pas en ajax
        }
            return (res != null && !res.isEmpty())?res:null;
    }



    /**PUBLIC **/

    /**
     * Accès à la page de création de compte
     * @param model
     * @return
     */
    @RequestMapping(value = { "/create" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        List<Commune> listeCommune = communeService.findAllCommune();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("editAnotherUser",false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "create";
    }

    /**
     * Créer un nouveau compte
     * @param user l'utilisateur à créer
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = { "/create" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,ModelMap model) {

        model.addAttribute("edit", false);
        model.addAttribute("editAnotherUser",false);
        if (result.hasErrors()) {
            return "create";
        }

        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "create";
        }

        /* Si on veut faire un controle sur l'adresse email
        if(!userService.isUserEmailUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","email",messageSource.getMessage("non.unique.email", new String[]{user.getEmail()}, Locale.getDefault()));
            result.addError(ssoError);
            return "create";
        }*/

        userService.saveUser(user);

        model.addAttribute("success", "Utilisateur " + user.getFirstName() + " "+ user.getLastName() + " crée");
        model.addAttribute("loggedinuser", getPrincipal());
        return "login";
    }


    /**
     * Accès à la page de login
     * @param model
     * @return
     */
    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public String loginPage(ModelMap model) {

        model.addAttribute("loggedinuser", getPrincipal());
        return "login";
        /* si on veut faire une redirection en fonction de l'état de connexion
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/list";
        }*/
    }

    /**DIVERS **/

    /**
     * Page d'erreur
     * @param model
     * @return
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * Pour afficher une liste des roles
     * @return
     */
    @ModelAttribute("roles") //pour appeler d'autres model dans la vue
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    /**
     * Pour afficher une liste de communes
     * @return
     */
    @ModelAttribute("listeCommune")
    public List<Commune> initializeCommunes() {
        return communeService.findAllCommune();
    }

    /**PRIVE **/

    /**
     * Retourne le nom d'utilisateur d'un utilisateur
     * @return le nom d'utilisateur
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * controle si l'utilisateur est anonyme ou non
     * @return
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
