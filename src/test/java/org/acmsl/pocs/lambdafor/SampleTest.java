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
 * Filename: SampleTest.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Tests whether the Sample class has been weaved by ForReplacer
 *              aspect.
 *
 * Date: 2014/08/23
 * Time: 08:46
 *
 */
package org.acmsl.pocs.lambdafor;

/*
 * Importing JUnit classes.
 */
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collection;

/**
 * Tests whether the Sample class has been weaved by ForReplacer aspect.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 0.0
 * Created: 2014/08/23 08:46
 */
@RunWith(JUnit4.class)
public class SampleTest
{
    /**
     * Tests whether the ControlFlowDriver has been driving the loop.
     */
    @Test
    public void CLI_for_gets_woven_correctly()
    {
        @NotNull final Collection<Integer> items = new Sample().sampleCode();

        Assert.assertEquals(4, items.size());
    }
}
