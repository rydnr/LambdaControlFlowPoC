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
 * Filename: StepwiseFor.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: A for loop which gets driven externally.
 *
 * Date: 2014/08/22
 * Time: 15:05
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * A for loop which gets driven externally.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 0.0
 * Created: 2014/08/22 15:05
 */
@ThreadSafe
public class StepwiseFor<C extends Collection<I>, I>
    implements For<C, I>
{
    /**
     * Creates a new instance.
     */
    public StepwiseFor()
    {
    }

    /**
     * Iterates over given collection.
     * @param collection the collection.
     * @param function the lambda expression.
     */
    @Override
    public void iterate(@NotNull final C collection, @NotNull final Consumer<I> function)
    {
        new ForDebugger<>(collection).process(function);
    }

    /**
     * An externally-managed for-loop.
     * @param <C> the collection.
     * @param <I> the item.
     */
    public static class ForDebugger<C extends Collection<I>, I>
    {
        /**
         * The collection.
         */
        private final C collection;

        /**
         * Creates a new instance.
         */
        public ForDebugger(@NotNull final C collection)
        {
            this.collection = collection;
        }

        /**
         * Processes the collection, applying given function.
         * @param function the function to apply.
         */
        public void process(@NotNull final Consumer<I> function)
        {
            @NotNull final List<I> list = new ArrayList<>(collection);

            int position = -1;

            while (true)
            {
                ForDebuggerCommand command = waitForCommand();

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

                function.accept(list.get(position));
            }
        }

        /**
         * Waits for a command.
         * @return the command.
         */
        protected ForDebuggerCommand waitForCommand()
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (@NotNull final InterruptedException interruptedException)
            {
                // whatever
            }

            return ForDebuggerCommand.NEXT;
        }
    }

    /**
     * Available commands.
     */
    public enum ForDebuggerCommand
    {
        NEXT,
        PREVIOUS,
        RELOAD;

        /**
         * The serial version uid.
         */
        private static final long serialVersionUID = 9150548302305646182L;
    }
}
