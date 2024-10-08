package com.sila.controller.auth;

import com.sila.config.CustomerUserDetailsService;
import com.sila.config.JwtProvider;
import com.sila.dto.request.LoginReq;
import com.sila.dto.request.SignUpReq;
import com.sila.dto.response.AuthResponse;
import com.sila.exception.BadRequestException;
import com.sila.exception.NotFoundException;
import com.sila.model.Profile;
import com.sila.model.User;
import com.sila.repository.UserRepository;
import com.sila.service.UserService;
import com.sila.utlis.enums.EnumRole;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;
  private final CustomerUserDetailsService customerUserDetailsService;
  private final UserService userService;
  private Authentication authenticate(String email, String password) {
    UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
    if(userDetails == null){
      throw new NotFoundException("Invalid username or email ...");
    }
    if(!passwordEncoder.matches(password,userDetails.getPassword())){
      throw new NotFoundException("Invalid password ...");
    }
    return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
  }
  @PostMapping("/sign-up")
  public ResponseEntity<User> createUserHandler(@Valid @RequestBody SignUpReq user) throws Exception {
    User isEmailExist = userRepository.findByEmail(user.getEmail());
    if(isEmailExist != null){
      throw new BadRequestException("Email is already used ");
    }
  //    Create New User
    User createUser = new User();
    Profile profile = new Profile();
    createUser.setEmail(user.getEmail());
//    createUser.setAddresses(user.getAddresses());
    createUser.setFullName(user.getFullName());
    createUser.setRole(EnumRole.valueOf(user.getRole()));
//    if(!user.getProfile().isEmpty()){
//      createUser.setProfile(user.getProfile());
//    }
    createUser.setEmail(user.getEmail());
    createUser.setPassword(passwordEncoder.encode(user.getPassword()));
  //    Save New User
    User saveUser = userRepository.save(createUser);
    profile.setUser(saveUser);
  //   Add Card to New User
//    Card card = new Card();
//    card.setCustomer(saveUser);
//    cartRepository.save(card);
//   Add Information of New User to Authentication
    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    jwtProvider.generateToken(authentication);
    return new ResponseEntity<>(createUser, HttpStatus.CREATED);
  }
  @PostMapping("/sign-in")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginReq req) throws Exception{
    String email = req.getEmail();
    String password = req.getPassword();
    //  auth passing email and password to get authorization
    Authentication authentication = authenticate(email,password);
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
    String jwt = jwtProvider.generateToken(authentication);
    User user = userService.findUserByEmail(req.getEmail());
//  Custom response data
    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(jwt);
    authResponse.setMessage("login successfully");
    authResponse.setRole(EnumRole.valueOf(role));
    authResponse.setUserId(user.getUserId());
    return new ResponseEntity<>(authResponse, HttpStatus.OK);

  }




}
