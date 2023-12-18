package ma.youcode.aftas.Services;

import ma.youcode.aftas.Models.Dtos.MemberDto.MemberRequestDto;
import ma.youcode.aftas.Models.Entities.Member;
import ma.youcode.aftas.Repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper){
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    public List<MemberRequestDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private MemberRequestDto convertToDto(Member member) {
        return modelMapper.map(member, MemberRequestDto.class);
    }

    public MemberRequestDto getMemberById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        return modelMapper.map(member, MemberRequestDto.class);
    }

    public MemberRequestDto createMember(MemberRequestDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        Member savedMember = memberRepository.save(member);
        return modelMapper.map(savedMember, MemberRequestDto.class);
    }

    public MemberRequestDto updateMember(MemberRequestDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        Member savedMember = memberRepository.save(member);
        return modelMapper.map(savedMember, MemberRequestDto.class);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
