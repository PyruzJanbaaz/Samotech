package com.pyruz.samotech.uaa.controller;

import com.pyruz.samotech.shared.model.domain.member.NewMember;
import com.pyruz.samotech.shared.model.domain.member.UpdateMember;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.uaa.service.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class MemberController {

    final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/v1/member")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseDTO addMember(@Valid @RequestBody NewMember newMember) {
        return memberService.addMember(newMember);
    }

    @PutMapping("/v1/member")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO editMember(@Valid @RequestBody UpdateMember updateMember) {
        return memberService.editMember(updateMember);
    }

    @GetMapping("/v1/member/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getMember(@PathVariable Integer memberId) {
        return memberService.getMember(memberId);
    }

    @GetMapping("/v1/members")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/v1/members/{page}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO getMembers(@PathVariable Integer page) {
        return memberService.getMembers(page);
    }

    @DeleteMapping("/v1/member")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO deleteMember(@RequestParam Integer memberId) {
        return memberService.deleteMember(memberId);
    }

}
