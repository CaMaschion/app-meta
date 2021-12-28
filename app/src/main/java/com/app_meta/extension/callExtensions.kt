package com.app_meta.extension

import com.app_meta.network.cache.Cache
import com.app_meta.network.cache.CacheLocalDisk.exist
import com.app_meta.network.cache.CacheLocalDisk.save
import com.app_meta.network.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.file.Files.find

//fun <T> Call<T>.makeAsyncOperation(
//    tag: String,
//    cache: Cache<T>,
//    onSuccess: (T) -> Unit,
//    onError: (Throwable) -> Unit = {}
//) {
//
//    if (cache.containsKey(tag)) {
//        cache[tag]?.let(onSuccess)
//        return
//    }
//
//    enqueueWithCache(
//        onSuccess = onSuccess,
//        onError = onError,
//        storeData = { save(tag, it) }
//    )
//}

//new cache

fun <T> Call<T>.doRequest(
    tag: String,
    type: Class<T>,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit = {}
) {

    if (exist(tag)) {
        onSuccess(find(tag, type))
        return
    }

    enqueueWithCache(
        onSuccess = onSuccess,
        onError = onError,
        storeData = { save(tag, it) }
    )

}

private fun <T> Call<T>.enqueueWithCache(
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit = {},
    storeData: (T) -> Unit
) {
    enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body() as T)
                    storeData(response.body() as T)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t)
            }
        }
    )
}
