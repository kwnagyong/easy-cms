package com.pky.easycms.configuration.security.filter

import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest


class FilterSkipMatcher(pathToSkip: Array<String>) : RequestMatcher {
    private val orRequestMatcher: OrRequestMatcher

    override fun matches(req: HttpServletRequest): Boolean {
        return !orRequestMatcher.matches(req)
    }

    init {
        val matchers = pathToSkip.map { p: String? -> AntPathRequestMatcher(p) }
        orRequestMatcher = OrRequestMatcher(matchers)
    }
}