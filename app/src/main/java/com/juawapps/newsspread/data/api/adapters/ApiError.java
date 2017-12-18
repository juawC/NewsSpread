package com.juawapps.newsspread.data.api.adapters;

import java.io.IOException;

/**
 * Created by joaocevada on 14/12/2017.
 */

public class ApiError extends IOException {
    private static final long serialVersionUID = 2097271660789185320L;

    public ApiError(String errorMsg) {
        super(errorMsg);
    }
}