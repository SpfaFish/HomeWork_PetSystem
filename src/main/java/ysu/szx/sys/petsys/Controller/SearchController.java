package ysu.szx.sys.petsys.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ysu.szx.sys.petsys.Data.Person;
import ysu.szx.sys.petsys.Pojo.Results;
import ysu.szx.sys.petsys.Service.SearchService;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;
    @GetMapping("/searchPerson")
    public Results SearchPerson(String name, String province, String city_or_county, String distinguish){
        Person person = new Person();
        person.setName(name);
        person.setProvince(province);
        person.setCity_or_county(city_or_county);
        person.setDistinguish(distinguish);
        return searchService.SearchPerson(person);
    }
    @GetMapping("/searchPost")
    public Results SearchPost(String name, Integer like_count, Integer collect_count){
        return searchService.SearchPost(name, like_count, collect_count);
    }
}
