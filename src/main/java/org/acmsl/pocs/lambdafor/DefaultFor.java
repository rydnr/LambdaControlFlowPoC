/*
                        LambdaControlFlowPoC

    Copyright (C) 2002-today  Jose San Leandro Armendariz
                              chous@acm-sl.org

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the License, or any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    General Public License for more details.

    You should have received a copy of the GNU General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

    Thanks to ACM S.L. for distributing this library under the GPL license.
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: DefaultFor.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: The default "for" implementation.
 *
 * Date: 2014/08/22
 * Time: 11:45
 *
 */
package org.acmsl.pocs.lambdafor;

/*
 * Importing JetBrains annotations.
 */
import org.jetbrains.annotations.NotNull;

/*
 * Importing checkthread.org annotations.
 */
import org.checkthread.annotations.ThreadSafe;

/*
 * Importing JDK classes.
 */
import java.util.Collection;
import java.util.function.Consumer;

/**
 * The default "for" implementation.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 0.0
 * Created: 2014/08/22 11:45
 */
@ThreadSafe
public class DefaultFor<C extends Collection<I>, I>
    implements For<C, I>
{
    /**
     * Iterates over given collection.
     * @param collection the collection.
     * @param function the lambda expression.
     */
    @Override
    public void iterate(@NotNull final C collection, @NotNull final Consumer<I> function)
    {
        collection.forEach(function);
    }
}
