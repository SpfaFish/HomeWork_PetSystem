package ysu.szx.sys.petsys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ysu.szx.sys.petsys.Dao.AccountDao;
import ysu.szx.sys.petsys.Dao.PersonDao;
import ysu.szx.sys.petsys.Dao.StatusDao;
import ysu.szx.sys.petsys.Data.Account;
import ysu.szx.sys.petsys.Data.Person;
import ysu.szx.sys.petsys.Pojo.Results;
import ysu.szx.sys.petsys.Utils.JwtUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private StatusDao statusDao;
    @Autowired
    private PersonDao personDao;

    public Results Login(String account, String password){
        Account tmp = accountDao.CheckAccount(account, password);
        if(tmp == null) return Results.Error();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", tmp.getPerson_id());
        Person person = personDao.GetPersonById(tmp.getPerson_id());
//        claims.put("name", person.getName());
        String jwt = JwtUtils.GetStringJwt(claims);
        return Results.Success(jwt);
    }
    public boolean checkAccount(String account){
        Account tmp = accountDao.FindAccount(account);
        if(tmp == null) return false;
        else return true;
    }

    public Results Regist(Account account, Person person){
        Account tmp = accountDao.CheckOnlyAccount(account.getAccount());
        if(tmp != null) return Results.ErrorMessage("Account Have Regist");
        int id = statusDao.GetPersonID() + 1;
        statusDao.AddPersonId(1);
        account.setPerson_id(id);
        person.setId(id);
        if(accountDao.Insert(account) > 0 && personDao.Insert(person) > 0) return Results.Success();
        else return Results.Error();
    }
}
