package com.satwik.taskrbotjava.dataaccess.queries;

import com.satwik.taskrbotjava.dataaccess.document.Communities;
import com.satwik.taskrbotjava.dataaccess.repository.CommunitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommunityQueryController {

    private CommunitiesRepository communitiesRepository;

    @Autowired
    public CommunityQueryController(CommunitiesRepository communitiesRepository) {
        this.communitiesRepository = communitiesRepository;
    }

    public void checkAndAddCommunity(Long communityID){
        List<Communities> communities = communitiesRepository.findByCommunityID(communityID);
        if (communities.isEmpty()) {
            Communities currCommunity = new Communities(communityID, new ArrayList<Integer>(), new ArrayList<Long>());
            communitiesRepository.save(currCommunity);
        }
    }
}
