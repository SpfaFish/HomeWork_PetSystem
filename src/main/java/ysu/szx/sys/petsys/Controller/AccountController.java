package ysu.szx.sys.petsys.Controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ysu.szx.sys.petsys.Data.Account;
import ysu.szx.sys.petsys.Data.Person;
import ysu.szx.sys.petsys.Pojo.Results;
import ysu.szx.sys.petsys.Service.AccountService;
import ysu.szx.sys.petsys.Service.PersonService;
import ysu.szx.sys.petsys.Utils.JwtUtils;

import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PersonService personService;
    @PostMapping("/Login")
    public Results Login(@RequestBody Map<String, Object> map){
        String account = map.get("account").toString();
        String password = map.get("password").toString();
        return accountService.Login(account, password);
    }
    @PostMapping("/Regist")
    public Results Regist(@RequestBody Map<String, Object> map){
        Account account = new Account();
        System.out.println(map);

        account.setAccount(map.get("account").toString());
        account.setPassword(map.get("password").toString());

        if(accountService.checkAccount(account.getAccount())) return Results.ErrorMessage("账户已存在");

        Person person = new Person();
        person.setName(map.get("name").toString());
        person.setProvince(map.get("province").toString());
        person.setCity_or_county(map.get("city_or_county").toString());
        person.setDistinguish(map.get("distinguish").toString());
        person.setCommunity(map.get("community").toString());
        person.setEmail(map.get("email").toString());
        if(map.containsKey("picture_id")) person.setPicture_id((int) map.get("picture_id"));

        return accountService.Regist(account, person);
    }
    @GetMapping("/CheckRoot")
    public Results CheckRoot(HttpServletRequest request){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int my_id = (int) claims.get("id");
            return personService.CheckRoot(my_id);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
}
