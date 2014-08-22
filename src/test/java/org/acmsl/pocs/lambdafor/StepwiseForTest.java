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
 * Filename: StepwiseForTest.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Tests for StepwiseFor.
 *
 * Date: 2014/08/22
 * Time: 15:24
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
 * Importing JUnit classes.
 */
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/*
 * Importing JDK classes.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Tests for {@link StepwiseFor}.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 0.0
 * Created: 2014/08/22 15:24
 */
@ThreadSafe
@RunWith(JUnit4.class)
public class StepwiseForTest
{
    /**
     * Checks StepwiseFor iterates through all items.
     */
    @Test
    public void for_iterates_through_all_items()
    {
        @NotNull final Collection<Integer> items = Arrays.asList(1, 2, 3, 4);

        @NotNull final Collection<Integer> processedItems = new ArrayList<>(4);

        new StepwiseFor<Collection<Integer>, Integer>()
            .iterate(
                items,
                (t) -> {
                     System.out.println(t);
                     Assert.assertTrue(items.contains(t));
                     Assert.assertFalse(processedItems.contains(t));
                     processedItems.add(t);
                 });
    }
}
