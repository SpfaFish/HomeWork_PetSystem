package ysu.szx.sys.petsys.Mapper;

import org.apache.ibatis.annotations.*;
import ysu.szx.sys.petsys.Data.Adoptapplication;
import ysu.szx.sys.petsys.Data.Pet;
import ysu.szx.sys.petsys.Data.Relationship;

import java.util.ArrayList;

@Mapper
public interface PetMapper {
    @Select("select * from pet where id = #{id}")
    public Pet GetById(int id);

    @Select("select * from relationship where master_id = #{id}")
    public ArrayList<Relationship> GetRelationShipByMasterId(int id);

    @Select("select * from relationship where master_id = #{master_id} and pet_id = #{pet_id}")
    public Relationship GetRelationship(int master_id, int pet_id);

    @Select("select * from adoptapplication where from_id = #{id}")
    public ArrayList<Adoptapplication> GetApplicationByMasterId(int id);

    @Insert("INSERT INTO pet(id, name, breeds, health, picture_id, notes, is_adopted) VALUES(#{id}, #{name}, #{breeds}, #{health}, #{picture_id}, #{notes}, #{is_adopted})")
    public int InsertPet(Pet pet);

    @Insert("INSERT INTO adoptapplication(from_id, pet_id, time, stat) VALUES(#{from_id}, #{pet_id}, #{time}, #{stat})")
    public boolean InsertApplication(Adoptapplication adoptapplication);

    @Update("UPDATE pet SET is_adopted = true WHERE id = #{id}")
    public boolean SetAdopted(int id);

    @Update("UPDATE adoptapplication SET stat = #{stat} WHERE from_id = #{from_id} AND pet_id = #{pet_id} AND time = #{time}")
    public int ResetStat(Adoptapplication adoptapplication);
    @Select("select * from adoptapplication where pet_id = #{id} AND stat = 0")
    public ArrayList<Adoptapplication> GetUnsolvedApplicationById(int id);
    @Update("UPDATE adoptapplication SET stat = 0 WHERE from_id = #{from_id} and pet_id != #{to_id}")
    public boolean ApplyApplication(Adoptapplication adoptapplication);

    @Delete("DELETE FROM pet WHERE id = #{id}")
    public int DeleteById(int id);

    @Delete("DELETE FROM relationship WHERE master_id = #{master_id} AND pet_id = #{pet_id}")
    public boolean DeleteRelationship(int master_id, int pet_id);

    @Insert("INSERT INTO relationship(master_id, pet_id) VALUES(#{masterId}, #{petId})")
    boolean InsertRelationship(int masterId, int petId);

    @Select("select name from pet where id = #{id}")
    String GetPetName(Integer x);

    @Select("select * from adoptapplication where pet_id = #{id}")
    ArrayList<Adoptapplication> GetApplicationByPetId(int id);
}
