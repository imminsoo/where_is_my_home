package com.ssafy.vue.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public MemberDto login(MemberDto memberDto) throws Exception {
        if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
            return null;
        return sqlSession.getMapper(MemberMapper.class).login(memberDto);
    }

    @Override
    public MemberDto userInfo(String userid) throws Exception {
        return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
    }

    @Override
    public void register(MemberDto memberDto) throws Exception {
        sqlSession.getMapper(MemberMapper.class).register(memberDto); 
    }

    @Override
    public MemberDto editInfo(MemberDto memberDto) throws Exception {
        sqlSession.getMapper(MemberMapper.class).editInfo(memberDto);
        return userInfo(memberDto.getUserid());
    }
    
    @Override
    public void deleteMember(String userid) throws Exception {
        sqlSession.getMapper(MemberMapper.class).deleteMember(userid);
    }

	@Override
	public int idCheck(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).idCheck(userid);
	}

	@Override
	public int recentlyDeleted(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).recentlyDeleted(userid);
	}
    
    
}