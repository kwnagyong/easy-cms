package com.pky.easycms.controller

import com.pky.easycms.controller.model.auth.RegisterMemberRequest
import com.pky.easycms.controller.model.auth.RegisterMemberResponse
import com.pky.easycms.controller.model.organization.OrganizationDetailResponse
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/organization")
class OrganizationController {

    @PostMapping()
    fun addOrganization(@RequestBody request: RegisterMemberRequest): RegisterMemberResponse? {
        return null;
    }

    @DeleteMapping("/:id")
    fun deletedOrganization() {

    }

    @GetMapping()
    fun getOrganizations(): List<OrganizationDetailResponse> {
        return Arrays.asList();
    }

}