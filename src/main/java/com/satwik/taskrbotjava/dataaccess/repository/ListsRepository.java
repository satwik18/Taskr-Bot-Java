package com.satwik.taskrbotjava.dataaccess.repository;

import com.satwik.taskrbotjava.dataaccess.document.Lists;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListsRepository extends MongoRepository<Lists, Integer> {
    @Query("{'listID' : ?0}")
    List<Lists> findByListID(Integer listID);

    @Query("{'name' : ?0}")
    List<Lists> findByName(String name);

    List<Lists> findByListIDIn(List<Integer> listID);

//    @Query(sort="{ listID : -1 }", fields="{'listID' : 1}")
//    List<Lists> findByListIDDesc();

    List<Lists> findTop1ByOrderByListIDDesc();

    default Integer getLatestId() {
        Lists res = findTop1ByOrderByListIDDesc().stream().findFirst().orElse(null);
        return res == null ? 0 : res.getListID();
    }
}
