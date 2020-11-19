package com.tans.demo

import java.lang.reflect.AnnotatedType

object ViewsInjector {


    fun Activity.injectViews() {
        val viewFields = this.javaClass.declaredFields
        for (field in viewFields) {
            if (field.isAnnotationPresent(FindView::class.java)) {
                val findView: FindView = field.getAnnotation(FindView::class.java)
                field.set(this, this.findViewById(findView.id))
            }
        }
    }
}