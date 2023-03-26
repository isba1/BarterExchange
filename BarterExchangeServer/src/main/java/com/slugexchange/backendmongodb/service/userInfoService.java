package com.slugexchange.backendmongodb.service;

import com.slugexchange.backendmongodb.model.userInfo;
import com.slugexchange.backendmongodb.repository.userInfoRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
//import org.springframework.security.crypto.bcrypt.BCrypt;



@Service
public class userInfoService {

    @Autowired
    private userInfoRepository userInfoRepo;

    //where the CRUD operations will be written

    //adds user contact to collection
    public userInfo addUserInfo(userInfo userInfo) {
        userInfo.setUserID(UUID.randomUUID());
        //String salt = BCrypt.gensalt();
        //String password = userInfo.getPassword();
        //String hashedPassword = BCrypt.hashpw(password, salt);
        //userInfo.setSalt(salt);
        //userInfo.setHashedPassword(hashedPassword);
        userInfoRepo.save(userInfo);
        return userInfo;
    }

    public userInfo addFollowing(UUID userID, UUID newFollowing) {
        userInfo userInfoFound = userInfoRepo.findById(userID).get();
        HashSet<UUID> following = userInfoFound.getFollowing();
        following.add(newFollowing);
        userInfoFound.setFollowing(following);
        userInfoRepo.save(userInfoFound);
        return userInfoFound;
    }

    public userInfo deleteFollowing(UUID userID, UUID oldFollowing) {
        userInfo userInfoFound = userInfoRepo.findById(userID).get();
        HashSet<UUID> following = userInfoFound.getFollowing();
        following.remove(oldFollowing);
        userInfoFound.setFollowing(following);
        userInfoRepo.save(userInfoFound);
        return userInfoFound;
    }


    //gets all user contacts
    public List<userInfo> findAllUserInfos() {
        return userInfoRepo.findAll();
    }


    //finds user contact by UUID primary key
    public userInfo findByID(UUID id) {
        return userInfoRepo.findById(id).get();
    }

    //finds list of users by first name
    public List<userInfo> findByFirstNameOrLastName(String firstName, String lastName) {

        if (firstName == null && lastName == null) {
            return userInfoRepo.findAll();
        }

        List<Document> criteria = new ArrayList<>();
        if (firstName != null) {
            criteria.add(new Document("firstName", firstName));
        }

        if (lastName != null) {
            criteria.add(new Document("lastName", lastName));
        }

        return userInfoRepo.findByCriteria(criteria);
    }

    //finds list of users by last name
    //public List<userContact> findByLastName(String lastName) {
    //    return userContactRepo.findByLastName(lastName);
    //}

    //finds user contact by email
    public userInfo findByEmail(String email) {
        return userInfoRepo.findByUserEmail(email);
    }

    //finds user contact by phone number
    public userInfo findByPhone(String phone) {
        return userInfoRepo.findByUserPhone(phone);
    }

    //finds user contact by address
    public userInfo findByAddress(String address) {
        return userInfoRepo.findByUserAddress(address);
    }

    public List<userInfo> findBySizesOrBrandsOrFollowing(UUID userID) {
        userInfo userInfoFound = userInfoRepo.findById(userID).get();
        String interestPantSize = userInfoFound.getInterestPantSize();
        String interestShoeSize = userInfoFound.getInterestShoeSize();
        String interestShirtSize = userInfoFound.getInterestShirtSize();
        String interestJacketSize = userInfoFound.getInterestJacketSize();
        List<String> interestBrand = userInfoFound.getInterestBrand();
        HashSet<UUID> following = userInfoFound.getFollowing();
        return userInfoRepo.findBySizesOrBrandsOrFollowing(interestPantSize, interestShoeSize, interestShirtSize,
                interestJacketSize, interestBrand, following, userID);
    }

    //updates userContact
    public userInfo updateUserInfo(userInfo userInfoRequest) {
        userInfo userInfoFound = userInfoRepo.findById(userInfoRequest.getUserID()).get();
        //String password = userInfoRequest.getPassword();
        //String salt = userInfoRequest.getSalt();
        //String hashedPassword = BCrypt.hashpw(password, salt);

        userInfoFound.setPassword(userInfoRequest.getPassword());
        //userInfoFound.setSalt(salt);
        //userInfoFound.setHashedPassword(hashedPassword);
        userInfoFound.setFirstName(userInfoRequest.getFirstName());
        userInfoFound.setLastName(userInfoRequest.getLastName());
        userInfoFound.setUserEmail(userInfoRequest.getUserEmail());
        userInfoFound.setUserPhone(userInfoRequest.getUserPhone());
        userInfoFound.setUserAddress(userInfoRequest.getUserAddress());
        userInfoFound.setInterestCategory(userInfoRequest.getInterestCategory());
        userInfoFound.setInterestBrand(userInfoRequest.getInterestBrand());
        userInfoFound.setFollowing(userInfoRequest.getFollowing());
        return userInfoRepo.save(userInfoFound);
    }

    public boolean authenticateUserInfo(String email, String password) {
        userInfo userInfoFound = findByEmail(email);
        String passwordFound = userInfoFound.getPassword();
        return Objects.equals(password, passwordFound);
    }

    //deletes userContact
    public String deleteUserInfo(UUID id) {
        userInfoRepo.deleteById(id);
        return "Deleted user info with ID: " + id;
    }


}
