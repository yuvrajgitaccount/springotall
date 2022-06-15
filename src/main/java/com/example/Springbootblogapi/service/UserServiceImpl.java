package com.example.Springbootblogapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Springbootblogapi.config.util.AppConstants;
import com.example.Springbootblogapi.entity.Role;
import com.example.Springbootblogapi.entity.User;
import com.example.Springbootblogapi.exception.ResourseNotFoundException;
import com.example.Springbootblogapi.payload.UserDTO;
import com.example.Springbootblogapi.repostiry.RoleRepository;
import com.example.Springbootblogapi.repostiry.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO craeteUser(UserDTO userDTO) {

		User user = this.DtoToUser(userDTO);

		User user2 = userRepository.save(user);

		return this.userToDto(user2);
	}

	@Override
	public List<UserDTO> getAllUser() {

		List<User> users = userRepository.findAll();

		return this.userAllUserDto(users);
	}

	@Override
	public UserDTO findByUserId(Integer id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("the id is" + id + "not presnt"));
		return this.userToDto(user);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("the id is" + id + "not presnt"));
		// TODO Auto-generated method stub

		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setAbout(userDTO.getAbout());
		user.setPassword(userDTO.getPassword());

		User user2 = userRepository.save(user);

		return this.userToDto(user2);
	}

	@Override
	public void DeleteUserById(Integer id) {
		// TODO Auto-generated method stub

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("the id is" + id + "not presnt"));

		this.userRepository.delete(user);

	}

	public User DtoToUser(UserDTO userDTO) {

		User user = modelMapper.map(userDTO, User.class);
//		User user = new User();
//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setAbout(userDTO.getAbout());
//		user.setPassword(userDTO.getPassword());

		return user;
	}

	public UserDTO userToDto(User user) {

		UserDTO userdto = modelMapper.map(user, UserDTO.class);

//		
//		UserDTO userdto = new UserDTO();
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setAbout(user.getAbout());
//		userdto.setPassword(user.getPassword());

		return userdto;
	}

	private List<UserDTO> userAllUserDto(List<User> users) {

		return users.stream().map((user) -> userToDto(user)).collect(Collectors.toList());

	}

	@Override
	public UserDTO registerUser(UserDTO userDTO) {

		User user = this.modelMapper.map(userDTO, User.class);

		user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

		// roles

		Role role = roleRepository.findById(AppConstants.ROLE_USER).get();
		user.getRoles().add(role);

		User user2 = userRepository.save(user);

		return this.modelMapper.map(user2, UserDTO.class);
	}
}
