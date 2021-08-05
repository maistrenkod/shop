package one.maistrenko.shop.user;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/user")
@RestController
public class UserServiceController {
    private final UserService userService;

    public UserServiceController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody User createUser(@RequestBody User user) throws ParseException {
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

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<User> showUsers(){
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
