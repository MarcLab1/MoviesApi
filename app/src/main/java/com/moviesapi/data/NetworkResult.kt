package com.moviesapi

//Jawwoong Enum inspired this
sealed class NetworkResult<T : Any>
{
    class Success<T: Any> (val data: T) : NetworkResult<T>()
    class Error<T: Any> (val code : Int, val message : String?) : NetworkResult<T>()
    class Exception<T : Any> (val e : Throwable) : NetworkResult<T> ()
}


sealed interface ApiResult<T : Any>

class Success<T: Any> (val data: T) : ApiResult<T>
class Error<T: Any> (val code : Int, val message : String?) : ApiResult<T>
class Exception<T : Any> (val e : Throwable) : ApiResult<T>

