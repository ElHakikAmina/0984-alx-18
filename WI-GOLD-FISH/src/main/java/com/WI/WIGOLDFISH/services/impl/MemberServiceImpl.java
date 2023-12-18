package com.WI.WIGOLDFISH.services.impl;

import com.WI.WIGOLDFISH.entities.member.Member;
import com.WI.WIGOLDFISH.entities.member.MemberDtoReq;
import com.WI.WIGOLDFISH.entities.member.MemberDtoRes;
import com.WI.WIGOLDFISH.exceptions.ResourceNotFound;
import com.WI.WIGOLDFISH.repositories.MemberRepository;
import com.WI.WIGOLDFISH.services.interfaces.MemberService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    public MemberDtoReq save(MemberDtoReq dtoMini) {
        Member member = modelMapper.map(dtoMini, Member.class);
        member = memberRepository.save(member);
        return modelMapper.map(member, MemberDtoReq.class);
    }

    @Override
    public MemberDtoReq update(MemberDtoReq dtoMini, Long aLong) {
        memberRepository.findById(aLong).orElseThrow(() -> new ResourceNotFound("Member not found"));
        Member member = modelMapper.map(dtoMini, Member.class);
        member.setNum(aLong);
        member = memberRepository.save(member);
        return modelMapper.map(member, MemberDtoReq.class);
    }

    @Override
    public Boolean delete(Long aLong) {
        memberRepository.findById(aLong).orElseThrow(() -> new ResourceNotFound("Member not found"));
        memberRepository.deleteById(aLong);
        if (memberRepository.findById(aLong).isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public MemberDtoRes findOne(Long aLong) {
        Member member = memberRepository.findById(aLong).orElseThrow(() -> new ResourceNotFound("Member not found"));
        return modelMapper.map(member, MemberDtoRes.class);
    }

    @Override
    public List<MemberDtoRes> findAll() {
        List<MemberDtoRes> memberDtoResList = memberRepository.findAll().stream().map(member -> modelMapper.map(member, MemberDtoRes.class)).toList();
        return memberDtoResList;
    }
}
