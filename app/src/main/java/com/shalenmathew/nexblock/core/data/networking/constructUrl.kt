package com.shalenmathew.nexblock.core.data.networking

import com.shalenmathew.nexblock.BuildConfig


fun constructUrl(url: String): String{
   return when{
       url.contains(BuildConfig.BASE_URL) -> url
       url.contains("/") -> BuildConfig.BASE_URL + url.drop(1)
       else -> BuildConfig.BASE_URL + url
   }
}

