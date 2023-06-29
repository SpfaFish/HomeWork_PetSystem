package ysu.szx.sys.petsys.Filter;


import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import ysu.szx.sys.petsys.MapConfig;
import ysu.szx.sys.petsys.Pojo.Results;
import ysu.szx.sys.petsys.Utils.JwtUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    private MapConfig mapConfig = new MapConfig();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        String url = req.getRequestURL().toString();
        System.out.println(url);
        if(mapConfig.CheckUrl(url)){//url在mapConfig中
            filterChain.doFilter(servletRequest, servletResponse);//放行
            return;
        }
//        String jwt = req.getHeader("token");
//        if(!StringUtils.hasLength(jwt)){
//            Results res = Results.Error("NOT_LOGIN");
//            String notLogin = JSONObject.toJSONString(res);
//            resp.getWriter().write(notLogin);
//            return;
//        }
//
//        try{
//            JwtUtils.ParseJwt(jwt);
//        }catch (Exception e){
//            e.printStackTrace();
////            log.info("解析令牌失败");
//            Results res = Results.Error("NOT_LOGIN");
//            String notLogin = JSONObject.toJSONString(res);
//            resp.getWriter().write(notLogin);
//            return;
//        }
        filterChain.doFilter(servletRequest, servletResponse);//放行
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}