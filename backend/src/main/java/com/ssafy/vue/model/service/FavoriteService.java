package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.FavoriteDto;

public interface FavoriteService {
	List<FavoriteDto> getAllFavorites() throws Exception;
	List<FavoriteDto> getFavoritesByUserId(String userid) throws Exception;
	boolean addFavorite(FavoriteDto favorite) throws Exception;
	void deleteFavorite(String userid, int aptCode) throws Exception;
	int getFavoritesCount(String userid) throws Exception;
}
