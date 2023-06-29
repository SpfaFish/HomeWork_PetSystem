package ysu.szx.sys.petsys.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adoptapplication {
    Integer from_id;
    Integer pet_id;
    Integer time;
    Integer stat;
}
