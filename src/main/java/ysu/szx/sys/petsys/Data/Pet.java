package ysu.szx.sys.petsys.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    Integer id;
    String name;
    String breeds;
    Integer health;
    Integer picture_id;
    String notes;
    Boolean is_adopted;
}
