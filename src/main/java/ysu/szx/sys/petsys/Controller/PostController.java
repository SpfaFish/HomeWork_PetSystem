package ysu.szx.sys.petsys.Controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.message.ReusableMessage;
import org.apache.poi.hssf.dev.ReSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ysu.szx.sys.petsys.Data.Post;
import ysu.szx.sys.petsys.Pojo.Results;
import ysu.szx.sys.petsys.Service.PostService;
import ysu.szx.sys.petsys.Utils.JwtUtils;
import ysu.szx.sys.petsys.Utils.TimeUtils;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping("/MyPost")
    public Results GetMyPost(int id){
//        int id = (int) map.get("id");
        return Results.Success(postService.GetPostById(id));
    }

    @PostMapping("/likePost")
    public Results LikePost(HttpServletRequest request, @RequestBody Map<String, Object> map){
        int post_id = (int) map.get("id");
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int person_id = (int) claims.get("id");
            return postService.LikePost(person_id, post_id);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("/colletPost")
    public Results ColletPost(HttpServletRequest request, @RequestBody Map<String, Object> map){
        int post_id = (int) map.get("id");
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int person_id = (int) claims.get("id");
            return postService.ColletPost(person_id, post_id);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @PostMapping("/uploadPost")
    public Results UploadPost(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int id = (int) claims.get("id");
            Post post = new Post();
            post.setName(map.get("name").toString());
            post.setTime(TimeUtils.getIntTime());
            post.setText(map.get("text").toString());
            post.setPicture_id((int) map.get("picture_id"));
            post.setCollect_count(0);
            post.setLike_count(0);
            if(map.containsKey("pet_id")) post.setPet_id((int) map.get("pet_id"));
           if(map.get("pic_id") != null) post.setPicArray((ArrayList<Integer>) map.get("pic_id"));
           else post.setPicArray(new ArrayList<>());
           return postService.UploadPost(id, post);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
    @GetMapping("/GetPost")
    public Results GetPost(int id){
        return postService.GetPost(id);
    }

    @PostMapping("/DeletePost")
    public Results DeletePost(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String jwt = request.getHeader("token");
        try{
            Claims claims = JwtUtils.ParseJwt(jwt);
            int person_id = (int) claims.get("id");
            int post_id = (int) map.get("id");
            return postService.DeletePost(person_id, post_id);
        }catch (Exception e){
            e.printStackTrace();
//            log.info("解析令牌失败");
            return Results.ErrorMessage("NOT_LOGIN");
        }
    }
}
