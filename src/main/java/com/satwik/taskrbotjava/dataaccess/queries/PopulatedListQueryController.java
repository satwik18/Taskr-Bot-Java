package com.satwik.taskrbotjava.dataaccess.queries;

import com.satwik.taskrbotjava.dataaccess.Views.PopulatedList;
import com.satwik.taskrbotjava.dataaccess.document.Communities;
import com.satwik.taskrbotjava.dataaccess.document.Items;
import com.satwik.taskrbotjava.dataaccess.document.Lists;
import com.satwik.taskrbotjava.dataaccess.document.Users;
import com.satwik.taskrbotjava.dataaccess.repository.CommunitiesRepository;
import com.satwik.taskrbotjava.dataaccess.repository.ItemsRepository;
import com.satwik.taskrbotjava.dataaccess.repository.ListsRepository;
import com.satwik.taskrbotjava.dataaccess.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PopulatedListQueryController {

    private CommunitiesRepository communitiesRepository;
    private ItemsRepository itemsRepository;
    private ListsRepository listsRepository;
    private UsersRepository usersRepository;

    @Autowired
    public PopulatedListQueryController(CommunitiesRepository communitiesRepository, ItemsRepository itemsRepository, ListsRepository listsRepository, UsersRepository usersRepository) {
        this.communitiesRepository = communitiesRepository;
        this.itemsRepository = itemsRepository;
        this.listsRepository = listsRepository;
        this.usersRepository = usersRepository;
    }

    public List<PopulatedList> getHubList(Long communityID) {
        Communities community = communitiesRepository.findByCommunityID(communityID).get(0);
        List<Users> users = usersRepository.findByIdIn(community.getUsers());
        List<Integer> listIds = users.stream().map(user -> user.getListIds().get(0)).collect(Collectors.toList());
        List<Lists> lists = listsRepository.findByListIDIn(listIds);
        List<PopulatedList> populatedLists = lists.stream().map(list -> {
            List<Items> items = itemsRepository.findByItemIDIn(list.getItemIds());
            return new PopulatedList(list, items);
        }).collect(Collectors.toList());

        return populatedLists;
    }
}
