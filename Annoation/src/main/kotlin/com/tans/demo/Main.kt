package com.tans.demo

class MyActivity : Activity {

    @FindView(0) lateinit var textView: TextView
    @FindView(1) lateinit var linearLayout: LinearLayout
    @FindView(2) lateinit var imageView: ImageView
    override fun onCreate() {
        ViewsInjector.apply { injectViews() }
        println(textView)
        println(linearLayout)
        println(imageView)
    }
}

class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val myActivity = MyActivity()
            myActivity.onCreate()
        }
    }
}