package com.ys.um.controller;


import com.ys.core.infra.domain.user.RoleEntity;
import com.ys.core.infra.domain.user.UserEntity;
import com.ys.core.service.UserService;
import com.ys.eportal.model.PasswordChange;
import com.ys.ui.controller.ControllerBase;
import com.ys.um.mapper.RoleMapper;
import com.ys.um.mapper.UserMapper;
import com.ys.um.model.Role;
import com.ys.um.model.User;
import com.ys.um.model.UserSearch;
import com.ys.um.validator.PasswordChangeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class UserManagementController extends ControllerBase {
    private static Logger logger = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper rollMapper;

    @Autowired
    private PasswordChangeValidator passwordChangeValidator;

    @RequestMapping(value = "/userSearch", method = RequestMethod.GET)
    public String retreiveAllUsers(Model model) {

        Iterable<UserEntity> allUsers = this.userService.findAllUsers();

        Iterable<User> users = userMapper.convert(allUsers);
        model.addAttribute("usersearch", new UserSearch());
        addAllCommonPageAttributes(model, "users", users, "User Search", "usermanagement", "userSearch", "userSearch");
        return "userSearch";
    }

    @RequestMapping(value = "/userSearch", method = RequestMethod.POST)
    public String userFilteredSearch(@ModelAttribute UserSearch search, Model model) {
        model.addAttribute("pageName", "User Search");
        model.addAttribute("usersearch", search);

        Iterable<UserEntity> allUsers = this.userService.findAllUsers();

        Iterable<User> users = userMapper.convert(allUsers);

        addAllCommonPageAttributes(model, "users", users, "User Search", "usermanagement", "userSearch", "userSearch");
        return "userSearch";
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userView(@RequestParam(value = "userId", required = true) long userId,
                           @RequestParam(value = "returnURL", required = false) String returnURL,
                           @RequestParam(value = "anchor", required = false) String anchor, Model model) {

        UserEntity ue = null;
        User user = null;
        ue = this.userService.findUserById(userId);

        if (ue != null) {
            user = this.userMapper.convert(ue);

        }
        user.setReadonly(false);
        // add in role values
        Iterable<RoleEntity> roles = this.userService.findAllRoles();
        Iterable<Role> userRoles = this.rollMapper.convert(roles);
        user.setRoleValues(userRoles);
        model.addAttribute("passwordChange",new PasswordChange(user.getUserId()));
        addCommonPageAttributes(model, "user", user, "user", "usermanagement", "userSearch", returnURL);

        return "user";
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String userSubmitForm(@ModelAttribute User user, Model model, BindingResult errors, HttpServletRequest request) {

        UserEntity ue = this.userService.findUserById(user.getUserId());

        if (ue != null) {

            ue.setFirstName(user.getFirstName());
            ue.setLastName(user.getLastName());

            long roleId = user.getRole().getRoleId();
            ue.setRole(this.userService.findRoleById(roleId));

            this.userService.save(ue);
            this.setSuccessAlertMessage(model,"user updated");
        }

        // add in role values
        Iterable<RoleEntity> roles = this.userService.findAllRoles();
        Iterable<Role> userRoles = this.rollMapper.convert(roles);
        user.setRoleValues(userRoles);
        model.addAttribute("passwordChange",new PasswordChange(user.getUserId()));
        addCommonPageAttributes(model, "user", user, "user", "usermanagement", "searchUser", "userSearch");
        return "user";

    }
    @RequestMapping(value = "/passwordUpdate", method = RequestMethod.POST)
    public String passwordChangeSubmitForm(@ModelAttribute PasswordChange passwordChange, Model model, BindingResult errors, HttpServletRequest request) {



        // validation
        passwordChangeValidator.validate(passwordChange,errors);

        UserEntity ue = null;
        User user = null;
        ue = this.userService.findUserById(passwordChange.getUserId());
        if (ue != null) {
            user = this.userMapper.convert(ue);

        }
        user.setReadonly(false);
        // add in role values
        Iterable<RoleEntity> roles = this.userService.findAllRoles();
        Iterable<Role> userRoles = this.rollMapper.convert(roles);
        user.setRoleValues(userRoles);

        // only save if no val errors
        if (!errors.hasErrors()) {

            this.userService.updatePassword(ue,passwordChange.getNewPassword());
            model.addAttribute("passwordChange", new PasswordChange(user.getUserId()));
            this.setSuccessAlertMessage(model,"password updated");
        }else{
            model.addAttribute("passwordChange", passwordChange);
        }




        addCommonPageAttributes(model, "user", user, "user", "usermanagement", "searchUser", "userSearch");
        return "user";

    }


}
