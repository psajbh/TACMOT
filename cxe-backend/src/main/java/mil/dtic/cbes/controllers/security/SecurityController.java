//package mil.dtic.cbes.controllers.security;
//
//import java.security.Principal;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import javax.security.auth.login.AccountNotFoundException;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import mil.dtic.cbes.controllers.BaseRestController;
//
//@RestController
//public class SecurityController extends BaseRestController{
//    
//    @GetMapping("/auth/user")
//    public Map<String,Object> user(Principal user) throws AccountNotFoundException, LDAPException {
//      Map<String, Object> map = new LinkedHashMap<>();
//      User currentUser = loginManager.getLoggedInUser();
//      Role userRole = currentUser.getRole();
//      
//      map.put("loginId", user.getName());
//      map.put("name", currentUser.getName());
//      map.put("role", userRole.getName());
//      map.put("isAuthenticated", auth.isAuthenticated(user));
//      return map;
//    }
//
//
//}
