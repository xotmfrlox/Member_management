package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.BoardMapper";

	@Override
	public void create(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".create", vo);

	}

	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace + ".update", vo);

	}

	@Override
	public void delete(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace + ".delete", bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		// TODO Auto-generated method stub
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList(namespace + ".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".countPaging", cri);
	}

}
