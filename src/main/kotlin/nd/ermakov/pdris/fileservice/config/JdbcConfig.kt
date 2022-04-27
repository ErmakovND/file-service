package nd.ermakov.pdris.fileservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import javax.sql.DataSource

@Configuration
@EnableJdbcRepositories("nd.ermakov.pdris.fileservice")
@ComponentScan("nd.ermakov.pdris.fileservice")
open class JdbcConfig : AbstractJdbcConfiguration() {
    @Bean
    open fun namedParameterJdbcTemplate(dataSource: DataSource) = NamedParameterJdbcTemplate(dataSource)

    @Bean
    open fun transactionManager(dataSource: DataSource) = DataSourceTransactionManager(dataSource)

    @Bean
    open fun dataSource(): DataSource {
        return EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("sql/create.sql")
            .build()
    }
}
