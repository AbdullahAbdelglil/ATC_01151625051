package com.eventbooking.service.impl;

import com.eventbooking.domain.User;
import com.eventbooking.domain.enums.Role;
import com.eventbooking.repository.UserRepository;
import com.eventbooking.service.UserService;
import com.eventbooking.service.dto.UserDTO;
import com.eventbooking.service.mapper.UserMapper;
import com.eventbooking.web.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.debug("Request to save User : {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) throws BadRequestAlertException {
        log.debug("Request to update User : {}", userDTO);
        checkUserInputs(id, userDTO);
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public Optional<UserDTO> partialUpdate(UserDTO userDTO) {
        log.debug("Request to partially update User : {}", userDTO);

        return userRepository
                .findById(userDTO.getId())
                .map(existingUser -> {
                    userMapper.partialUpdate(existingUser, userDTO);

                    return existingUser;
                })
                .map(userRepository::save)
                .map(userMapper::toDto);
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Users");
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        log.debug("Request to get User : {}", id);
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> findOne(String email) {
        log.debug("Request to get User by email : {}", email);
        return userRepository.findByEmail(email).map(userMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete User : {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.debug("existsByEmail: {}", email);
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<UserDTO> getByRole(Role role) {
        return userRepository.getByRole(role).map(userMapper::toDto);
    }


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    private void checkUserInputs(Long id, UserDTO userDTO) throws BadRequestAlertException {
        if (userDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id",  "id-null");
        }
        if (!Objects.equals(id, userDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID",  "id-invalid");
        }

        if (!userRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", "id-notfound");
        }
    }
}
