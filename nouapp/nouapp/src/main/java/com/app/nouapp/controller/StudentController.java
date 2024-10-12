package com.app.nouapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.nouapp.dto.ResponseDto;
import com.app.nouapp.model.Material;
import com.app.nouapp.model.Response;
import com.app.nouapp.model.StudentInfo;
import com.app.nouapp.service.MaterialRepository;
import com.app.nouapp.service.ResponseRepository;
import com.app.nouapp.service.StudentInfoRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentInfoRepository srepo;
	
	@Autowired
	ResponseRepository rrepo;
	
	@Autowired
	MaterialRepository mrepo;
	
	@GetMapping("/studenthome")
	public String showStudentHome(HttpSession session, HttpServletResponse response) {
		
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if (session.getAttribute("studentid")!=null) {
				return "/student/studenthome";
			}
			else {
				return "redirect:/studentlogin";
			}
		}
		
		catch(Exception e) {
			return "redirect:/studentlogin";
		}
	}


@GetMapping("studentlogout")
public String stuLogout(HttpSession session) {
	session.invalidate();
	return "redirect:/studentlogin";
}

@GetMapping("/changepassword")
public String showChangePassword(HttpSession session, HttpServletResponse response) {
	
	try {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("studentid")!=null) {
			return "/student/changepassword";
		}
		else {
			return "redirect:/studentlogin";
		}
	}
	
	catch(Exception e) {
		return "redirect:/studentlogin";
	}
}

@PostMapping("/changepassword")
public String changePassword(HttpSession session, HttpServletResponse response, HttpServletRequest request, RedirectAttributes attrib) {
	
	try {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("studentid")!=null) {
			
			String oldpassword=request.getParameter("oldpassword");
			String newpassword=request.getParameter("newpassword");
			String confirmpassword=request.getParameter("confirmpassword");
			if(!newpassword.equals(confirmpassword)) {
				attrib.addFlashAttribute("msg", "Confirm Password are not matched");
				return "redirect:/student/changepassword";
			}
			StudentInfo s=srepo.getById(Long.parseLong(session.getAttribute("studentid").toString()));
			if(!oldpassword.equals(s.getPassword()))
			{
				attrib.addFlashAttribute("msg", "Old Password is not matched");
				return "redirect:/student/changepassword";
			}
			else {
				s.setPassword(newpassword);
				srepo.save(s);
				return "redirect:/student/studentlogout";
			}
			
		}
		else {
			return "redirect:/studentlogin";
		}
	}
	
	catch(Exception e) {
		return "redirect:/studentlogin";
	}
}

@GetMapping("/giveresponse")
public String showGiveResponse(HttpSession session, HttpServletResponse response, Model model) {
	
	try {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("studentid")!=null) {
			ResponseDto dto=new ResponseDto();
			model.addAttribute("dto", dto);
			return "/student/giveresponse";
		}
		else {
			return "redirect:/studentlogin";
		}
	}
	
	catch(Exception e) {
		return "redirect:/studentlogin";
	}
}

@PostMapping("/giveresponse")
public String giveResponse(HttpSession session, HttpServletResponse response, @ModelAttribute ResponseDto dto, RedirectAttributes attrib) {
	
	try {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("studentid")!=null) {
			StudentInfo s=srepo.getById(Long.parseLong(session.getAttribute("studentid").toString()));
			Response res=new Response();
			res.setEnrollmentno(s.getEnrollmentno());
			res.setName(s.getName());
			res.setProgram(s.getProgram());
			res.setBranch(s.getBranch());
			res.setYear(s.getYear());
			res.setResponsetype(dto.getResponsetype());
			res.setResponsetext(dto.getResponsetext());
			Date dt=new Date();
			SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
			String posteddate=df.format(dt);
			res.setPosteddate(posteddate);
			rrepo.save(res);
			attrib.addFlashAttribute("msg", "Response is Submitted");
			
			return "redirect:/student/giveresponse";
		}
		else {
			return "redirect:/studentlogin";
		}
	}
	
	catch(Exception e) {
		return "redirect:/studentlogin";
	}
}

@GetMapping("/viewstudymaterial")
public String showStudyMaterial(HttpSession session, HttpServletResponse response, Model model) {
	
	try {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("studentid")!=null) {
			StudentInfo s=srepo.findById(Long.parseLong(session.getAttribute("studentid").toString())).get();
			List<Material> smat=mrepo.getMaterial(s.getProgram(),s.getBranch(),s.getYear(),"smat");
			model.addAttribute("smat", smat);
			
			return "/student/viewstudymaterial";
		}
		else {
			return "redirect:/studentlogin";
		}
	}
	
	catch(Exception e) {
		return "redirect:/studentlogin";
	}
}

@GetMapping("/viewassignment")
public String showAssignment(HttpSession session, HttpServletResponse response,Model model) {
	
	try {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("studentid")!=null) {
			StudentInfo s=srepo.findById(Long.parseLong(session.getAttribute("studentid").toString())).get();
			List<Material> assign=mrepo.getMaterial(s.getProgram(), s.getBranch(), s.getYear(),"assign");
			model.addAttribute("assign", assign);
			return "/student/viewassignment";
		}
		else {
			return "redirect:/studentlogin";
		}
	}
	
	catch(Exception e) {
		return "redirect:/studentlogin";
	}
}
}