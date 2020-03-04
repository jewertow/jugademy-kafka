package pl.jugademy.offersearch.infrastructure;

import org.elasticsearch.client.Client
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import java.net.InetAddress

class ElasticSearchClientFactory {
    companion object {
        fun provide(config: Config): Client {
            val clusterName: String = config.getProperty("clusterName")
            val host: String = config.getProperty("host")
            val port: Int = config.getProperty("port")
            return PreBuiltTransportClient(
                    Settings.builder()
                            .put("cluster.name", clusterName)
                            .build())
                    .addTransportAddress(
                            InetSocketTransportAddress(
                                    InetAddress.getByName(host), port
                            )
                    )
        }
    }
}
