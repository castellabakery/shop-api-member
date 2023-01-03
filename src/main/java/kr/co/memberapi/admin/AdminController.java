package kr.co.memberapi.admin;

import kr.co.memberapi.common.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 관리자 로그인용 계정 조회.
    @GetMapping("/login")
    public ResponseEntity<ApiResponse> getAdminInfoById(@RequestParam String adminId) {
        return ResponseEntity.ok(ApiResponse.success(adminService.getAdminInfoById(adminId)));
    }

    @GetMapping("/my/info")
    public ResponseEntity<ApiResponse> getAdminInfoByCode(@RequestParam String adminCode) {
        return ResponseEntity.ok(ApiResponse.success(adminService.getAdminInfoByCode(adminCode)));
    }


}
