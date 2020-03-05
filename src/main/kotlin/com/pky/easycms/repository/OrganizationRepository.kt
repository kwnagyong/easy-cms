package com.pky.easycms.repository

import com.pky.easycms.model.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepository : JpaRepository<Long, Organization> {

}