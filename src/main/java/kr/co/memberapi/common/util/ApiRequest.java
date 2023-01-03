package kr.co.memberapi.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiRequest {

    private final int page;
    private final int pageSize;
    private final Map<String, Object> map;

    public ApiRequest(int page, int pageSize, Map<String, Object> map) {
        this.page = page;
        this.pageSize = pageSize;
        this.map = map;
    }

}
