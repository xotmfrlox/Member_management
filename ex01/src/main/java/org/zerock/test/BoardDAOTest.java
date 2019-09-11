package org.zerock.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.persistence.BoardDAO;
import org.zerock.persistence.BoardDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })

public class BoardDAOTest {

	@Inject
	private BoardDAO dao;

	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

//	@Test
	public void testCreate() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("�߰��ȰŴ�");
		board.setContent("�߰��ȰŴ�");
		board.setWriter("user012");

		dao.create(board);
	}

	@Test
	public void testRead() throws Exception {
		System.out.println(dao.read(3651));
	}

//	@Test
	public void testUpdate() throws Exception {

		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("������ ���Դϴ�.");
		board.setContent("���� �׽�Ʈ");
		dao.update(board);

	}

//	@Test
	public void testDelete() throws Exception {
		dao.delete(1);

	}

	@Test
	public void testListPage() throws Exception {
		int page = 3;

		List<BoardVO> list = dao.listPage(page);

		for (BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}

	@Test
	public void testListCriteria() throws Exception {

		Criteria cri = new Criteria();
		cri.setPage(2);

		cri.setPerPageNum(20);

		List<BoardVO> list = dao.listCriteria(cri);

		for (BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}

	}

	@Test
	public void testURI() throws Exception {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/read")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build();

		logger.info("/read?bno=12&perPageNum20");
		logger.info(uriComponents.toString());
	}

	@Test
	public void testURI2() throws Exception {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build().expand("board", "read")
				.encode();

		logger.info("/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}

}
