/**
 * 
 */
package com.fosun.fc.projects.creepers.service;

import java.util.List;

import com.fosun.fc.projects.creepers.dto.UserDTO;

/**
 * @author Administrator
 *
 */
public interface IUserService extends BaseService {
    public void saveUserDTO(UserDTO user);

    public void updateUserDTO(UserDTO user);

    public List<UserDTO> findDTOByNameAndSex(String userName, String sex);

    public List<UserDTO> findByName(String name);

    public List<UserDTO> findById(String userId);
}
