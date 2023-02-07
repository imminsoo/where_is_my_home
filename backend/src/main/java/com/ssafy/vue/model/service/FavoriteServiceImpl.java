package com.ssafy.vue.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.FavoriteDto;
import com.ssafy.vue.model.mapper.FavoriteMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	FavoriteMapper mapper;

	@Override
	public List<FavoriteDto> getAllFavorites() throws Exception {
		return mapper.getAllFavorites();
	}

	@Override
	public List<FavoriteDto> getFavoritesByUserId(String userid) throws Exception {
		return mapper.getFavoritesByUserId(userid);
	}

	@Override
	public boolean addFavorite(FavoriteDto favorite) throws Exception {
		int before = mapper.getFavoritesCount(favorite.getUserid());
		mapper.addFavorite(favorite);
		int after = mapper.getFavoritesCount(favorite.getUserid());
		return before != after;
	}

	@Override
	public void deleteFavorite(String userid, int aptCode) throws Exception {
		mapper.deleteFavorite(userid, aptCode);
	}

	@Override
	public int getFavoritesCount(String userid) throws Exception {
		return mapper.getFavoritesCount(userid);
	}
}
