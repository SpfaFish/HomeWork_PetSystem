package ysu.szx.sys.petsys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ysu.szx.sys.petsys.Dao.PersonDao;
import ysu.szx.sys.petsys.Dao.PostDao;
import ysu.szx.sys.petsys.Data.Person;
import ysu.szx.sys.petsys.Data.SearchPostData;
import ysu.szx.sys.petsys.Pojo.Results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Service
public class SearchService {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PostDao postDao;
    public Results SearchPerson(Person person) {
        return Results.Success();
    }

    public Results SearchPost(String name, Integer likeCount, Integer collectCount) {
        if(name == null) name = "";
        if(likeCount == null) likeCount = 0;
        if(collectCount == null) collectCount = 0;
        ArrayList<SearchPostData> res = postDao.SearchPost(name, likeCount, collectCount);
        for(int i = 0; i < res.size(); i++){
            res.get(i).setPerson_id(postDao.GetPostOwner(res.get(i).getId()));
            res.get(i).setPerson_name(personDao.GetPersonById(res.get(i).getPerson_id()).getName());
            System.out.println(res.get(i).getTime());
        }
        Collections.sort(res, Comparator.comparingInt(SearchPostData::getTime).reversed());
        return Results.Success(res);
    }
}
