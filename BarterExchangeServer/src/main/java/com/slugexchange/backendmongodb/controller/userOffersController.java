package com.slugexchange.backendmongodb.controller;

import com.slugexchange.backendmongodb.model.userOffers;
import com.slugexchange.backendmongodb.service.userOffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/userOffers")

public class userOffersController {

    @Autowired
    private userOffersService userOffersService;

    @PostMapping("/addOffers")
    public userOffers addUserOffers(@RequestBody userOffers userOffers) {
        return userOffersService.addUserOffer(userOffers);
    }

    @GetMapping("/findAllOffers")
    public List<userOffers> getAllOffers() {
        return userOffersService.findAllUserOffers();
    }

    @GetMapping("/findByPostID")
    public userOffers getByPostID(@RequestParam(name = "postID") String postID) {
        return userOffersService.findByPostId(UUID.fromString(postID));
    }

    @GetMapping("/findByUserID")
    public List<userOffers> getByUserID(@RequestParam(name = "userID") String userID) {
        return userOffersService.findByUserID(UUID.fromString(userID));
    }

    @GetMapping("/findByFollowing")
    public List<userOffers> getByFollowing(@RequestParam(name = "userID") String userID) {
        return userOffersService.findByFollowing(UUID.fromString(userID));
    }


    @GetMapping("/findByOptions")
    public List<userOffers> getByOptions
            (@RequestParam(name = "itemName", required = false) String itemName,
             @RequestParam(name = "category", required = false) String category,
             @RequestParam(name = "value", required = false) Integer value,
             @RequestParam(name = "brand", required = false) String brand,
             @RequestParam(name = "size", required = false) String size) {
        return userOffersService.findByOptions(itemName, category, brand, value, size);
    }

    @GetMapping("/findByInterests")
    public List<userOffers> getByInterests (@RequestParam(name = "userID") String userID) {
        return userOffersService.findByInterests(UUID.fromString(userID));
    }


    @PutMapping("/updateOffers")
    public userOffers updateUserOffers(@RequestBody userOffers userOffers) {
        return userOffersService.updateUserOffer(userOffers);
    }

    @DeleteMapping("/deleteOffers")
    public String deleteUserOffers(@RequestParam(name = "postID") String postID) {
        return userOffersService.deleteUserOffer(UUID.fromString(postID));
    }

}
