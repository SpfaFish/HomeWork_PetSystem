package ysu.szx.sys.petsys.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ysu.szx.sys.petsys.Data.Attention;
import ysu.szx.sys.petsys.Data.Person;
import ysu.szx.sys.petsys.Mapper.PersonMapper;

import java.util.ArrayList;

@Repository
public class PersonDao {
    @Autowired
    private PersonMapper personMapper;
    public ArrayList<Attention> GetAttentionListByFromId(int from_id){
        return personMapper.GetAttentionByFromId(from_id);
    }
    public Person GetPersonById(int id){
        return personMapper.GetById(id);
    }
    public Integer Insert(Person person){
        return personMapper.InsertPerson(person);
    }
    public boolean Update(Person person){
        return personMapper.UpdateById(person);
    }

    public Attention checkAttention(int from_id, int to_id) {
        return personMapper.GetAttention(from_id, to_id);
    }

    public void InsertAttention(int myId, int otherId) {
        personMapper.InsertAttention(myId, otherId);
    }

    public void DeleteAttention(int myId, int otherId) {
        personMapper.DeleteAttention(myId, otherId);
    }

    public boolean CheckRoot(int personId) {
        return personMapper.FindRoot(personId) != null;
    }
}
