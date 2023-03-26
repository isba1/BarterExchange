package com.slugexchange.backendmongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "userOffers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class userOffers {

    @Id
    private UUID postID;
    private UUID userID;
    private String itemName;
    private String category;
    private String description;
    private Integer value;
    private String brand;
    private String size;


}
