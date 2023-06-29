package ysu.szx.sys.petsys.Service;

import com.beust.ah.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Text;
import ysu.szx.sys.petsys.Dao.ContactDao;
import ysu.szx.sys.petsys.Data.Contact;
import ysu.szx.sys.petsys.Data.ContactList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageService {
    @Autowired
    private ContactDao contactDao;
    public ArrayList<Contact> GetMessage(int a_id, int b_id){
        return contactDao.GetFromExactlyId(a_id, b_id);
    }
    public boolean InsertMessage(Contact message){
        return contactDao.Insert(message);
    }

    public Object GetMessageList(int id) {
        ArrayList<Contact> list = contactDao.GetFromId(id);
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, String> textMap = new HashMap<>();
        for(Contact a : list){
            System.out.println(a.getFrom_id());
            int from_id;
            if(a.getFrom_id() == id) from_id = a.getTo_id();
            else from_id = a.getFrom_id();
            if(!map.containsKey(from_id)){
                map.put(from_id, a.getTime());
                textMap.put(from_id, a.getText());
            }else if(map.get(from_id) < a.getTime()){
                map.replace(from_id, a.getTime());
                textMap.replace(from_id, a.getText());
            }
        }
        ArrayList<ContactList> res = new ArrayList<>();
        for(Integer key : textMap.keySet()){
            String text = textMap.get(key);
            ContactList tmp = new ContactList(key, text);
            res.add(tmp);
        }
        System.out.println("DDD");
        System.out.println(textMap);
        return res;
    }
}
