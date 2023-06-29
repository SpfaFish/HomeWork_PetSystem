package ysu.szx.sys.petsys.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ysu.szx.sys.petsys.Data.Status;
import ysu.szx.sys.petsys.Mapper.StatusMapper;

@Repository
public class StatusDao {
    @Autowired
    private StatusMapper statusMapper;
    public int GetPersonID(){
        Status tmp = statusMapper.GetStatus("root");
        return tmp.getPersonID();
    }
    public int GetPetID(){
        Status tmp = statusMapper.GetStatus("root");
        return tmp.getPetID();
    }
    public int GetPostID(){
        Status tmp = statusMapper.GetStatus("root");
        return tmp.getPostID();
    }
    public int GetPictureID(){
        Status tmp = statusMapper.GetStatus("root");
        return tmp.getPictureID();
    }

    public Status GetStatus(){
        return statusMapper.GetStatus("root");
    }

    public void AddPersonId(int d){
        Status tmp = statusMapper.GetStatus("root");
        int new_id = tmp.getPersonID() + d;
        statusMapper.SetPersonID(new_id);
    }
    public void AddPetId(int d){
        Status tmp = statusMapper.GetStatus("root");
        int new_id = tmp.getPetID() + d;
        statusMapper.SetPetID(new_id);
    }
    public void AddPostId(int d){
        Status tmp = statusMapper.GetStatus("root");
        int new_id = tmp.getPostID() + d;
        statusMapper.SetPostID(new_id);
    }
    public void AddPictureId(int d){
        Status tmp = statusMapper.GetStatus("root");
        int new_id = tmp.getPictureID() + d;
        statusMapper.SetPictureID(new_id);
    }

}
