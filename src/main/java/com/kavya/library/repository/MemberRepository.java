package com.kavya.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kavya.library.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
