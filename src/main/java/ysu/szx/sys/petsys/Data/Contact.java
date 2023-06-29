package ysu.szx.sys.petsys.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    Integer from_id;
    Integer to_id;
    Integer time;
    String text;
}
