package ysu.szx.sys.petsys.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import ysu.szx.sys.petsys.Data.Account;

@Mapper
public interface AccountMapper {
    @Select("select * from account where account = #{account} and password = #{password}")
    public Account CheckAccountWithPassword(String account, String password);

    @Select("select * from account where account = #{account}")
    public Account CheckAccount(String account);

    @Insert("insert into account(person_id, account, password) values(#{person_id}, #{account}, #{password})")
    public Integer InsertAccount(Account account);

    @Select("SELECT * FROM account WHERE person_id = #{id}")
    public Account FindAccountById(int id);

    @Update("UPDATE account SET password = #{password} WHERE person_id = #{person_id}")
    public int Update(Account account);
}
