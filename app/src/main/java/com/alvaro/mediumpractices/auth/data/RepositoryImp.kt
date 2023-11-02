package com.alvaro.mediumpractices.auth.data


import com.alvaro.mediumpractices.auth.data.datasource.api.AuthenticationService
import com.alvaro.mediumpractices.auth.data.datasource.api.FirebaseClient
import com.alvaro.mediumpractices.auth.domain.model.AuthResponse
import com.alvaro.mediumpractices.auth.domain.model.UserSignIn
import com.alvaro.mediumpractices.auth.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val authenticationService: AuthenticationService) :
    AuthRepository {
    override suspend fun login(email: String, password: String):AuthResponse?  {
        return authenticationService.login(email, password)
    }

    override suspend fun createAccount(newUser: UserSignIn): AuthResponse? {
        return authenticationService.createAccount(newUser)
    }

}

