package com.pyruz.samotech.uaa.controller;

import com.pyruz.samotech.shared.model.domain.member.NewMember;
import com.pyruz.samotech.shared.model.domain.member.UpdateMember;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.uaa.service.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseDTO> addMember(@Valid @RequestBody NewMember newMember) {
        return new ResponseEntity<>(memberService.addMember(newMember), HttpStatus.CREATED);
    }

    @PutMapping("/v1/member")
    public ResponseEntity<BaseDTO> editMember(@Valid @RequestBody UpdateMember updateMember) {
        return new ResponseEntity<>(memberService.editMember(updateMember), HttpStatus.OK);
    }

    @GetMapping("/v1/member/{memberId}")
    public ResponseEntity<BaseDTO> getMember(@PathVariable Integer memberId) {
        return new ResponseEntity<>(memberService.getMember(memberId), HttpStatus.OK);
    }

    @GetMapping("/v1/members")
    public ResponseEntity<BaseDTO> getAllMembers() {
        return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.OK);
    }

    @GetMapping("/v1/members/{page}")
    public ResponseEntity<BaseDTO> getMembers(@PathVariable Integer page) {
        return new ResponseEntity<>(memberService.getMembers(page), HttpStatus.OK);
    }

    @DeleteMapping("/v1/member")
    public ResponseEntity<BaseDTO> deleteMember(@RequestParam Integer memberId) {
        return new ResponseEntity<>(memberService.deleteMember(memberId), HttpStatus.OK);
    }

}
