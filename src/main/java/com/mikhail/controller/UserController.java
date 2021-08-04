package com.mikhail.controller;


import com.mikhail.errors.CatsNotFoundException;
import com.mikhail.model.User;
import com.mikhail.model.dto.UserDTO;
import com.mikhail.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

/**
 * Фейково тестовый REST controller для управления пользователями.
 * Шаблонно - эталонный контроллер. Использовать его как основу для других контроллеров.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * GET /users/:login : получение логина("login") пользователя.
     *
     * @param login логин пользователя (для поиска)
     * @return сущность ResponseEntity со статусом 200 (OK) и телом "login" пользователя,
     * или со статусом 404 (Not Found)
     */
    @GetMapping("/some/{login}")
    public ResponseEntity<Object> getUser(@PathVariable String login) {
        log.debug("REST request to get User : {}", login);

        if(!login.contains("cat")){
            throw new CatsNotFoundException();
        }

        //return new ResponseEntity<>(new Object(), HttpStatus.OK);
        return new ResponseEntity<>(new String("1111" + login), HttpStatus.OK);
    }

    /**
     * POST  /users  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param userDTO the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request)
     * if the login or email is already in use
     * @throws URISyntaxException если URI синтаксис некорректен
     * @throws com.mikhail.errors.BadRequestAlertException 400 (Bad Request) если логин или емейл уже используются
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);

        /*if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            User newUser = userService.createUser(userDTO);
            mailService.sendCreationEmail(newUser);
            return ResponseEntity.created(new URI("/users/" + newUser.getLogin()))
                    .headers(HeaderUtil.createAlert( "userManagement.created", newUser.getLogin()))
                    .body(newUser);
        }*/

        return ResponseEntity.ok().build();
    }

    /**
     * PUT /users : Обновление существующего пользователя (User).
     *
     * @param userDTO пользователь для обновления
     * @return the ResponseEntity with status 200 (OK) and with body the updated user
     * @throws com.mikhail.errors.EmailAlreadyUsedException 400 (Bad Request) if the email is already in use
     * @throws com.mikhail.errors.LoginAlreadyUsedException 400 (Bad Request) if the login is already in use
     */
    @PutMapping("/users")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        log.debug("REST request to update User : {}", userDTO);

        //Раскоментить после добавление сервиса User.
/*        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new EmailAlreadyUsedException();
        }
        existingUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new LoginAlreadyUsedException();
        }
        Optional<UserDTO> updatedUser = userService.updateUser(userDTO);

        return ResponseUtil.wrapOrNotFound(updatedUser,
                HeaderUtil.createAlert("userManagement.updated", userDTO.getLogin()));*/

        return ResponseEntity.ok().build();
    }

    /**
     * DELETE /users/:login : удаление пользователя по его логину.
     *
     * @param login логин пользователя для удаления
     * @return сущность ResponseEntity со статусом 200 (OK)
     */
    @DeleteMapping("/users/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.debug("REST request to delete User: {}", login);

        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "userManagement.deleted", login)).build();
    }

}
