package com.kavya.library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kavya.library.entity.Member;
import com.kavya.library.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "Member deleted successfully";
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id,
            @RequestBody Member member) {
        return memberService.updateMember(id, member);
    }
}
