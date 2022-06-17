package com.kruger.inventario.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kruger.inventario.model.Usr;

public interface IUser_S {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	void delete(long id);
	Usr save(Usr user);
}