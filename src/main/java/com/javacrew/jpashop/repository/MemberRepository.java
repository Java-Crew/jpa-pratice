package com.javacrew.jpashop.repository;

import com.javacrew.jpashop.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
