package mil.dtic.cbes.controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.dto.UserDto;
import mil.dtic.cbes.model.enums.StatusFlag;
import mil.dtic.cbes.service.user.UserEntityService;

@RestController
public class ManageUsersController extends BaseRestController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private UserEntityService userEntityService;
    
    public ManageUsersController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }
        
    @GetMapping("/user/manageusers")
    public ResponseEntity<List<UserDto>> getManagedUsers() {
        log.trace("getManagedUsers-");
        return processUser();
    }
    
    @PutMapping(path="/user/manageusers", consumes="application/json", produces="application/json")
    public ResponseEntity<UserDto> updateManagedUser(@RequestBody UserDto userDto) {
        log.trace("updateManagedUser-");
        return ResponseEntity.status(HttpStatus.OK).body(userEntityService.updateUser(userDto));
    }
    
    @PostMapping("/user/manageusers")
    public ResponseEntity<List<UserDto>> addManagedUser(@RequestBody UserDto userDto) {
        log.trace("addManagedUser-");
        return addUser(userDto);
    }
    
    @DeleteMapping("/user/manageusers")
    public ResponseEntity<List<UserDto>> deleteManagedUser(@RequestBody UserDto userDto){
        log.trace("deleteManagedUser-");
        return deleteUser(userDto);
    }

    private ResponseEntity<List<UserDto>> processUser() {
        log.trace("processUser-");
        List<UserDto> userDtos = userEntityService.findManagedUsers(getCredential());
        
        if (null != userDtos) {
            return ResponseEntity.status(HttpStatus.OK).body(userDtos);
        }
        else {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    private ResponseEntity<List<UserDto>> addUser(UserDto userDto){
        log.trace("addUser-");
        List<UserDto> userDtos = new ArrayList<>();
        UserDto updatedUserDto = userEntityService.addUser(userDto);
        if (null != updatedUserDto) {
            userDtos.add(updatedUserDto);
            return ResponseEntity.status(HttpStatus.OK).body(userDtos);
        }
        return ResponseEntity.status(400).body(null);
    }
    
    private ResponseEntity<List<UserDto>> deleteUser(UserDto userDto){
        log.trace("deleteUser-");
        userDto.setStatusFlag(StatusFlag.D);
        userEntityService.updateUser(userDto);
        return processUser();
    }

}
