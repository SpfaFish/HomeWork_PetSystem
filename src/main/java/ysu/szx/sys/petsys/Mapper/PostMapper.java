package ysu.szx.sys.petsys.Mapper;

import org.apache.ibatis.annotations.*;
import ysu.szx.sys.petsys.Data.*;

import java.util.ArrayList;

@Mapper
public interface PostMapper {
    @Select("select post_id from my_post where person_id = #{id}")
    public ArrayList<Integer> GetPostById(int id);

    @Select("SELECT * FROM liked WHERE person_id = #{person_id} AND post_id = #{post_id}")
    public Liked CheckLiked(int person_id, int post_id);
    @Select("SELECT * FROM collect WHERE person_id = #{person_id} AND post_id = #{post_id}")
    public Collect CheckCollect(int person_id, int post_id);
    @Insert("INSERT INTO liked(person_id, post_id) VALUES(#{person_id}, #{post_id})")
    public boolean InsertLike(int person_id, int post_id);
    @Insert("INSERT INTO collect(person_id, post_id) VALUES(#{person_id}, #{post_id})")
    public boolean InsertCollect(int person_id, int post_id);
    @Update("UPDATE post SET like_count = #{d} WHERE id = #{id}")
    public boolean ChangeLikeCount(int id, int d);

    @Select("SELECT like_count FROM post WHERE id = #{id}")
    public Integer GetLikeCount(int id);
    @Select("SELECT collect_count FROM post WHERE id = #{id}")
    public Integer GetCollectCount(int id);
    @Update("UPDATE post SET collect_count = #{d} WHERE id = #{id}")
    public boolean ChangeCollectCount(int id, int d);

    @Select("SELECT picture_id FROM post WHERE id = #{x}")
    int GetPicId(Integer x);

    @Select("SELECT name FROM post WHERE id = #{x}")
    String GetName(Integer x);

    @Insert("INSERT INTO my_post(person_id, post_id) VALUES(#{id}, #{postId})")
    void InsertMyPost(int id, int postId);

    @Insert("INSERT INTO post(id, name, time, text, like_count, collect_count, picture_id) VALUES(#{id}, #{name}, #{time}, #{text}, #{like_count}, #{collect_count}, #{picture_id})")
    void InsertPost(Post post);

    @Insert("INSERT INTO postpic(post_id, pic_id) VALUES(#{post_id}, #{pic_id})")
    void InsertPostPic(Integer postId, Integer picId);

    @Select("SELECT id, picture_id, name, time FROM post WHERE like_count >= #{like_count} AND collect_count >= #{collect_count} AND name LIKE '%${name}%'")
    ArrayList<SearchPostData> SearchPost(String name, Integer like_count, Integer collect_count);

    @Select("SELECT person_id FROM my_post WHERE post_id = #{id}")
    Integer SearchOwner(Integer id);


    @Select("SELECT * FROM post WHERE id = #{id}")
    Post GetPost(int id);

    @Select("SELECT pic_id FROM postpic WHERE post_id = #{id}")
    ArrayList<Integer> GetPicArray(int id);

    @Delete("DELETE FROM my_post WHERE person_id = #{personId} AND post_id = #{postId}")
    void DeleteMyPost(int personId, int postId);

    @Delete("DELETE FROM post WHERE id = #{postId}")
    void DeletePost(int postId);

    @Select("SELECT pet_id FROM postpet WHERE post_id = #{id}")
    Integer GetPostPet(int id);

    @Insert("INSERT INTO postpet(post_id, pet_id) VALUES(#{postId}, #{petId})")
    void AddPostPet(int postId, Integer petId);
}
