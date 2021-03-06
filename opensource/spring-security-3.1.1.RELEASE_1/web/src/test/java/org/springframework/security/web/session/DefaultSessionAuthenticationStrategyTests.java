package org.springframework.security.web.session;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

/**
 *
 * @author Luke Taylor
 */
public class DefaultSessionAuthenticationStrategyTests {

    @Test
    public void newSessionShouldNotBeCreatedIfNoSessionExistsAndAlwaysCreateIsFalse() throws Exception {
        SessionFixationProtectionStrategy strategy = new SessionFixationProtectionStrategy();
        HttpServletRequest request = new MockHttpServletRequest();

        strategy.onAuthentication(mock(Authentication.class), request, new MockHttpServletResponse());

        assertNull(request.getSession(false));
    }

    @Test
    public void newSessionIsCreatedIfSessionAlreadyExists() throws Exception {
        SessionFixationProtectionStrategy strategy = new SessionFixationProtectionStrategy();
        HttpServletRequest request = new MockHttpServletRequest();
        String sessionId = request.getSession().getId();

        strategy.onAuthentication(mock(Authentication.class), request, new MockHttpServletResponse());

        assertFalse(sessionId.equals(request.getSession().getId()));
    }

    // See SEC-1077
    @Test
    public void onlySavedRequestAttributeIsMigratedIfMigrateAttributesIsFalse() throws Exception {
        SessionFixationProtectionStrategy strategy = new SessionFixationProtectionStrategy();
        strategy.setMigrateSessionAttributes(false);
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        session.setAttribute("blah", "blah");
        session.setAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY", "DefaultSavedRequest");

        strategy.onAuthentication(mock(Authentication.class), request, new MockHttpServletResponse());

        assertNull(request.getSession().getAttribute("blah"));
        assertNotNull(request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY"));
    }

    @Test
    public void sessionIsCreatedIfAlwaysCreateTrue() throws Exception {
        SessionFixationProtectionStrategy strategy = new SessionFixationProtectionStrategy();
        strategy.setAlwaysCreateSession(true);
        HttpServletRequest request = new MockHttpServletRequest();
        strategy.onAuthentication(mock(Authentication.class), request, new MockHttpServletResponse());
        assertNotNull(request.getSession(false));
    }

}
