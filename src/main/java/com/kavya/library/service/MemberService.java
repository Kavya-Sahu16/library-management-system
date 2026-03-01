package com.kavya.library.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kavya.library.entity.Member;
import com.kavya.library.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
    public Member updateMember(Long id, Member updatedMember) {

    Member existingMember = memberRepository.findById(id)
            .orElse(null);

    if (existingMember != null) {
        existingMember.setName(updatedMember.getName());
        existingMember.setEmail(updatedMember.getEmail());
        existingMember.setPhone(updatedMember.getPhone());
        existingMember.setMembershipDate(updatedMember.getMembershipDate());
        existingMember.setActive(updatedMember.getActive());

        return memberRepository.save(existingMember);
    }

    return null;
}
}
