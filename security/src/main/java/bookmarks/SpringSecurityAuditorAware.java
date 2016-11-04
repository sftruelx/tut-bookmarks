package bookmarks;

import bookmarks.entity.SysUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

class SpringSecurityAuditorAware implements AuditorAware<String> {

    public String getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return ((SysUser) authentication.getPrincipal()).getId().toString();
    }
}