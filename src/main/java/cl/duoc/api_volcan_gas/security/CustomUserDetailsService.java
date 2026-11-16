
package cl.duoc.api_volcan_gas.security;

import cl.duoc.api_volcan_gas.entity.Cliente;
import cl.duoc.api_volcan_gas.repository.ClienteRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public CustomUserDetailsService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No existe un cliente con el correo: " + email
                ));

        return User.builder()
                .username(cliente.getEmail())
                .password(cliente.getContrasena())
                .authorities("ROLE_CLIENTE")
                .build();
    }
}
