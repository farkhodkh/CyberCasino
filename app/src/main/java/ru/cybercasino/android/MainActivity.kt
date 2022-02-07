package ru.cybercasino.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.ui.Scaffold
import ru.cybercasino.android.system.navigation.AppNavGraph
import ru.cybercasino.ui.CyberCasinoTheme
import ru.cybercasino.ui.system.BaseActivity

/**
 * The starter activity
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CyberCasinoTheme {
                ProvideWindowInsets {
                    Scaffold {
                        AppNavGraph(Modifier.padding(it))
                    }
                }
            }
        }
    }
}