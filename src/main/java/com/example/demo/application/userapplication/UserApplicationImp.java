package com.example.demo.application.userapplication;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.demo.core.ApplicationBase;
import com.example.demo.core.configurationBeans.NanoIdUtils;
import com.example.demo.domain.userdomain.User;
import com.example.demo.domain.userdomain.UserProjection;
import com.example.demo.domain.userdomain.UserReadRepository;
import com.example.demo.domain.userdomain.UserWriteRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.mindrot.jbcrypt.BCrypt;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserApplicationImp extends ApplicationBase<User, UUID> implements UserApplication{

    private final UserWriteRepository userWriteRepository;
    private final UserReadRepository userReadRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserApplicationImp (final UserWriteRepository userWriteRepository, final UserReadRepository userReadRepository, 
    final ModelMapper modelMapper){
        super((id) -> userWriteRepository.findById(id));

        this.userReadRepository = userReadRepository;
        this.userWriteRepository = userWriteRepository;
        this.modelMapper = modelMapper;
    }

  /* @Override
    public Mono<UserDTOIn> add(UserDTOIn userDTOIn) {
        User user = modelMapper.map(userDTOIn, User.class);
        user.setId(UUID.randomUUID());
        user.setPassword(BCrypt.hashpw(userDTOIn.getPassword(), BCrypt.gensalt()));
        user.setThisNew(true);
        return this.userWriteRepository.add(user).flatMap(entity -> Mono.just(this.modelMapper.map(entity, UserDTOIn.class)));
    }
*/

@Override
public Mono<UserDtoOut> add(UserDTOIn userDTOIn) {
    User user = modelMapper.map(userDTOIn, User.class);
    user.setId(UUID.randomUUID());
    user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
    user.setThisNew(true);
    //TODO validar email
    UserDtoOut userDto = this.modelMapper.map(user, UserDtoOut.class);
    userDto.setType("Bearer");
    userDto.setToken(getJWTToken(user.getId()));
    userDto.setRefreshToken(NanoIdUtils.randomNanoId());
    //userDto.setDecodedToken(JWTUtils.decodeJWT((userDto.getToken())));
    return userWriteRepository.add(user).then(Mono.just(userDto));
        //.<String, User>validate(userRepository::findByEmail, user.getEmail())
}
    

    @Override
    public Mono<UserDTOIn> get(UUID id) {
        return this.findById(id).flatMap( dbuser -> Mono.just(this.modelMapper.map(dbuser, UserDTOIn.class)));
    }

    @Override
    public Mono<UserDTOIn> update(UUID id, UpdateDTO updateDTO) {
        return this.findById(id).flatMap( dbUser -> {

            User userUpdated = this.modelMapper.map(updateDTO, User.class);
            userUpdated.setId(dbUser.getId());
            userUpdated.setEmail(dbUser.getEmail());
            userUpdated.setRol(dbUser.getRol());

            if(BCrypt.checkpw(updateDTO.getNewPassword(), dbUser.getPassword()) && BCrypt.checkpw(userUpdated.getPassword(), dbUser.getPassword())) {
                userUpdated.setPassword(dbUser.getPassword());
            } else {
                userUpdated.setPassword(BCrypt.hashpw(updateDTO.getNewPassword(), BCrypt.gensalt()));
            }
            userUpdated.validate();

            return this.userWriteRepository.update(dbUser).flatMap(user -> 
            Mono.just(this.modelMapper.map(user, UserDTOIn.class)));     
        });
    } 


    @Override
    public Flux<UserProjection> getAll(String firstname, int page, int size) {
        return this.userReadRepository.getAll(firstname, page, size);
    }

    private String getJWTToken(UUID id) {
		String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList("USER");
		String token = Jwts
				.builder()
				.setId(id.toString())
                .claim("authorities",
                    grantedAuthorities.stream()
                    .map(GrantedAuthority::getAuthority)
                     .collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return token;
	}


}


