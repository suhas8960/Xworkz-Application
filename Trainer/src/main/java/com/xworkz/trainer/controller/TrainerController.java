package com.xworkz.trainer.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Set;
import java.util.Base64.Decoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xworkz.trainer.dto.TrainerDTO;
import com.xworkz.trainer.entity.TrainerEntity;
import com.xworkz.trainer.service.TrainerService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class TrainerController {

	@Autowired(required = true)
	@Qualifier("trainerServiceImpl")
	private TrainerService trainerServiceImpl;

	@PostMapping("singUp")
	public String onSave(TrainerDTO dto, Model model) {
		log.info("created onSave TrainerController.......");
		Set<ConstraintViolation<TrainerDTO>> validateAndSave = trainerServiceImpl.validateAndSave(dto);
		log.info("validateAndSave " + validateAndSave);
		if (validateAndSave.isEmpty()) {
			model.addAttribute("TrainerDTO", dto);
			model.addAttribute("msgs",
					"Registration is successfull password send to your mail pls check your mail and signIn ");
			return "Signin";
		} else {
			model.addAttribute("msg", "Registration is Failed");
		}

		return "SignUp";

	}

	@GetMapping("singin")
	public String onSingIn(String email, String password, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			TrainerEntity userSignIn = trainerServiceImpl.userSignIn(email, password);
			log.info("userSignIn :" + userSignIn);
			log.info("userSignIn count : " + userSignIn.getCount());
			log.info("password : " + password);
			String decryptedPassword = decryptedPassword(userSignIn.getPassword());
			log.info("entity password : " + decryptedPassword);
			if (userSignIn.getCount() >= 3) {
				model.addAttribute("msgs", "Your Account is block");
				return "forgetPassword";
			}
			if (userSignIn != null) {
				if (decryptedPassword.equals(password)) {
					if (userSignIn.getOtpTime() != null) {
						if (!userSignIn.getOtpTime().isAfter(LocalTime.now())) {
							model.addAttribute("msgs", "Time out plz try again");
							return "Signin";
						}
					}

					log.info("User ID and password is matched");
					HttpSession httpSession = request.getSession(true);
					httpSession.setAttribute("udto", userSignIn);
					System.err.println(userSignIn.getName());
					System.err.println(userSignIn.getImgPath());
					if (userSignIn.getImgPath() != null) {
						httpSession.setAttribute("dtoPic", userSignIn.getImgPath());
					}
					
					return "LoginSucess";
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info("UserID OR Password is not matching");
		model.addAttribute("match", "UserID OR Password is not matching");
		return "Signin";

	}

	@PostMapping("/upload")
	public String onUpload(@RequestParam("infinity") MultipartFile multipartFile, String email,String name,String lastName, Model model)
			throws IOException {
		log.info("multipartFile :" + multipartFile);
		log.info("email :" + email);
		log.info("name:- "+name);
		if (multipartFile.isEmpty()) {
			log.info("file not uploaded");
			model.addAttribute("error", "please select file");
			return "UpdateProfile";
		}
		String imageFormat = multipartFile.getOriginalFilename().substring(
				multipartFile.getOriginalFilename().lastIndexOf('.'), multipartFile.getOriginalFilename().length());
		log.info("Image last form" + imageFormat);
		model.addAttribute("sucess", "image uploaded sucessfully");
		byte[] bytes = multipartFile.getBytes();
		Path path = Paths.get("D:\\xworkz\\"+name+ System.currentTimeMillis() + imageFormat);
		Files.write(path, bytes);
		String imageName = path.getFileName().toString();
		log.info("Image name--" + imageName);

		this.trainerServiceImpl.updateProfilePic(email,name,lastName, imageName);
		return "LoginSucess";
	}
	
	@GetMapping("/download")
	public void onDownload(HttpServletResponse response, @RequestParam String fileName, TrainerDTO dto)
			throws IOException {
		try {
			Path path = Paths.get("D:\\xworkz\\" + dto.getImgPath());
			path.toFile();
			response.setContentType("image/jpeg");
			File file = new File("D:\\xworkz\\" + fileName);
			InputStream in = new BufferedInputStream(new FileInputStream(file));
			ServletOutputStream out = response.getOutputStream();
			IOUtils.copy(in, out);
			response.flushBuffer();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
	}

	public String decryptedPassword(String encryptPassword) {
		Decoder decoder = Base64.getDecoder();
		byte[] decode = decoder.decode(encryptPassword);
		String decrypt = new String(decode);
		log.info("DecryptedPassword" + decrypt);
		return decrypt;
	}
}
