package com.pky.easycms.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(@Id @GeneratedValue var userNo: Int? = null,
                var name: String,
                var email: String,
                var password: String,
                var createdAt: Date?)