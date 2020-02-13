package com.pky.easycms.controller.model.organization

import java.util.*

data class OrganizationDetailResponse(val organizationNo: Long?,
                                      val memberNo: Long,
                                      val name: String,
                                      val members: Array<OrganizationMemberResponse>,
                                      val createdAt: Date,
                                      val updatedAt: Date)