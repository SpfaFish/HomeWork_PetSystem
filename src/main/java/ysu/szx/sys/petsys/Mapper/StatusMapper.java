package ysu.szx.sys.petsys.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ysu.szx.sys.petsys.Data.Status;

@Mapper
public interface StatusMapper {
    @Select("select * from status where name = #{name}")
    public Status GetStatus(String name);

    @Update("update status set personID = #{id} where name = 'root'")
    public void SetPersonID(int id);
    @Update("update status set petID = #{id} where name = 'root'")
    public void SetPetID(int id);
    @Update("update status set postID = #{id} where name = 'root'")
    public void SetPostID(int id);
    @Update("update status set pictureID = #{id} where name = 'root'")
    public void SetPictureID(int id);

}
