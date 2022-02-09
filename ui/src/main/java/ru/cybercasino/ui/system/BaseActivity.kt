package ru.cybercasino.ui.system

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

/**
 * The base activity.
 * Use it to get the right wrapped context.
 */
abstract class BaseActivity : ComponentActivity() {
//    override fun attachBaseContext(newBase: Context) {
////        val wrappedContext = getKoin()
////            .getAll<ContextWrapper>()
////            .sortedBy(ContextWrapper::priority)
////            .fold(newBase) { context, wrapper -> wrapper.wrap(context) }
//        super.attachBaseContext(wrappedContext)
//    }
}