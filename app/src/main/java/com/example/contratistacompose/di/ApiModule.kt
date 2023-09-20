package com.example.contratistacompose.di

//import com.example.contratistacompose.BuildConfig
import com.example.contratistacompose.data.repository.web.BudgetRepositoryImp
import com.example.contratistacompose.data.repository.web.CustomerRepositoryImp
import com.example.contratistacompose.data.repository.web.EventRepositoryImp
import com.example.contratistacompose.data.repository.web.ProductRepositoryImp
import com.example.contratistacompose.data.repository.web.ReservedRepositoryImp
import com.example.contratistacompose.data.repository.web.SingRepositoryImp
import com.example.contratistacompose.data.source.web.retrofit.Service
import com.example.contratistacompose.domain.BudgetRepository
import com.example.contratistacompose.domain.CustomerRepository
import com.example.contratistacompose.domain.EventRepository
import com.example.contratistacompose.domain.ProductRepository
import com.example.contratistacompose.domain.ReservedRepository
import com.example.contratistacompose.domain.SingRepository
import com.example.contratistacompose.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideInterceptor(): OkHttpClient { //if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }
//    } else {
//        OkHttpClient.Builder().build()
//    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): Service = retrofit.create(Service::class.java)

    @Singleton
    @Provides
    fun provideCustomer(customer: CustomerRepositoryImp): CustomerRepository = customer

    @Singleton
    @Provides
    fun provideEvent(event: EventRepositoryImp): EventRepository = event

    @Singleton
    @Provides
    fun provideSing(sing: SingRepositoryImp): SingRepository = sing

    @Singleton
    @Provides
    fun provideProduct(product: ProductRepositoryImp): ProductRepository = product

    @Singleton
    @Provides
    fun provideReserved(reserved: ReservedRepositoryImp): ReservedRepository = reserved

    @Singleton
    @Provides
    fun provideBudget(budget: BudgetRepositoryImp): BudgetRepository = budget

//    factory<ProductService> { ProductService(get(), get()) }
//    factory<EventService> { EventService(get(), get()) }
//    factory<SingService> { SingService(get(), get()) }
//    factory<ReservedService> { ReservedService(get(), get()) }
//    factory<BudgetService> { BudgetService(get(), get()) }

}