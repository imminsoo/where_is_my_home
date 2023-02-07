package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.FavoriteDto;

@Mapper
public interface FavoriteMapper {
	public List<FavoriteDto> getAllFavorites() throws SQLException;
	public List<FavoriteDto> getFavoritesByUserId(String userid) throws SQLException;
	public void addFavorite(FavoriteDto favorite) throws SQLException;
	public void deleteFavorite(String userid, int aptCode) throws SQLException;
	int getFavoritesCount(String userid) throws SQLException;
}