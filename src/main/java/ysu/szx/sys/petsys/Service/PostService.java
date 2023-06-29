package ysu.szx.sys.petsys.Service;

import com.beust.ah.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ysu.szx.sys.petsys.Dao.PersonDao;
import ysu.szx.sys.petsys.Dao.PostDao;
import ysu.szx.sys.petsys.Dao.StatusDao;
import ysu.szx.sys.petsys.Data.MyPostData;
import ysu.szx.sys.petsys.Data.Post;
import ysu.szx.sys.petsys.Data.PostPic;
import ysu.szx.sys.petsys.Data.Status;
import ysu.szx.sys.petsys.Mapper.PostMapper;
import ysu.szx.sys.petsys.Mapper.StatusMapper;
import ysu.szx.sys.petsys.Pojo.Results;

import java.util.ArrayList;

@Service
public class PostService {
    @Autowired
    private PostDao postDao;
    @Autowired
    private StatusDao statusDao;
    @Autowired
    private PersonDao personDao;

    public ArrayList<MyPostData> GetPostById(int id){
        ArrayList<Integer> res;
        res = postDao.GetPostById(id);
        ArrayList<MyPostData> rt = new ArrayList<>();
        for(Integer x : res){
            int pic_id = postDao.GetPicId(x);
            String name = postDao.getName(x);
            rt.add(new MyPostData(x, pic_id, name));
        }
        return rt;
    }
    public Results GetPost(int id){
        Post post = postDao.GetPost(id);
        post.setPicArray(postDao.GetPicArray(id));
        post.setPerson_id(postDao.GetPostOwner(id));
        post.setPet_id(postDao.GetPostPet(id));
        return Results.Success(post);
    }
    public Results LikePost(int master_id, int post_id){
        if(postDao.CheckLike(master_id, post_id) != null) return Results.ErrorMessage("Has already Liked");
        postDao.InsertLike(master_id, post_id);
        Integer count = postDao.GetLikeCount(post_id);
        count += 1;
        postDao.ChangeLikeCount(post_id, count);
        return Results.Success();
    }

    public Results ColletPost(int person_id, int post_id){
        if(postDao.CheckCollect(person_id, post_id) != null) return Results.ErrorMessage("Has already Collected");
        postDao.InsertCollect(person_id, post_id);
        Integer count = postDao.GetCollectCount(post_id);
        count += 1;
        postDao.ChangeCollectCount(post_id, count);
        return Results.Success();
    }

    public Results UploadPost(int id, Post post) {
        int post_id = statusDao.GetPostID() + 1;
        statusDao.AddPostId(1);
        post.setId(post_id);
        postDao.InsertMyPost(id, post_id);
        if(post.getPet_id() != null) postDao.AddPostPet(post_id, post.getPet_id());
        postDao.InsertPost(post);
        for(Integer x : post.getPicArray()){
            postDao.InsertPostPic(post.getId(), x);
        }
        return Results.Success();
    }

    public Results DeletePost(int personId, int postId) {
        if(!personDao.CheckRoot(personId) && postDao.GetPostOwner(postId) != personId) return Results.ErrorMessage("Not your post can not be delete!");
        postDao.DeleteMyPost(personId, postId);
        postDao.DeletePost(postId);
        return Results.Success();
    }
}
