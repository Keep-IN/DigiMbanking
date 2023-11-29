package com.core.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.core.data.response.riwayatTransaksi.RiwayatResponse
import com.core.di.ApiContractRiwayat

//class RiwayatPagingSource (
//    private val apiContractRiwayat: ApiContractRiwayat,
//    private val kredit: Boolean,
//    private val debit: Boolean,
//    private val dateStart: String,
//    private val dateEnd: String
//) : PagingSource<Int, RiwayatResponse>() {
//
//    private companion object {
//        const val INITIAL_PAGE_INDEX = 1
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RiwayatResponse> {
//         try {
//            val response = apiContractRiwayat.getRiwayat(
//                kredit,
//                debit,
//                dateStart,
//                dateEnd,
//                params.key ?: 1, // page number, default to 1 if null
//                params.loadSize
//            )
//             val responseBody = response.body()
//
//             if(response.isSuccessful && responseBody != null){
//                 return LoadResult.Page(
//                     data = listOf(responseBody),
//                     prevKey = params.key?.minus(1),
//                     nextKey = if (responseBody.transactions.isEmpty()) null else params.key?.plus(1)
//                 )
//             }else {
//                 return LoadResult.Error(Exception("Failed to get a valid response"))
//             }
//        } catch (exception: Exception) {
//            return LoadResult.Error(exception)
//        }
//    }
//
////    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RiwayatResponse> {
////        return try {
////            val position = params.key ?: INITIAL_PAGE_INDEX
////            val responseData = apiContractRiwayat.getRiwayat(position, params.loadSize)
////            LoadResult.Page(
////                data = responseData,
////                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
////                nextKey = if (responseData.isNullOrEmpty()) null else position + 1
////            )
////        } catch (exception: Exception) {
////            return LoadResult.Error(exception)
////        }
////    }
//
//    override fun getRefreshKey(state: PagingState<Int, RiwayatResponse>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//
//    }
//}