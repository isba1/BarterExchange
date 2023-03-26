package com.slugexchange.backendmongodb.controller;


import com.slugexchange.backendmongodb.model.userInfo;
import com.slugexchange.backendmongodb.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/userInfo")
public class userInfoController {

    @Autowired
    private userInfoService userInfoService;

    @PostMapping("/addUser")
    public userInfo addUserInfo(@RequestBody userInfo userInfo) {
        return userInfoService.addUserInfo(userInfo);
    }

    @PostMapping("/addFollowing")
    public userInfo addUserFollowing (
            @RequestParam(name = "userID") String userID, @RequestParam(name = "followingID") String followingID) {
        return userInfoService.addFollowing(UUID.fromString(userID), UUID.fromString(followingID));
    }

    @PostMapping("/deleteFollowing")
    public userInfo deleteUserFollowing(
            @RequestParam(name = "userID") String userID, @RequestParam(name = "followingID") String followingID
    ) {
        return userInfoService.deleteFollowing(UUID.fromString(userID), UUID.fromString(followingID));
    }

    @GetMapping("/getAllUsers")
    public List<userInfo> getAllUsers() {
        return userInfoService.findAllUserInfos();
    }

    @GetMapping("/findByID")
    public userInfo getByID(@RequestParam(name = "userID") String userID) {
        return userInfoService.findByID(UUID.fromString(userID));
    }

    @GetMapping("/findByName")
    public List<userInfo> getByName(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName) {
        return userInfoService.findByFirstNameOrLastName(firstName, lastName);
    }


    @GetMapping("/findByEmail")
    public userInfo getByEmail(@RequestParam(name = "email") String email) {
        return userInfoService.findByEmail(email);
    }

    @GetMapping("/findByPhone")
    public userInfo getByPhone(@RequestParam(name = "phone") String phone) {
        return userInfoService.findByPhone(phone);
    }

    @GetMapping("/findByAddress")
    public userInfo getByAddress(@RequestParam(name = "address") String address) {
        return userInfoService.findByAddress(address);
    }

    @GetMapping("/findRecommended")
    public List<userInfo> getRecommended(@RequestParam(name = "userID") String userID) {
        return userInfoService.findBySizesOrBrandsOrFollowing(UUID.fromString(userID));
    }

    @GetMapping("/authenticateUser")
    public boolean authenticateUser(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        return userInfoService.authenticateUserInfo(email, password);
    }

    @PutMapping("/updateUser")
    public userInfo updateUserInfo(@RequestBody userInfo userInfo) {
        return userInfoService.updateUserInfo(userInfo);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUserInfo(@RequestParam(name = "userID") String userID) {
        return userInfoService.deleteUserInfo(UUID.fromString(userID));
    }









}
