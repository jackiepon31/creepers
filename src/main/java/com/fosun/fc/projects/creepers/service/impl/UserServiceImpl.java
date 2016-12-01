package com.fosun.fc.projects.creepers.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fosun.fc.projects.creepers.dto.UserDTO;
import com.fosun.fc.projects.creepers.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    /*
     * @see
     * com.fosun.fc.projects.creepers.service.IUserService#saveUserDTO(com.fosun
     * .fc.projects.creepers.dto.UserDTO)
     */
    @Override
    public void saveUserDTO(UserDTO user) {
        // TODO Auto-generated method stub

    }

    /*
     * @see
     * com.fosun.fc.projects.creepers.service.IUserService#updateUserDTO(com.
     * fosun.fc.projects.creepers.dto.UserDTO)
     */
    @Override
    public void updateUserDTO(UserDTO user) {
        // TODO Auto-generated method stub

    }

    /*
     * @see
     * com.fosun.fc.projects.creepers.service.IUserService#findDTOByNameAndSex(
     * java.lang.String, java.lang.String)
     */
    @Override
    public List<UserDTO> findDTOByNameAndSex(String userName, String sex) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * @see
     * com.fosun.fc.projects.creepers.service.IUserService#findByName(java.lang.
     * String)
     */
    @Override
    public List<UserDTO> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * @see
     * com.fosun.fc.projects.creepers.service.IUserService#findById(java.lang.
     * String)
     */
    @Override
    public List<UserDTO> findById(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

}
