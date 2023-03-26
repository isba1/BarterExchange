package com.slugexchange.backendmongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Document(collection = "userInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class userInfo {
    @Id
    private UUID userID;
    private String password;
    //private String hashedPassword;
    //private String salt;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private List<String> interestCategory;
    private List<String> interestBrand;
    private HashSet<UUID> following;
    private String interestPantSize;
    private String interestShoeSize;
    private String interestShirtSize;
    private String interestJacketSize;

}
