package com.api.AFTAS.domains.member;


import com.api.AFTAS.domains.member.DTOs.MemberReqDTO;
import com.api.AFTAS.domains.member.DTOs.MemberRespDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService implements MemberServiceInterface {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MemberRespDTO create(MemberReqDTO memberReqDTO) {
        Member member = modelMapper.map(memberReqDTO, Member.class);
            member = memberRepository.save(member);
            return modelMapper.map(member, MemberRespDTO.class);
    }

    @Override
    public MemberRespDTO update(MemberReqDTO memberReqDTO, Integer num) {
        Optional<Member> existMember = memberRepository.findById(num);
        if (existMember.isPresent()) {
            memberReqDTO.setNum(existMember.get().getNum());
            return modelMapper.map(memberRepository.save(modelMapper.map(memberReqDTO, Member.class)), MemberRespDTO.class);
        }
        return null;
    }

    @Override
    public Integer delete(Integer num) {
        Optional<Member> member = memberRepository.findById(num);
        if(member.isPresent()) {
            memberRepository.delete(member.get());
            return 1;
        }else return 0;
    }

    @Override
    public List<MemberRespDTO> getAll() {
        return memberRepository.findAll()
                .stream()
                .map(member -> modelMapper.map(member,MemberRespDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MemberRespDTO getOne(Integer num) {
        Optional<Member> member = memberRepository.findById(num);
        return member.map(value -> modelMapper.map(value, MemberRespDTO.class)).orElse(null);
    }
}
