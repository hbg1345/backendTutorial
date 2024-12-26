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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/edit";
    }
    @PostMapping("/members/update")
    public String update(MemberForm memberForm){
        Member memberEntity = memberForm.toEntity();
        Member found = memberRepository.findById(memberEntity.getId()).orElse(null);
        log.info(found.toString());
        if (found != null)
            memberRepository.save(memberEntity);
        return "redirect:/members/" + memberEntity.getId();
    }
    @GetMapping("/members/{id}/delete")
    public String edit(@PathVariable Long id, RedirectAttributes rttr){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        if (memberEntity != null){
            memberRepository.delete(memberEntity);
            rttr.addFlashAttribute("msg", "삭제됐다!!");
        }
        return "redirect:/members";
    }
}
