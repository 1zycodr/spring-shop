package kz.iitu.itse1909.amirlan.application.user.service.impl;

import kz.iitu.itse1909.amirlan.application.user.entity.Privilege;
import kz.iitu.itse1909.amirlan.application.user.entity.Role;
import kz.iitu.itse1909.amirlan.application.user.entity.AppUser;
import kz.iitu.itse1909.amirlan.application.user.repository.RoleRepository;
import kz.iitu.itse1909.amirlan.application.user.repository.UserRepository;
import kz.iitu.itse1909.amirlan.security.auth.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    private static final Logger logger = LoggerFactory.getLogger(AppUserDetailsService.class);

    public AppUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void logPostConstruct() {
        String log = AppUserDetailsService.class.getSimpleName() + " constructed!";
        logger.info(log);
    }

    @PreDestroy
    public void logPreDestroy() {
        String log = AppUserDetailsService.class.getSimpleName() + " destroying!";
        logger.info(log);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        AppUser user = userRepository.findUserByUsername(username);
        if (loginAttemptService.isBlocked(ip)) {throw new RuntimeException("blocked");}
        if (user == null) { throw new UsernameNotFoundException("User not found"); }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),true,true,true,true,getAuthorities( roleRepository.findAll()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    public List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) { collection.addAll(role.getPrivileges()); }
        for (Privilege item : collection) { privileges.add(item.getName()); }

        roles.forEach(role -> { privileges.add(role.getName()); });

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) { authorities.add(new SimpleGrantedAuthority(privilege)); }
        return authorities;
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){return request.getRemoteAddr();}
        return xfHeader.split(",")[0];
    }
}
