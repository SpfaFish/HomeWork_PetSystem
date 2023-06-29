package ysu.szx.sys.petsys.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ysu.szx.sys.petsys.Data.Account;
import ysu.szx.sys.petsys.Data.Contact;

import java.util.ArrayList;

@Mapper
public interface ContactMapper {
    @Select("select * from contact where from_id = #{id} or to_id = #{id}")
    public ArrayList<Contact> FindFromId(int id);

    @Insert("INSERT INTO contact(from_id, to_id, time, text) VALUES(#{from_id}, #{to_id}, #{time}, #{text})")
    public int Insert(Contact contact);

    @Select("SELECT * FROM contact WHERE (from_id = #{aId} AND to_id = #{bId}) OR (from_id = #{bId} AND to_id = #{aId})")
    ArrayList<Contact> GetExactlyId(int aId, int bId);
}
