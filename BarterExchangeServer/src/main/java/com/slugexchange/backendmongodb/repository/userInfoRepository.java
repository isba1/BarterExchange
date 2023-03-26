package com.slugexchange.backendmongodb.repository;

import com.slugexchange.backendmongodb.model.userInfo;
import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public interface userInfoRepository extends MongoRepository<userInfo, UUID> {

    @Query("{ '$and': ?0 }")
    List<userInfo> findByCriteria(List<Document> criteria);

    //@Query("{ '$or': [ { 'interestPantSize': ?0 }, { 'interestShoeSize':  ?1 }, { 'interestShirtSize':  ?2 }, { 'interestJacketSize':  ?3 }, { 'interestBrand': { '$in': ?4 } }, { 'following': { '$in': ?5, '$size': { '$gte': 2 } } } ], 'userID': { '$ne': ?6 } }")
    @Query("{ '$or': [ { 'interestPantSize': ?0 }, { 'interestShoeSize':  ?1 }, { 'interestShirtSize':  ?2 }, { 'interestJacketSize':  ?3 }, { 'interestBrand': { '$in': ?4 } }, { 'following': { '$in': ?5 } } ], 'userID': { '$ne': ?6 } }")
    List<userInfo> findBySizesOrBrandsOrFollowing(String interestPantSize, String interestShoeSize, String interestShirtSize,
                                                  String interestJacketSize, List<String> interestBrands, HashSet<UUID> following, UUID userIDCurrent);

    //List<userContact> findByLastName(String lastName);

    userInfo findByUserEmail(String email);

    userInfo findByUserPhone(String phone);

    userInfo findByUserAddress(String address);

}
