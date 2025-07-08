package ch.noseryoung.blj.engine;

import java.util.Map;

public interface ParametrizedBehavior {
    Map<String, Class<?>> getExpectedParams();
}
