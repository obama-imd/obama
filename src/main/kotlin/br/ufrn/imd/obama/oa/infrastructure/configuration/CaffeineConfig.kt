package br.ufrn.imd.obama.oa.infrastructure.configuration

import com.github.benmanes.caffeine.cache.Caffeine
import java.util.concurrent.TimeUnit
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class CaffeineConfig {

    @Value("\${spring.cache.caffeine.spec.expireAfterAccess}")
    var expireAccessAfter: Long = 0

    @Value("\${spring.cache.caffeine.spec.maximumSizes}")
    var maximumSize: Long = 0

    @Bean
    fun cacheManager(): CacheManager {
        val cacheManager = CaffeineCacheManager()
        cacheManager.setCaffeine(caffeineCacheBuilder())

        return cacheManager
    }

    private fun caffeineCacheBuilder(): Caffeine<Any, Any> {
        return Caffeine.newBuilder()
            .initialCapacity(100)
            .maximumSize(maximumSize)
            .expireAfterAccess(expireAccessAfter, TimeUnit.SECONDS)
            .weakKeys()
            .recordStats()
    }
}
