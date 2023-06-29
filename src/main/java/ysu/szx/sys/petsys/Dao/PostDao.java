package ysu.szx.sys.petsys.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ysu.szx.sys.petsys.Data.Collect;
import ysu.szx.sys.petsys.Data.Liked;
import ysu.szx.sys.petsys.Data.Post;
import ysu.szx.sys.petsys.Data.SearchPostData;
import ysu.szx.sys.petsys.Mapper.PostMapper;
import ysu.szx.sys.petsys.Pojo.Results;

import java.util.ArrayList;

@Repository
public class PostDao {
    @Autowired
    private PostMapper postMapper;
    public ArrayList<Integer> GetPostById(int id){
        return postMapper.GetPostById(id);
    }

    public Liked CheckLike(int person_id, int post_id){
        return postMapper.CheckLiked(person_id, post_id);
    }
    public Collect CheckCollect(int person_id, int post_id){
        return postMapper.CheckCollect(person_id, post_id);
    }

    public boolean InsertLike(int person_id, int post_id){
        return postMapper.InsertLike(person_id, post_id);
    }

    public boolean InsertCollect(int person_id, int post_id){
        return postMapper.InsertCollect(person_id, post_id);
    }
    public boolean ChangeLikeCount(int id, int d){
        return postMapper.ChangeLikeCount(id, d);
    }
    public Integer GetLikeCount(int id){
        return postMapper.GetLikeCount(id);
    }
    public Integer GetCollectCount(int id){
        return postMapper.GetCollectCount(id);
    }

    public boolean ChangeCollectCount(int id, int d) {
        return postMapper.ChangeCollectCount(id, d);
    }

    public int GetPicId(Integer x) {
        return postMapper.GetPicId(x);
    }

    public String getName(Integer x) {
        return postMapper.GetName(x);
    }

    public void InsertMyPost(int id, int postId) {
        postMapper.InsertMyPost(id, postId);
    }

    public void InsertPost(Post post) {
        postMapper.InsertPost(post);
    }

    public void InsertPostPic(Integer post_id, Integer pic_id) {
        postMapper.InsertPostPic(post_id, pic_id);
    }

    public ArrayList<SearchPostData> SearchPost(String name, Integer likeCount, Integer collectCount) {
        return postMapper.SearchPost(name, likeCount, collectCount);
    }

    public Integer GetPostOwner(Integer id) {
        return postMapper.SearchOwner(id);
    }

    public Post GetPost(int id) {
        return postMapper.GetPost(id);
    }

    public ArrayList<Integer> GetPicArray(int id) {
        return postMapper.GetPicArray(id);
    }

    public void DeleteMyPost(int personId, int postId) {
        postMapper.DeleteMyPost(personId, postId);
    }

    public void DeletePost(int postId) {
        postMapper.DeletePost(postId);
    }

    public Integer GetPostPet(int id) {
        return postMapper.GetPostPet(id);
    }

    public void AddPostPet(int postId, Integer petId) {
        postMapper.AddPostPet(postId, petId);
    }
}
