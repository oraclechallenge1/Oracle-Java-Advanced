package br.com.fiap.medsave.ProjectMedSave.insfrastructure.config;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.UserSysRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthConfig implements UserDetailsService {

    private final UserSysRepository userSysRepository;

    public AuthConfig(UserSysRepository userSysRepository) {
        this.userSysRepository = userSysRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userSysRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
