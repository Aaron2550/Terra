/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.terrascript.parser.lang.functions;

import com.dfsek.terra.addons.terrascript.parser.lang.ImplementationArguments;
import com.dfsek.terra.addons.terrascript.parser.lang.Expression;
import com.dfsek.terra.addons.terrascript.parser.lang.Scope;
import com.dfsek.terra.addons.terrascript.tokenizer.SourcePosition;


public interface Function<T> extends Expression<T> {
    Function<?> NULL = new Function<>() {
        @Override
        public ReturnType returnType() {
            return null;
        }

        @Override
        public Object invoke(ImplementationArguments implementationArguments, Scope scope) {
            return null;
        }

        @Override
        public SourcePosition getPosition() {
            return null;
        }
    };
    
    @Override
    default double applyDouble(ImplementationArguments implementationArguments, Scope scope) {
        return ((Number) invoke(implementationArguments, scope)).doubleValue();
    }
    
    @Override
    default boolean applyBoolean(ImplementationArguments implementationArguments, Scope scope) {
        return (Boolean) invoke(implementationArguments, scope);
    }
}
