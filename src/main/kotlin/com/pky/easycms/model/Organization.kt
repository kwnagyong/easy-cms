package com.pky.easycms.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Organization(@Id @GeneratedValue var organizationNo: Int? = null)
