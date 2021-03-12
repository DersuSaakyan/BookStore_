package com.inconcept.demo.service.criteria;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCriteria {
    private Integer size;
    private Integer page;

//    public Pageable pageRequest() {
//        if (size != null && size == Integer.MAX_VALUE) {
//            return null;
//        }
//        int size = this.size == null ? 0 : this.size;
//        int page = this.page == null ? 20 : this.page;
//        return PageRequest.of(size, page);
//    }
}
