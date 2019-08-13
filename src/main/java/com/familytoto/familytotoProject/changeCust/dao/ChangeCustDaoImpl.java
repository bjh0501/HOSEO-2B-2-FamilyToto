package com.familytoto.familytotoProject.changeCust.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Repository
public class ChangeCustDaoImpl implements ChangeCustDAO {
	@Autowired
	SqlSession sqlSession;

	@Override
	public Map<String, Object> getCustInfo(CustVO cVo) {
		return sqlSession.selectOne("changeCust.getCustInfo", cVo);
	}

	// 트랜잭션 처리해야함
	@Override
	public int updateCustInfo(CustVO cVo, RegisterCustVO rVo) {
		int n = sqlSession.update("changeCust.updateCust", cVo);
		int y = sqlSession.update("changeCust.updateFamilyCust", rVo);
		
		if(n == 1 && y == 1) {
			return 2;
		} else {
			return -1;
		}
	}

	@Override
	public int updateDropCust(CustVO rVo) {
		return sqlSession.update("changeCust.dropCust", rVo);
	}

	@Override
	public int updateDropFamilyCust(CustVO rVo) {
		return sqlSession.update("changeCust.dropFamilyCust", rVo);
	}

	@Override
	public Map<String, Object> getCheckPassword(CustVO cVo) {
		return sqlSession.selectOne("changeCust.getDropCheckPassword", cVo);
	}

}

