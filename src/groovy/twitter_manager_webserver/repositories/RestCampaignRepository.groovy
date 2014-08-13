package twitter_manager_webserver.repositories

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import twitter_manager_webserver.dto.CampaignDTO
import twitter_manager_webserver.factories.CampaignDTOFactory
import twitter_manager_webserver.utils.RetryUtil

class RestCampaignRepository implements CampaignRepository {

    CampaignDTOFactory campaignDTOFactory

    private static final String ON = "on"
    private static final String OFF = "off"

    private static final String API_URL = "http://localhost:8080/twitter_manager_consumer/"

    @Override
    def findAll() {
        def response = null
        try {
            HTTPBuilder httpBuilder = new HTTPBuilder(API_URL)
            response = RetryUtil.retry(3, 100) {
                return httpBuilder.get(
                        path: 'campaigns',
                        contentType: ContentType.JSON)
            }
        } catch (e) {
            log.error "Hubo un error al leer de la api de campaigns", e
            throw e
        }
        return campaignDTOFactory.createListOfCampaignDTOFromJSONList(response)
    }

    @Override
    def save(CampaignDTO campaignDTO) {
        def campaignJSON = this.campaignDTOFactory.createJSONFromCampaign(campaignDTO)

        def response = null
        try {
            HTTPBuilder httpBuilder = new HTTPBuilder(API_URL)
            response = RetryUtil.retry(3, 100) {
                return httpBuilder.post(
                        path: 'campaigns',
                        requestContentType: ContentType.JSON,
                        contentType: ContentType.JSON,
                        body: campaignJSON
                )
            }
        } catch (e) {
            log.error "Hubo un error al leer de la api de tweets", ${e}
            throw e
        }
        return response.id
    }

    @Override
    def turnOn(campaignId) {
        return turn(ON, campaignId)
    }

    @Override
    def turnOff(Object campaignId) {
        return this.turn(OFF, campaignId)
    }

    def turn(String action, campaignId) {
        def result
        try {
            HTTPBuilder httpBuilder = new HTTPBuilder(API_URL)
            RetryUtil.retry(3, 100) {
                result = httpBuilder.request( Method.PUT) { req ->
                    uri.path = "campaigns/${campaignId}/turn${action}"

                    response.success = { resp, json ->
                        log.info("Success! ${resp.status}")
                        return true
                    }

                    response.failure = { resp ->
                        log.error("Request failed with status ${resp.status}")
                        return false
                    }
                }
            }
        } catch (e) {
            log.error "Hubo un error al leer de la api de tweets", ${e}
            throw e
        }
        return result
    }
}
