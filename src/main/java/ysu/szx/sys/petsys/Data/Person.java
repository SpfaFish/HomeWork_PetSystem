package ysu.szx.sys.petsys.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    Integer id;
    String name;
    String province;
    String city_or_county;
    String distinguish;
    String community;
    String email;
    Integer picture_id;
}
