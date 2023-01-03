package kr.co.memberapi.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.memberapi.common.ApiRequestFailExceptionMsg;
import kr.co.memberapi.common.constants.ApiResponseCodeConstants;

import java.util.Map;

public class ObjectMapperUtil {

    public static <T> T convertDto(ApiRequest apiRequest, Class<T> responseType) {
        try {
            Map<String, Object> map = apiRequest.getMap();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(map, responseType);
        }catch (Exception e){
            throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.CONVERT_FAIL, "CONVERT_FAIL_API_REQUEST", apiRequest);
        }
    }


}
