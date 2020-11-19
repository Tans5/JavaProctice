package com.tans.demo

interface Activity {
    fun onCreate() {}
    fun onStart() {}
    fun onResume() {}
    fun onStop() {}
    fun onDestroy() {}

    fun findViewById(id: Int): View? = when (id) {
        0 -> TextView()
        1 -> LinearLayout()
        2 -> ImageView()
        else -> null
    }
}