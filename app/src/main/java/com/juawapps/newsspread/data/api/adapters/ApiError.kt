package com.juawapps.newsspread.data.api.adapters

import java.io.IOException

/**
 * Api error class.
 */
private const val serialVersionUID = 2097271660789185320L
class ApiError(errorMsg: String) : IOException(errorMsg)