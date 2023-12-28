package com.example.movieskmm.domain.usecase

interface UseCase<out Result, in ExecutableParam> {

    /**
     * Perform an operation with no input parameters.
     * Will throw an exception by default, if not implemented but invoked.
     *
     * @return
     */
    suspend fun perform(): Result = throw NotImplementedError()

    /**
     * Perform an operation.
     *  Will throw an exception by default, if not implemented but invoked.
     *
     * @param params
     * @return
     */
    fun perform(params: ExecutableParam): Result? = throw NotImplementedError()
}
