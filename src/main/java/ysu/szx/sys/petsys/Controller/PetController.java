package ysu.szx.sys.petsys.Controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ysu.szx.sys.petsys.Data.Adoptapplication;
import ysu.szx.sys.petsys.Data.Pet;
import ysu.szx.sys.petsys.Pojo.Results;
import ysu.szx.sys.petsys.Service.PetService;
import ysu.szx.sys.petsys.Utils.JwtUtils;
import ysu.szx.sys.petsys.Utils.TimeUtils;

import java.util.Map;

@RestController
public class PetController {
    @Autowired
    private PetService petService;
    @GetMapping("/getPet")
    public Results getPet(int id){
//        int id = (int) map.get("id");
        return petService.GetPet(id);
    }
    @PostMapping("/getAdoptRecords")
    public Results GetAdoptRecords(HttpServletRequest request){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int id = (int) claims.get("id");
            return Results.Success(petService.GetAdoptList(id));
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("/getAdoptApplication")
    public Results GetAdoptRecords(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int id = (int) claims.get("id");
            int pet_id = (int) map.get("id");
            System.out.println(id);
            System.out.println(pet_id);
            if(!petService.CheckRelationship(id, pet_id)) return Results.ErrorMessage("非本人宠物无法查看");
            return Results.Success(petService.GetApplication(pet_id));
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("getAllAdoptApplication")
    public Results GetAllAdoptApplication(HttpServletRequest request){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int id = (int) claims.get("id");
            return Results.Success(petService.GetAllApplication(id));
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @GetMapping("/MyPet")
    public Results GetPetList(Integer id){
//        Integer id = (Integer) map.get("id");
        if(id == null) return Results.ErrorMessage("Can not find master id");
        return Results.Success(petService.GetPetSimpleList(id));
    }
    @PostMapping("/uploadPet")
    public Results UploadPet(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int id = (int) claims.get("id");
            Pet pet = new Pet();
            pet.setName(map.get("name").toString());
            pet.setBreeds(map.get("breeds").toString());
            String healths = map.get("health").toString();
            int h;
            if(healths.equals("-1")) h = -1;
            else if(healths.equals("0")) h = 0;
            else h = 1;
            pet.setHealth(h);
            pet.setPicture_id((int) map.get("picture_id"));
            pet.setNotes(map.get("notes").toString());
            pet.setIs_adopted(false);
            return petService.InsertPet(id, pet);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("/adoptApplication")
    public Results Applicate(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int id = (int) claims.get("id");
            Adoptapplication adoptapplication = new Adoptapplication();
            adoptapplication.setFrom_id(id);
            adoptapplication.setPet_id((int) map.get("to_id"));
            adoptapplication.setTime(TimeUtils.getIntTime());
            adoptapplication.setStat(0);
            return petService.AdoptApplicate(adoptapplication);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("/Adopt")
    public Results Adopt(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int id = (int) claims.get("id");
            Adoptapplication adoptapplication = new Adoptapplication();
            adoptapplication.setFrom_id((int) map.get("from_id"));
            adoptapplication.setPet_id((int) map.get("to_id"));
            adoptapplication.setTime((int) map.get("time"));
            adoptapplication.setStat((int) map.get("stat"));
            return petService.Adopt(id, adoptapplication);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("/DeletePet")
    public Results DeletePet(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int master_id = (int) claims.get("id");
            int pet_id = (int) map.get("id");
            return petService.DeletePet(master_id, pet_id);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("/CheckPet")
    public Results CheckPet(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int master_id = (int) claims.get("id");
            int pet_id = (int) map.get("id");
            if(petService.CheckRelationship(master_id, pet_id)) return Results.Success(1);
            else return Results.Success(0);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
}
