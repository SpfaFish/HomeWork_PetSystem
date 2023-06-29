package ysu.szx.sys.petsys.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ysu.szx.sys.petsys.Dao.PetDao;
import ysu.szx.sys.petsys.Dao.StatusDao;
import ysu.szx.sys.petsys.Data.Adoptapplication;
import ysu.szx.sys.petsys.Data.Pet;
import ysu.szx.sys.petsys.Data.PetListData;
import ysu.szx.sys.petsys.Data.Relationship;
import ysu.szx.sys.petsys.Pojo.Results;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

@Service
public class PetService {
    @Autowired
    private PetDao petDao;
    @Autowired
    private StatusDao statusDao;
    public Results GetPet(int id){
        Pet pet = petDao.GetPet(id);
        if(pet == null) return Results.Error();
        else return Results.Success(pet);
    }
    public ArrayList<Pet> GetPetList(int id){
        ArrayList<Relationship> relationships = petDao.GetByMasterId(id);
        ArrayList<Pet> pet = new ArrayList<Pet>();
        for(Relationship relationship : relationships){
            pet.add(petDao.GetPet(relationship.getPet_id()));
        }
        return pet;
    }
    public ArrayList<Integer> GetPetIdList(int id){
        ArrayList<Relationship> relationships = petDao.GetByMasterId(id);
        ArrayList<Integer> pet = new ArrayList<Integer>();
        for(Relationship relationship : relationships){
            pet.add(relationship.getPet_id());
        }
        return pet;
    }
    public ArrayList<PetListData> GetPetSimpleList(int id){
        ArrayList<Integer> pet = GetPetIdList(id);
        ArrayList<PetListData> res = new ArrayList<>();
        for(Integer x : pet){
            res.add(new PetListData(x, petDao.GetPetName(x)));
        }
        return res;
    }
    public ArrayList<Adoptapplication> GetAdoptList(int id){
        return petDao.GetApplicationById(id);
    }
    public ArrayList<Adoptapplication> GetApplication(int id){
        return petDao.GetApplicationByPetId(id);
    }
    public boolean CheckRelationship(int master_id, int pet_id){
        Relationship tmp = petDao.GetRelationship(master_id, pet_id);
        if(tmp == null) return false;
        else return true;
    }

    public Results InsertPet(int master_id, Pet pet){
        int new_id = statusDao.GetPetID() + 1;
        pet.setId(new_id);
        statusDao.AddPetId(1);
        if(petDao.Insert(pet) > 0 && petDao.AddRelationship(master_id, new_id)) return Results.Success(new_id);
        else return Results.ErrorMessage("DataBase can not insert");
    }

    public Results AdoptApplicate(Adoptapplication application){
        Pet pet = petDao.GetPet(application.getPet_id());
        if(pet == null) return Results.ErrorMessage("Pet can not find");
        if(pet.getIs_adopted()) return Results.ErrorMessage("Pet has been adopted");
        if(petDao.InsertApplication(application)) return Results.Success();
        else return Results.Error();
    }

    public Results Adopt(int master_id, Adoptapplication application){
        if(!petDao.CheckRelationship(master_id, application.getPet_id())) return Results.ErrorMessage("Not your pet");
        if(application.getStat().equals(1)){
            int flag = 0;
            petDao.SetAdopted(application.getPet_id());
            ArrayList<Adoptapplication> list = petDao.GetUnsolvedApplicationById(application.getPet_id());
            for(int i = 0; i < list.size(); i++){
                Adoptapplication tmp = list.get(i);
                if(!tmp.getFrom_id().equals(application.getFrom_id()) || !tmp.getTime().equals(application.getTime())) tmp.setStat(-1);
                else{
                    tmp.setStat(1);
                    flag = 1;
                }
                if(!(petDao.ResetStat(tmp) > 0)) return Results.ErrorMessage("DataBase Error");
            }
            if(flag != 1) return Results.ErrorMessage("can not manage");
        }else if(application.getStat().equals(-1)){
            ArrayList<Adoptapplication> list = petDao.GetUnsolvedApplicationById(application.getPet_id());
            for(int i = 0; i < list.size(); i++){
                Adoptapplication tmp = list.get(i);
                System.out.println(tmp.getFrom_id());
                System.out.println(application.getFrom_id());
                System.out.println(tmp.getTime());
                System.out.println(application.getTime());
                if(tmp.getFrom_id().equals(application.getFrom_id()) && tmp.getTime().equals(application.getTime())){
                    tmp.setStat(-1);
                    if(!(petDao.ResetStat(tmp) > 0)) return Results.ErrorMessage("DataBase Error");
                    Results.Success();
                }
            }
            return Results.ErrorMessage("Can not manage");
        }else return Results.ErrorMessage("Unknown Stat");
        return Results.Success();
    }
    public Results DeletePet(int master_id, int pet_id){
        if(!CheckRelationship(master_id, pet_id)){
            return Results.ErrorMessage("Not Your Pet");
        }
        if(petDao.DeletePetById(pet_id) > 0 && petDao.DeleteRelationShip(master_id, pet_id)) return Results.Success();
        else return Results.ErrorMessage("DateBase Error when Delete pet");
    }

    public ArrayList<Adoptapplication> GetAllApplication(int id) {
        ArrayList<Integer> petId = GetPetIdList(id);
        ArrayList<Adoptapplication> res = new ArrayList<>();
        for(Integer x : petId){
            res.addAll(petDao.GetApplicationByPetId(x));
        }
        return res;
    }
}
