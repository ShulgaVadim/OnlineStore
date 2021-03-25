package com.vadim.store.utility;

import java.util.Map;

public interface ConditionsReader {

    Map<String, String> getSortConditions(String path);
}
