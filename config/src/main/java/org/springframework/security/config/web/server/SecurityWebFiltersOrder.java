/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.config.web.server;


/**
 * @author Rob Winch
 * @since 5.0
 */
public enum SecurityWebFiltersOrder {
	FIRST(Integer.MIN_VALUE),
	HTTP_HEADERS_WRITER,
	SECURITY_CONTEXT_REPOSITORY,
	LOGIN_PAGE_GENERATING,
	/**
	 * Instance of AuthenticationWebFilter
	 */
	HTTP_BASIC,
	/**
	 * Instance of AuthenticationWebFilter
	 */
	FORM_LOGIN,
	AUTHENTICATION,
	LOGOUT,
	AUTHENTICATION_CONTEXT,
	EXCEPTION_TRANSLATION,
	AUTHORIZATION,
	LAST(Integer.MAX_VALUE);

	private static final int INTERVAL = 100;

	private final int order;

	private SecurityWebFiltersOrder() {
		this.order = ordinal() * INTERVAL;
	}

	private SecurityWebFiltersOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return this.order;
	}
}
