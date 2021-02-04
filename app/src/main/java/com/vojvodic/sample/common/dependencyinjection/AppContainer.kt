package com.vojvodic.sample.common.dependencyinjection

import com.vojvodic.sample.networking.usecases.FetchUserUseCase
import com.vojvodic.sample.networking.usecases.FetchUserWithProductsUseCase

class AppContainer {

    fun fetchUserUserCase(): FetchUserUseCase {
        return FetchUserUseCase()
    }

    fun fetchUserWithProductsUseCase(): FetchUserWithProductsUseCase {
        return FetchUserWithProductsUseCase()
    }
}