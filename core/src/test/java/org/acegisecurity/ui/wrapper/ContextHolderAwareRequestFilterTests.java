/* Copyright 2004 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.acegisecurity.ui.wrapper;

import junit.framework.TestCase;

import net.sf.acegisecurity.MockFilterConfig;
import net.sf.acegisecurity.MockHttpServletRequest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * Tests {@link ContextHolderAwareRequestFilter}.
 *
 * @author Ben Alex
 * @version $Id$
 */
public class ContextHolderAwareRequestFilterTests extends TestCase {
    //~ Constructors ===========================================================

    public ContextHolderAwareRequestFilterTests() {
        super();
    }

    public ContextHolderAwareRequestFilterTests(String arg0) {
        super(arg0);
    }

    //~ Methods ================================================================

    public final void setUp() throws Exception {
        super.setUp();
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ContextHolderAwareRequestFilterTests.class);
    }

    public void testCorrectOperation() throws Exception {
        ContextHolderAwareRequestFilter filter = new ContextHolderAwareRequestFilter();
        filter.init(new MockFilterConfig());
        filter.doFilter(new MockHttpServletRequest(null, null), null,
            new MockFilterChain(ContextHolderAwareRequestWrapper.class));

        // Now re-execute the filter, ensuring our replacement wrapper is still used
        filter.doFilter(new MockHttpServletRequest(null, null), null,
            new MockFilterChain(ContextHolderAwareRequestWrapper.class));

        filter.destroy();
    }

    //~ Inner Classes ==========================================================

    private class MockFilterChain implements FilterChain {
        private Class expectedServletRequest;

        public MockFilterChain(Class expectedServletRequest) {
            this.expectedServletRequest = expectedServletRequest;
        }

        private MockFilterChain() {
            super();
        }

        public void doFilter(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
            if (request.getClass().isAssignableFrom(expectedServletRequest)) {
                assertTrue(true);
            } else {
                fail("Expected class to be of type " + expectedServletRequest
                    + " but was: " + request.getClass());
            }
        }
    }
}
