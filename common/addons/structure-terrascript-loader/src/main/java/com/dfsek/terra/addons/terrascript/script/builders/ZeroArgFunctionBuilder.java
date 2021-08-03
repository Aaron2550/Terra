package com.dfsek.terra.addons.terrascript.script.builders;

import com.dfsek.terra.addons.terrascript.api.Function;
import com.dfsek.terra.addons.terrascript.api.FunctionBuilder;
import com.dfsek.terra.addons.terrascript.api.ImplementationArguments;
import com.dfsek.terra.addons.terrascript.api.Position;
import com.dfsek.terra.addons.terrascript.api.lang.Returnable;
import com.dfsek.terra.addons.terrascript.api.lang.Variable;
import com.dfsek.terra.addons.terrascript.script.TerraImplementationArguments;

import java.util.List;
import java.util.Map;

public class ZeroArgFunctionBuilder<T> implements FunctionBuilder<Function<T>> {
    private final java.util.function.Function<TerraImplementationArguments, T> function;
    private final Returnable.ReturnType type;

    public ZeroArgFunctionBuilder(java.util.function.Function<TerraImplementationArguments, T> function, Returnable.ReturnType type) {
        this.function = function;
        this.type = type;
    }

    @Override
    public Function<T> build(List<Returnable<?>> argumentList, Position position) {
        return new Function<T>() {
            @Override
            public ReturnType returnType() {
                return type;
            }

            @Override
            public T apply(ImplementationArguments implementationArguments, Map<String, Variable<?>> variableMap) {
                return function.apply((TerraImplementationArguments) implementationArguments);
            }

            @Override
            public Position getPosition() {
                return position;
            }
        };
    }

    @Override
    public int argNumber() {
        return 0;
    }

    @Override
    public Returnable.ReturnType getArgument(int position) {
        if(position == 0) return type;
        return null;
    }
}