package one.maistrenko.shop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/v1/user")
@Service("user-service-controller")
@RestController
public class UserServiceController {
    private final UserService userService;

    public UserServiceController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public @ResponseBody User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id:\\d+}")
    public void removeUser(@PathVariable long id){
        userService.removeUser(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<Long, User> showUsers(){
        return userService.showUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id:\\d+}")
    public @ResponseBody User getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{name:\\D+}")
    public @ResponseBody User getUserByName(@PathVariable String name){
        return userService.getUserByName(name);
    }
}
