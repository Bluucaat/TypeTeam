package hu.unideb.typeteam.controller;

import hu.unideb.typeteam.dto.MemberDto;
import hu.unideb.typeteam.entity.Member;
import hu.unideb.typeteam.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    private final MemberService memberService;

    public AuthenticationController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login-page";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        MemberDto memberDto = new MemberDto();
        model.addAttribute("member", memberDto);
        return "register-page";
    }

    @PostMapping("/register/save")
    public String registerMember(@Valid @ModelAttribute("member") MemberDto memberDto,
                                 BindingResult bindingResult, Model model) {
        Member existingMember = memberService.findUserByUserId(memberDto.getUserId());
        if (existingMember != null) {
            if (existingMember.getEmail() != null && !existingMember.getEmail().isEmpty()) {
                bindingResult.rejectValue("email", null,
                        "There is already an account with that email");
            }
            if (existingMember.getUserId() != null && !existingMember.getUserId().isEmpty()) {
                bindingResult.rejectValue("userId", null,
                        "There is already an account with that username");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("member", memberDto);
            return "register-page";
        }

        memberService.saveMember(memberDto);
        return "redirect:/register?success";
    }
}
