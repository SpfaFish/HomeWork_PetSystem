package ysu.szx.sys.petsys.Mapper;

import org.apache.ibatis.annotations.*;
import ysu.szx.sys.petsys.Data.Attention;
import ysu.szx.sys.petsys.Data.Person;
import ysu.szx.sys.petsys.Data.Pet;

import java.util.ArrayList;

@Mapper
public interface PersonMapper {
    @Select("select * from person where id = #{id}")
    public Person GetById(int id);

    @Select("select * from attention where from_id = #{from_id}")
    public ArrayList<Attention> GetAttentionByFromId(int from_id);

    @Insert("INSERT INTO person(id, name, province, city_or_county, distinguish, community, email, picture_id) VALUES(#{id}, #{name}, #{province}, #{city_or_county}, #{distinguish}, #{community}, #{email}, #{picture_id})")
    public Integer InsertPerson(Person person);

    @Update("UPDATE person SET name = #{name}, province = #{province}, city_or_county = #{city_or_county}, distinguish = #{distinguish}, community = #{community}, email = #{email}, picture_id = #{picture_id} WHERE id = #{id}")
    public boolean UpdateById(Person person);

    @Select("SELECT * FROM attention WHERE from_id = #{fromId} AND to_id = #{toId}")
    Attention GetAttention(int fromId, int toId);

    @Insert("INSERT INTO attention(from_id, to_id) VALUES(#{from_id}, #{to_id})")
    void InsertAttention(int from_id, int to_id);

    @Delete("DELETE FROM attention WHERE from_id = #{myId} AND to_id = #{otherId}")
    void DeleteAttention(int myId, int otherId);

    @Select("SELECT * FROM root WHERE person_id = #{personId}")
    Integer FindRoot(int personId);
}
