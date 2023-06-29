package ysu.szx.sys.petsys.Controller;

import com.beust.ah.A;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ysu.szx.sys.petsys.Data.Account;
import ysu.szx.sys.petsys.Data.Contact;
import ysu.szx.sys.petsys.Data.Person;
import ysu.szx.sys.petsys.Pojo.Results;
import ysu.szx.sys.petsys.Service.MessageService;
import ysu.szx.sys.petsys.Service.PersonService;
import ysu.szx.sys.petsys.Utils.JwtUtils;
import ysu.szx.sys.petsys.Utils.TimeUtils;

import java.util.Map;

@RestController
public class PersonController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private PersonService personService;
    @GetMapping("/getPerson")
    public Results getPreson(int id){
        return personService.findPerson(id);
    }
    @PostMapping("/getContactList")
    public Results GetContactList(HttpServletRequest request){
        String jwt = request.getHeader("token");
        System.out.println(jwt);
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int id = (int) claims.get("id");
            return Results.Success(messageService.GetMessageList(id));
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("/getContact")
    public Results GetContact(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int a_id = (int) claims.get("id");
            int b_id = (int) map.get("id");
            return Results.Success(messageService.GetMessage(a_id, b_id));
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @GetMapping("/MyAttention")
    public Results GetMyAttentionId(int id){
//        int id = (int) map.get("id");
        return Results.Success(personService.GetAttentionId(id));
    }
    @PostMapping("/sendMessage")
    public Results SendMessage(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int id = (int) claims.get("id");
            Contact message = new Contact();
            message.setFrom_id(id);
            message.setTo_id((int) map.get("to_id"));
            message.setText(map.get("text").toString());
            message.setTime(TimeUtils.getIntTime());
            if(messageService.InsertMessage(message)) return Results.Success();
            else return Results.ErrorMessage("Can not insert a message");
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("updateProfile")
    public Results UpdateProfile(HttpServletRequest request, @RequestBody Map<String, Object> map){


        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int id = (int) claims.get("id");
            Person person = new Person();
            person.setId(id);
            person.setName(map.get("name").toString());
            person.setProvince(map.get("province").toString());
            person.setCity_or_county(map.get("city_or_county").toString());
            person.setDistinguish(map.get("distinguish").toString());
            person.setCommunity(map.get("community").toString());
            person.setEmail(map.get("email").toString());
            if(map.containsKey("picture_id")) person.setPicture_id((int) map.get("picture_id"));

            Account account = new Account();
            account.setPerson_id(id);
            if(map.containsKey("oldPassword")){
                account.setAccount(map.get("oldPassword").toString());//用账户存储旧密码核对
                account.setPassword(map.get("newPassword").toString());
            }
            return personService.UpdateProfile(account, person);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }

    }

    @PostMapping("/LikePerson")
    public Results LikePerson(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int my_id = (int) claims.get("id");
            int other_id = (int) map.get("id");
            return personService.LikePerson(my_id, other_id);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("/UnLikePerson")
    public Results UnlikePerson(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int my_id = (int) claims.get("id");
            int other_id = (int) map.get("id");
            return personService.UnlikePerson(my_id, other_id);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }

    @PostMapping("/CheckAttention")
    public Results CheckAttention(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);

//            System.out.println((int) claims.get("id"));
//            System.out.println(map.get("id").toString());
            int my_id = (int) claims.get("id");
            int other_id = (int) map.get("id");
            boolean stat = personService.checkAttention(my_id, other_id);
            return Results.Success(stat);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN OR param ERROR");
        }
    }
}
