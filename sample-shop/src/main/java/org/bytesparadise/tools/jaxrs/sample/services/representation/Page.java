package org.bytesparadise.tools.jaxrs.sample.services.representation;

import java.util.List;

import javax.persistence.Query;

public class Page<T> {

	private final List<T> results;
	private final int pageSize;
	public int getPageSize() {
		return pageSize;
	}

	public int getStart() {
		return start;
	}

	private final int start;

	@SuppressWarnings("unchecked")
	public Page(Query query, int start, int pageSize) {

		this.start = start;
		this.pageSize = pageSize;
		results = query.setFirstResult(start).setMaxResults(
				pageSize + 1).getResultList();

	}

	public boolean hasNextPage() {
		return results.size() > pageSize;
	}

	public boolean hasPreviousPage() {
		return start > 0;
	}

	public List<T> getList() {
		return hasNextPage() ? results.subList(0, pageSize) : results;
	}

}
