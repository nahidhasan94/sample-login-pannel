package com.example.loginSystem.service;

import com.example.loginSystem.dto.ResponseDTO;
import com.example.loginSystem.dto.UserCreateDTO;
import com.example.loginSystem.enums.Status;
import com.example.loginSystem.model.User;
import com.example.loginSystem.repository.LoginRepository;
import com.example.loginSystem.utils.AES256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Transactional
public class LoginService {
    private ResponseDTO output = new ResponseDTO();

    @Autowired
    private LoginRepository loginRepository;

    public ResponseDTO register(UserCreateDTO input) {
        User createUser = loginRepository.findByUsername(input.getUsername());
        if (createUser == null)
        {
            User user = new User();
            boolean result = validate(input.getUsername());
            if (result)
            {
                user.setUsername(input.getUsername());
                user.setPassword(AES256.encrypt(input.getPassword()));
                user.setStatus(Status.V);
                loginRepository.save(user);
                return output.generateSuccessResponse(user,"Successfully Created");
            }
            else {
                return output.generateErrorResponse("This Phone No account is not valid");
            }

        }
        else {
            return output.generateErrorResponse("This mail already exist");
        }
    }

    public ResponseDTO login(UserCreateDTO input) {
        User existingUser = loginRepository.findByUsername(input.getUsername());
        if (existingUser == null)
        {
            return output.generateErrorResponse("Invalid PhoneNo Address");
        }
        else {
            if (existingUser.getPassword() == null){
                return output.generateErrorResponse("Invalid user request");
            }
            else {
                String decryptedPassword = AES256.decrypt(existingUser.getPassword());
                if (decryptedPassword != null && input.getPassword() != null) {
                    if (decryptedPassword.equals(input.getPassword())) {
                        System.out.println("Successfully login");
                        return output.generateSuccessResponse(null, "Successfully Login");
                    }
                }
                return output.generateErrorResponse("Wrong password ");
            }
        }
    }


    private boolean validate(String phoneNo) {
        Pattern VALID_PHONE_NO_REGEX = Pattern.compile("\\+?(88)?0?1[56789][0-9]{8}\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_PHONE_NO_REGEX.matcher(phoneNo);
        return matcher.find();
    }

}
