package com.slugexchange.backendmongodb.service;

import com.slugexchange.backendmongodb.model.userInfo;
import com.slugexchange.backendmongodb.model.userOffers;
import com.slugexchange.backendmongodb.repository.userInfoRepository;
import com.slugexchange.backendmongodb.repository.userOffersRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class userOffersService {

    @Autowired
    private userOffersRepository userOffersRepository;

    @Autowired
    private userInfoRepository userInfoRepository;


    //adding the post
    public userOffers addUserOffer(userOffers userOffers) {
        userOffers.setPostID(UUID.randomUUID());
        userOffersRepository.save(userOffers);
        return userOffers;
    }

    //all the finds for posts
    public List<userOffers> findAllUserOffers() {
        return userOffersRepository.findAll();
    }

    public userOffers findByPostId(UUID id) {
        return userOffersRepository.findById(id).get();
    }

    //finding all posts from a single user
    public List<userOffers> findByUserID(UUID id) {
        return userOffersRepository.findByUserID(id);
    }

    //finding all posts from all users that you follow
    public List<userOffers> findByFollowing(UUID userID) {
        userInfo userInfoFound = userInfoRepository.findById(userID).get();
        HashSet<UUID> following = userInfoFound.getFollowing();
        return userOffersRepository.findByFollowing(following);
    }

    //used for the specific search
    public List<userOffers> findByOptions(String itemName, String category, String brand, Integer value, String size) {
        if (itemName == null && category == null && brand == null && value == null && size == null) {
            return userOffersRepository.findAll();
        }


        List<Document> criteria = new ArrayList<>();
        if (itemName != null){
            criteria.add(new Document("itemName", new Document("$regex", itemName).append("$options", "i")));
        }
        if (category != null) {
            criteria.add(new Document("category", category));
        }
        if (brand != null) {
            criteria.add(new Document("brand", brand));
        }
        if (value != null) {
            criteria.add(new Document("value", new Document("$lte", value)));
        }
        if (size != null) {
            criteria.add(new Document("size", size));
        }


        return userOffersRepository.findByCriteria(criteria);

    }

    //function for the recommended posts based off of userInfo interests
    public List<userOffers> findByInterests(UUID userInfoRequestID) {
        userInfo userInfoFound = userInfoRepository.findById(userInfoRequestID).get();
        List<String> brands = userInfoFound.getInterestBrand();
        List<String> categories = userInfoFound.getInterestCategory();
        return userOffersRepository.findByBrandsAndCategories(brands, categories);
    }



    //updating post
    public userOffers updateUserOffer(userOffers userOffersRequest) {
        userOffers userOffersFound = userOffersRepository.findById(userOffersRequest.getPostID()).get();
        userOffersFound.setUserID(userOffersRequest.getUserID());
        userOffersFound.setItemName(userOffersRequest.getItemName());
        userOffersFound.setCategory(userOffersRequest.getCategory());
        userOffersFound.setDescription(userOffersRequest.getDescription());
        userOffersFound.setValue(userOffersRequest.getValue());
        userOffersFound.setBrand(userOffersRequest.getBrand());
        userOffersFound.setSize(userOffersRequest.getSize());
        return userOffersRepository.save(userOffersFound);
    }

    //deleting a post
    public String deleteUserOffer(UUID postID) {
        userOffersRepository.deleteById(postID);
        return "Post Deleted with ID: " + postID;
    }

}
