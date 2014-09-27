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
 * Filename: ControlFlowDriver.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Externally-driven control flows.
 *
 * Date: 2014/08/22
 * Time: 17:14
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

/**
 * Externally-driven control flows.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 0.0
 * Created: 2014/08/22 17:14
 */
@ThreadSafe
public class ControlFlowDriver
{

    /**
     * Whether it's been used or not.
     */
    private static boolean m__bUsed = false;

    /**
     * Creates a new instance.
     */
    public ControlFlowDriver()
    {
    }

    /**
     * Specifies it's been used.
     * @param used such information.
     */
    protected static void immutableSetUsed(final boolean used)
    {
        m__bUsed = used;
    }

    /**
     * Specifies it's been used.
     * @param used such information.
     */
    protected static void setUsed(final boolean used)
    {
        immutableSetUsed(used);
    }

    /**
     * Retrieves whether it's been used.
     * @return such information.
     */
    public static boolean isUsed()
    {
        return m__bUsed;
    }

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
        setUsed(true);

        @NotNull final List<R> result = new ArrayList<R>(items.size());

        @NotNull final List<I> list = new ArrayList<I>(items);

        int position = -1;

        while (true)
        {
            ControlFlowCommand command = waitForCommand();

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

    /**
     * Waits for a command.
     * @return the command.
     */
    protected ControlFlowCommand waitForCommand()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (@NotNull final InterruptedException interruptedException)
        {
            // whatever
        }

        return ControlFlowCommand.NEXT;
    }

}
