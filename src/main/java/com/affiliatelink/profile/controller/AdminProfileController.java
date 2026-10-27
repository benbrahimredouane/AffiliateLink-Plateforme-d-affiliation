package com.affiliatelink.profile.controller;

import com.affiliatelink.profile.service.AdmineProfileService;
import com.affiliatelink.profile.dto.ChangeStatusReq;
import com.affiliatelink.profile.dto.ChangeStatusRes;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminProfileController {

    private final AdmineProfileService admineProfileService;
    AdminProfileController(AdmineProfileService admineProfileService){

        this.admineProfileService = admineProfileService;
    }

    @GetMapping("/hiii")
    String he(){
        return "hi admin working";
    }
   @PatchMapping("/profiles/{id}/status")
    ResponseEntity<?> changeStatus(@PathVariable("id") Long id ,@Valid @RequestBody ChangeStatusReq req){

        ChangeStatusRes changeStatusRes = admineProfileService.changeStatus(id,req);

        return ResponseEntity.noContent().build();

   }



    
}
