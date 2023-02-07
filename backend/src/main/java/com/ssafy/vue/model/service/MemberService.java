package com.ssafy.vue.model.service;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {

    MemberDto login(MemberDto memberDto) throws Exception;
    MemberDto userInfo(String userid) throws Exception;
    void register(MemberDto memberDto) throws Exception;
    MemberDto editInfo(MemberDto memberDto) throws Exception;
    void deleteMember(String userid) throws Exception;
    
    int idCheck(String userid) throws Exception;
    int recentlyDeleted(String userid) throws Exception;
}