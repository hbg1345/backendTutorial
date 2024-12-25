package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/signup")
    public String newMemeberForm(){
        return "members/new";
    }

    @PostMapping("/join")
    public String joinMember(MemberForm memberForm){
        log.info(memberForm.toString());
        Member member = memberForm.toEntity();
        log.info(member.toString());
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("members")
    public String index(Model model){
        List<Member> memberEntityList = (List<Member>) memberRepository.findAll();
        model.addAttribute("memberList", memberEntityList);
        return "members/index";
    }
}
