/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
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

package org.springframework.security.authentication.encoding;


/**
 * Interface for performing authentication operations on a password.
 *
 * @author colin sampaleanu
 */
public interface PasswordEncoder {
    //~ Methods ========================================================================================================

    /**
     * <p>Encodes the specified raw password with an implementation specific algorithm.</p>
     *  <P>This will generally be a one-way message digest such as MD5 or SHA, but may also be a plaintext
     * variant which does no encoding at all, but rather returns the same password it was fed. The latter is useful to
     * plug in when the original password must be stored as-is.</p>
     *  <p>The specified salt will potentially be used by the implementation to "salt" the initial value before
     * encoding. A salt is usually a user-specific value which is added to the password before the digest is computed.
     * This means that computation of digests for common dictionary words will be different than those in the backend
     * store, because the dictionary word digests will not reflect the addition of the salt. If a per-user salt is
     * used (rather than a system-wide salt), it also means users with the same password will have different digest
     * encoded passwords in the backend store.</p>
     *  <P>If a salt value is provided, the same salt value must be use when calling the  {@link
     * #isPasswordValid(String, String, Object)} method. Note that a specific implementation may choose to ignore the
     * salt value (via <code>null</code>), or provide its own.</p>
     *
     * @param rawPass the password to encode
     * @param salt optionally used by the implementation to "salt" the raw password before encoding. A
     *        <code>null</code> value is legal.
     *
     * @return encoded password
     */
    String encodePassword(String rawPass, Object salt);

    /**
     * <p>Validates a specified "raw" password against an encoded password.</p>
     *  <P>The encoded password should have previously been generated by {@link #encodePassword(String,
     * Object)}. This method will encode the <code>rawPass</code> (using the optional <code>salt</code>),  and then
     * compared it with the presented <code>encPass</code>.</p>
     *  <p>For a discussion of salts, please refer to {@link #encodePassword(String, Object)}.</p>
     *
     * @param encPass a pre-encoded password
     * @param rawPass a raw password to encode and compare against the pre-encoded password
     * @param salt optionally used by the implementation to "salt" the raw password before encoding. A
     *        <code>null</code> value is legal.
     *
     * @return true if the password is valid , false otherwise
     */
    boolean isPasswordValid(String encPass, String rawPass, Object salt);
}
