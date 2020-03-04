package pl.jugademy.offersearch.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.client.Client
import org.elasticsearch.common.xcontent.XContentType
import org.elasticsearch.index.query.QueryBuilders
import org.slf4j.LoggerFactory
import pl.jugademy.offersearch.domain.Offer
import pl.jugademy.offersearch.domain.OfferRepository

class ElasticSearchOfferRepository(
        private val client: Client,
        private val mapper: ObjectMapper
) : OfferRepository {

    override fun save(offer: Offer) {
        val offerJson = mapper.writeValueAsBytes(offer)
        val response = client
                .prepareIndex("offer-search", "offer", offer.id.toString())
                .setSource(offerJson, XContentType.JSON)
                .get()
        logger.info("Response from elastic search: $response")
    }

    override fun getByNameOrDescription(name: String, description: String): List<Offer> {
        val query = QueryBuilders.boolQuery()
                .should(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("name", name)))
                .should(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("description", description)))

        val response = client
                .prepareSearch("offer-search")
                .setTypes("offer")
                .setQuery(query)
                .get()

        logger.info("Received result: $response")

        return response.hits.hits.iterator().asSequence()
                .map { mapper.readValue(it.sourceAsString, Offer::class.java) }
                .sortedBy { it.id }
                .toList()
    }

    companion object {
        val logger = LoggerFactory.getLogger(ElasticSearchOfferRepository::class.java)!!
    }
}
