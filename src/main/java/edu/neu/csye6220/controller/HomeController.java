package edu.neu.csye6220.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.captcha.botdetect.web.servlet.Captcha;

import edu.neu.csye6220.pojo.FlyDuty;
import edu.neu.csye6220.pojo.User;
import edu.neu.csye6220.service.FlyDutyService;
import edu.neu.csye6220.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private FlyDutyService flyDutyService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start() {
		return "redirect:/searchFlights.htm";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String DispatcherUser(HttpServletRequest request, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.get(userDetails.getUsername());
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if (user != null) {
			switch ((int) user.getRole().getId()) {
			case 1:
				model.addAttribute("errorMessage", "Please activate your account to login!");
				model.addAttribute("resendLink", true);
				model.addAttribute("email", user.getEmail());
				return "error";
			case 6:
				return "redirect:/airline/airline.htm";
			case 7:
				return "redirect:/admin/admin.htm";
			default:
				break;
			}
		}
		return "redirect:/searchFlights.htm";
	}

	@RequestMapping(value = "/registerUser.htm", method = RequestMethod.GET)
	public String registerUser() {
		return "register-user";
	}

	@RequestMapping(value = "/registerUser.htm", method = RequestMethod.POST)
	public String handleCreateForm(HttpServletRequest request, Model model) {
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");
		HttpSession session = request.getSession();
		if (captcha.validate(captchaCode)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			try {
				userService.add(username, password, email);
				Random rand = new Random();
				int randomNum1 = rand.nextInt(5000000);
				int randomNum2 = rand.nextInt(5000000);
				try {
					String str = "http://localhost:8080/AirlineTicket/validateEmail.htm?email=" + email + "&key1="
							+ randomNum1 + "&key2=" + randomNum2;
					session.setAttribute("key1", randomNum1);
					session.setAttribute("key2", randomNum2);
					sendEmail(email, username + " :Click on this link to activate your account : " + str);
				} catch (Exception e) {
					System.out.println("Email cannot be sent");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			model.addAttribute("errorMessage", "Invalid Captcha!");
			return "register-user";
		}
		return "user-created";
	}

	@RequestMapping(value = "/forgetPassword.htm", method = RequestMethod.GET)
	public String forgetPassword(HttpServletRequest request) {
		return "forget-password";
	}

	@RequestMapping(value = "/forgetPassword.htm", method = RequestMethod.POST)
	public String forgetPasswordForm(HttpServletRequest request, Model model) {

		String email = request.getParameter("email");
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");
		HttpSession session = request.getSession();

		if (captcha.validate(captchaCode)) {
			if (!userService.duplicateEmail(email)) {
				model.addAttribute("errorMessage", "The user does not exist.");
				return "forget-password";
			}
			User user = userService.getByEmail(email);
			Random rand = new Random();
			int randomNum1 = rand.nextInt(5000000);
			int randomNum2 = rand.nextInt(5000000);
			try {
				String str = "http://localhost:8080/AirlineTicket/resetPassword.htm?email=" + email + "&key1="
						+ randomNum1 + "&key2=" + randomNum2;
				session.setAttribute("key1", randomNum1);
				session.setAttribute("key2", randomNum2);
				sendEmail(email, user.getUsername() + ":Click on this link to reset your password : " + str);
			} catch (Exception e) {
				System.out.println("Email cannot be sent");
			}
			return "user-created";
		} else {
			model.addAttribute("errorMessage", "Captcha not valid");
			return "forget-password";
		}
	}

	@RequestMapping(value = "/resendEmail.htm", method = RequestMethod.GET)
	public String reActive(Model model) {
		model.addAttribute("resendLink", true);
		model.addAttribute("enter", true);
		return "error";
	}

	@RequestMapping(value = "/resendEmail.htm", method = RequestMethod.POST)
	public String resendEmail(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		if (!userService.duplicateEmail(email)) {
			model.addAttribute("errorMessage", "The user does not exist.");
			model.addAttribute("resendLink", true);
			model.addAttribute("enter", true);
			return "error";
		}
		Random rand = new Random();
		int randomNum1 = rand.nextInt(5000000);
		int randomNum2 = rand.nextInt(5000000);
		try {
			String username = userService.getByEmail(email).getUsername();
			String str = "http://localhost:8080/AirlineTicket/validateEmail.htm?email=" + email + "&key1=" + randomNum1
					+ "&key2=" + randomNum2;
			session.setAttribute("key1", randomNum1);
			session.setAttribute("key2", randomNum2);
			sendEmail(email, username + " :Click on this link to activate your account : " + str);
		} catch (Exception e) {
			System.out.println("Email cannot be sent");
		}
		return "user-created";
	}

	@RequestMapping(value = "/validateEmail.htm", method = RequestMethod.GET)
	public String validateEmail(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		int key1 = Integer.parseInt(request.getParameter("key1"));
		int key2 = Integer.parseInt(request.getParameter("key2"));
		System.out.println(session.getAttribute("key1"));
		System.out.println(session.getAttribute("key2"));

		if ((Integer) (session.getAttribute("key1")) == key1 && ((Integer) session.getAttribute("key2")) == key2) {
			try {
				System.out.println("HI________");
				boolean updateStatus = userService.updateRole(email);
				if (updateStatus) {
					return "redirect:/login";
				} else {
					return "error";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			model.addAttribute("errorMessage", "Link expired , generate new link");
			model.addAttribute("resendLink", true);
			model.addAttribute("email", email);
			return "error";
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/resetPassword.htm", method = RequestMethod.GET)
	public String resetPassword(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		int key1 = Integer.parseInt(request.getParameter("key1"));
		int key2 = Integer.parseInt(request.getParameter("key2"));
		System.out.println(session.getAttribute("key1"));
		System.out.println(session.getAttribute("key2"));

		if ((Integer) (session.getAttribute("key1")) == key1 && ((Integer) session.getAttribute("key2")) == key2) {
			model.addAttribute("email", email);
			return "reset-password";
		} else {
			model.addAttribute("errorMessage", "Link expired , generate new link");
			return "forget-password";
		}
	}

	@RequestMapping(value = "/resetPassword.htm", method = RequestMethod.POST)
	public String resetPasswordForm(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean reset = userService.resetPassword(email, password);
		if (reset) {
			return "redirect:/login";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	public void sendEmail(String useremail, String message) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com", "springmvc"));
			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu");
			email.setSubject("Password Reminder");
			email.setMsg(message);
			email.addTo(useremail);
			email.send();
		} catch (EmailException e) {
			System.out.println("Email cannot be sent");
		}
	}

	@RequestMapping(value = "/searchFlights.htm", method = RequestMethod.GET)
	public String searchFlights(Model model) {
		return "search-flights";
	}

	@RequestMapping(value = "/searchFlights.htm", method = RequestMethod.POST)
	public String searchFlights(HttpServletRequest request) {
		String departureCity = request.getParameter("departureCity");
		String arrivalCity = request.getParameter("arrivalCity");
		String date = request.getParameter("date");
		List<FlyDuty> flyDuties = flyDutyService.search(departureCity, arrivalCity, date);
		HttpSession session = request.getSession();
		session.setAttribute("flyDuties", flyDuties);
		return "search-flights";
	}

	@RequestMapping(value = "/bookTickets.htm", method = RequestMethod.GET)
	public String bookTickets(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String id = (String) request.getParameter("id");
		session.setAttribute("id", id);
		if (user == null) {
			return "redirect:/login";
		} else {
			return "redirect:/user/bookTickets.htm";
		}
	}

}
