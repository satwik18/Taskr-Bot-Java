package com.satwik.taskrbotjava.dataaccess.repository;

import com.satwik.taskrbotjava.dataaccess.document.Items;
import com.satwik.taskrbotjava.dataaccess.document.Lists;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends MongoRepository<Items, Integer> {
    @Query("{'itemID' : ?0}")
    List<Items> findByItemID(Integer itemID);

//    @Query("{'itemID' : ?0}")
    List<Items> findByItemIDIn(List<Integer> itemID);

    List<Items> findTop1ByOrderByItemIDDesc();

    default Integer getLatestId() {
        Items res = findTop1ByOrderByItemIDDesc().stream().findFirst().orElse(null);
        return res == null ? 0 : res.getItemID();
    }
}
