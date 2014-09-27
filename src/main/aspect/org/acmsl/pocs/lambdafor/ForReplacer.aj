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
 * Filename: ForReplacer.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Replaces for loops with their externally-managed versions.
 *
 * Date: 2014/08/23
 * Time: 08:27
 *
 */
package org.acmsl.pocs.lambdafor;

/*
 * Importing JDK classes.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

/**
 * Replaces for loops with their externally-managed versions.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 0.0
 * Created: 2014/08/23 08:27
 */
public aspect ForReplacer
{
    /**
     * Intercepting for loop constructs.
     */
    pointcut forLoopPointcut():
//        execution(* Sample.sampleCode(..)); // -> error
        execution(* forloop(..));
//        && args(Collect, ..);

    /**
     * Replacing the loop construct.
     */
    Object around() : forLoopPointcut()
    {
//        return proceed();
//        Collection<Integer> result = new ArrayList<>();
//        result.addAll(new ControlFlow().externallyDrivenForloop(new ControlFlowDriver(), Arrays.asList(4, 5, 6), (i) -> { System.out.println(i); return i;}));
//        return result;
        return new ControlFlow().externallyDrivenForloop(new ControlFlowDriver(), Arrays.asList(4, 5, 6), (i) -> { System.out.println(i); return i;});
    }

    /**
     * Intercepting for loop constructs.
     *
    pointcut forLoopPointcut(ControlFlow loop):
        call(* ControlFlow.forloop(..))
        && target(loop);
//        && args(items, ..);

    /**
     * Replacing the loop construct.
     *
    Collection around(ControlFlow loop) : forLoopPointcut(loop)
    {
        return loop.externallyDrivenForloop(new ControlFlowDriver(), Arrays.asList(4, 5, 6), (i) -> { System.out.println(i); return i;});
//        return new ControlFlow().externallyDrivenForloop(new ControlFlowDriver(), Arrays.asList(4, 5, 6), (i) -> { System.out.println(i); return i;});
    }
     */
}
