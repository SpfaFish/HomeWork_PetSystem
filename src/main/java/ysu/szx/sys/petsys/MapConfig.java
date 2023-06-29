package ysu.szx.sys.petsys;

import java.util.HashSet;
import java.util.Set;

public class MapConfig {
    private Set<String> acceptUrlSet = new HashSet<>();
    public MapConfig(){//这些地址不需要被过滤
        acceptUrlSet.add("http://10.136.132.34:9000/regist");
        acceptUrlSet.add("http://10.136.132.34:9000/login");
        acceptUrlSet.add("http://localhost/regist");
        acceptUrlSet.add("http://localhost:9000/login");
    }
    public boolean CheckUrl(String url){
        return acceptUrlSet.contains(url);
    }
}
