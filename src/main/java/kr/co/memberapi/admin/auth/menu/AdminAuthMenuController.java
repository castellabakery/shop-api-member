package kr.co.memberapi.admin.auth.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/auth/menu")
public class AdminAuthMenuController {

    private final AdminAuthMenuService adminAuthMenuService;

    public AdminAuthMenuController(AdminAuthMenuService adminAuthMenuService) {
        this.adminAuthMenuService = adminAuthMenuService;
    }
//
//    // 관리자 권한 메뉴 등록
//    @PostMapping
//    public ResponseEntity<ApiResponse> addAdminMenuAuthority(@RequestBody AdminMenuAuthorityDto adminMenuAuthorityDto) {
//        log.info("======addAdminMenuAuthority========");
//        adminAuthMenuService.addAdminMenuAuthority(adminMenuAuthorityDto);
//        return ResponseEntity.ok(ApiResponse.success());
//    }
//
//    // 관리자 권한 메뉴 리스트 조회
//    @GetMapping
//    public ResponseEntity<ApiResponse> getAdminMenuAuthorityList() {
//        log.info("======getAdminMenuAuthority========");
//        List<AdminMenuAuthority> adminMenuAuthorities = adminAuthMenuService.getAdminMenuAuthorityList();
//        return ResponseEntity.ok(ApiResponse.success(new ApiListResponse(adminMenuAuthorities, adminMenuAuthorities.size())));
//    }
//
//    // 관리자 권한 메뉴 단건 조회
//    @GetMapping("/{seq}")
//    public ResponseEntity<ApiResponse> getAdminMenuAuthority(@PathVariable Long seq) {
//        log.info("======getAdminMenuAuthority========");
//        return ResponseEntity.ok(ApiResponse.success(adminAuthMenuService.getAdminMenuAuthority(seq)));
//    }

}
