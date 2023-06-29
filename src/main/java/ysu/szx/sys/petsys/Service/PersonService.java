package ysu.szx.sys.petsys.Service;

import com.beust.ah.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ysu.szx.sys.petsys.Dao.AccountDao;
import ysu.szx.sys.petsys.Dao.PersonDao;
import ysu.szx.sys.petsys.Data.Account;
import ysu.szx.sys.petsys.Data.Attention;
import ysu.szx.sys.petsys.Data.Person;
import ysu.szx.sys.petsys.Pojo.Results;

import java.util.ArrayList;

@Service
public class PersonService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private PersonDao personDao;
    public Results findPerson(int id){
        Person person = accountDao.Profile(id);
        if(person == null) return Results.ErrorMessage("can not find user");
        else return Results.Success(person);
    }
    public ArrayList<Integer> GetAttentionId(int from_id){
        ArrayList<Attention> attentions = personDao.GetAttentionListByFromId(from_id);
        ArrayList<Integer> attentionId = new ArrayList<>();
        for(Attention attention : attentions){
            attentionId.add(attention.getTo_id());
        }
        return attentionId;
    }

    public Results UpdateProfile(Account account, Person person){
        Account past = accountDao.FindAccountById(account.getPerson_id());
        Person pastp = personDao.GetPersonById(person.getId());
        if(person.getPicture_id() == null) person.setPicture_id(pastp.getPicture_id());
        if(account.getAccount() == null){
            account.setAccount(past.getPassword());
            account.setPassword(past.getPassword());
        }
        if(!past.getPassword().equals(account.getAccount())) return Results.ErrorMessage("Old password is wrong");
        if(accountDao.Update(account) && personDao.Update(person)) return Results.Success();
        else return Results.ErrorMessage("DataBase Error when Update account and person");

    }
    public boolean checkAttention(int myId, int otherId){
        Attention tmp = personDao.checkAttention(myId, otherId);
        return tmp != null;
    }
    public Results LikePerson(int myId, int otherId) {
        if(checkAttention(myId, otherId)) return Results.ErrorMessage("Have already liked");
        personDao.InsertAttention(myId, otherId);
        return Results.Success();
    }

    public Results UnlikePerson(int myId, int otherId) {
        if(!checkAttention(myId, otherId)) return Results.ErrorMessage("Not liked");
        personDao.DeleteAttention(myId, otherId);
        return Results.Success();
    }

    public Results CheckRoot(int id) {
        if(personDao.CheckRoot(id)) return Results.Success(1);
        else return Results.Success(0);
    }
}
