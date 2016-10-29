package bookmarks;


import bookmarks.entity.SysRight;
import bookmarks.entity.SysRole;
import bookmarks.entity.SysUser;
import bookmarks.utils.SysRightComparator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Configuration
@EnableAutoConfiguration
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获得授权后可得到用户信息   可使用SUserService进行数据库操作
        SysUser userDetails = (SysUser)authentication.getPrincipal();                       //输出登录提示信息
        System.out.println("管理员 " + userDetails.getUsrName() + " 登录");
        System.out.println("IP :" + getIpAddress(request));
        SysUser user = (SysUser) authentication.getPrincipal();
        Set<SysRight> rightSet = new HashSet();
        for (SysRole role: user.getSysRoles()) {
            if(role.getRoleFlag() == 1) {
                rightSet.addAll(role.getSysRights());
            }
        }
        List<SysRight> list = new ArrayList(rightSet);
        Collections.sort(list,new SysRightComparator());
        request.getSession().setAttribute("MY_MENU",list);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}