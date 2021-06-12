package com.idat.webservices.domain.services;

import java.util.ArrayList;

import com.idat.webservices.persistence.models.User;
import com.idat.webservices.persistence.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//en esta clase le indica a spring que sobreescriba el metodo de registro de usuarios
@Service("UserDetailsServiceOverride")
public class UserDetailsServiceOverride implements UserDetailsService {

     @Autowired
     private UserRepository repository;

     @Override
     @Transactional(readOnly = true)
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

          // si encuentra un resultado devuelve el cliente si no null
          User user = repository.findByUsername(username).map(u -> {
               return u;
          }).orElse(null);

          if (user == null) {
               throw new UsernameNotFoundException(username);
          }
          /* creo array de roles */
          ArrayList<GrantedAuthority> roles = new ArrayList<>();
          roles.add(new SimpleGrantedAuthority(user.getRole().getName()));

          // obligatoriamente los roles se tiene que llamar|
          return new org.springframework.security.core.userdetails.User(user.getUsername(), "{bcrypt}" + user.getPassword(), roles);
     }
}
