package org.github.jrdalpra.organizze.utils;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public abstract class ErrorAware {

    String error;
    Map<String, List<String>> errors;

    public boolean hasErrors() {
        var hasASingleError = error != null && !error.isEmpty();
        var hasMultipleErrors = errors != null && errors.size() > 0;
        return hasASingleError || hasMultipleErrors;
    }

}
