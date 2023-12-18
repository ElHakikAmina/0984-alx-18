package com.example.aftas.repository;

import com.example.aftas.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member , Long> {
    List<Member> findMemberByName(String name);
    List<Member> findMemberByFamilyName(String familyName);
}
