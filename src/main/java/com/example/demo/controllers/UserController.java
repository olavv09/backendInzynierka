package com.example.demo.controllers;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/addrating")
    public void addRating(@RequestParam int ratedUserId, @RequestParam int rating){
        User ratedUser = userRepository.findUser(ratedUserId).orElse(null);
        int newRatingCount = ratedUser.getRating_count() + 1;
        float newAverageRating = (ratedUser.getAverage_rating() * ratedUser.getRating_count() + rating)/ newRatingCount;
        userRepository.addUserRating(ratedUserId, newAverageRating, newRatingCount);
    }

    @GetMapping(value = "/getrating")
    public float getRating(@RequestParam int ratedUserId){
        return userRepository.findUser(ratedUserId).orElse(null).getAverage_rating();
    }

    @GetMapping(value = "/getallusers")
    public @ResponseBody Iterable<User> getAllWithFilters(@RequestParam String name, @RequestParam String username, @RequestParam String email_adress,
                                                          @RequestParam Integer status, @RequestParam Integer role){
        return userRepository.findAllWithFilters(name, username, email_adress, status, role);
    }

    @GetMapping(value = "/unblockuser")
    public void unblockUser(@RequestParam int userId){
        userRepository.unblockUser(userId);
    }

    @GetMapping(value = "/blockuser")
    public void blockUser(@RequestParam int userId){
        userRepository.blockUser(userId);
    }

    @GetMapping(value = "/edituser")
    public void editUser(@RequestParam int userId, @RequestParam String name,@RequestParam String surname, @RequestParam String username,
                         @RequestParam String email_adress, @RequestParam String identity_number ,@RequestParam Integer status,
                         @RequestParam Integer accepted ,@RequestParam Integer role){
        User user = userRepository.findById(userId).orElse(null);
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setEmail_adress(email_adress);
        user.setIdentity_number(identity_number);
        user.setAccount_status(status);
        user.setAccepted(accepted);
        user.setRole(role);
        userRepository.save(user);
    }
}
