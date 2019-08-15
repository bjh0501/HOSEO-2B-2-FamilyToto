package com.familytoto.familytotoProject.login.dao.naver;

import java.util.Map;

public interface NaverDao {
	int insertNaver(Map<String, Object> map);
	Map<String, Object> checkNaver(Map<String, Object> map);
}
