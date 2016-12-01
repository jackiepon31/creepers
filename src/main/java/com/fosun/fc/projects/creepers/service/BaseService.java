package com.fosun.fc.projects.creepers.service;

import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.fosun.fc.modules.persistence.DynamicSpecifications;
import com.fosun.fc.modules.persistence.SearchFilter;

/**
 * 
 *<p>
 *description:  baseService
 *</p>
 * @author Maxin
 * @since 2016年5月26日
 * @see
 */
public interface BaseService {

	public default PageRequest buildPageRequest(int pageNumber, int pageSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		}
		return new PageRequest(pageNumber - 1, pageSize, sort);
	}

	public default Specification<?> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<?> spec = DynamicSpecifications.bySearchFilter(filters.values(), T.class);
		return spec;
	}

}
