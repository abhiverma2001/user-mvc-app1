package org.jsp.usermvcapp.controller;
import org.jsp.usermvcapp.dao.UserDao;
import org.jsp.usermvcapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired
  private UserDao dao;
	
	
	
	@RequestMapping("/open")
	public String openView(String view) {
		return view;
	}
	
	
	  @PostMapping(value = "/register")
	public ModelAndView saveUser(@ModelAttribute User u, ModelAndView view) {
		u=dao.saveUser(u);
		view.addObject("msg", "User saved with Id:" +u.getId());
		view.setViewName("print");
		return view;
	}
	  @RequestMapping("edit")
	  public ModelAndView edit(@RequestParam int id, ModelAndView view) {
		  User u=dao.findUserById(id);
		  if(u!=null) {
			  view.addObject("u", u);
			  view.setViewName("update");
			  return view;
		  }else {
			  view.addObject("msg", "The Id is invalid");
			  view.setViewName("print");
			  return view;
		  }
	  }
	  @RequestMapping(value= "/update")
		public ModelAndView updateUser(@ModelAttribute User u, ModelAndView view) {
			 dao.updateUser(u);
			view.addObject("msg", "User Updated Successfully!");
			view.setViewName("print");
			return view;
	  }

		@RequestMapping("/verify-phone")
		public ModelAndView verifyUser(@RequestParam long phone, @RequestParam String password, ModelAndView view) {
			User u = dao.verifyUser(phone, password);
			if (u != null) {
				view.addObject("u", u);
				view.setViewName("view");
				return view;
			} else {
				view.addObject("msg", "Invalid Phone Number or Password");
				view.setViewName("print");
				return view;
			}
		}

		@RequestMapping("/verify-email")
		public ModelAndView verifyUser(@RequestParam String email, @RequestParam String password, ModelAndView view) {
			User u = dao.verifyUser(email, password);
			if (u != null) {
				view.addObject("u", u);
				view.setViewName("view");
				return view;
			} else {
				view.addObject("msg", "Invalid Email Id or Password");
				view.setViewName("print");
				return view;
			}
		}

		@RequestMapping("/verify-id")
		public ModelAndView verifyUser(@RequestParam int id, @RequestParam String password, ModelAndView view) {
			User u = dao.verifyUser(id, password);
			if (u != null) {
				view.addObject("u", u);
				view.setViewName("view");
				return view;
			} else {
				view.addObject("msg", "Invalid Email Id or Password");
				view.setViewName("print");
				return view;
			}
		}

		@RequestMapping("/delete")
		public ModelAndView deleteUser(@RequestParam int id, ModelAndView view) {
			boolean deleted = dao.deleteUser(id);
			view.setViewName("print");
			if (deleted) {
				view.addObject("msg", "User deleted Successfully!!");
				return view;
			} else {
				view.addObject("msg", "Can not delete User as the Id is invalid");
				return view;
			}
		}
	}


