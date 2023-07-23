/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.terrascript.script.builders;

import java.util.List;

import com.dfsek.terra.addons.terrascript.parser.lang.ImplementationArguments;
import com.dfsek.terra.addons.terrascript.parser.lang.Expression;
import com.dfsek.terra.addons.terrascript.parser.lang.Scope;
import com.dfsek.terra.addons.terrascript.parser.lang.functions.Function;
import com.dfsek.terra.addons.terrascript.parser.lang.functions.FunctionBuilder;
import com.dfsek.terra.addons.terrascript.tokenizer.SourcePosition;


public class UnaryNumberFunctionBuilder implements FunctionBuilder<Function<Number>> {
    
    private final java.util.function.Function<Number, Number> function;
    
    public UnaryNumberFunctionBuilder(java.util.function.Function<Number, Number> function) {
        this.function = function;
    }
    
    @Override
    public Function<Number> build(List<Expression<?>> argumentList, SourcePosition position) {
        return new Function<>() {
            @Override
            public ReturnType returnType() {
                return ReturnType.NUMBER;
            }
            
            @SuppressWarnings("unchecked")
            @Override
            public Number invoke(ImplementationArguments implementationArguments, Scope scope) {
                return function.apply(((Expression<Number>) argumentList.get(0)).invoke(implementationArguments, scope));
            }
            
            @Override
            public SourcePosition getPosition() {
                return position;
            }
        };
    }
    
    @Override
    public int argNumber() {
        return 1;
    }
    
    @Override
    public Expression.ReturnType getArgument(int position) {
        if(position == 0) return Expression.ReturnType.NUMBER;
        return null;
    }
}
