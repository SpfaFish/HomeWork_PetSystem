package ysu.szx.sys.petsys.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ysu.szx.sys.petsys.Data.Adoptapplication;
import ysu.szx.sys.petsys.Data.Pet;
import ysu.szx.sys.petsys.Data.Relationship;
import ysu.szx.sys.petsys.Mapper.PetMapper;

import java.util.ArrayList;

@Repository
public class PetDao {
    @Autowired
    private PetMapper petMapper;
    public Pet GetPet(int id){
        return petMapper.GetById(id);
    }
    public ArrayList<Relationship> GetByMasterId(int master_id){
        return petMapper.GetRelationShipByMasterId(master_id);
    }
    public ArrayList<Adoptapplication> GetApplicationById(int master_id){
        return petMapper.GetApplicationByMasterId(master_id);
    }
    public Relationship GetRelationship(int master_id, int pet_id){
        return petMapper.GetRelationship(master_id, pet_id);
    }
    public int Insert(Pet pet){
        return petMapper.InsertPet(pet);
    }
    public void SetAdopted(int id){
        petMapper.SetAdopted(id);
    }
    public boolean InsertApplication(Adoptapplication application){
        return petMapper.InsertApplication(application);
    }
    public boolean CheckRelationship(int master_id, int pet_id){
        Relationship relationship = petMapper.GetRelationship(master_id, pet_id);
        return relationship != null;
    }
    public ArrayList<Adoptapplication> GetUnsolvedApplicationById(int pet_id){
        return petMapper.GetUnsolvedApplicationById(pet_id);
    }
    public int ResetStat(Adoptapplication application){
        return petMapper.ResetStat(application);
    }
    public int DeletePetById(int pet_id){
        return petMapper.DeleteById(pet_id);
    }
    public boolean DeleteRelationShip(int master_id, int pet_id){
        return petMapper.DeleteRelationship(master_id, pet_id);
    }


    public boolean AddRelationship(int masterId, int petId) {
        return petMapper.InsertRelationship(masterId, petId);
    }

    public String GetPetName(Integer x) {
        return petMapper.GetPetName(x);
    }

    public ArrayList<Adoptapplication> GetApplicationByPetId(int id) {
        return petMapper.GetApplicationByPetId(id);
    }
}
