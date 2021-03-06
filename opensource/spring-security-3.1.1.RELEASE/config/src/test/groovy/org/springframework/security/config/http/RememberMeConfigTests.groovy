package org.springframework.security.config.http

import org.springframework.beans.factory.parsing.BeanDefinitionParsingException
import org.springframework.security.TestDataSource
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.RememberMeAuthenticationProvider
import org.springframework.security.config.BeanIds
import org.springframework.security.core.userdetails.MockUserDetailsService
import org.springframework.security.util.FieldUtils
import org.springframework.security.web.authentication.logout.LogoutFilter
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices
import static org.springframework.security.config.ConfigTestUtils.AUTH_PROVIDER_XML
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

/**
 *
 * @author Luke Taylor
 */
class RememberMeConfigTests extends AbstractHttpConfigTests {

    def rememberMeServiceWorksWithTokenRepoRef() {
        httpAutoConfig () {
            'remember-me'('token-repository-ref': 'tokenRepo')
        }
        bean('tokenRepo', InMemoryTokenRepositoryImpl.class.name)

        createAppContext(AUTH_PROVIDER_XML)

        expect:
        rememberMeServices() instanceof PersistentTokenBasedRememberMeServices
        FieldUtils.getFieldValue(rememberMeServices(), "useSecureCookie") == null
    }

    def rememberMeServiceWorksWithDataSourceRef() {
        httpAutoConfig () {
            'remember-me'('data-source-ref': 'ds')
        }
        bean('ds', TestDataSource.class.name, ['tokendb'])

        createAppContext(AUTH_PROVIDER_XML)

        expect:
        rememberMeServices() instanceof PersistentTokenBasedRememberMeServices
    }

    def rememberMeServiceWorksWithAuthenticationSuccessHandlerRef() {
        httpAutoConfig () {
            'remember-me'('authentication-success-handler-ref': 'sh')
        }
        bean('sh', SimpleUrlAuthenticationSuccessHandler.class.name, ['/target'])

        createAppContext(AUTH_PROVIDER_XML)

        expect:
        getFilter(RememberMeAuthenticationFilter.class).successHandler instanceof SimpleUrlAuthenticationSuccessHandler
    }

    def rememberMeServiceWorksWithExternalServicesImpl() {
        httpAutoConfig () {
            'remember-me'('key': "#{'our' + 'key'}", 'services-ref': 'rms')
        }
        bean('rms', TokenBasedRememberMeServices.class.name,
                ['key':'ourKey', 'tokenValiditySeconds':'5000'], ['userDetailsService':'us'])

        createAppContext(AUTH_PROVIDER_XML)

        List logoutHandlers = FieldUtils.getFieldValue(getFilter(LogoutFilter.class), "handlers");
        Map ams = appContext.getBeansOfType(ProviderManager.class);
        ProviderManager am = (ams.values() as List).find { it instanceof ProviderManager && it.providers.size() == 2}
        RememberMeAuthenticationProvider rmp = am.providers.find { it instanceof RememberMeAuthenticationProvider}

        expect:
        rmp != null
        5000 == FieldUtils.getFieldValue(rememberMeServices(), "tokenValiditySeconds")
        // SEC-909
        logoutHandlers.size() == 2
        logoutHandlers.get(1) == rememberMeServices()
        // SEC-1281
        rmp.key == "ourkey"
    }

    def rememberMeTokenValidityIsParsedCorrectly() {
        httpAutoConfig () {
            'remember-me'('key': 'ourkey', 'token-validity-seconds':'10000')
        }

        createAppContext(AUTH_PROVIDER_XML)
        expect:
        rememberMeServices().tokenValiditySeconds == 10000
    }

    def 'Remember-me token validity allows negative value for non-persistent implementation'() {
        httpAutoConfig () {
            'remember-me'('key': 'ourkey', 'token-validity-seconds':'-1')
        }

        createAppContext(AUTH_PROVIDER_XML)
        expect:
        rememberMeServices().tokenValiditySeconds == -1
    }

    def rememberMeSecureCookieAttributeIsSetCorrectly() {
        httpAutoConfig () {
            'remember-me'('key': 'ourkey', 'use-secure-cookie':'true')
        }

        createAppContext(AUTH_PROVIDER_XML)
        expect:
        FieldUtils.getFieldValue(rememberMeServices(), "useSecureCookie")
    }

    // SEC-1827
    def rememberMeSecureCookieAttributeFalse() {
        httpAutoConfig () {
            'remember-me'('key': 'ourkey', 'use-secure-cookie':'false')
        }

        createAppContext(AUTH_PROVIDER_XML)
        expect: 'useSecureCookie is false'
        FieldUtils.getFieldValue(rememberMeServices(), "useSecureCookie") == Boolean.FALSE
    }

    def 'Negative token-validity is rejected with persistent implementation'() {
        when:
        httpAutoConfig () {
            'remember-me'('key': 'ourkey', 'token-validity-seconds':'-1', 'token-repository-ref': 'tokenRepo')
        }
        bean('tokenRepo', InMemoryTokenRepositoryImpl.class.name)
        createAppContext(AUTH_PROVIDER_XML)

        then:
        BeanDefinitionParsingException e = thrown()
    }

    def 'Custom user service is supported'() {
        when:
        httpAutoConfig () {
            'remember-me'('key': 'ourkey', 'token-validity-seconds':'-1', 'user-service-ref': 'userService')
        }
        bean('userService', MockUserDetailsService.class.name)
        createAppContext(AUTH_PROVIDER_XML)

        then: "Parses OK"
        notThrown BeanDefinitionParsingException
    }

    // SEC-742
    def rememberMeWorksWithoutBasicProcessingFilter() {
        when:
        xml.http () {
            'form-login'('login-page': '/login.jsp', 'default-target-url': '/messageList.html' )
            logout('logout-success-url': '/login.jsp')
            anonymous(username: 'guest', 'granted-authority': 'guest')
            'remember-me'()
        }
        createAppContext(AUTH_PROVIDER_XML)

        then: "Parses OK"
        notThrown BeanDefinitionParsingException
    }

    def rememberMeServices() {
        getFilter(RememberMeAuthenticationFilter.class).getRememberMeServices()
    }
}
