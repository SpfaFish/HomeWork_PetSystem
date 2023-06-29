package ysu.szx.sys.petsys.Utils;

import java.time.Instant;

public class TimeUtils {
    public static int getIntTime(){
        Instant instant = Instant.now();
        int currentTime = (int) (instant.getEpochSecond()); // 时间戳以秒为单位
//        System.out.println("当前系统时间的整型值：" + currentTime);
        return currentTime;
    }

}
