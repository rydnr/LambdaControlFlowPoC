LambdaControlFlowPoC
====================

Lambda-based control flow PoC for Java 8.

The idea is to find out if there's a way to replace code such as:

for (I item: items) {
    ...
}

with

debuggerFor(items, i -> {
    ...
});

somehow. The purpose is simple: be able to use AOP to take control on for loops, the same way as with regular method invocations.
