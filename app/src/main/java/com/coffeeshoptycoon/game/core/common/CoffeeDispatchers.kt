package com.coffeeshoptycoon.game.core.common

import javax.inject.Qualifier

enum class CoffeeDispatchers { Default, IO, Main }

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Dispatcher(val coffeeDispatcher: CoffeeDispatchers)

/** Qualifies the process-wide [kotlinx.coroutines.CoroutineScope] used by repositories to share long-lived flows. */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
