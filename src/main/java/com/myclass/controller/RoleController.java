package com.myclass.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

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

import com.myclass.entity.Role;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping("role")
public class RoleController {

	private List<Role> listRole;

	/**
	 * Hàm init chạy ngay sau contructor
	 * Dùng để khởi tạo đối tượng
	 */
	@PostConstruct
	public void init() {
		listRole = new ArrayList<Role>();
	}
	
	/**
	 * Phương thức lấy tất cả
	 * @return Trả về danh sách
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll() {
		return listRole;
	}
	
	/**
	 * Phương thức tìm kiếm
	 * @param value: Từ khóa tìm kiếm kiểu string
	 * @return Trả về danh sách tìm kiếm được
	 */
	@RequestMapping(value = "search", method = RequestMethod.GET)
	@ResponseBody
	public Object search(@RequestParam("value") String value ) {
		List<Role> listSearch = new ArrayList<Role>();
		for (Role item : listRole) {
			if(item.getDescription().contains(value)) {
				listSearch.add(item);
			}
		}
		if (listSearch.size()==0) {
			return new ResponseEntity<List<Role>>(listSearch,HttpStatus.NO_CONTENT);
		}
		return  new ResponseEntity<List<Role>>(listSearch, HttpStatus.OK);
	}
	
	/**
	 * Phương thức thêm mới
	 * @param role: Đối tượng có kiểu Role
	 * @return Trả về danh sách sau khi thêm mới
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@Valid @RequestBody Role role, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
		}
		role.setId(UUID.randomUUID().toString());
		listRole.add(role);
		return listRole;
	}
	
	/**
	 * Phương thức cập nhật
	 * @param id: Id của đối tượng cần cập nhật thông tin
	 * @param role: Đối tượng dữ liệu có kiểu Role
	 * @return Trả về danh sách sau khi cập nhật
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object edit(@PathVariable("id") String id, @RequestBody Role role) {
		for (Role item : listRole) {
			if(item.getId().equals(id)) {
				item.setName(role.getName());
				item.setDescription(role.getDescription());
				break;
			}
		}
		return listRole;
	}
	
	/**
	 * Phương thức xóa
	 * @param id: Id của đối tượng cần xóa
	 * @return Trả về danh sách sau khi xóa
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object delete(@PathVariable String id) {
		for (int i = 0; i < listRole.size(); i++) {
			if(listRole.get(i).getId().equals(id)) {
				listRole.remove(i);
				break;
			}
		}
		return listRole;
	}
	
}
