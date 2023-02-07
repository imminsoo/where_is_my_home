package com.ssafy.vue.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MemberDto;

@Mapper
public interface MemberMapper {
	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public void register(MemberDto memberDto) throws SQLException;
	public void editInfo(MemberDto memberDto) throws SQLException;
	public void deleteMember(String userid) throws SQLException;
	
	public int idCheck(String userid) throws SQLException;
	public int recentlyDeleted(String userid) throws SQLException;
    
}