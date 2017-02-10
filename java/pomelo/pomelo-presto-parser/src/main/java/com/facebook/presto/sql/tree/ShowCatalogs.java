/*
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
package com.facebook.presto.sql.tree;

import com.google.common.base.Optional;

import static com.google.common.base.MoreObjects.toStringHelper;
import static java.util.Objects.requireNonNull;

public final class ShowCatalogs
        extends Statement
{
    private final Optional<String> likePattern;

    public ShowCatalogs(Optional<String> likePattern)
    {
        this(Optional.absent(), likePattern);
    }

    public ShowCatalogs(NodeLocation location, Optional<String> likePattern)
    {
        this(Optional.of(location), likePattern);
    }

    public ShowCatalogs(Optional<NodeLocation> location, Optional<String> likePattern)
    {
        super(location);
        this.likePattern = requireNonNull(likePattern, "likePattern is null");
    }

    public Optional<String> getLikePattern()
    {
        return likePattern;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitShowCatalogs(this, context);
    }

    @Override
    public int hashCode()
    {
        return 0;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        return (obj != null) && (getClass() == obj.getClass());
    }

    @Override
    public String toString()
    {
        return toStringHelper(this).toString();
    }
}
