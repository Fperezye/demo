package com.example.demo.application.userapplication;

import java.util.UUID;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.userdomain.User;
import com.example.demo.domain.userdomain.UserProjection;
import com.example.demo.domain.userdomain.UserReadRepository;
import com.example.demo.domain.userdomain.UserWriteRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Mono<UserDTOOut> add(UserDTOIn userDTOIn) {
        User user = modelMapper.map(userDTOIn, User.class);
        user.setId(UUID.randomUUID());
        user.setThisNew(true);
        return this.userWriteRepository.add(user).flatMap(entity -> Mono.just(this.modelMapper.map(entity, UserDTOOut.class)));
    }

    @Override
    public Mono<UserDTOOut> get(UUID id) {
        return this.findById(id).flatMap( dbuser -> Mono.just(this.modelMapper.map(dbuser, UserDTOOut.class)));
    }

    @Override
    public Mono<UserDTOOut> update(UUID id, UserDTOIn userDTOIn) {
        return this.findById(id).flatMap( dbUser -> {
            if(dbUser.getName().equals(userDTOIn.getName())){
                this.modelMapper.map(userDTOIn, dbUser);
                return this.userWriteRepository.update(dbUser).flatMap(user -> Mono.just(this.modelMapper.map(user, UserDTOOut.class)));
            } else{
                this.modelMapper.map(userDTOIn, dbUser);
                return this.userWriteRepository.update(dbUser).flatMap(user -> Mono.just(this.modelMapper.map(user, UserDTOOut.class)));
            }   
        });
    } 

    @Override
    public Mono<UserDTOOut> delete(UUID id) {
        return this.findById(id).flatMap(
            user -> this.userWriteRepository.delete(user).then(Mono.just(this.modelMapper.map(user, UserDTOOut.class)))
        );
    }

    @Override
    public Flux<UserProjection> getAll(String name, int page, int size) {
        return this.userReadRepository.getAll(name, page, size);
    }
}


