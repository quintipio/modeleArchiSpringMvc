package fr.quintipio.modelArchiSpringMvc.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/")
@SessionAttributes("roles")
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
    @RequestMapping(value = {"/list" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userslist";
    }

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

    /**UTILISATEUR **/

    @RequestMapping(value = { "/update" }, method = RequestMethod.GET)
    public String editUser(ModelMap model) {
        User user = userService.findBySSO(getPrincipal());
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "create";
    }

    @RequestMapping(value = { "/update-{ssoId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String ssoId) {

        if (result.hasErrors()) {
            return "create";
        }

        userService.updateUser(user);

        model.addAttribute("success", "Utilisateur " + user.getFirstName() + " "+ user.getLastName() + " modifié");
        model.addAttribute("loggedinuser", getPrincipal());
        return "create";
    }

    /**PUBLIC **/

    @RequestMapping(value = { "/create" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        List<Commune> listeCommune = communeService.findAllCommune();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "create";
    }

    @RequestMapping(value = { "/create" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,ModelMap model) {

        if (result.hasErrors()) {
            return "create";
        }

        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "create";
        }

        if(!userService.isUserEmailUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","email",messageSource.getMessage("non.unique.email", new String[]{user.getEmail()}, Locale.getDefault()));
            result.addError(ssoError);
            return "create";
        }

        userService.saveUser(user);

        model.addAttribute("success", "Utilisateur " + user.getFirstName() + " "+ user.getLastName() + " crée");
        model.addAttribute("loggedinuser", getPrincipal());
        return "login";
    }



    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/list";
        }
    }

    /**DIVERS **/

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    @ModelAttribute("listeCommune")
    public List<Commune> initializeCommunes() {
        return communeService.findAllCommune();
    }

    /**PRIVE **/

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

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
