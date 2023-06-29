package ysu.szx.sys.petsys.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ysu.szx.sys.petsys.Data.Contact;
import ysu.szx.sys.petsys.Mapper.ContactMapper;

import java.util.ArrayList;

@Repository
public class ContactDao {
    @Autowired
    private ContactMapper contactMapper;
    public ArrayList<Contact> GetFromId(int id){
        return contactMapper.FindFromId(id);
    }
    public boolean Insert(Contact contact){
        return contactMapper.Insert(contact) > 0;
    }

    public ArrayList<Contact> GetFromExactlyId(int aId, int bId) {
        return contactMapper.GetExactlyId(aId, bId);
    }
}
