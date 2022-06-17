package com.kruger.inventario.service.impl;

import com.kruger.inventario.model.Rol;
import com.kruger.inventario.model.Usr;
import com.kruger.inventario.repository.User_R;
import com.kruger.inventario.service.IUser_S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService,IUser_S {

    @Autowired
    private User_R userRepository;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usr user = userRepository.findUserByUsername(username);
		
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }
	
    private List<SimpleGrantedAuthority> getAuthority(Usr user) {
    	List<SimpleGrantedAuthority> roles = new ArrayList<>();
    	for (Rol r : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority("ROLE_" + r.getRol().toUpperCase()));
		}
        return roles;
    }
    public List<Usr> findAll() {
        List<Usr> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
    	userRepository.deleteById((int) id);
    }
    
    @Override
    public Usr save(Usr user) {
        return userRepository.save(user);
    }    
}