package kr.co.memberapi.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiListResponse<T> {

    private final List<T> list;

    public ApiListResponse(List<T> list) {
        this.list = list;
    }

}
