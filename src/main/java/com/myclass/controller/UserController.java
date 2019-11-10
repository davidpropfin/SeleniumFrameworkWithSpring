package com.myclass.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.myclass.DTO.SignUpDTO;
import com.myclass.entity.User;
import com.myclass.validator.PasswordValidator;

@Controller
@RequestMapping("user")
public class UserController {

	private List<User> listUser;
	@Autowired
	PasswordValidator passwordValidator;

	/**
	 * Hàm init chạy ngay sau contructor Dùng để khởi tạo đối tượng
	 */
	@PostConstruct
	public void init() {
		listUser = new ArrayList<User>();
	}

	/**
	 * Phương thức lấy tất cả
	 * 
	 * @return Trả về danh sách
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll() {
		return listUser;
	}

	/**
	 * Phương thức tìm kiếm
	 * 
	 * @param value: Từ khóa tìm kiếm kiểu string
	 * @return Trả về danh sách tìm kiếm được
	 */
	@RequestMapping(value = "search", method = RequestMethod.GET)
	@ResponseBody
	public Object search(@RequestParam("value") String value) {
		List<User> listSearch = new ArrayList<User>();
		for (User item : listUser) {
			if (item.getFullname().contains(value)) {
				listSearch.add(item);
			}
		}
		return listSearch;
	}

	/**
	 * Phương thức thêm mới
	 * 
	 * @param user: Đối tượng có kiểu User
	 * @return Trả về danh sách sau khi thêm mới
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	
	public Object add(@Valid @RequestBody SignUpDTO userDTO, BindingResult errors) {
		
		
		passwordValidator.validate(userDTO, errors);
		
		if (!userDTO.getPassword().equals(userDTO.getConfirm())) {
			return new ResponseEntity<String>("Mat Khau Khong Khop", HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setFullname(userDTO.getFullname());
		user.setRoleId(userDTO.getRoleId());
		listUser.add(user);

		return new ResponseEntity<SignUpDTO>(userDTO, HttpStatus.CREATED);
	}

	/**
	 * Phương thức cập nhật
	 * 
	 * @param id:   Id của đối tượng cần cập nhật thông tin
	 * @param user: Đối tượng dữ liệu có kiểu User
	 * @return Trả về danh sách sau khi cập nhật
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object edit(@PathVariable("id") String id, @RequestBody User user) {
		for (User item : listUser) {
			if (item.getId().equals(id)) {
				item.setEmail(user.getEmail());
				item.setPassword(user.getPassword());
				item.setFullname(user.getFullname());
				item.setAvatar(user.getAvatar());
				item.setPhone(user.getPhone());
				item.setAddress(user.getAddress());
				item.setFacebook(user.getFacebook());
				item.setWebsite(user.getWebsite());
				item.setRoleId(user.getRoleId());
				break;
			}
		}
		return listUser;
	}

	/**
	 * Phương thức xóa
	 * 
	 * @param id: Id của đối tượng cần xóa
	 * @return Trả về danh sách sau khi xóa
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable String id) {
		for (int i = 0; i < listUser.size(); i++) {
			if (listUser.get(i).getId().equals(id)) {
				listUser.remove(i);
				break;
			}
		}
		return listUser;
	}
}
