package ysu.szx.sys.petsys.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPostData {
    Integer id;
    Integer picture_id;
    Integer person_id;
    String name;
    String person_name;
    Integer time;
}
