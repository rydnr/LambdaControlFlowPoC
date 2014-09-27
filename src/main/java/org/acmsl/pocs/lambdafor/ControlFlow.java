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
 * Filename: ControlFlow.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Default control flow constructs.
 *
 * Date: 2014/08/22
 * Time: 17:05
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Default control flow constructs.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 0.0
 * Created: 2014/08/22 17:05
 */
@ThreadSafe
public class ControlFlow
{
    /**
     * Default for loop.
     * @param items the collection.
     * @param lambda the lambda to apply.
     * @param <C> the collection type.
     * @param <I> the item type.
     * @param <R> the return type.
     * @return the collection of results.
     */
    @NotNull
    public <C extends Collection<I>, I, R> Collection<R> forloop(@NotNull final C items, @NotNull final Function<I, R> lambda)
    {
        return functionalForLoop(items, lambda);
    }

    /**
     * Functional for loop.
     * @param items the collection.
     * @param lambda the lambda to apply.
     * @param <C> the collection type.
     * @param <I> the item type.
     * @param <R> the return type.
     * @return the collection of results.
     */
    @NotNull
    public <C extends Collection<I>, I, R> Collection<R> functionalForLoop(@NotNull final C items, @NotNull final Function<I, R> lambda)
    {
        return items.stream().map(lambda::apply).collect(Collectors.toList());
    }

    /**
     * Iterative for loop.
     * @param items the collection.
     * @param lambda the lambda to apply.
     * @return the collection of results.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public Collection iterativeForloop(@NotNull final Collection items, @NotNull final Function lambda)
    {
        @NotNull final List<Object> result = new ArrayList<>();

        for (final Object item: items)
        {
            result.add(lambda.<Object>apply(item));
        }

        return result;
    }

    /**
     * Default for loop.
     * @param driver the driver.
     * @param items the collection.
     * @param lambda the lambda to apply.
     * @param <C> the collection type.
     * @param <I> the item type.
     * @param <R> the return type.
     * @return the collection of results.
     */
    @NotNull
    public <C extends Collection<I>, I, R> Collection<R> externallyDrivenForloop(
        @NotNull final ControlFlowDriver driver, @NotNull final C items, @NotNull final Function<I, R> lambda)
    {
        @NotNull final List<R> result = new ArrayList<>(items.size());

        @NotNull final List<I> list = new ArrayList<>(items);

        int position = -1;

        while (true)
        {
            ControlFlowCommand command = driver.waitForCommand();

            switch (command)
            {
                case NEXT:
                    position++;
                    break;
                case PREVIOUS:
                    position++;
                    break;
                case RELOAD:
                    break;
                default:
                    break;
            }

            if (position < 0)
            {
                position = 0;
            }
            else if (position > list.size() - 1)
            {
                break;
            }

            result.set(position, lambda.apply(list.get(position)));
        }

        return result;
    }
}
