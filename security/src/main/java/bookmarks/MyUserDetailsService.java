package bookmarks;


import bookmarks.entity.SysUser;
import bookmarks.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("MyUserDetailsImpl")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser user;

        if(userName.indexOf("@")<0){
            userName = userName+ "@hwahau.com";
        }

        user = userRepository.findByUsrNameAndUsrFlag(userName, "1");
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName +
                    " not found");
        }
        return user;
    }
}