package ysu.szx.sys.petsys.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    Integer id;
    String name;
    Integer time;
    String text;
    Integer like_count;
    Integer collect_count;
    Integer picture_id;
    ArrayList<Integer> picArray;
    Integer person_id;
    Integer pet_id;
}
