package damo.helper.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import damo.helper.domain.Company;
import damo.helper.domain.Member;
import damo.helper.dto.request.JoinRequest;
import damo.helper.dto.request.LoginRequest;
import damo.helper.dto.response.CompanyResponse;
import damo.helper.service.CompanyService;
import damo.helper.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final CompanyService companyService;
	
	@GetMapping("/")
	public RedirectView main() {
		return new RedirectView("/questions");
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		LoginRequest loginDto = new LoginRequest();
		model.addAttribute("loginDto", loginDto);
		return "/auth/loginForm";
	}
	
	@GetMapping("/join")
	public String joinForm(Model model) {
		List<CompanyResponse> companys = companyService.findAllDto();
		model.addAttribute("companys",companys);
		model.addAttribute("joinDto", new JoinRequest());
		return "/auth/joinForm";
	}
	
	@PostMapping("/join")
	public String join(@Valid JoinRequest joinDto, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			List<CompanyResponse> companys = companyService.findAllDto();
			model.addAttribute("companys",companys);
			return "/auth/joinForm";
		}
		Company company = companyService.findOne(joinDto.getCompanyId());
		memberService.save(joinDto, company);
		return "redirect:/questions";
	}
}
