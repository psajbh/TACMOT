package mil.dtic.cbes.controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.service.user.api.UserEntityService;

@Slf4j
@RestController
public class ManageUsersController extends BaseRestController{
    
    private UserEntityService userEntityService;
    
    public ManageUsersController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }
        

//    @GetMapping("/user/manageusers")
//    public ResponseEntity<UserDto> getManagedUser(@PathVariable String id){
//        return processUser(id);
//    }
    
    @GetMapping("/user/manageusers")
    public ResponseEntity<List<UserDto>> getManagedUsers(){
        return processUser();
    }
    
    @PutMapping("/user/manageusers")
    public ResponseEntity<List<UserDto>> updateManagedUser(@RequestBody UserDto userDto){
        return updateUser(userDto);
    }
    
    @PostMapping("/user/manageusers")
    public ResponseEntity<List<UserDto>> addManagedUser(@RequestBody UserDto userDto){
        return addUser(userDto);
    }
    
    @DeleteMapping("/user/manageusers")
    public ResponseEntity<List<UserDto>> deleteManagedUser(@RequestBody UserDto userDto){
        return deleteUser(userDto);
    }

    private ResponseEntity<UserDto> processUser(String id){
        return null;
    }
    
    
    private ResponseEntity<List<UserDto>> processUser(){
//        List<UserDto> userDtos = userEntityService.findManagedUsers(getCredential());
//        
//        if (null != userDtos) {
//            return ResponseEntity.status(HttpStatus.OK).body(userDtos);
//        }
//        else {
//            return ResponseEntity.status(500).body(null);
//        }
    	return ResponseEntity.status(500).body(null);
    }
    
    private ResponseEntity<List<UserDto>> updateUser(UserDto userDto){
        List<UserDto> userDtos = new ArrayList<>();
        UserDto updatedUserDto = userEntityService.updateUser(userDto);
        if (null != updatedUserDto) {
            userDtos.add(updatedUserDto);
            return ResponseEntity.status(HttpStatus.OK).body(userDtos);
        }
        return ResponseEntity.status(400).body(null);
    }
    
    private ResponseEntity<List<UserDto>> addUser(UserDto userDto){
        List<UserDto> userDtos = new ArrayList<>();
        UserDto updatedUserDto = userEntityService.addUser(userDto);
        if (null != updatedUserDto) {
            userDtos.add(updatedUserDto);
            return ResponseEntity.status(HttpStatus.OK).body(userDtos);
        }
        return ResponseEntity.status(400).body(null);
    }
    
    private ResponseEntity<List<UserDto>> deleteUser(UserDto userDto){
        
        if (userEntityService.deleteUser(userDto)) {
            System.out.println("suddessfully deleted user id: " + userDto.getId());
        }
        return processUser();
    }

}
