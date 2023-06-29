package ysu.szx.sys.petsys.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ysu.szx.sys.petsys.Data.Account;
import ysu.szx.sys.petsys.Data.Person;
import ysu.szx.sys.petsys.Mapper.AccountMapper;
import ysu.szx.sys.petsys.Mapper.PersonMapper;

@Repository
public class AccountDao {
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AccountMapper accountMapper;
    public Person Profile(int id){
        return personMapper.GetById(id);
    }
    public Account CheckAccount(String account, String password){
        return accountMapper.CheckAccountWithPassword(account, password);
    }
    public Account FindAccount(String account){
        return accountMapper.CheckAccount(account);
    }
    public Account FindAccountById(int id){
        return accountMapper.FindAccountById(id);
    }
    public Integer Insert(Account account){
        return accountMapper.InsertAccount(account);
    }

    public boolean Update(Account account){
        return accountMapper.Update(account) > 0;
    }

    public Account CheckOnlyAccount(String account) {
        return accountMapper.CheckAccount(account);
    }
}
