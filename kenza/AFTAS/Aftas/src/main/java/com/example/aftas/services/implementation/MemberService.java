package com.example.aftas.services.implementation;

import com.example.aftas.dto.MemberReq;
import com.example.aftas.dto.MemberResp;
import com.example.aftas.entity.Member;
import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.services.interfaces.MemberServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Optional<MemberResp> AddMember(MemberReq member) {
        Member memberToSave = modelMapper.map(member , Member.class);
        LocalDate currentDate = LocalDate.now();
        memberToSave.setAccessionDate(currentDate);
        Member memberSaved = memberRepository.save(memberToSave);
        return Optional.of(modelMapper.map(memberSaved,MemberResp.class));
    }

    @Override
    public Optional<MemberResp> findById(Long num) {
        Optional<Member> member = memberRepository.findById(num);
        if(member.isPresent()){
            return Optional.of(modelMapper.map(member, MemberResp.class));
        }else{
            throw new ResourceNotFoundException("Member not found with ID : " + num);
        }
    }

    @Override
    public List<MemberResp> findByName(String name) {
        List<Member> members = memberRepository.findMemberByName(name);
        if(members.isEmpty()){
            throw new ResourceNotFoundException("No member found with this name : " + name);
        }else{
            return members.stream()
                    .map(member -> modelMapper.map(member, MemberResp.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<MemberResp> findByFamilyName(String familyName) {
        List<Member> members = memberRepository.findMemberByFamilyName(familyName);
        if(members.isEmpty()){
            throw new ResourceNotFoundException("No member found with this family name : " + familyName);
        }else{
            return members.stream()
                    .map(member -> modelMapper.map(member, MemberResp.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<MemberResp> getAllMembers(int page, int size) {
        Page<Member> membersPage = memberRepository.findAll(PageRequest.of(page, size));
        List<Member> members = membersPage.getContent();
        return members.stream()
                .map(member -> modelMapper.map(member, MemberResp.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MemberResp> updateMember(Long memberNum, MemberReq member) {
        Optional<Member> memberToUpdate = memberRepository.findById(memberNum);
        if(memberToUpdate.isPresent()){
            memberToUpdate.get().setName(member.getName());
            memberToUpdate.get().setFamilyName(member.getFamilyName());
            memberToUpdate.get().setNationality(member.getNationality());
            memberToUpdate.get().setIdentityDocument(member.getIdentityDocument());
            memberToUpdate.get().setIdentityNumber(member.getIdentityNumber());
            memberRepository.save(memberToUpdate.get());
            return Optional.ofNullable(modelMapper.map(memberToUpdate, MemberResp.class));
        }else{
            throw new ResourceNotFoundException("Member not found with ID : " + memberNum);
        }
    }

    @Override
    public Optional<MemberResp> deleteMember(Long num) {
        Optional<Member> member = memberRepository.findById(num);
        if(member.isPresent()){
            memberRepository.delete(member.get());
            return Optional.of(modelMapper.map(member, MemberResp.class));
        }else{
            throw new ResourceNotFoundException("Member not found with ID : " + num);
        }
    }
}
