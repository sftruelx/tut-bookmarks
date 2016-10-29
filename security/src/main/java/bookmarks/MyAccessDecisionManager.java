package bookmarks;


import bookmarks.entity.SysRight;
import bookmarks.entity.SysRole;
import bookmarks.entity.SysUser;
import bookmarks.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.List;

/**
 * Created by Larry on 2016/7/24.
 */
public class MyAccessDecisionManager implements AccessDecisionManager {
    private static final Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        if (null == collection) {
            return;
        }
        String url = ((FilterInvocation)object).getRequestUrl();

		System.out.print(url + "  ..................");
        if(url.startsWith("/login")||"/".equals(url)||"/logout".equals(url)){
            return;
        }
        if(authentication.getPrincipal() instanceof SysUser) {
            SysUser user = (SysUser) authentication.getPrincipal();
            List<SysRole> roleSet = user.getSysRoles();
            for (SysRole role : roleSet) {
                for (SysRight right : role.getSysRights()) {
//					System.out.println("url is user*=" + url + "  " + right.getRightUrl());
                    String patten = right.getRightUrl() + "*";
                    if (StringUtil.match(patten, url)) {
                        //获得该uri所需要的角色列表
						System.out.println("pass " + url);
                        return;
                    }
                }
            }
        }
        throw new AccessDeniedException("Access Denied");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
