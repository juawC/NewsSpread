package com.juawapps.newsspread.data.api.adapters;

import java.io.IOException;

/**
 * Api error class.
 */
@SuppressWarnings({"WeakerAccess", "SameParameterValue"})
public class ApiError extends IOException {
    private static final long serialVersionUID = 2097271660789185320L;

    public ApiError(String errorMsg) {
        super(errorMsg);
    }
}