package com.slugexchange.backendmongodb.repository;

import com.slugexchange.backendmongodb.model.userOffers;
import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public interface userOffersRepository extends MongoRepository<userOffers, UUID> {

    List<userOffers> findByUserID(UUID userID);

    // query for searching with filters
    @Query("{ '$and': ?0 }")
    List<userOffers> findByCriteria(List<Document> criteria);

    //need a method that inputs two lists for brands and categories, and can return offers of all combinations of brands and categories
    @Query("{ '$and': [ { 'brand': { '$in': ?0 } }, { 'category': { '$in': ?1 } } ] }")
    List<userOffers> findByBrandsAndCategories(List<String> brands, List<String> categories);

    @Query("{ 'userID' :  { '$in':  ?0 } }")
    List<userOffers> findByFollowing(HashSet<UUID> following);


    //@Query("{ '$and': [?0, { 'value': { 'lte': ?1 } }] }")
    //List<userOffers> findByCriteriaAndValue(List<Criteria> criteria, @Param("value") Integer value);

    //@Query("{ '$and': [ { 'itemName': ?0 }, { 'category': ?1 }, { 'brand': ?2 }, { 'value': { '$lte': ?3 } } ] }")
    //List<userOffers> findByFields(String itemName, String category, String brand, Integer value);

    //@Query("{ '$and': [ { '$or': [ { '$and': [ { 'itemName': { '$type': 2 } }, { 'itemName': { '$eq': ?0 } } ] }, { '$and': [ { 'itemName': { '$exists': true } }, { 'itemName': { '$type': 10 } } ] } ] }, { '$or': [ { '$and': [ { 'category': { '$type': 2 } }, { 'category': { '$eq': ?1 } } ] }, { '$and': [ { 'category': { '$exists': true } }, { 'category': { '$type': 10 } } ] } ] }, { '$or': [ { '$and': [ { 'brand': { '$type': 2 } }, { 'brand': { '$eq': ?2 } } ] }, { '$and': [ { 'brand': { '$exists': true } }, { 'brand': { '$type': 10 } } ] } ] }, { '$or': [ { '$and': [ { 'value': { '$type': 16 } }, { 'value': { '$lte': ?3 } } ] }, { '$and': [ { 'value': { '$exists': true } }, { 'value': { '$type': 10 } } ] } ] } ] }")
    //@Query("{'$and' : [{'$or': [ { 'itemName': { '$type': 2, '$eq': ?0 } }, { 'itemName': { '$exists': false } } ] }, {'$or': [ { 'category': { '$type': 2, '$eq': ?1 } }, { 'category': { '$exists': false } } ] }, {'$or': [ { 'brand': { '$type': 2, '$eq': ?2 } }, { 'brand': { '$exists': false } } ] }, {'$or': [ { 'value': { '$type': 16, '$lte': ?3 } }, { 'value': { '$exists': false } } ] } ] }")
    //@Query("{ '$or': [ { 'itemName': { '$type': 2, '$eq': ?0 } }, { 'itemName': { '$exists': false } }, { 'category': { '$type': 2, '$eq': ?1 } }, { 'category': { '$exists': false } }, { 'brand': { '$type': 2, '$eq': ?2 } }, { 'brand': { '$exists': false } }, { 'value': { '$type': 16, '$lte': ?3 } }, { 'value': { '$exists': false } } ] }")
    //@Query("{ '$and': [ { 'itemName': { '$exists': true, '$eq': ?0 } }, { 'category': { '$exists': true, '$eq': ?1 } }, { 'brand': { '$exists': true, '$eq': ?2 } }, { 'value': { '$exists': true, '$lte': ?3 } } ] }")
    //@Query("{ '$and': [ { 'itemName': { '$exists': true, '$eq': ?0 } }, { 'category': { '$exists': true, '$eq': ?1 } }, { 'brand': { '$exists': true, '$eq': ?2 } }, { 'value': { '$exists': true, '$lte': ?3 } } ] }")
    //List <userOffers> findByItemNameAndCategoryAndBrandAndValueLessThanEqual(String itemName,String category,String brand,Integer value);

    //List<userOffers> findByItemName(String itemName);

    //List<userOffers> findByCategory(String category);

    //List<userOffers> findByCategoryAndSubcategory(String category, String subcategory);

}
