//�Ķ���Ͱ� ���� ���� �þ�� �����ϱ� ��������� ������ �ƿ� Ŭ������ ���� ��ü�� ó���ϴ� ���� �ٶ����ϴ�.
package org.zerock.domain;

public class Criteria {

	private int page;
	private int perPageNum;
	
	//������ new �ν��Ͻ�
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page) {
		//����ó��
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;

	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {

			this.perPageNum = 10;
		} else {
			this.perPageNum = perPageNum;

		}
	}

	public int getPage() {
		return page;
	}

	// method for MyBatis SQL Mapper -
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	// method for mybatis sql mapper
	public int getPerPageNum() {
		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + "," + "perPageNum=" + perPageNum + "]";
	}

}
